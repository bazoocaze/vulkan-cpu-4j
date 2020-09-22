package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkShaderModule;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkShaderModuleCreateInfo;

public class DummyShaderModule implements VkShaderModule {

    private final VkShaderModuleCreateInfo createInfo;

    public DummyShaderModule(VkShaderModuleCreateInfo createInfo) {
        this.createInfo = createInfo;
    }
}
