package ut.com.github.bazoocaze.vulkancpu4j.util;

import com.github.bazoocaze.vulkancpu4j.util.TimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class TimeFormatterTest {

    @Before
    public void setup() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void shouldFormatTime() {
        Assert.assertEquals("0.00s", TimeFormatter.format(0));
        assertEquals("1.00s", TimeFormatter.format(1.0));
        assertEquals("3600.00s", TimeFormatter.format(3600.0));
        assertEquals("45.23ms", TimeFormatter.format(0.045234));
        assertEquals("452.34us", TimeFormatter.format(0.00045234));
        assertEquals("12.24ns", TimeFormatter.format(0.000000012241));
        assertEquals("123.45ps", TimeFormatter.format(0.00000000012345));
    }
}