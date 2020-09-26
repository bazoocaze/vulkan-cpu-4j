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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class SoftwareInstance implements VkInstance {


    private final VkInstanceCreateInfo createInfo;
    private final List<SoftwareDebugUtilsMessenger> debugMessengers = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(SoftwareInstance.class);

    public SoftwareInstance(VkInstanceCreateInfo createInfo) {
        this.createInfo = createInfo;
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

    private List<PhysicalDeviceFactory> getPhysicalDeviceFactories() {
        return createPhysicalDevices(VulkanContext.DEFAULT.physicalDeviceFactoryClasses());
    }

    public <T> List<T> createPhysicalDevices(Collection<Class<? extends T>> classDefinitions) {
        List<T> ret = new ArrayList<>();
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

}
