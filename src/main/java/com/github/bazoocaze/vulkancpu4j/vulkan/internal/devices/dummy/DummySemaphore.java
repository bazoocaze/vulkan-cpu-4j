package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkSemaphore;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkSemaphoreCreateInfo;

public class DummySemaphore implements VkSemaphore {

    private final VkSemaphoreCreateInfo createInfo;

    public DummySemaphore(VkSemaphoreCreateInfo createInfo) {
        this.createInfo = createInfo;
    }
}
