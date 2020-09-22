package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkCommandPool;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkCommandPoolCreateInfo;

public class DummyCommandPool implements VkCommandPool {

    private final VkCommandPoolCreateInfo createInfo;

    public DummyCommandPool(VkCommandPoolCreateInfo createInfo) {
        this.createInfo = createInfo;
    }
}
