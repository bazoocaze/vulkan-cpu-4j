package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkImage;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkSwapchainCreateInfoKHR;

public class DummyImage implements VkImage {

    private final VkSwapchainCreateInfoKHR swapchainCreateInfoKHR;

    public DummyImage(VkSwapchainCreateInfoKHR swapchainCreateInfoKHR) {
        this.swapchainCreateInfoKHR = swapchainCreateInfoKHR;
    }
}
