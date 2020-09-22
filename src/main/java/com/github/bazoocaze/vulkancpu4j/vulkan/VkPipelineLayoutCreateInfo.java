package com.github.bazoocaze.vulkancpu4j.vulkan;

@SuppressWarnings("unused")
public class VkPipelineLayoutCreateInfo extends VkStructureBase {

    public int flags;
    public int setLayoutCount;
    public VkDescriptorSetLayout[] pSetLayouts;
    public int pushConstantRangeCount;
    public VkPushConstantRange[] pPushConstantRanges;
}
