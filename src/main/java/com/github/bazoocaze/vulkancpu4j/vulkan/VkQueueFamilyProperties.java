package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkExtent3D;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkQueueFlags;

public class VkQueueFamilyProperties {

    public final VkQueueFlags queueFlags;
    public final int queueCount;
    public final int timestampValidBits;
    public final VkExtent3D minImageTransferGranularity;

    public VkQueueFamilyProperties(VkQueueFlags queueFlags, int queueCount, int timestampValidBits,
                                   VkExtent3D minImageTransferGranularity) {
        this.queueFlags = queueFlags;
        this.queueCount = queueCount;
        this.timestampValidBits = timestampValidBits;
        this.minImageTransferGranularity = minImageTransferGranularity;
    }

    public static VkQueueFamilyProperties create(VkQueueFlags queueFlags, int queueCount, int timestampValidBits,
                                                 VkExtent3D minImageTransferGranularity) {
        return new VkQueueFamilyProperties(queueFlags, queueCount, timestampValidBits, minImageTransferGranularity);
    }
}
