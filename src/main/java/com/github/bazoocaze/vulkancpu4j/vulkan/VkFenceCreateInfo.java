package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkFenceCreateFlagBits;

public class VkFenceCreateInfo extends VkStructureBase {

    @VkFenceCreateFlagBits
    public int flags;
}
