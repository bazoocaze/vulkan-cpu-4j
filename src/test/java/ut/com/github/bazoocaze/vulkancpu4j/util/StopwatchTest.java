package ut.com.github.bazoocaze.vulkancpu4j.util;

import com.github.bazoocaze.vulkancpu4j.util.NanoClock;
import com.github.bazoocaze.vulkancpu4j.util.Stopwatch;
import org.junit.Test;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StopwatchTest {

    @Test
    public void shouldReturnDuration() {
        when(nanoClock.getNanos()).thenReturn(10L, 11L);

        stopwatch.start();
        stopwatch.stop();

        Duration elapsed = stopwatch.elapsed();
        assertNotNull(elapsed);
        assertEquals(1, elapsed.getNano());
        assertEquals(0, elapsed.getSeconds());
    }

    @Test
    public void shouldElapsedMilliseconds() {
        when(nanoClock.getNanos()).thenReturn(250000L, 750000L);

        stopwatch.start();
        stopwatch.stop();

        double milliseconds = stopwatch.elapsedMilliseconds();
        assertTrue(milliseconds > 0.499 && milliseconds < 0.501);
    }

    @Test
    public void shouldResetTimer() {
        when(nanoClock.getNanos()).thenReturn(10L, 20L, 25L);

        stopwatch.start();
        stopwatch.reset();

        assertThat(stopwatch.elapsed().toNanos(), is(0L));
    }

    @Test
    public void shouldRestartTimer() {
        when(nanoClock.getNanos()).thenReturn(10L, 20L, 25L);

        stopwatch.start();
        stopwatch.stop();
        stopwatch.restart();

        assertThat(stopwatch.elapsed().toNanos(), is(0L));
    }

    private final NanoClock nanoClock = mock(NanoClock.class);
    private final Stopwatch stopwatch = new Stopwatch(nanoClock);
}