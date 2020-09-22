package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

public enum VkDebugUtilsMessageTypeFlagBitsEXT {
    VK_DEBUG_UTILS_MESSAGE_TYPE_GENERAL_BIT_EXT(0x00000001),
    VK_DEBUG_UTILS_MESSAGE_TYPE_VALIDATION_BIT_EXT(0x00000002),
    VK_DEBUG_UTILS_MESSAGE_TYPE_PERFORMANCE_BIT_EXT(0x00000004),
    ;

    private final int value;

    VkDebugUtilsMessageTypeFlagBitsEXT(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
