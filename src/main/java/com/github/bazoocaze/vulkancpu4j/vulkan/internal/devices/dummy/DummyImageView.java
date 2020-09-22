package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkImageView;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkImageViewCreateInfo;

public class DummyImageView implements VkImageView {

    private final VkImageViewCreateInfo createInfo;

    public DummyImageView(VkImageViewCreateInfo createInfo) {
        this.createInfo = createInfo;
    }
}
