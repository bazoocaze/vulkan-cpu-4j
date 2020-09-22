package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface VkDevice {

    @Internal
    void getQueue(int queueFamilyIndex, int queueIndex, OutRef<VkQueue> queue);

    @Internal
    VkResult createSwapchainKHR(VkSwapchainCreateInfoKHR createInfo,
                                VkAllocationCallbacks allocator,
                                OutRef<VkSwapchainKHR> swapChain);

    @Internal
    VkResult getSwapchainImagesKHR(VkSwapchainKHR swapchain,
                                   ByRef<Integer> swapchainImageCount,
                                   VkImage[] swapchainImages);

    @Internal
    VkResult createImageView(VkImageViewCreateInfo createInfo,
                             VkAllocationCallbacks allocator,
                             OutRef<VkImageView> view);

    @Internal
    VkResult createRenderPass(VkRenderPassCreateInfo createInfo,
                              VkAllocationCallbacks allocator,
                              OutRef<VkRenderPass> renderPass);

    @Internal
    VkResult createShaderModule(VkShaderModuleCreateInfo createInfo,
                                VkAllocationCallbacks allocator,
                                OutRef<VkShaderModule> shaderModule);

    @Internal
    VkResult createPipelineLayout(VkPipelineLayoutCreateInfo createInfo,
                                  VkAllocationCallbacks allocator,
                                  OutRef<VkPipelineLayout> pipelineLayout);

    @Internal
    VkResult createGraphicsPipelines(VkPipelineCache pipelineCache, int createInfoCount,
                                     VkGraphicsPipelineCreateInfo[] createInfos, VkAllocationCallbacks allocator,
                                     VkPipeline[] pipelines);

    @Internal
    void destroyShaderModule(VkShaderModule shaderModule, VkAllocationCallbacks allocator);

    @Internal
    VkResult createFramebuffer(VkFramebufferCreateInfo createInfo,
                               VkAllocationCallbacks allocator,
                               OutRef<VkFramebuffer> frameBuffer);

    @Internal
    VkResult createCommandPool(VkCommandPoolCreateInfo createInfo,
                               VkAllocationCallbacks allocator,
                               OutRef<VkCommandPool> commandPool);

    @Internal
    VkResult allocateCommandBuffers(VkCommandBufferAllocateInfo allocateInfo, VkCommandBuffer[] commandBuffers);

    @Internal
    VkResult createSemaphore(VkSemaphoreCreateInfo createInfo,
                             VkAllocationCallbacks allocator,
                             OutRef<VkSemaphore> semaphore);

    @Internal
    VkResult createFence(VkFenceCreateInfo createInfo, VkAllocationCallbacks allocator, OutRef<VkFence> fence);

    @Internal
    VkResult waitForFences(int fenceCount, VkFence[] fences, VkBool32 waitAll, long timeout);

    @Internal
    VkResult acquireNextImageKHR(VkSwapchainKHR swapChain, long timeout,
                                 VkSemaphore semaphore, VkFence fence, OutRef<Integer> imageIndex);

    @Internal
    VkResult resetFences(int fenceCount, VkFence[] fences);

    @Internal
    VkResult deviceWaitIdle();

    @Internal
    void destroySemaphore(VkSemaphore semaphore, VkAllocationCallbacks allocator);

    @Internal
    void destroyFence(VkFence fence, VkAllocationCallbacks allocator);

    @Internal
    void destroyCommandPool(VkCommandPool commandPool, VkAllocationCallbacks allocator);

    @Internal
    void destroyFramebuffer(VkFramebuffer framebuffer, VkAllocationCallbacks allocator);

    @Internal
    void destroyPipeline(VkPipeline pipeline, VkAllocationCallbacks allocator);

    @Internal
    void destroyPipelineLayout(VkPipelineLayout pipelineLayout, VkAllocationCallbacks allocator);

    @Internal
    void destroyRenderPass(VkRenderPass renderPass, VkAllocationCallbacks allocator);

    @Internal
    void destroyImageView(VkImageView imageView, VkAllocationCallbacks allocator);

    @Internal
    void destroySwapchainKHR(VkSwapchainKHR swapChain, VkAllocationCallbacks allocator);

    @Internal
    void destroy(VkAllocationCallbacks allocator);
}
