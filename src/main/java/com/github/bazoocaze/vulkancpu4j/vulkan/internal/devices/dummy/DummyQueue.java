package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

public class DummyQueue implements VkQueue {

    private final VkDeviceQueueCreateInfo info;

    public DummyQueue(VkDeviceQueueCreateInfo info) {
        this.info = info;
    }

    public int queueFamilyIndex() {
        return info.queueFamilyIndex;
    }

    @Override
    public VkResult queueSubmit(int submitCount, VkSubmitInfo[] submits, VkFence fence) {
        // do nothing
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult queuePresentKHR(VkPresentInfoKHR presentInfo) {
        // do nothing
        return VkResult.VK_SUCCESS;
    }
}
