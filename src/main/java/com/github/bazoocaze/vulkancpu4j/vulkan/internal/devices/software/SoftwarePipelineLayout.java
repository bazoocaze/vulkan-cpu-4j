package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkPipelineLayout;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkPipelineLayoutCreateInfo;

public class SoftwarePipelineLayout implements VkPipelineLayout {

    private final SoftwareDevice device;
    private final VkPipelineLayoutCreateInfo createInfo;

    public SoftwarePipelineLayout(SoftwareDevice device, VkPipelineLayoutCreateInfo createInfo) {
        this.device = device;
        this.createInfo = createInfo;
    }

    public void destroy() {
        // TODO: SoftwarePipelineLayout.destroy
    }
}
