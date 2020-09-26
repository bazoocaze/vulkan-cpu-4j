package com.github.bazoocaze.vulkancpu4j.vulkan.internal;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

import java.util.List;

public class VkArrayUtil {

    private VkArrayUtil() {
        // static class
    }

    public static <T> VkResult copyArray(T[] data, ByRef<Integer> dataCount, T[] output) {
        if (output == null) {
            dataCount.set(data.length);
            return VkResult.VK_SUCCESS;
        } else {
            int size = Math.min(data.length, dataCount.get());
            System.arraycopy(data, 0, output, 0, size);
            dataCount.set(size);
            return size < data.length ? VkResult.VK_INCOMPLETE : VkResult.VK_SUCCESS;
        }
    }

    public static <T> VkResult copyArray(List<? extends T> data, ByRef<Integer> dataCount, T[] output) {
        return copyArray(data.toArray(), dataCount, output);
    }
}
