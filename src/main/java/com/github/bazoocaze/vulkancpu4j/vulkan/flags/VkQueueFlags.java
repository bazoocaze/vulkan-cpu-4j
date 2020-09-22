package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

public class VkQueueFlags extends VkFlags<VkQueueFlagBits> {

    public VkQueueFlags(VkQueueFlagBits[] flags) {
        super(VkQueueFlagBits.class, flags);
    }

    public static VkQueueFlags of(VkQueueFlagBits... flags) {
        return new VkQueueFlags(flags);
    }
}
