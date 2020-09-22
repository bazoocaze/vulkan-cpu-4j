package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkRect2D;
import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkViewport;

public class VkPipelineViewportStateCreateInfo extends VkStructureBase {

    public int flags;
    public int viewportCount;
    public VkViewport[] pViewports;
    public int scissorCount;
    public VkRect2D[] pScissors;
}
