package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

import org.intellij.lang.annotations.MagicConstant;

@SuppressWarnings("EmptyClass")
@MagicConstant(flagsFromClass = VkColorComponentFlagBits.class)
public @interface VkColorComponentFlagBits {

    int VK_COLOR_COMPONENT_R_BIT = 0x00000001;
    int VK_COLOR_COMPONENT_G_BIT = 0x00000002;
    int VK_COLOR_COMPONENT_B_BIT = 0x00000004;
    int VK_COLOR_COMPONENT_A_BIT = 0x00000008;
}
