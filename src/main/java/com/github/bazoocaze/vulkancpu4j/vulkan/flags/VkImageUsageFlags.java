package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

public class VkImageUsageFlags extends VkFlags<VkImageUsageFlagBits> {

    public VkImageUsageFlags(VkImageUsageFlagBits[] flags) {
        super(VkImageUsageFlagBits.class, flags);
    }

    public static VkImageUsageFlags of(VkImageUsageFlagBits... flags) {
        return new VkImageUsageFlags(flags);
    }
}
