package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

public class VkDebugUtilsMessageTypeFlagsEXT extends VkFlags<VkDebugUtilsMessageTypeFlagBitsEXT> {

    public VkDebugUtilsMessageTypeFlagsEXT(VkDebugUtilsMessageTypeFlagBitsEXT[] flags) {
        super(VkDebugUtilsMessageTypeFlagBitsEXT.class, flags);
    }

    public static VkDebugUtilsMessageTypeFlagsEXT of(VkDebugUtilsMessageTypeFlagBitsEXT... flags) {
        return new VkDebugUtilsMessageTypeFlagsEXT(flags);
    }
}
