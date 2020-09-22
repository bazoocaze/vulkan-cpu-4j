package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkFrontFace;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkPolygonMode;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkCullModeFlagBits;

@SuppressWarnings("unused")
public class VkPipelineRasterizationStateCreateInfo extends VkStructureBase {

    public int flags;
    public VkBool32 depthClampEnable;
    public VkBool32 rasterizerDiscardEnable;
    public VkPolygonMode polygonMode;
    @VkCullModeFlagBits
    public int cullMode;
    public VkFrontFace frontFace;
    public VkBool32 depthBiasEnable;
    public float depthBiasConstantFactor;
    public float depthBiasClamp;
    public float depthBiasSlopeFactor;
    public float lineWidth;
}
