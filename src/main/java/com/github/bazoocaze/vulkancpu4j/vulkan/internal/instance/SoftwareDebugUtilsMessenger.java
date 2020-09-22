package com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkDebugUtilsMessengerCreateInfoEXT;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkDebugUtilsMessengerEXT;

public class SoftwareDebugUtilsMessenger implements VkDebugUtilsMessengerEXT {

    private final VkDebugUtilsMessengerCreateInfoEXT createInfo;

    public SoftwareDebugUtilsMessenger(VkDebugUtilsMessengerCreateInfoEXT createInfo) {
        this.createInfo = createInfo;
    }

    public VkDebugUtilsMessengerCreateInfoEXT createInfo() {
        return createInfo;
    }
}
