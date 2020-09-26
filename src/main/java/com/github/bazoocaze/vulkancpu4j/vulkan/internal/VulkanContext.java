package com.github.bazoocaze.vulkancpu4j.vulkan.internal;

import com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy.DummyPhysicalDeviceFactory;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software.SoftwarePhysicalDeviceFactory;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance.InstanceFactory;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance.PhysicalDeviceFactory;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance.SoftwareInstanceFactory;
import org.jetbrains.annotations.ApiStatus.Internal;

import java.util.ArrayList;
import java.util.List;

@Internal
public class VulkanContext {

    public static final VulkanContext DEFAULT = new VulkanContext();

    private final List<Class<? extends PhysicalDeviceFactory>> physicalDeviceFactoryClasses = new ArrayList<>();
    private InstanceFactory instanceFactory;

    private VulkanContext() {
        registerPhysicalDeviceFactory(SoftwarePhysicalDeviceFactory.class);
        registerPhysicalDeviceFactory(DummyPhysicalDeviceFactory.class);
        registerInstanceFactory(new SoftwareInstanceFactory());
    }

    private void registerInstanceFactory(InstanceFactory instanceFactory) {
        this.instanceFactory = instanceFactory;
    }

    public <T extends PhysicalDeviceFactory> void registerPhysicalDeviceFactory(Class<T> targetFactory) {
        physicalDeviceFactoryClasses.add(targetFactory);
    }

    public List<Class<? extends PhysicalDeviceFactory>> physicalDeviceFactoryClasses() {
        return physicalDeviceFactoryClasses;
    }

    public InstanceFactory instanceFactory() {
        return instanceFactory;
    }
}