package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkPipelineLayout;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkPipelineLayoutCreateInfo;

public class DummyPipelineLayout implements VkPipelineLayout {

    private final VkPipelineLayoutCreateInfo createInfo;

    public DummyPipelineLayout(VkPipelineLayoutCreateInfo createInfo) {
        this.createInfo = createInfo;
    }
}
