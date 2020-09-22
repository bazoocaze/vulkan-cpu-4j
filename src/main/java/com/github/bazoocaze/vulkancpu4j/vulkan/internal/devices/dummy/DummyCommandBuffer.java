package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkSubpassContents;

public class DummyCommandBuffer implements VkCommandBuffer {

    private final VkCommandBufferAllocateInfo allocateInfo;

    public DummyCommandBuffer(VkCommandBufferAllocateInfo allocateInfo) {
        this.allocateInfo = allocateInfo;
    }

    @Override
    public VkResult beginCommandBuffer(VkCommandBufferBeginInfo beginInfo) {
        return VkResult.VK_SUCCESS;
    }

    @Override
    public void cmdBeginRenderPass(VkRenderPassBeginInfo renderPassBegin, VkSubpassContents contents) {
        // do nothing
    }

    @Override
    public void cmdBindPipeline(VkPipelineBindPoint pipelineBindPoint, VkPipeline pipeline) {
        // do nothing
    }

    @Override
    public void cmdDraw(int vertexCount, int instanceCount, int firstVertex, int firstInstance) {
        // do nothing
    }

    @Override
    public void cmdEndRenderPass() {
        // do nothing
    }

    @Override
    public VkResult endCommandBuffer() {
        return VkResult.VK_SUCCESS;
    }
}
