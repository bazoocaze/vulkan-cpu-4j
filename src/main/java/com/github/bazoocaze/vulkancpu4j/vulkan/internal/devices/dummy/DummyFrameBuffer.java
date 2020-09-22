package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkFramebuffer;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkFramebufferCreateInfo;

public class DummyFrameBuffer implements VkFramebuffer {

    private final VkFramebufferCreateInfo createInfo;

    public DummyFrameBuffer(VkFramebufferCreateInfo createInfo) {
        this.createInfo = createInfo;
    }
}
