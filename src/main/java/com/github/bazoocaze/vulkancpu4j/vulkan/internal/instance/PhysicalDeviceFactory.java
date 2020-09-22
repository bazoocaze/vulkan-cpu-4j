package com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkPhysicalDevice;

public interface PhysicalDeviceFactory {

    VkPhysicalDevice createPhysicalDevice(SoftwareInstance softwareInstance);
}
