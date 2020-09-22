package com.github.bazoocaze.vulkancpu4j.vulkan;

public class VkInstanceCreateInfo extends VkStructureBase {

    public VkApplicationInfo pApplicationInfo;
    public int enabledExtensionCount;
    public String[] ppEnabledExtensionNames;
    public int enabledLayerCount;
    public String[] ppEnabledLayerNames;
}
