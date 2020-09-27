package com.github.bazoocaze.vulkancpu4j.util;

import java.util.concurrent.Callable;

public class UncheckedExceptions {

    private UncheckedExceptions() {
        // static class
    }

    public static <T> T unchecked(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception ex) {
            throw throwAsUnchecked(ex);
        }
    }

    public static void unchecked(ThrowingRunnable throwingRunnable) {
        try {
            throwingRunnable.run();
        } catch (Exception ex) {
            throw throwAsUnchecked(ex);
        }
    }

    public interface ThrowingRunnable {

        void run() throws Exception;
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable> RuntimeException throwAsUnchecked(Exception exception) throws E {
        throw (E) exception;
    }
}
