package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkPhysicalDevice;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance.PhysicalDeviceFactory;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance.SoftwareInstance;

public class SoftwarePhysicalDeviceFactory implements PhysicalDeviceFactory {

    @Override
    public VkPhysicalDevice createPhysicalDevice(SoftwareInstance softwareInstance) {
        return new SoftwarePhysicalDevice(softwareInstance);
    }
}
