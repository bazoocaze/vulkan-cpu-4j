package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

import org.intellij.lang.annotations.MagicConstant;

@SuppressWarnings("unused")
@MagicConstant(flagsFromClass = VkCommandBufferUsageFlags.class)
public @interface VkCommandBufferUsageFlags {

    int VK_COMMAND_BUFFER_USAGE_ONE_TIME_SUBMIT_BIT = 0x00000001;
    int VK_COMMAND_BUFFER_USAGE_RENDER_PASS_CONTINUE_BIT = 0x00000002;
    int VK_COMMAND_BUFFER_USAGE_SIMULTANEOUS_USE_BIT = 0x00000004;
}
