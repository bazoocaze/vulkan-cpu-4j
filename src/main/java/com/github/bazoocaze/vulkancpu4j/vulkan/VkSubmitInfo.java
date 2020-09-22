package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkPipelineStageFlagBits;

public class VkSubmitInfo extends VkStructureBase {

    public int waitSemaphoreCount;
    public VkSemaphore[] pWaitSemaphores;
    @VkPipelineStageFlagBits
    public int[] pWaitDstStageMask;
    public int commandBufferCount;
    public VkCommandBuffer[] pCommandBuffers;
    public int signalSemaphoreCount;
    public VkSemaphore[] pSignalSemaphores;
}
