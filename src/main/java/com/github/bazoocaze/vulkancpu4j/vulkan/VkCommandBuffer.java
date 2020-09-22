package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkSubpassContents;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface VkCommandBuffer {

    @Internal
    VkResult beginCommandBuffer(VkCommandBufferBeginInfo beginInfo);

    @Internal
    void cmdBeginRenderPass(VkRenderPassBeginInfo renderPassBegin, VkSubpassContents contents);

    @Internal
    void cmdBindPipeline(VkPipelineBindPoint pipelineBindPoint, VkPipeline pipeline);

    @Internal
    void cmdDraw(int vertexCount, int instanceCount, int firstVertex, int firstInstance);

    @Internal
    void cmdEndRenderPass();

    @Internal
    VkResult endCommandBuffer();
}
