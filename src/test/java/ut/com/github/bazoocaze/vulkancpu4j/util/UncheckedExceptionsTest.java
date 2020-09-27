package ut.com.github.bazoocaze.vulkancpu4j.util;

import com.github.bazoocaze.vulkancpu4j.util.UncheckedExceptions;
import com.github.bazoocaze.vulkancpu4j.util.UncheckedExceptions.ThrowingRunnable;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class UncheckedExceptionsTest {

    @Test
    public void shouldReturnValueForCallable() throws Exception {
        Callable<Integer> callable = mock(Callable.class);

        when(callable.call()).thenReturn(42);

        int value = UncheckedExceptions.unchecked(callable);

        assertThat(value, is(42));
    }

    @Test
    public void shouldRunRunnable() {
        Runnable runnable = mock(Runnable.class);

        UncheckedExceptions.unchecked(runnable::run);

        verify(runnable).run();
    }

    @Test
    public void shouldThrownCheckExceptionForCallable() throws Exception {
        Callable<Integer> callable = mock(Callable.class);

        when(callable.call()).thenThrow(new Exception());

        Exception exception = assertThrows(Exception.class, () ->
                UncheckedExceptions.unchecked(callable)
        );

        assertEquals(exception.getClass().getSimpleName(), Exception.class.getSimpleName());
    }

    @Test
    public void shouldThrowCheckExceptionForRunnable() throws Exception {
        ThrowingRunnable runnable = mock(ThrowingRunnable.class);

        doThrow(new Exception()).when(runnable).run();

        Exception exception = assertThrows(Exception.class, () ->
                UncheckedExceptions.unchecked(runnable::run)
        );

        assertEquals(exception.getClass().getSimpleName(), Exception.class.getSimpleName());
    }
}