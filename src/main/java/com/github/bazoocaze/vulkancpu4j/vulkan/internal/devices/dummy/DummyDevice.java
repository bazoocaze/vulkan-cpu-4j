package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.dummy;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DummyDevice implements VkDevice {

    private final DummyPhysicalDevice physicalDevice;
    private final VkDeviceCreateInfo createInfo;
    private final List<DummyQueue> queues;
    private final Logger logger = LoggerFactory.getLogger(DummyDevice.class);

    public DummyDevice(DummyPhysicalDevice physicalDevice, VkDeviceCreateInfo createInfo) {
        this.physicalDevice = physicalDevice;
        this.createInfo = createInfo;
        this.queues = createQueues(createInfo);
    }

    private List<DummyQueue> createQueues(VkDeviceCreateInfo createInfo) {
        if (createInfo.queueCreateInfoCount > 0) {
            return Arrays.stream(createInfo.pQueueCreateInfos)
                    .map(DummyQueue::new)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public void getQueue(int queueFamilyIndex, int queueIndex, OutRef<VkQueue> queue) {
        queues.stream().filter(q -> q.queueFamilyIndex() == queueFamilyIndex)
                .findAny()
                .ifPresentOrElse(
                        queue::set,
                        () -> logger.error("Queue not found for queueFamilyIndex={}", queueFamilyIndex));
    }

    @Override
    public VkResult createSwapchainKHR(VkSwapchainCreateInfoKHR createInfo,
                                       VkAllocationCallbacks allocator,
                                       OutRef<VkSwapchainKHR> swapChain) {
        swapChain.set(new DummySwapChain(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult getSwapchainImagesKHR(VkSwapchainKHR swapchain, ByRef<Integer> swapchainImageCount,
                                          VkImage[] swapchainImages) {
        return swapchain.getSwapchainImagesKHR(swapchainImageCount, swapchainImages);
    }

    @Override
    public VkResult createImageView(VkImageViewCreateInfo createInfo, VkAllocationCallbacks allocator,
                                    OutRef<VkImageView> view) {
        view.set(new DummyImageView(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createRenderPass(VkRenderPassCreateInfo createInfo,
                                     VkAllocationCallbacks allocator,
                                     OutRef<VkRenderPass> renderPass) {
        renderPass.set(new DummyRenderPass(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createShaderModule(VkShaderModuleCreateInfo createInfo,
                                       VkAllocationCallbacks allocator,
                                       OutRef<VkShaderModule> shaderModule) {
        shaderModule.set(new DummyShaderModule(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createPipelineLayout(VkPipelineLayoutCreateInfo createInfo,
                                         VkAllocationCallbacks allocator,
                                         OutRef<VkPipelineLayout> pipelineLayout) {
        pipelineLayout.set(new DummyPipelineLayout(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createGraphicsPipelines(VkPipelineCache pipelineCache, int createInfoCount,
                                            VkGraphicsPipelineCreateInfo[] createInfos,
                                            VkAllocationCallbacks allocator,
                                            VkPipeline[] pipelines) {
        for (int i = 0; i < createInfoCount; i++) {
            pipelines[i] = new DummyGraphicPipeline(createInfos[i], pipelineCache);
        }
        return VkResult.VK_SUCCESS;
    }

    @Override
    public void destroyShaderModule(VkShaderModule shaderModule, VkAllocationCallbacks allocator) {
        // do nothing
    }

    @Override
    public VkResult createFramebuffer(VkFramebufferCreateInfo createInfo,
                                      VkAllocationCallbacks allocator,
                                      OutRef<VkFramebuffer> frameBuffer) {
        frameBuffer.set(new DummyFrameBuffer(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createCommandPool(VkCommandPoolCreateInfo createInfo,
                                      VkAllocationCallbacks allocator,
                                      OutRef<VkCommandPool> commandPool) {
        commandPool.set(new DummyCommandPool(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult allocateCommandBuffers(VkCommandBufferAllocateInfo allocateInfo,
                                           VkCommandBuffer[] commandBuffers) {
        for (int i = 0; i < allocateInfo.commandBufferCount; i++) {
            commandBuffers[i] = new DummyCommandBuffer(allocateInfo);
        }
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createSemaphore(VkSemaphoreCreateInfo createInfo,
                                    VkAllocationCallbacks allocator,
                                    OutRef<VkSemaphore> semaphore) {
        semaphore.set(new DummySemaphore(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createFence(VkFenceCreateInfo createInfo,
                                VkAllocationCallbacks allocator,
                                OutRef<VkFence> fence) {
        fence.set(new DummyFence(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult waitForFences(int fenceCount, VkFence[] fences, VkBool32 waitAll, long timeout) {
        // do nothing
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult acquireNextImageKHR(VkSwapchainKHR swapChain, long timeout, VkSemaphore semaphore, VkFence fence, OutRef<Integer> imageIndex) {
        return swapChain.acquireNextImageKHR(timeout, semaphore, fence, imageIndex);
    }

    @Override
    public VkResult resetFences(int fenceCount, VkFence[] fences) {
        // do nothing
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult deviceWaitIdle() {
        // do nothing
        return VkResult.VK_SUCCESS;
    }

    @Override
    public void destroySemaphore(VkSemaphore semaphore, VkAllocationCallbacks allocator) {
        // do nothing
    }

    @Override
    public void destroyFence(VkFence fence, VkAllocationCallbacks allocator) {
        // do nothing
    }

    @Override
    public void destroyCommandPool(VkCommandPool commandPool, VkAllocationCallbacks allocator) {
        // do nothing
    }

    @Override
    public void destroyFramebuffer(VkFramebuffer framebuffer, VkAllocationCallbacks allocator) {
        // do nothing
    }

    @Override
    public void destroyPipeline(VkPipeline pipeline, VkAllocationCallbacks allocator) {
        // do nothing
    }

    @Override
    public void destroyPipelineLayout(VkPipelineLayout pipelineLayout, VkAllocationCallbacks allocator) {
        // do nothing
    }

    @Override
    public void destroyRenderPass(VkRenderPass renderPass, VkAllocationCallbacks allocator) {
        // do nothing
    }

    @Override
    public void destroyImageView(VkImageView imageView, VkAllocationCallbacks allocator) {
        // do nothing
    }

    @Override
    public void destroySwapchainKHR(VkSwapchainKHR swapChain, VkAllocationCallbacks allocator) {
        // do nothing
    }

    @Override
    public void destroy(VkAllocationCallbacks allocator) {
        // do nothing
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}