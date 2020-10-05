package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkShaderModule;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkShaderModuleCreateInfo;

public class SoftwareShaderModule implements VkShaderModule {

    private final SoftwareDevice device;
    private final VkShaderModuleCreateInfo createInfo;

    public SoftwareShaderModule(SoftwareDevice device, VkShaderModuleCreateInfo createInfo) {
        this.device = device;
        this.createInfo = createInfo;
    }
}
