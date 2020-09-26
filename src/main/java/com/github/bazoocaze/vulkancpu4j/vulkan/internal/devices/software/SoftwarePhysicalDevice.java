package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkPresentModeKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.VkArrayUtil;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance.SoftwareInstance;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.surface.SoftwareJavaSwingSurface;

public class SoftwarePhysicalDevice implements VkPhysicalDevice {

    private final SoftwareInstance softwareInstance;

    public SoftwarePhysicalDevice(SoftwareInstance softwareInstance) {
        this.softwareInstance = softwareInstance;
    }

    @Override
    public VkResult getPhysicalDeviceQueueFamilyProperties(ByRef<Integer> queueFamilyCount,
                                                           VkQueueFamilyProperties[] queueFamilies) {
        return VkArrayUtil.copyArray(SoftwareDeviceSpecs.QUEUE_FAMILY_PROPERTIES, queueFamilyCount, queueFamilies);
    }

    @Override
    public VkResult getPhysicalDeviceSurfaceSupportKHR(int queueFamilyIndex, VkSurfaceKHR surface,
                                                       OutRef<VkBool32> supported) {
        supported.set(VkBool32.from(surface instanceof SoftwareJavaSwingSurface));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult enumerateDeviceExtensionProperties(String layerName, ByRef<Integer> propertyCount,
                                                       VkExtensionProperties[] properties) {
        return VkArrayUtil.copyArray(SoftwareDeviceSpecs.EXTENSION_PROPERTIES, propertyCount, properties);
    }

    @Override
    public VkResult getPhysicalDeviceSurfaceCapabilitiesKHR(VkSurfaceKHR surface,
                                                            OutRef<VkSurfaceCapabilitiesKHR> surfaceCapabilities) {
        return surface.getCapabilities(surfaceCapabilities);
    }

    @Override
    public VkResult getPhysicalDeviceSurfaceFormatsKHR(VkSurfaceKHR surface, ByRef<Integer> surfaceFormatCount,
                                                       VkSurfaceFormatKHR[] surfaceFormats) {
        return surface.getSurfaceFormats(surfaceFormatCount, surfaceFormats);
    }

    @Override
    public VkResult getPhysicalDeviceSurfacePresentModesKHR(VkSurfaceKHR surface,
                                                            ByRef<Integer> presentModeCount,
                                                            VkPresentModeKHR[] presentModes) {
        return surface.getPresentModes(presentModeCount, presentModes);
    }

    @Override
    public VkResult createDevice(VkDeviceCreateInfo createInfo, VkAllocationCallbacks allocator,
                                 OutRef<VkDevice> outputDevice) {
        return SoftwareDevice.create(createInfo, outputDevice);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
