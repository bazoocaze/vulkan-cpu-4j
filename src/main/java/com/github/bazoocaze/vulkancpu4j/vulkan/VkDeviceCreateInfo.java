package com.github.bazoocaze.vulkancpu4j.vulkan;

public class VkDeviceCreateInfo extends VkStructureBase {

    public int queueCreateInfoCount;
    public VkDeviceQueueCreateInfo[] pQueueCreateInfos;
    public VkPhysicalDeviceFeatures pEnabledFeatures;
    public int enabledExtensionCount;
    public String[] ppEnabledExtensionNames;
    public int enabledLayerCount;
    public String[] ppEnabledLayerNames;
}
