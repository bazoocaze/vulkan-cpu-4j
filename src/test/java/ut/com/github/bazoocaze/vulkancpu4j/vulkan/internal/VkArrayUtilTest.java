package ut.com.github.bazoocaze.vulkancpu4j.vulkan.internal;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.VkArrayUtil;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;

public class VkArrayUtilTest {

    public static final Integer[] SOURCE = new Integer[]{10, 20, 40, 30};

    @Test
    public void shouldFillSize() {
        ByRef<Integer> size = new ByRef<>();

        VkResult result = VkArrayUtil.copyArray(SOURCE, size, null);

        assertThat(result, notNullValue());
        assertThat(result, is(VkResult.VK_SUCCESS));
        assertThat(size.get(), is(4));
    }

    @Test
    public void shouldCopyData() {
        ByRef<Integer> size = new ByRef<>(4);
        Integer[] output = new Integer[4];

        VkResult result = VkArrayUtil.copyArray(SOURCE, size, output);

        assertThat(result, notNullValue());
        assertThat(result, is(VkResult.VK_SUCCESS));
        assertThat(size.get(), is(4));
        assertArrayEquals(SOURCE, output);
    }

    @Test
    public void shouldCopyDataWhenSizeIsLowerThanCurrentSize() {
        ByRef<Integer> size = new ByRef<>(2);
        Integer[] output = new Integer[2];

        VkResult result = VkArrayUtil.copyArray(SOURCE, size, output);

        assertThat(result, notNullValue());
        assertThat(result, is(VkResult.VK_INCOMPLETE));
        assertThat(size.get(), is(2));
        assertThat(output[0], is(10));
        assertThat(output[1], is(20));
    }

    @Test
    public void shouldFillSizeOfListData() {
        ByRef<Integer> size = new ByRef<>(2);

        VkResult result = VkArrayUtil.copyArray(Arrays.asList(SOURCE), size, null);

        assertThat(result, notNullValue());
        assertThat(result, is(VkResult.VK_SUCCESS));
        assertThat(size.get(), is(4));
    }

    @Test
    public void shouldCopyListData() {
        ByRef<Integer> size = new ByRef<>(4);
        Integer[] output = new Integer[4];

        VkResult result = VkArrayUtil.copyArray(Arrays.asList(SOURCE), size, output);

        assertThat(result, is(VkResult.VK_SUCCESS));
        assertArrayEquals(SOURCE, output);
    }

    @Test
    public void shouldCopyPartialListData() {
        ByRef<Integer> size = new ByRef<>(2);
        Integer[] output = new Integer[2];

        VkResult result = VkArrayUtil.copyArray(Arrays.asList(SOURCE), size, output);

        assertThat(result, is(VkResult.VK_INCOMPLETE));
        assertThat(output[0], is(10));
        assertThat(output[1], is(20));
    }

}