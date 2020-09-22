package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface VkInstance {

    @Internal
    VkResult createDebugUtilsMessengerEXT(VkDebugUtilsMessengerCreateInfoEXT createInfo,
                                          VkAllocationCallbacks pAllocator,
                                          OutRef<VkDebugUtilsMessengerEXT> debugMessenger);

    @Internal
    VkResult createJavaSwingSurfaceEMU(VkJavaSwingSurfaceCreateInfoEMU createInfo,
                                       VkAllocationCallbacks allocator,
                                       OutRef<VkSurfaceKHR> surface);

    @Internal
    VkResult enumeratePhysicalDevices(ByRef<Integer> physicalDeviceCount, VkPhysicalDevice[] physicalDevices);

    @Internal
    void destroySurfaceKHR(VkSurfaceKHR surface, VkAllocationCallbacks allocator);

    @Internal
    void destroyDebugUtilsMessengerEXT(VkDebugUtilsMessengerEXT messenger, VkAllocationCallbacks allocator);

    @Internal
    void destroyInstance();
}
