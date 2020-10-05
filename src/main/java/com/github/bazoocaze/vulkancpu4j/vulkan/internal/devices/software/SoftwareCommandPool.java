package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.VkCommandBuffer;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkCommandBufferAllocateInfo;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkCommandPool;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkCommandPoolCreateInfo;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

import java.util.ArrayList;
import java.util.List;

public class SoftwareCommandPool implements VkCommandPool {

    private final VkCommandPoolCreateInfo createInfo;
    private final List<SoftwareCommandBuffer> commandBuffers;

    public SoftwareCommandPool(VkCommandPoolCreateInfo createInfo) {
        this.createInfo = createInfo;
        this.commandBuffers = new ArrayList<>();
    }

    public void destroy() {
        // TODO: SoftwareCommandPool.destroy
    }

    public VkResult allocateCommandBuffers(VkCommandBufferAllocateInfo allocateInfo,
                                           VkCommandBuffer[] commandBuffers) {
        for (int i = 0; i < allocateInfo.commandBufferCount; i++) {
            SoftwareCommandBuffer buffer = new SoftwareCommandBuffer(allocateInfo);
            commandBuffers[i] = buffer;
            this.commandBuffers.add(buffer);
        }
        return VkResult.VK_SUCCESS;

    }
}
