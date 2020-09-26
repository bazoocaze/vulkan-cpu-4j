package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.VkArrayUtil;

import java.util.ArrayList;
import java.util.List;

public class SoftwareSwapchain implements VkSwapchainKHR {

    private final SoftwareDevice device;
    private final VkSwapchainCreateInfoKHR createInfo;
    private final List<SoftwareImage> images;

    public SoftwareSwapchain(SoftwareDevice device, VkSwapchainCreateInfoKHR createInfo) {
        this.device = device;
        this.createInfo = createInfo;
        this.images = createImages(device, createInfo);
    }

    private List<SoftwareImage> createImages(SoftwareDevice device, VkSwapchainCreateInfoKHR createInfo) {
        final List<SoftwareImage> ret = new ArrayList<>();
        for (int i = 0; i < createInfo.minImageCount; i++) {
            VkResult result = SoftwareImage.createSwapchainImage(device, createInfo,
                    image -> ret.add((SoftwareImage) image));
            if (result != VkResult.VK_SUCCESS) {
                throw new UnsupportedOperationException(String.format("Failed to create image: %s", result));
            }
        }
        return ret;
    }

    public static VkResult create(SoftwareDevice device, VkSwapchainCreateInfoKHR createInfo,
                                  VkAllocationCallbacks allocator,
                                  OutRef<VkSwapchainKHR> swapChain) {
        swapChain.set(new SoftwareSwapchain(device, createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult getSwapchainImagesKHR(ByRef<Integer> swapchainImageCount, VkImage[] swapchainImages) {
        return VkArrayUtil.copyArray(images, swapchainImageCount, swapchainImages);
    }

    @Override
    public VkResult acquireNextImageKHR(long timeout, VkSemaphore semaphore, VkFence fence, OutRef<Integer> imageIndex) {
        return null;
    }
}
