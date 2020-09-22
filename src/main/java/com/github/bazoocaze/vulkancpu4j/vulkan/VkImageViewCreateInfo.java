package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkFormat;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkImageViewType;

public class VkImageViewCreateInfo extends VkStructureBase {

    public VkImage image;
    public VkImageViewType viewType;
    public VkFormat format;
    public VkComponentMapping components;
    public VkImageSubresourceRange subresourceRange;

    public VkImageViewCreateInfo() {
        components = new VkComponentMapping();
        subresourceRange = new VkImageSubresourceRange();
    }
}
