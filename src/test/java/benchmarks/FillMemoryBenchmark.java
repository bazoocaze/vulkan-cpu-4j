package benchmarks;

import it.unimi.dsi.util.XoRoShiRo128PlusRandom;
import org.openjdk.jmh.annotations.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.IntSupplier;

import static com.github.bazoocaze.vulkancpu4j.util.MyBenchmark.aNewBenchmark;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Timeout(time = 30)
@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
public class FillMemoryBenchmark {

    final Random random = new Random();
    final int width = 1280;
    final int height = 720;
    final int pixelSize = 4;
    final int pixels = width * height;

    final byte[] byteSurface = new byte[width * height * pixelSize];
    final int[] intSurface = new int[width * height];

    final ByteBuffer byteBuffer = ByteBuffer.wrap(byteSurface);
    final IntBuffer intByteBuffer = byteBuffer.asIntBuffer();
    final IntBuffer intBuffer = IntBuffer.wrap(intSurface);
    final FloatBuffer floatByteBuffer = byteBuffer.asFloatBuffer();

    @Setup
    public void setup() {
        for (int n = 0; n < byteSurface.length; n++) {
            byteSurface[n] = (byte) random.nextInt();
        }
        for (int n = 0; n < intSurface.length; n++) {
            intSurface[n] = (byte) random.nextInt();
        }
    }

    public static void main(String[] args) {
        new FillMemoryBenchmark().run();
    }

    private void run() {
        setup();
        benchmarkFill();
    }

    private void benchmarkFill() {
        setup();
        aNewBenchmark()
                .withWorkSize(1000)
                .withAction("fill_byteArray_ArraysFill", this::fill_byteArray_ArraysFill)
                .withAction("fill_byteArray_SystemArrayCopy1", this::fill_byteArray_SystemArrayCopy1)
                .withAction("fill_byteArray_SystemArrayCopy2", this::fill_byteArray_SystemArrayCopy2)
                .withAction("fill_intArray_SystemArrayCopy2", this::fill_intArray_SystemArrayCopy2)
                .withAction("fill_intArray", this::fill_intArray)
                .withAction("fill_intArray_ArraysFill", this::fill_intArray_ArraysFill)
                .withAction("fill_floatByteBuffer", this::fill_floatByteBuffer)
                .withAction("fill_intBuffer", this::fill_intBuffer)
                .withAction("fill_intBuffer_withSupplier_baseline", this::fill_intBuffer_withSupplier_baseline)
                .withAction("fill_intByteBuffer", this::fill_intByteBuffer)
//                .withAction("fill_intBuffer_RandomDefault", this::fill_intBuffer_RandomDefault)
                .withAction("fill_intBuffer_RandomSplittable", this::fill_intBuffer_RandomSplittable)
                .withAction("fill_intBuffer_RandomXoRo", this::fill_intBuffer_RandomXoRo)
                .run();
    }

    @Benchmark
    public void fill_byteArray_ArraysFill() {
        Arrays.fill(byteSurface, (byte) 0);
    }

    @Benchmark
    public void fill_intArray_ArraysFill() {
        Arrays.fill(intSurface, 0);
    }

    @Benchmark
    public void fill_byteArray_SystemArrayCopy1() {
        fill_byteArray_SystemArrayCopy1(byteSurface, 0, pixels, 0xA511225A);
    }

    @Benchmark
    public void fill_byteArray_SystemArrayCopy2() {
        fill_byteArray_SystemArrayCopy2(byteSurface, 0, pixels, 0xA511225A);
    }

    @Benchmark
    public void fill_intArray_SystemArrayCopy2() {
        fill_intArray_SystemArrayCopy2(intSurface, 0, pixels, 0xA511225A);
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
    public void fill_intByteBuffer() {
        fill_intBuffer(intByteBuffer, 0, pixels, 0xA511225A);
    }

    @Benchmark
    public void fill_intArray() {
        fill_intArray(intSurface, 0, pixels, 0xA511225A);
    }

    @Benchmark
    public void fill_floatByteBuffer() {
        fill_floatBuffer(floatByteBuffer, 0, pixels, 1f);
    }

    @Benchmark
    public void fill_intBuffer_withSupplier_baseline() {
        fill_intBuffer_withSupplier(intByteBuffer, 0, pixels, () -> 0xA511225A);
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
            int copySize = Math.min(1024, Math.min((total - i), i));
            System.arraycopy(surface, 0, surface, i, copySize);
            i += copySize;
        }
    }

    private void fill_intArray_SystemArrayCopy2(final int[] surface, final int offset, final int size,
                                                final int color) {
        if (size > 0) {
            surface[offset] = color;
        }

        int i = 1;
        while (i < size) {
            int copySize = Math.min(256, Math.min((size - i), i));
            System.arraycopy(surface, 0, surface, i, copySize);
            i += copySize;
        }
    }

    private void fill_intBuffer_RandomXoRo(final IntBuffer intBuffer, final int offset, final int size) {
        final XoRoShiRo128PlusRandom random = new XoRoShiRo128PlusRandom();
        fill_intBuffer_withSupplier(intBuffer, offset, size, () -> random.nextInt() | 0x000000FF);
    }

    private void fill_intBuffer_RandomSplittable(final IntBuffer intBuffer, final int offset, final int size) {
        SplittableRandom random = new SplittableRandom();
        fill_intBuffer_withSupplier(intBuffer, offset, size, () -> random.nextInt() | 0x000000FF);
    }

    private void fill_intBuffer_RandomDefault(final IntBuffer intBuffer, final int offset, final int size) {
        Random random = new Random();
        fill_intBuffer_withSupplier(intBuffer, offset, size, () -> random.nextInt() | 0x000000FF);
    }

    private void fill_intBuffer_withSupplier(final IntBuffer intBuffer, final int offset, final int size,
                                             final IntSupplier value) {
        intBuffer.position(offset);
        for (int i = 0; i < size; i++) {
            intBuffer.put(value.getAsInt());
        }
    }
}
