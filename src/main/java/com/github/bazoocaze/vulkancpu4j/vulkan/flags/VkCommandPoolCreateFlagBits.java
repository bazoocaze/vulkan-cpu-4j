package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

import org.intellij.lang.annotations.MagicConstant;

@SuppressWarnings("unused")
@MagicConstant(flagsFromClass = VkCommandPoolCreateFlagBits.class)
public @interface VkCommandPoolCreateFlagBits {

    int VK_COMMAND_POOL_CREATE_TRANSIENT_BIT = 0x00000001;
    int VK_COMMAND_POOL_CREATE_RESET_COMMAND_BUFFER_BIT = 0x00000002;
    int VK_COMMAND_POOL_CREATE_PROTECTED_BIT = 0x00000004;
}
