package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

public class VkPresentInfoKHR extends VkStructureBase {

    public int waitSemaphoreCount;
    public VkSemaphore[] pWaitSemaphores;
    public int swapchainCount;
    public VkSwapchainKHR[] pSwapchains;
    public int[] pImageIndices;
    public VkResult[] pResults;
}
