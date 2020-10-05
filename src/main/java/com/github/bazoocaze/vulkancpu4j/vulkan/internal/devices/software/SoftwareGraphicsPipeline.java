package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkGraphicsPipelineCreateInfo;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkPipeline;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

public class SoftwareGraphicsPipeline extends SoftwarePipeline {

    private final SoftwareDevice device;
    private final VkGraphicsPipelineCreateInfo createInfo;

    public SoftwareGraphicsPipeline(SoftwareDevice device, VkGraphicsPipelineCreateInfo createInfo) {
        this.device = device;
        this.createInfo = createInfo;
    }

    public static VkResult create(
            SoftwareDevice device,
            VkGraphicsPipelineCreateInfo createInfo,
            OutRef<VkPipeline> pipeline) {
        pipeline.set(new SoftwareGraphicsPipeline(device, createInfo));
        return VkResult.VK_SUCCESS;
    }
}
