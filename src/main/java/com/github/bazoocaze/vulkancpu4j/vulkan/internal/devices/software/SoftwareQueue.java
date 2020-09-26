package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

public class SoftwareQueue implements VkQueue {

    private final VkDeviceQueueCreateInfo queueCreateInfo;

    public SoftwareQueue(VkDeviceQueueCreateInfo queueCreateInfo) {
        this.queueCreateInfo = queueCreateInfo;
    }

    @Override
    public VkResult queueSubmit(int submitCount, VkSubmitInfo[] submits, VkFence fence) {
        return null;
    }

    @Override
    public VkResult queuePresentKHR(VkPresentInfoKHR presentInfo) {
        return null;
    }
}
