package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkPrimitiveTopology;

public class VkPipelineInputAssemblyStateCreateInfo extends VkStructureBase {

    public int flags;
    public VkPrimitiveTopology topology;
    public VkBool32 primitiveRestartEnable;
}
