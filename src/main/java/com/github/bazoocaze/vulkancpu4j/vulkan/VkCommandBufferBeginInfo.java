package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkCommandBufferUsageFlags;

public class VkCommandBufferBeginInfo extends VkStructureBase {

    @VkCommandBufferUsageFlags
    public int flags;
    public VkCommandBufferInheritanceInfo pInheritanceInfo;

}
