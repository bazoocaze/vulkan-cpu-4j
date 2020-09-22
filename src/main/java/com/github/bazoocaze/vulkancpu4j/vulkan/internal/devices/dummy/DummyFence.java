package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkFence;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkFenceCreateInfo;

public class DummyFence implements VkFence {

    private final VkFenceCreateInfo createInfo;

    public DummyFence(VkFenceCreateInfo createInfo) {
        this.createInfo = createInfo;
    }
}
