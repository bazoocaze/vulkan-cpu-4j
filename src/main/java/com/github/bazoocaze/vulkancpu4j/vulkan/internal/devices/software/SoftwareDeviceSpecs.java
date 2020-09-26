package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkExtensionNames;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkExtensionProperties;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkQueueFamilyProperties;
import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkExtent3D;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkQueueFlags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkQueueFlagBits.*;

public class SoftwareDeviceSpecs {

    public static final VkQueueFamilyProperties[] QUEUE_FAMILY_PROPERTIES = new VkQueueFamilyProperties[]{
            VkQueueFamilyProperties.create(
                    VkQueueFlags.of(VK_QUEUE_GRAPHICS_BIT, VK_QUEUE_COMPUTE_BIT,
                            VK_QUEUE_TRANSFER_BIT, VK_QUEUE_SPARSE_BINDING_BIT),
                    1, 64, VkExtent3D.create(1, 1, 1)),
            VkQueueFamilyProperties.create(
                    VkQueueFlags.of(VK_QUEUE_TRANSFER_BIT, VK_QUEUE_SPARSE_BINDING_BIT),
                    1, 64, VkExtent3D.create(1, 1, 1)),
    };

    public static final VkExtensionProperties[] EXTENSION_PROPERTIES = new VkExtensionProperties[]{
            new VkExtensionProperties(70, VkExtensionNames.VK_KHR_SWAPCHAIN_EXTENSION_NAME),
    };

    private static final Logger logger = LoggerFactory.getLogger(SoftwareDeviceSpecs.class);
    private static final Map<String, VkExtensionProperties> MAP_EXTENSION_PROPERTIES = mapExtensionProperties();

    private static Map<String, VkExtensionProperties> mapExtensionProperties() {
        return Arrays
                .stream(EXTENSION_PROPERTIES)
                .collect(Collectors
                        .toMap(VkExtensionProperties::getExtensionName, Function.identity()));
    }

    public static boolean validateExtensions(Iterable<String> extensionNames) {
        for (var extensionName : extensionNames) {
            if (!MAP_EXTENSION_PROPERTIES.containsKey(extensionName)) {
                logger.warn("Extension not found: {}", extensionName);
                return false;
            }
        }
        return true;
    }
}
