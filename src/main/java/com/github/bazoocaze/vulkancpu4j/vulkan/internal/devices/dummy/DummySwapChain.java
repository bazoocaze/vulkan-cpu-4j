package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.VkArrayUtil;

import java.util.ArrayList;
import java.util.List;

public class DummySwapChain implements VkSwapchainKHR {

    private final VkSwapchainCreateInfoKHR createInfo;
    private final VkImage[] images;
    private int currentImage;

    public DummySwapChain(VkSwapchainCreateInfoKHR createInfo) {
        this.createInfo = createInfo;
        this.images = createImages(createInfo);
    }

    private VkImage[] createImages(VkSwapchainCreateInfoKHR createInfo) {
        List<VkImage> ret = new ArrayList<>();
        for (int i = 0; i < createInfo.minImageCount; i++) {
            ret.add(new DummyImage(createInfo));
        }
        return ret.toArray(VkImage[]::new);
    }

    @Override
    public VkResult getSwapchainImagesKHR(ByRef<Integer> swapchainImageCount, VkImage[] swapchainImages) {
        return VkArrayUtil.copyArray(images, swapchainImageCount, swapchainImages);
    }

    @Override
    public VkResult acquireNextImageKHR(long timeout, VkSemaphore semaphore,
                                        VkFence fence, OutRef<Integer> imageIndex) {
        currentImage = (currentImage + 1) % images.length;
        imageIndex.set(currentImage);
        return VkResult.VK_SUCCESS;
    }
}
