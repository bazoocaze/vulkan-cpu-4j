package com.github.bazoocaze.vulkancpu4j.vulkan.internal;

import com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy.DummyPhysicalDeviceFactory;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance.InstanceFactory;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance.PhysicalDeviceFactory;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance.SoftwareInstance;

import java.util.HashSet;
import java.util.Set;

public class VulkanContext {

    public static final VulkanContext DEFAULT = new VulkanContext();

    private final Set<Class<? extends PhysicalDeviceFactory>> physicalDeviceFactoryClasses = new HashSet<>();
    private InstanceFactory instanceFactory;

    private VulkanContext() {
        registerPhysicalDeviceFactory(DummyPhysicalDeviceFactory.class);
        registerInstanceFactory(new SoftwareInstance.SoftwareInstanceFactory());
    }

    private void registerInstanceFactory(InstanceFactory instanceFactory) {
        this.instanceFactory = instanceFactory;
    }

    public <T extends PhysicalDeviceFactory> void registerPhysicalDeviceFactory(Class<T> targetFactory) {
        physicalDeviceFactoryClasses.add(targetFactory);
    }

    public Set<Class<? extends PhysicalDeviceFactory>> physicalDeviceFactoryClasses() {
        return physicalDeviceFactoryClasses;
    }

    public InstanceFactory instanceFactory() {
        return instanceFactory;
    }
}