package com.github.bazoocaze.vulkancpu4j.vulkan;

public class VkDebugUtilsMessengerCallbackDataEXT extends VkStructureBase {

    public final String pMessage;

    public VkDebugUtilsMessengerCallbackDataEXT(String message) {
        pMessage = message;
    }

    public static VkDebugUtilsMessengerCallbackDataEXT forMessage(String message) {
        return new VkDebugUtilsMessengerCallbackDataEXT(message);
    }
}
