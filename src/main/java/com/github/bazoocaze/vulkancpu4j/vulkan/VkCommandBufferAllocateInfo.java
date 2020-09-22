package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkCommandBufferLevel;

public class VkCommandBufferAllocateInfo extends VkStructureBase {

    public VkCommandPool commandPool;
    public VkCommandBufferLevel level;
    public int commandBufferCount;
}
