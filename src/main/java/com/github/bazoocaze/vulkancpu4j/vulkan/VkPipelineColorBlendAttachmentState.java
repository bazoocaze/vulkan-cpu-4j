package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBlendFactor;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBlendOp;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkColorComponentFlagBits;

@SuppressWarnings("unused")
public class VkPipelineColorBlendAttachmentState {

    public VkBool32 blendEnable;
    public VkBlendFactor srcColorBlendFactor;
    public VkBlendFactor dstColorBlendFactor;
    public VkBlendOp colorBlendOp;
    public VkBlendFactor srcAlphaBlendFactor;
    public VkBlendFactor dstAlphaBlendFactor;
    public VkBlendOp alphaBlendOp;
    @VkColorComponentFlagBits
    public int colorWriteMask;
}
