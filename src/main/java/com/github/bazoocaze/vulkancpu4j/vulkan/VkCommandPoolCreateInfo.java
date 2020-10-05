package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkCommandPoolCreateFlagBits;

public class VkCommandPoolCreateInfo extends VkStructureBase {

    @VkCommandPoolCreateFlagBits
    public int flags;
    public int queueFamilyIndex;
}
