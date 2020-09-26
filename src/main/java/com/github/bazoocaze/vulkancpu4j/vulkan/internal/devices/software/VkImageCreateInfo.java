package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkStructureBase;
import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkExtent3D;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkFormat;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkImageLayout;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkSampleCountFlagBits;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkSharingMode;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkImageUsageFlags;

public class VkImageCreateInfo extends VkStructureBase {
    public VkExtent3D extent;
    public VkFormat format;
    public VkImageType imageType;
    public int arrayLayers;
    public VkSharingMode sharingMode;
    public VkImageUsageFlags usage;
    public VkSampleCountFlagBits samples;
    public int mipLevels;
    public VkImageLayout initialLayout;
}
