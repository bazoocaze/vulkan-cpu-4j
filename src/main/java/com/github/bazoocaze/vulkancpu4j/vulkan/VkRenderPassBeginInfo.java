package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkClearValue;
import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkRect2D;

public class VkRenderPassBeginInfo extends VkStructureBase {

    public VkRenderPass renderPass;
    public VkFramebuffer framebuffer;
    public VkRect2D renderArea = new VkRect2D();
    public int clearValueCount;
    public VkClearValue[] pClearValues;
}
