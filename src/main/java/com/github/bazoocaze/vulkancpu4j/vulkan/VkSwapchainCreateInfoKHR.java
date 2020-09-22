package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkExtent2D;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkCompositeAlphaFlagBitsKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkImageUsageFlags;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkSurfaceTransformFlagBitsKHR;

public class VkSwapchainCreateInfoKHR extends VkStructureBase {

    public VkSurfaceKHR surface;
    public int minImageCount;
    public VkFormat imageFormat;
    public VkColorSpaceKHR imageColorSpace;
    public VkExtent2D imageExtent;
    public int imageArrayLayers;
    public VkImageUsageFlags imageUsage;
    public VkSharingMode imageSharingMode;
    public int queueFamilyIndexCount;
    public int[] pQueueFamilyIndices;
    public VkCompositeAlphaFlagBitsKHR compositeAlpha;
    public VkPresentModeKHR presentMode;
    public VkBool32 clipped;
    public VkSurfaceTransformFlagBitsKHR preTransform;
    public VkSwapchainKHR oldSwapchain;
}
