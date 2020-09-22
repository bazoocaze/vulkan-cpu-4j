package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface VkQueue {

    @Internal
    VkResult queueSubmit(int submitCount, VkSubmitInfo[] submits, VkFence fence);

    @Internal
    VkResult queuePresentKHR(VkPresentInfoKHR presentInfo);
}
