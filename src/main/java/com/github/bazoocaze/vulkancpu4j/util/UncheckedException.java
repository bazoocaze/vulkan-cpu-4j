package com.github.bazoocaze.vulkancpu4j.util;

import java.util.concurrent.Callable;

public class UncheckedException extends RuntimeException {

    public UncheckedException(Throwable ex) {
        super(ex);
    }

    public static <T> T unchecked(Callable<T> callable) {
        try {
            return callable.call();
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new UncheckedException(ex);
        }
    }

    public static void unchecked(ThrowingRunnable throwingRunnable) {
        try {
            throwingRunnable.run();
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new UncheckedException(ex);
        }
    }

    public interface ThrowingRunnable {

        void run() throws Exception;
    }
}
