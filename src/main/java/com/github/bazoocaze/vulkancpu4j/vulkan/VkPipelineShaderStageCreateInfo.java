package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkShaderStageFlagBits;

public class VkPipelineShaderStageCreateInfo extends VkStructureBase {

    public int flags;
    @VkShaderStageFlagBits
    public int stage;
    public VkShaderModule module;
    public String pName;
}
