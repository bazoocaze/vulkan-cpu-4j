package com.github.bazoocaze.vulkancpu4j.vulkan.callback;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkDebugUtilsMessengerCallbackDataEXT;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkDebugUtilsMessageSeverityFlagBitsEXT;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkDebugUtilsMessageTypeFlagsEXT;

@FunctionalInterface
public interface PFN_vkDebugUtilsMessengerCallbackEXT {

    VkBool32 run(VkDebugUtilsMessageSeverityFlagBitsEXT messageSeverity,
                 VkDebugUtilsMessageTypeFlagsEXT messageTypes,
                 VkDebugUtilsMessengerCallbackDataEXT callbackData,
                 Object userData);
}
