package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkPresentModeKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

public interface VkPhysicalDevice {

    VkResult getPhysicalDeviceQueueFamilyProperties(ByRef<Integer> queueFamilyCount,
                                                    VkQueueFamilyProperties[] queueFamilies);

    VkResult getPhysicalDeviceSurfaceSupportKHR(int queueFamilyIndex,
                                                VkSurfaceKHR surface,
                                                OutRef<VkBool32> supported);

    VkResult enumerateDeviceExtensionProperties(String layerName,
                                                ByRef<Integer> propertyCount,
                                                VkExtensionProperties[] properties);

    VkResult getPhysicalDeviceSurfaceCapabilitiesKHR(VkSurfaceKHR surface,
                                                     OutRef<VkSurfaceCapabilitiesKHR> surfaceCapabilities);

    VkResult getPhysicalDeviceSurfaceFormatsKHR(VkSurfaceKHR surface,
                                                ByRef<Integer> surfaceFormatCount,
                                                VkSurfaceFormatKHR[] surfaceFormats);

    VkResult getPhysicalDeviceSurfacePresentModesKHR(VkSurfaceKHR surface,
                                                     ByRef<Integer> presentModeCount,
                                                     VkPresentModeKHR[] presentModes);

    VkResult createDevice(VkDeviceCreateInfo createInfo,
                          VkAllocationCallbacks allocator,
                          OutRef<VkDevice> device);
}
