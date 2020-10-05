package benchmarks;

import org.openjdk.jmh.annotations.*;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.IntUnaryOperator;

import static com.github.bazoocaze.vulkancpu4j.util.MyBenchmark.aNewBenchmark;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Timeout(time = 30)
@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
public class CopyMemoryBenchmark {

    private static final Unsafe UNSAFE = UnsafeWrapper.get();

    final Random random = new Random();
    final int width = 1280;
    final int height = 720;
    final int pixels = width * height;
    final byte[] surface = new byte[width * height * 4];
    final byte[] surface2 = new byte[width * height * 4];
    final int[] intSurface = new int[width * height];

    @Setup
    public void setup() {
        for (int n = 0; n < 16 * 16; n++) {
            surface[n] = (byte) random.nextInt();
        }
    }

    public static void main(String[] args) {
        new CopyMemoryBenchmark().run();
    }

    private void run() {
        setup();
        benchmarkCopy();
    }

    private void benchmarkCopy() {
        setup();
        aNewBenchmark()
                .withWorkSize(1000)
                .withAction("copy_byteArray_SystemArrayCopy", this::copy_byteArray_SystemArrayCopy)
                .withAction("copy_byte2int_UnsafeCopyMemory", this::copy_byte2Int_UnsafeCopyMemory)
                .withAction("copy_byte2int_loop", this::copy_byte2int_loop)
                .withAction("copy_byte2int_VarHandle", this::copy_byte2int_VarHandle)
                .withAction("copy_byte2int_VarHandleStatic", this::copy_byte2int_VarHandleStatic)
                .withAction("copy_byte2int_VarHandleStatic2", this::copy_byte2int_VarHandleStatic2)
                .withAction("copy_byte2int_UnsafeGetInt", this::copy_byte2int_UnsafeGetInt)
                .run();
    }

    @Benchmark
    public void copy_byteArray_SystemArrayCopy() {
        System.arraycopy(surface, 0, surface2, 0, surface.length);
    }

    @Benchmark
    public void copy_byte2Int_UnsafeCopyMemory() {
        copy_byte2Int_UnsafeCopyMemory(surface, intSurface, pixels);
    }

    @Benchmark
    public void copy_byte2int_loop() {
        copy_byte2int_loop(surface, intSurface, pixels);
    }

    @Benchmark
    public void copy_byte2int_VarHandle() {
        copy_byte2int_VarHandle(surface, intSurface, pixels);
    }

    @Benchmark
    public void copy_byte2int_VarHandleStatic() {
        copy_byte2int_VarHandleStatic(surface, intSurface, pixels);
    }

    @Benchmark
    public void copy_byte2int_VarHandleStatic2() {
        copy_byte2int_VarHandleStatic2(surface, intSurface, pixels);
    }

    @Benchmark
    public void copy_byte2int_UnsafeGetInt() {
        copy_byte2int_UnsafeGetInt(surface, intSurface, pixels);
    }

    private void copy_byte2Int_UnsafeCopyMemory(final byte[] surface, final int[] intSurface, final int pixelCount) {
        int srcOffsetByteArray = UNSAFE.arrayBaseOffset(byte[].class);
        int destOffsetIntArray = UNSAFE.arrayBaseOffset(int[].class);
        UNSAFE.copyMemory(
                surface, srcOffsetByteArray,
                intSurface, destOffsetIntArray, pixelCount * 4);
    }

    private void copy_byte2int_loop(final byte[] surface, final int[] intSurface, final int pixelCount) {
        int byteIndex = 0;
        for (int intIndex = 0; intIndex < pixelCount; intIndex++) {
            final int value = surface[byteIndex]
                    | (surface[byteIndex + 1] << 8)
                    | (surface[byteIndex + 2] << 16)
                    | (surface[byteIndex + 3] << 24);
            intSurface[intIndex] = value;
            byteIndex += 4;
        }
    }

    private void copy_byte2int_VarHandle(final byte[] surface, final int[] intSurface, final int pixelCount) {
        VarHandle varHandle = MethodHandles.byteArrayViewVarHandle(int[].class, ByteOrder.BIG_ENDIAN);
        int byteIndex = 0;
        for (int intIndex = 0; intIndex < pixelCount; intIndex++) {
            intSurface[intIndex] = (int) varHandle.get(surface, byteIndex);
            byteIndex += 4;
        }
    }

    private static final VarHandle BYTE_ARRAY_INT_VIEW =
            MethodHandles.byteArrayViewVarHandle(int[].class, ByteOrder.BIG_ENDIAN);

    private void copy_byte2int_VarHandleStatic(final byte[] surface, final int[] intSurface, final int pixelCount) {
        int byteIndex = 0;
        for (int intIndex = 0; intIndex < pixelCount; intIndex++) {
            intSurface[intIndex] = (int) BYTE_ARRAY_INT_VIEW.get(surface, byteIndex);
            byteIndex += 4;
        }
    }

    private void copy_byte2int_VarHandleStatic2(final byte[] surface, final int[] intSurface, final int pixelCount) {
        int byteIndex = 0;
        ReadIntFromByteArray reader = new ReadIntFromByteArray();
        IntUnaryOperator theReader = i -> reader.read(surface, i);
        intSurfaceWriter(intSurface, pixelCount, byteIndex, theReader);
    }

    private void intSurfaceWriter(int[] intSurface, int pixelCount, int byteIndex, IntUnaryOperator theReader) {
        for (int intIndex = 0; intIndex < pixelCount; intIndex++) {
            intSurface[intIndex] = theReader.applyAsInt(byteIndex);
            byteIndex += 4;
        }
    }

    public static class ReadIntFromByteArray {

        private static final VarHandle BYTE_ARRAY_INT_VIEW =
                MethodHandles.byteArrayViewVarHandle(int[].class, ByteOrder.BIG_ENDIAN);

        public int read(byte[] array, int byteIndex) {
            return (int) BYTE_ARRAY_INT_VIEW.get(array, byteIndex);
        }
    }

    private void copy_byte2int_UnsafeGetInt(final byte[] surface, final int[] intSurface, final int pixelCount) {
        int arrayBase = UNSAFE.arrayBaseOffset(byte[].class);
        int byteIndex = 0;
        for (int intIndex = 0; intIndex < pixelCount; intIndex++) {
            intSurface[intIndex] = UNSAFE.getInt(surface, arrayBase + byteIndex);
            byteIndex += 4;
        }
    }
}
