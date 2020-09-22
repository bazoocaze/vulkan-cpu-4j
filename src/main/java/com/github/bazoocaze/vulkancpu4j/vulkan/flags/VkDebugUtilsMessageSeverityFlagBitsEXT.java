package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

public enum VkDebugUtilsMessageSeverityFlagBitsEXT {

    VK_DEBUG_UTILS_MESSAGE_SEVERITY_VERBOSE_BIT_EXT(0x00000001),
    VK_DEBUG_UTILS_MESSAGE_SEVERITY_INFO_BIT_EXT(0x00000010),
    VK_DEBUG_UTILS_MESSAGE_SEVERITY_WARNING_BIT_EXT(0x00000100),
    VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT(0x00001000),
    ;

    private final int value;

    VkDebugUtilsMessageSeverityFlagBitsEXT(int value) {
        this.value = value;
    }
}
