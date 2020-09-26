package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkImageView;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkImageViewCreateInfo;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

public class SoftwareImageView implements VkImageView {

    private final SoftwareDevice device;
    private final VkImageViewCreateInfo createInfo;
    private final SoftwareImage image;

    public SoftwareImageView(SoftwareDevice device, VkImageViewCreateInfo createInfo) {
        this.device = device;
        this.createInfo = createInfo;
        this.image = (SoftwareImage) createInfo.image;
    }

    public static VkResult createImageView(SoftwareDevice device,
                                           VkImageViewCreateInfo createInfo,
                                           OutRef<VkImageView> view) {
        view.set(new SoftwareImageView(device, createInfo));
        return VkResult.VK_SUCCESS;
    }
}
