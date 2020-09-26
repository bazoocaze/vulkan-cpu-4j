package com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkExtensionProperties;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkInstanceExtensionNames;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkLayerNames;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkLayerProperties;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.github.bazoocaze.vulkancpu4j.vulkan.VkApiVersion.VK_MAKE_VERSION;

public class SoftwareInstanceSpecs {

    public static final VkLayerProperties[] LAYER_PROPERTIES = {
            new VkLayerProperties(VkLayerNames.VK_LAYER_LUNARG_standard_validation, VK_MAKE_VERSION(1, 2, 141), 2,
                    "LUNARG standard validation"),
            new VkLayerProperties(VkLayerNames.VK_LAYER_KHRONOS_validation, VK_MAKE_VERSION(1, 2, 131), 1,
                    "KHRONOS validation"),
    };

    public static final VkExtensionProperties[] EXTENSION_PROPERTIES = new VkExtensionProperties[]{
            new VkExtensionProperties(25, VkInstanceExtensionNames.VK_KHR_surface),
            new VkExtensionProperties(9, VkInstanceExtensionNames.VK_EXT_debug_report),
            new VkExtensionProperties(2, VkInstanceExtensionNames.VK_EXT_debug_utils),
            new VkExtensionProperties(1, "VK_EMU_java_swing_surface"),
    };

    public static final Map<String, VkLayerProperties> MAP_LAYER_PROPERTIES = mapLayerProperties();

    public static final Map<String, VkExtensionProperties> MAP_EXTENSION_PROPERTIES = mapLayerExtensionProperties();

    private SoftwareInstanceSpecs() {
        // static class
    }

    private static Map<String, VkLayerProperties> mapLayerProperties() {
        return Arrays.stream(LAYER_PROPERTIES)
                .collect(Collectors.toUnmodifiableMap(s -> s.layerName, Function.identity()));
    }

    private static Map<String, VkExtensionProperties> mapLayerExtensionProperties() {
        return Arrays.stream(EXTENSION_PROPERTIES)
                .collect(Collectors.toUnmodifiableMap(s -> s.extensionName, Function.identity()));
    }

    public static boolean validateExtensions(Iterable<String> extensionNames) {
        for (var extension : extensionNames) {
            if (!SoftwareInstanceSpecs.MAP_EXTENSION_PROPERTIES.containsKey(extension)) {
                LoggerFactory.getLogger(SoftwareInstanceSpecs.class).warn("Extension not found: {}", extension);
                return false;
            }
        }
        return true;
    }

    public static boolean validateLayers(Iterable<String> layerNames) {
        for (var layer : layerNames) {
            if (!SoftwareInstanceSpecs.MAP_LAYER_PROPERTIES.containsKey(layer)) {
                LoggerFactory.getLogger(SoftwareInstanceSpecs.class).warn("Layer not found: {}", layer);
                return false;
            }
        }
        return true;
    }
}
