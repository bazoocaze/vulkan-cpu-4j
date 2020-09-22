package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

import org.intellij.lang.annotations.MagicConstant;

@SuppressWarnings("EmptyClass")
@MagicConstant(flagsFromClass = VkCullModeFlagBits.class)
public @interface VkCullModeFlagBits {

    int VK_CULL_MODE_NONE = 0;
    int VK_CULL_MODE_FRONT_BIT = 1;
    int VK_CULL_MODE_BACK_BIT = 2;
    int VK_CULL_MODE_FRONT_AND_BACK = 3;
}
