package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkExtent2D;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkCompositeAlphaFlagsKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkImageUsageFlags;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkSurfaceTransformFlagBitsKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkSurfaceTransformFlagsKHR;

public class VkSurfaceCapabilitiesKHR {

    public final int minImageCount;
    public final int maxImageCount;
    public VkExtent2D currentExtent;
    public VkExtent2D minImageExtent;
    public VkExtent2D maxImageExtent;
    public final int maxImageArrayLayers;
    public final VkSurfaceTransformFlagsKHR supportedTransforms;
    public final VkSurfaceTransformFlagBitsKHR currentTransform;
    public final VkCompositeAlphaFlagsKHR supportedCompositeAlpha;
    public final VkImageUsageFlags supportedUsageFlags;

    public VkSurfaceCapabilitiesKHR(int minImageCount, int maxImageCount, VkExtent2D currentExtent,
                                    VkExtent2D minImageExtent, VkExtent2D maxImageExtent, int maxImageArrayLayers,
                                    VkSurfaceTransformFlagsKHR supportedTransforms,
                                    VkSurfaceTransformFlagBitsKHR currentTransform,
                                    VkCompositeAlphaFlagsKHR supportedCompositeAlpha,
                                    VkImageUsageFlags supportedUsageFlags) {
        this.minImageCount = minImageCount;
        this.maxImageCount = maxImageCount;
        this.currentExtent = currentExtent;
        this.minImageExtent = minImageExtent;
        this.maxImageExtent = maxImageExtent;
        this.maxImageArrayLayers = maxImageArrayLayers;
        this.supportedTransforms = supportedTransforms;
        this.currentTransform = currentTransform;
        this.supportedCompositeAlpha = supportedCompositeAlpha;
        this.supportedUsageFlags = supportedUsageFlags;
    }

    public VkSurfaceCapabilitiesKHR(VkSurfaceCapabilitiesKHR capabilities) {
        this.minImageCount = capabilities.minImageCount;
        this.maxImageCount = capabilities.maxImageCount;
        this.currentExtent = capabilities.currentExtent;
        this.minImageExtent = capabilities.minImageExtent;
        this.maxImageExtent = capabilities.maxImageExtent;
        this.maxImageArrayLayers = capabilities.maxImageArrayLayers;
        this.supportedTransforms = capabilities.supportedTransforms;
        this.currentTransform = capabilities.currentTransform;
        this.supportedCompositeAlpha = capabilities.supportedCompositeAlpha;
        this.supportedUsageFlags = capabilities.supportedUsageFlags;
    }
}
