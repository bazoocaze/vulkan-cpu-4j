package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkSampleCountFlagBits;

@SuppressWarnings("unused")
public class VkPipelineMultisampleStateCreateInfo extends VkStructureBase {

    public int flags;
    public VkSampleCountFlagBits rasterizationSamples;
    public VkBool32 sampleShadingEnable;
    public float minSampleShading;
    public int[] pSampleMask;
    public VkBool32 alphaToCoverageEnable;
    public VkBool32 alphaToOneEnable;
}
