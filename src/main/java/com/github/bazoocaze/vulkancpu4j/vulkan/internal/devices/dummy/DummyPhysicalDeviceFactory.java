package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkPhysicalDevice;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance.PhysicalDeviceFactory;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance.SoftwareInstance;

public class DummyPhysicalDeviceFactory implements PhysicalDeviceFactory {

    @Override
    public VkPhysicalDevice createPhysicalDevice(SoftwareInstance softwareInstance) {
        return new DummyPhysicalDevice(softwareInstance);
    }
}
