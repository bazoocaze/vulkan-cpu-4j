package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkRenderPass;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkRenderPassCreateInfo;

public class DummyRenderPass implements VkRenderPass {

    private final VkRenderPassCreateInfo createInfo;

    public DummyRenderPass(VkRenderPassCreateInfo createInfo) {
        this.createInfo = createInfo;
    }
}
