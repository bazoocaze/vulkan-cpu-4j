package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkAccessFlagBits;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkPipelineStageFlagBits;

public class VkSubpassDependency {

    public int srcSubpass;
    public int dstSubpass;

    @VkPipelineStageFlagBits
    public int srcStageMask;
    @VkPipelineStageFlagBits
    public int dstStageMask;

    @VkAccessFlagBits
    public int srcAccessMask;
    @VkAccessFlagBits
    public int dstAccessMask;

    public int dependencyFlags;
}
