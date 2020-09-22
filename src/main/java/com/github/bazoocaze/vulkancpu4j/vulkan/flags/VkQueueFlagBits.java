package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

public enum  VkQueueFlagBits {
    VK_QUEUE_GRAPHICS_BIT(0x00000001),
    VK_QUEUE_COMPUTE_BIT(0x00000002),
    VK_QUEUE_TRANSFER_BIT(0x00000004),
    VK_QUEUE_SPARSE_BINDING_BIT(0x00000008),

    // Provided by VK_VERSION_1_1
    VK_QUEUE_PROTECTED_BIT(0x00000010),
    ;

    private final int value;

    VkQueueFlagBits(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
