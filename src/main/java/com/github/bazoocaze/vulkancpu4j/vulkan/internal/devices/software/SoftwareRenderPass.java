package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkRenderPass;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkRenderPassCreateInfo;

public class SoftwareRenderPass implements VkRenderPass {

    private final SoftwareDevice device;
    private final VkRenderPassCreateInfo createInfo;

    public SoftwareRenderPass(SoftwareDevice device, VkRenderPassCreateInfo createInfo) {
        this.device = device;
        this.createInfo = createInfo;
    }
}
