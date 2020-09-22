package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkImageAspectFlagBits;

public class VkImageSubresourceRange {

    @VkImageAspectFlagBits
    public int aspectMask;

    public int baseMipLevel;
    public int levelCount;
    public int baseArrayLayer;
    public int layerCount;
}
