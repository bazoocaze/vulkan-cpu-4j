package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

public class VkDebugUtilsMessageSeverityFlagsEXT extends VkFlags<VkDebugUtilsMessageSeverityFlagBitsEXT> {

    public VkDebugUtilsMessageSeverityFlagsEXT(VkDebugUtilsMessageSeverityFlagBitsEXT[] flags) {
        super(VkDebugUtilsMessageSeverityFlagBitsEXT.class, flags);
    }

    public static VkDebugUtilsMessageSeverityFlagsEXT of(VkDebugUtilsMessageSeverityFlagBitsEXT... flags) {
        return new VkDebugUtilsMessageSeverityFlagsEXT(flags);
    }
}
