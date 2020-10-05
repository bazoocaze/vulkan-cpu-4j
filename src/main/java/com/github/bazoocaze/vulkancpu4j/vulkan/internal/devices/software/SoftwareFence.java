package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkFence;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkFenceCreateInfo;

public class SoftwareFence implements VkFence {

    private final VkFenceCreateInfo createInfo;

    public SoftwareFence(VkFenceCreateInfo createInfo) {
        this.createInfo = createInfo;
    }

    public void destroy() {
        // TODO: SoftwareFence.destroy
    }
}
