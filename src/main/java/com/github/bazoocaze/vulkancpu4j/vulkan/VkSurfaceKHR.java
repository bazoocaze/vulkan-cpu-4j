package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkExtent2D;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkPresentModeKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

public interface VkSurfaceKHR {

    VkResult presentImage(VkImage image, VkExtent2D imageExtent);

    VkResult getCapabilities(OutRef<VkSurfaceCapabilitiesKHR> surfaceCapabilities);

    VkResult getSurfaceFormats(ByRef<Integer> surfaceFormatCount, VkSurfaceFormatKHR[] surfaceFormats);

    VkResult getPresentModes(ByRef<Integer> presentModeCount, VkPresentModeKHR[] presentModes);

    void destroySurface();
}
