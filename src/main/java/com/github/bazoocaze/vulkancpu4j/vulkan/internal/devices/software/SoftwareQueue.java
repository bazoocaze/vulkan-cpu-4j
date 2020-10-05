package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

import static com.github.bazoocaze.vulkancpu4j.util.UncheckedExceptions.unchecked;

public class SoftwareQueue implements VkQueue {

    private final VkDeviceQueueCreateInfo queueCreateInfo;

    public SoftwareQueue(VkDeviceQueueCreateInfo queueCreateInfo) {
        this.queueCreateInfo = queueCreateInfo;
    }

    @Override
    public VkResult queueSubmit(int submitCount, VkSubmitInfo[] submits, VkFence fence) {
        // TODO: queueSubmit
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult queuePresentKHR(VkPresentInfoKHR presentInfo) {
        unchecked(() -> Thread.sleep(1));
        // TODO: queuePresentKHR
        return VkResult.VK_SUCCESS;
    }
}
