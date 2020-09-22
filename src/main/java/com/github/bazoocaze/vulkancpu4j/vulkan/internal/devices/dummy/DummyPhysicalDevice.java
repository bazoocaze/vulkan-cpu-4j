package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkExtent3D;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkPresentModeKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkQueueFlags;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.VkArrayUtil;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance.SoftwareInstance;

import static com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkQueueFlagBits.*;

public class DummyPhysicalDevice implements VkPhysicalDevice {

    private static final VkQueueFamilyProperties[] QUEUE_FAMILY_PROPERTIES = new VkQueueFamilyProperties[]{
            VkQueueFamilyProperties.create(
                    VkQueueFlags.of(VK_QUEUE_GRAPHICS_BIT, VK_QUEUE_COMPUTE_BIT,
                            VK_QUEUE_TRANSFER_BIT, VK_QUEUE_SPARSE_BINDING_BIT),
                    1, 64, VkExtent3D.create(1, 1, 1)),
            VkQueueFamilyProperties.create(
                    VkQueueFlags.of(VK_QUEUE_TRANSFER_BIT, VK_QUEUE_SPARSE_BINDING_BIT),
                    2, 64, VkExtent3D.create(1, 1, 1)),
    };

    private static final VkExtensionProperties[] EXTENSION_PROPERTIES = new VkExtensionProperties[]{
            new VkExtensionProperties(70, VkExtensionNames.VK_KHR_SWAPCHAIN_EXTENSION_NAME),
    };

    private final SoftwareInstance softwareInstance;

    public DummyPhysicalDevice(SoftwareInstance softwareInstance) {
        this.softwareInstance = softwareInstance;
    }

    @Override
    public VkResult getPhysicalDeviceQueueFamilyProperties(ByRef<Integer> queueFamilyCount,
                                                           VkQueueFamilyProperties[] queueFamilies) {
        return VkArrayUtil.copyArray(QUEUE_FAMILY_PROPERTIES, queueFamilyCount, queueFamilies);
    }

    @Override
    public VkResult getPhysicalDeviceSurfaceSupportKHR(int queueFamilyIndex, VkSurfaceKHR surface,
                                                       OutRef<VkBool32> supported) {
        supported.set(VkBool32.VK_TRUE);
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult enumerateDeviceExtensionProperties(String layerName,
                                                       ByRef<Integer> propertyCount,
                                                       VkExtensionProperties[] properties) {
        if (layerName != null) {
            return VkResult.VK_ERROR_LAYER_NOT_PRESENT;
        }
        return VkArrayUtil.copyArray(EXTENSION_PROPERTIES, propertyCount, properties);
    }

    @Override
    public VkResult getPhysicalDeviceSurfaceCapabilitiesKHR(VkSurfaceKHR surface,
                                                            OutRef<VkSurfaceCapabilitiesKHR> surfaceCapabilities) {
        return surface.getCapabilities(surfaceCapabilities);
    }

    @Override
    public VkResult getPhysicalDeviceSurfaceFormatsKHR(VkSurfaceKHR surface, ByRef<Integer> surfaceFormatCount, VkSurfaceFormatKHR[] surfaceFormats) {
        return surface.getSurfaceFormats(surfaceFormatCount, surfaceFormats);
    }

    @Override
    public VkResult getPhysicalDeviceSurfacePresentModesKHR(VkSurfaceKHR surface, ByRef<Integer> presentModeCount, VkPresentModeKHR[] presentModes) {
        return surface.getPresentModes(presentModeCount, presentModes);
    }

    @Override
    public VkResult createDevice(VkDeviceCreateInfo createInfo,
                                 VkAllocationCallbacks allocator,
                                 OutRef<VkDevice> device) {
        device.set(new DummyDevice(this, createInfo));
        return VkResult.VK_SUCCESS;
    }
}
