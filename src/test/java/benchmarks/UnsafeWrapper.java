package benchmarks;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static com.github.bazoocaze.vulkancpu4j.util.UncheckedExceptions.unchecked;

public class UnsafeWrapper {

    private static final Unsafe UNSAFE = unchecked(UnsafeWrapper::getUnsafe);

    private static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    public static Unsafe get() {
        return UNSAFE;
    }
}
