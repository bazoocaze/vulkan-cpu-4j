package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.callback.PFN_vkDebugUtilsMessengerCallbackEXT;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkDebugUtilsMessageSeverityFlagsEXT;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkDebugUtilsMessageTypeFlagsEXT;

public class VkDebugUtilsMessengerCreateInfoEXT extends VkStructureBase {

    public VkDebugUtilsMessageSeverityFlagsEXT messageSeverity;
    public VkDebugUtilsMessageTypeFlagsEXT messageType;
    public PFN_vkDebugUtilsMessengerCallbackEXT pfnUserCallback;
    public Object pUserData;
}
