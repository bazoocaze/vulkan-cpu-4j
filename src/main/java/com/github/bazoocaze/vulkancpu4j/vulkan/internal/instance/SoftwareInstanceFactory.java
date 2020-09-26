package com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkInstance;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkInstanceCreateInfo;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkLayerProperties;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.VkArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class SoftwareInstanceFactory implements InstanceFactory {

    private final Logger logger = LoggerFactory.getLogger(SoftwareInstanceFactory.class);

    @Override
    public VkResult create(VkInstanceCreateInfo createInfo, OutRef<VkInstance> instance) {
        if (!SoftwareInstanceSpecs.validateLayers(Arrays.asList(createInfo.ppEnabledLayerNames))) {
            logger.error("Failed to create instance: VK_ERROR_LAYER_NOT_PRESENT");
            return VkResult.VK_ERROR_LAYER_NOT_PRESENT;
        }
        if (!SoftwareInstanceSpecs.validateExtensions(Arrays.asList(createInfo.ppEnabledExtensionNames))) {
            logger.error("Failed to create instance: VK_ERROR_EXTENSION_NOT_PRESENT");
            return VkResult.VK_ERROR_EXTENSION_NOT_PRESENT;
        }
        instance.set(new SoftwareInstance(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult enumerateInstanceLayerProperties(ByRef<Integer> propertyCount, VkLayerProperties[] properties) {
        return VkArrayUtil.copyArray(SoftwareInstanceSpecs.LAYER_PROPERTIES, propertyCount, properties);
    }
}
