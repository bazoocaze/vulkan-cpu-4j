package benchmarks;

import it.unimi.dsi.util.XoRoShiRo128PlusRandom;
import org.openjdk.jmh.annotations.*;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

import static com.github.bazoocaze.vulkancpu4j.util.MyBenchmark.aNewBenchmark;
import static com.github.bazoocaze.vulkancpu4j.util.UncheckedException.unchecked;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Timeout(time = 30)
@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
public class MemoryBenchmarks {

    private static Unsafe unsafe = unchecked(MemoryBenchmarks::getUnsafe);

    private static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    final Random random = new Random();
    final int width = 1280;
    final int height = 720;
    final int pixels = width * height;
    final byte[] surface = new byte[width * height * 4];
    final byte[] surface2 = new byte[width * height * 4];
    final ByteBuffer buffer1 = ByteBuffer.wrap(surface);
    final int[] intSurface = new int[width * height];
    final ByteBuffer buffer2 = ByteBuffer.wrap(surface);
    final IntBuffer intBuffer = buffer1.asIntBuffer();
    final FloatBuffer floatBuffer = buffer1.asFloatBuffer();

    @Setup
    public void setup() {
        for (int n = 0; n < 16 * 16; n++) {
            surface[n] = (byte) random.nextInt();
        }
    }

//    public static void main(String[] args) {
//        new MemoryTest().run();
//    }

    private void run() {
        setup();
        benchmarkFill();
        benchmarkCopy();
    }

    private void benchmarkFill() {
        setup();
        aNewBenchmark()
                .withWorkSize(1000)
                .withAction("fill_byteArray_ArraysFill", this::fill_byteArray_ArraysFill)
                .withAction("fill_byteArray_SystemArrayCopy1", this::fill_byteArray_SystemArrayCopy1)
                .withAction("fill_byteArray_SystemArrayCopy2", this::fill_byteArray_SystemArrayCopy2)
                .withAction("fill_intArray", this::fill_intArray)
                .withAction("fill_intArray_ArraysFill", this::fill_intArray_ArraysFill)
                .withAction("fill_floatBuffer", this::fill_floatBuffer)
                .withAction("fill_intBuffer", this::fill_intBuffer)
                .withAction("fill_intBuffer_RandomDefault", this::fill_intBuffer_RandomDefault)
                .withAction("fill_intBuffer_RandomSplittable", this::fill_intBuffer_RandomSplittable)
                .withAction("fill_intBuffer_RandomXoRo", this::fill_intBuffer_RandomXoRo)
                .run();
    }

    private void benchmarkCopy() {
        setup();
        aNewBenchmark()
                .withWorkSize(1000)
                .withAction("copy_byteArray_SystemArrayCopy", this::copy_byteArray_SystemArrayCopy)
                .withAction("copy_byte2int_UnsafeCopyMemory", this::copy_byte2Int_UnsafeCopyMemory)
                .withAction("copy_byte2int_loop", this::copy_byte2int_loop)
                .withAction("copy_byte2int_VarHandle", this::copy_byte2int_VarHandle)
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
    public void copy_byte2int_UnsafeGetInt() {
        copy_byte2int_UnsafeGetInt(surface, intSurface, pixels);
    }

    @Benchmark
    public void fill_byteArray_ArraysFill() {
        Arrays.fill(surface, (byte) 0);
    }

    @Benchmark
    public void fill_intArray_ArraysFill() {
        Arrays.fill(intSurface, 0);
    }

    @Benchmark
    public void fill_intBuffer_RandomDefault() {
        fill_intBuffer_RandomDefault(intBuffer, 0, pixels);
    }

    @Benchmark
    public void fill_intBuffer_RandomSplittable() {
        fill_intBuffer_RandomSplittable(intBuffer, 0, pixels);
    }

    @Benchmark
    public void fill_intBuffer_RandomXoRo() {
        fill_intBuffer_RandomXoRo(intBuffer, 0, pixels);
    }

    @Benchmark
    public void fill_intBuffer() {
        fill_intBuffer(intBuffer, 0, pixels, 0xA511225A);
    }

    @Benchmark
    public void fill_intArray() {
        fill_intArray(intSurface, 0, pixels, 0xA511225A);
    }

    @Benchmark
    public void fill_byteArray_SystemArrayCopy1() {
        fill_byteArray_SystemArrayCopy1(surface, 0, pixels, 0xA511225A);
    }

    @Benchmark
    public void fill_byteArray_SystemArrayCopy2() {
        fill_byteArray_SystemArrayCopy2(surface, 0, pixels, 0xA511225A);
    }

    @Benchmark
    public void fill_floatBuffer() {
        fill_floatBuffer(floatBuffer, 0, pixels, 1f);
    }

    private void fill_intBuffer(final IntBuffer intBuffer, final int offset, final int size, final int color) {
        intBuffer.position(offset);
        for (int i = 0; i < size; i++) {
            intBuffer.put(color);
        }
    }

    private void fill_intArray(final int[] intSurface, final int offset, final int size, final int color) {
        for (int i = offset; i < offset + size; i++) {
            intSurface[i] = color;
        }
    }

    private void fill_floatBuffer(final FloatBuffer floatBuffer, final int offset, final int size, final float value) {
        floatBuffer.position(offset);
        for (int i = 0; i < size; i++) {
            floatBuffer.put(value);
        }
    }

    private void fill_byteArray_SystemArrayCopy1(final byte[] surface, final int offset, final int size, final int color) {
        int total = size * 4;

        if (size > 0) {
            surface[offset] = (byte) ((color >> 24) & 0x00FF);
            surface[offset + 1] = (byte) ((color >> 16) & 0x00FF);
            surface[offset + 2] = (byte) ((color >> 8) & 0x00FF);
            surface[offset + 3] = (byte) (color & 0x00FF);
        }

        for (int i = 1; i < total; i += i) {
            System.arraycopy(surface, 0, surface, i, Math.min((total - i), i));
        }
    }

    private void fill_byteArray_SystemArrayCopy2(final byte[] surface, final int offset, final int size, final int color) {
        int total = size * 4;

        if (size > 0) {
            surface[offset] = (byte) ((color >> 24) & 0x00FF);
            surface[offset + 1] = (byte) ((color >> 16) & 0x00FF);
            surface[offset + 2] = (byte) ((color >> 8) & 0x00FF);
            surface[offset + 3] = (byte) (color & 0x00FF);
        }

        int i = 1;
        while (i < total) {
            int copySize = Math.min(8192, Math.min((total - i), i));
            System.arraycopy(surface, 0, surface, i, copySize);
            i += copySize;
        }
    }

    private void copy_byte2Int_UnsafeCopyMemory(final byte[] surface, final int[] intSurface, final int pixelCount) {
        int srcOffsetByteArray = unsafe.arrayBaseOffset(byte[].class);
        int destOffsetIntArray = unsafe.arrayBaseOffset(int[].class);
        unsafe.copyMemory(
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

    private void copy_byte2int_UnsafeGetInt(final byte[] surface, final int[] intSurface, final int pixelCount) {
        int arrayBase = unsafe.arrayBaseOffset(byte[].class);
        int byteIndex = 0;
        for (int intIndex = 0; intIndex < pixelCount; intIndex++) {
            intSurface[intIndex] = unsafe.getInt(surface, arrayBase + byteIndex);
            byteIndex += 4;
        }
    }

    private void fill_intBuffer_RandomXoRo(final IntBuffer intBuffer, final int offset, final int size) {
        XoRoShiRo128PlusRandom random = new XoRoShiRo128PlusRandom();
        intBuffer.position(offset);
        for (int i = 0; i < size; i++) {
            intBuffer.put(random.nextInt() | 0x000000FF);
        }
    }

    private void fill_intBuffer_RandomSplittable(final IntBuffer intBuffer, final int offset, final int size) {
        SplittableRandom random = new SplittableRandom();
        intBuffer.position(offset);
        for (int i = 0; i < size; i++) {
            intBuffer.put(random.nextInt() | 0x000000FF);
        }
    }

    private void fill_intBuffer_RandomDefault(final IntBuffer intBuffer, final int offset, final int size) {
        Random random = new Random();
        intBuffer.position(offset);
        for (int i = 0; i < size; i++) {
            intBuffer.put(random.nextInt() | 0x000000FF);
        }
    }
}
