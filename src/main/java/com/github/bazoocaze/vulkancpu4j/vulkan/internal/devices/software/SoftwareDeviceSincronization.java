package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkFence;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

public class SoftwareDeviceSincronization {

    private SoftwareDeviceSincronization() {
        // static class
    }

    public static VkResult waitForFences(int fenceCount, int fenceCount1, VkBool32 waitAll, long timeout) {
        // TODO: waitForFences
        return VkResult.VK_SUCCESS;
    }

    public static VkResult resetFences(int fenceCount, VkFence[] fences) {
        // TODO: resetFences
        return VkResult.VK_SUCCESS;
    }
}
