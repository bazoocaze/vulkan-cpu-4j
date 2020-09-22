package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface VkSwapchainKHR {

    @Internal
    VkResult getSwapchainImagesKHR(ByRef<Integer> swapchainImageCount, VkImage[] swapchainImages);

    @Internal
    VkResult acquireNextImageKHR(long timeout, VkSemaphore semaphore, VkFence fence, OutRef<Integer> imageIndex);
}
