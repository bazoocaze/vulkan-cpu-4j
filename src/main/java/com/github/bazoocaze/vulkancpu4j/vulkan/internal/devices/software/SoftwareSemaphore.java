package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkSemaphore;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkSemaphoreCreateInfo;

public class SoftwareSemaphore implements VkSemaphore {

    private final VkSemaphoreCreateInfo createInfo;

    public SoftwareSemaphore(VkSemaphoreCreateInfo createInfo) {
        this.createInfo = createInfo;
    }

    public void destroy() {
        // TODO: SoftwareSemaphore.destroy
    }
}
