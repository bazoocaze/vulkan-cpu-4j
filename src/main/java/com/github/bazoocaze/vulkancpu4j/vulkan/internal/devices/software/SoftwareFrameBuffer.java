package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkFramebuffer;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkFramebufferCreateInfo;

public class SoftwareFrameBuffer implements VkFramebuffer {

    private final VkFramebufferCreateInfo createInfo;

    public SoftwareFrameBuffer(VkFramebufferCreateInfo createInfo) {
        this.createInfo = createInfo;
    }

    public void destroy() {
        // TODO: destroy
    }
}
