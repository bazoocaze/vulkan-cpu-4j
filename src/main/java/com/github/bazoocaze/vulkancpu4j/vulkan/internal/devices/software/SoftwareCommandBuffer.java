package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkSubpassContents;

public class SoftwareCommandBuffer implements VkCommandBuffer {

    private final VkCommandBufferAllocateInfo allocateInfo;

    public SoftwareCommandBuffer(VkCommandBufferAllocateInfo allocateInfo) {
        this.allocateInfo = allocateInfo;
    }

    @Override
    public VkResult beginCommandBuffer(VkCommandBufferBeginInfo beginInfo) {
        // TODO: beginCommandBuffer
        return VkResult.VK_SUCCESS;
    }

    @Override
    public void cmdBeginRenderPass(VkRenderPassBeginInfo renderPassBegin, VkSubpassContents contents) {
        // TODO: cmdBeginRenderPass
    }

    @Override
    public void cmdBindPipeline(VkPipelineBindPoint pipelineBindPoint, VkPipeline pipeline) {
        // TODO: cmdBindPipeline
    }

    @Override
    public void cmdDraw(int vertexCount, int instanceCount, int firstVertex, int firstInstance) {
        // TODO: cmdDraw
    }

    @Override
    public void cmdEndRenderPass() {
        // TODO: cmdEndRenderPass
    }

    @Override
    public VkResult endCommandBuffer() {
        // TODO: endCommandBuffer
        return VkResult.VK_SUCCESS;
    }
}
