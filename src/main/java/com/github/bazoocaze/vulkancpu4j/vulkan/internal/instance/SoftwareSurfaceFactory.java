package com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance;

import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkJavaSwingSurfaceCreateInfoEMU;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkSurfaceKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.surface.SoftwareJavaSwingSurface;

public class SoftwareSurfaceFactory {

    public static VkResult create(SoftwareInstance instance,
                                  VkJavaSwingSurfaceCreateInfoEMU createInfo,
                                  OutRef<VkSurfaceKHR> surface) {
        surface.set(new SoftwareJavaSwingSurface(instance, createInfo));
        return VkResult.VK_SUCCESS;
    }
}
