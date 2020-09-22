package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkGraphicsPipelineCreateInfo;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkPipeline;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkPipelineCache;

public class DummyGraphicPipeline implements VkPipeline {

    private final VkGraphicsPipelineCreateInfo createInfo;
    private final VkPipelineCache pipelineCache;

    public DummyGraphicPipeline(VkGraphicsPipelineCreateInfo createInfo, VkPipelineCache pipelineCache) {
        this.createInfo = createInfo;
        this.pipelineCache = pipelineCache;
    }
}
