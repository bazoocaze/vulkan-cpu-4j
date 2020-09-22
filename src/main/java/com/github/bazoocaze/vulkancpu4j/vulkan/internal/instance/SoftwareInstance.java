package com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.VkArrayUtil;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.VulkanContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.github.bazoocaze.vulkancpu4j.vulkan.VkApiVersion.VK_MAKE_VERSION;

public class SoftwareInstance implements VkInstance {

    private static final VkLayerProperties[] LAYER_PROPERTIES = {
            new VkLayerProperties(VkLayerNames.VK_LAYER_LUNARG_standard_validation, VK_MAKE_VERSION(1, 2, 141), 2,
                    "LUNARG standard validation"),
            new VkLayerProperties(VkLayerNames.VK_LAYER_KHRONOS_validation, VK_MAKE_VERSION(1, 2, 131), 1,
                    "KHRONOS validation"),
    };

    private static final VkExtensionProperties[] EXTENSION_PROPERTIES = new VkExtensionProperties[]{
            new VkExtensionProperties(25, VkInstanceExtensionNames.VK_KHR_surface),
            new VkExtensionProperties(9, VkInstanceExtensionNames.VK_EXT_debug_report),
            new VkExtensionProperties(2, VkInstanceExtensionNames.VK_EXT_debug_utils),
            new VkExtensionProperties(1, "VK_EMU_java_swing_surface"),
    };

    private static final Map<String, VkLayerProperties> MAP_LAYER_PROPERTIES = mapLayerProperties();
    private static final Map<String, VkExtensionProperties> MAP_EXTENSION_PROPERTIES = mapLayerExtensionProperties();

    private final VkInstanceCreateInfo createInfo;
    private final List<SoftwareDebugUtilsMessenger> debugMessengers = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(SoftwareInstance.class);

    private SoftwareInstance(VkInstanceCreateInfo createInfo) {
        this.createInfo = createInfo;
    }

    private static Map<String, VkLayerProperties> mapLayerProperties() {
        return Arrays.stream(LAYER_PROPERTIES).collect(Collectors.toMap(s -> s.layerName, Function.identity()));
    }

    private static Map<String, VkExtensionProperties> mapLayerExtensionProperties() {
        return Arrays.stream(EXTENSION_PROPERTIES).collect(Collectors.toMap(s -> s.extensionName, Function.identity()));
    }


    @Override
    public VkResult createDebugUtilsMessengerEXT(VkDebugUtilsMessengerCreateInfoEXT createInfo,
                                                 VkAllocationCallbacks pAllocator,
                                                 OutRef<VkDebugUtilsMessengerEXT> debugMessenger) {
        debugMessengers.add(new SoftwareDebugUtilsMessenger(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createJavaSwingSurfaceEMU(VkJavaSwingSurfaceCreateInfoEMU createInfo, VkAllocationCallbacks allocator,
                                              OutRef<VkSurfaceKHR> surface) {
        return SoftwareSurfaceFactory.create(this, createInfo, surface);
    }

    @Override
    public VkResult enumeratePhysicalDevices(ByRef<Integer> physicalDeviceCount, VkPhysicalDevice[] physicalDevices) {
        VkPhysicalDevice[] devices = getPhysicalDevices();
        return VkArrayUtil.copyArray(devices, physicalDeviceCount, physicalDevices);
    }

    @Override
    public void destroySurfaceKHR(VkSurfaceKHR surface, VkAllocationCallbacks allocator) {
        surface.destroySurface();
    }

    @Override
    public void destroyDebugUtilsMessengerEXT(VkDebugUtilsMessengerEXT messenger, VkAllocationCallbacks allocator) {
        debugMessengers.remove((SoftwareDebugUtilsMessenger) messenger);
    }

    @Override
    public void destroyInstance() {
        // destroy instance
    }

    private VkPhysicalDevice[] getPhysicalDevices() {
        return getPhysicalDeviceFactories()
                .stream()
                .map(physicalDeviceFactory -> physicalDeviceFactory.createPhysicalDevice(this))
                .toArray(VkPhysicalDevice[]::new);
    }

    private Set<PhysicalDeviceFactory> getPhysicalDeviceFactories() {
        return createPhysicalDevices(VulkanContext.DEFAULT.physicalDeviceFactoryClasses());
    }

    public <T> Set<T> createPhysicalDevices(Collection<Class<? extends T>> classDefinitions) {
        Set<T> ret = new HashSet<>();
        for (var definition : classDefinitions) {
            createPhysicalDeviceInstance(definition).ifPresent(ret::add);
        }
        return ret;
    }

    private <T> Optional<T> createPhysicalDeviceInstance(Class<? extends T> definition) {
        try {
            return Optional.of(definition.getDeclaredConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException ex) {
            logger.error("Failed to create instance of physical device [{}]: {}",
                    definition.getSimpleName(), ex.getMessage());
            return Optional.empty();
        }
    }

    public static class SoftwareInstanceFactory implements InstanceFactory {

        private final Logger logger = LoggerFactory.getLogger(SoftwareInstanceFactory.class);

        @Override
        public VkResult create(VkInstanceCreateInfo createInfo, OutRef<VkInstance> instance) {
            if (!validateLayers(createInfo)) {
                logger.error("Failed to create instance: VK_ERROR_LAYER_NOT_PRESENT");
                return VkResult.VK_ERROR_LAYER_NOT_PRESENT;
            }
            if (!validateExtensions(createInfo)) {
                logger.error("Failed to create instance: VK_ERROR_EXTENSION_NOT_PRESENT");
                return VkResult.VK_ERROR_EXTENSION_NOT_PRESENT;
            }
            instance.set(new SoftwareInstance(createInfo));
            return VkResult.VK_SUCCESS;
        }

        private static boolean validateExtensions(VkInstanceCreateInfo createInfo) {
            for (var extension : createInfo.ppEnabledExtensionNames) {
                if (!MAP_EXTENSION_PROPERTIES.containsKey(extension)) {
                    LoggerFactory.getLogger(SoftwareInstance.class).warn("Extension not found: {}", extension);
                    return false;
                }
            }
            return true;
        }

        private static boolean validateLayers(VkInstanceCreateInfo createInfo) {
            for (var layer : createInfo.ppEnabledLayerNames) {
                if (!MAP_LAYER_PROPERTIES.containsKey(layer)) {
                    LoggerFactory.getLogger(SoftwareInstance.class).warn("Layer not found: {}", layer);
                    return false;
                }
            }
            return true;
        }
    }
}
