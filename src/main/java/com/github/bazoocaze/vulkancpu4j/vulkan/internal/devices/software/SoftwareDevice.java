package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class SoftwareDevice implements VkDevice {

    private final VkDeviceCreateInfo createInfo;
    private final Map<Integer, List<SoftwareQueue>> familyQueues;
    private final Logger logger = LoggerFactory.getLogger(SoftwareDevice.class);

    public SoftwareDevice(VkDeviceCreateInfo createInfo) {
        this.createInfo = createInfo;
        this.familyQueues = createQueues(createInfo);
    }

    public static VkResult create(VkDeviceCreateInfo createInfo, OutRef<VkDevice> outputDevice) {
        if (!SoftwareDeviceSpecs.validateExtensions(Arrays.asList(createInfo.ppEnabledExtensionNames))) {
            LoggerFactory.getLogger(SoftwareDevice.class)
                    .error("Failed to create device: VK_ERROR_EXTENSION_NOT_PRESENT");
            return VkResult.VK_ERROR_EXTENSION_NOT_PRESENT;
        }
        // TODO: treatment of VkDeviceCreateInfo.pEnabledFeatures
        outputDevice.set(new SoftwareDevice(createInfo));
        return VkResult.VK_SUCCESS;
    }

    private Map<Integer, List<SoftwareQueue>> createQueues(VkDeviceCreateInfo createInfo) {
        Map<Integer, List<SoftwareQueue>> ret = new HashMap<>();
        for (int i = 0; i < createInfo.queueCreateInfoCount; i++) {
            List<SoftwareQueue> queues = new ArrayList<>();
            for (int j = 0; j < createInfo.pQueueCreateInfos[i].queueCount; j++) {
                queues.add(new SoftwareQueue(createInfo.pQueueCreateInfos[i]));
            }
            ret.put(createInfo.pQueueCreateInfos[i].queueFamilyIndex, queues);
        }
        return ret;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void getQueue(int queueFamilyIndex, int queueIndex, OutRef<VkQueue> queue) {
        if (!familyQueues.containsKey(queueFamilyIndex)) {
            String message = String.format("Queue family index not found: %s", queueFamilyIndex);
            throw new UnsupportedOperationException(message);
        }
        queue.set(familyQueues.get(queueFamilyIndex).get(queueIndex));
    }

    @Override
    public VkResult createSwapchainKHR(VkSwapchainCreateInfoKHR createInfo,
                                       VkAllocationCallbacks allocator,
                                       OutRef<VkSwapchainKHR> swapChain) {
        return SoftwareSwapchain.create(this, createInfo, allocator, swapChain);
    }

    @Override
    public VkResult getSwapchainImagesKHR(VkSwapchainKHR swapchain,
                                          ByRef<Integer> swapchainImageCount,
                                          VkImage[] swapchainImages) {
        return swapchain.getSwapchainImagesKHR(swapchainImageCount, swapchainImages);
    }

    @Override
    public VkResult createImageView(VkImageViewCreateInfo createInfo,
                                    VkAllocationCallbacks allocator,
                                    OutRef<VkImageView> view) {
        return SoftwareImageView.createImageView(this, createInfo, view);
    }

    @Override
    public VkResult createRenderPass(VkRenderPassCreateInfo createInfo,
                                     VkAllocationCallbacks allocator,
                                     OutRef<VkRenderPass> renderPass) {
        renderPass.set(new SoftwareRenderPass(this, createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createShaderModule(VkShaderModuleCreateInfo createInfo,
                                       VkAllocationCallbacks allocator,
                                       OutRef<VkShaderModule> shaderModule) {
        shaderModule.set(new SoftwareShaderModule(this, createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createPipelineLayout(VkPipelineLayoutCreateInfo createInfo,
                                         VkAllocationCallbacks allocator,
                                         OutRef<VkPipelineLayout> pipelineLayout) {
        pipelineLayout.set(new SoftwarePipelineLayout(this, createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createGraphicsPipelines(VkPipelineCache pipelineCache,
                                            int createInfoCount,
                                            VkGraphicsPipelineCreateInfo[] createInfos,
                                            VkAllocationCallbacks allocator,
                                            VkPipeline[] pipelines) {
        // TODO: treatment of pipelineCache
        for (int i = 0; i < createInfoCount; i++) {
            final int pos = i;
            VkResult result = SoftwareGraphicsPipeline.create(this, createInfos[i], p -> pipelines[pos] = p);
            if (result != VkResult.VK_SUCCESS) {
                return result;
            }
        }
        return VkResult.VK_SUCCESS;
    }

    @Override
    public void destroyShaderModule(VkShaderModule shaderModule, VkAllocationCallbacks allocator) {
        // TODO: destroyShaderModule
    }

    @Override
    public VkResult createFramebuffer(VkFramebufferCreateInfo createInfo,
                                      VkAllocationCallbacks allocator,
                                      OutRef<VkFramebuffer> frameBuffer) {
        frameBuffer.set(new SoftwareFrameBuffer(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createCommandPool(VkCommandPoolCreateInfo createInfo,
                                      VkAllocationCallbacks allocator,
                                      OutRef<VkCommandPool> commandPool) {
        commandPool.set(new SoftwareCommandPool(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult allocateCommandBuffers(VkCommandBufferAllocateInfo allocateInfo,
                                           VkCommandBuffer[] commandBuffers) {
        return ((SoftwareCommandPool) allocateInfo.commandPool).allocateCommandBuffers(allocateInfo, commandBuffers);
    }

    @Override
    public VkResult createSemaphore(VkSemaphoreCreateInfo createInfo,
                                    VkAllocationCallbacks allocator,
                                    OutRef<VkSemaphore> semaphore) {
        semaphore.set(new SoftwareSemaphore(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult createFence(VkFenceCreateInfo createInfo,
                                VkAllocationCallbacks allocator,
                                OutRef<VkFence> fence) {
        fence.set(new SoftwareFence(createInfo));
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult waitForFences(int fenceCount, VkFence[] fences, VkBool32 waitAll, long timeout) {
        return SoftwareDeviceSincronization.waitForFences(fenceCount, fenceCount, waitAll, timeout);
    }

    @Override
    public VkResult acquireNextImageKHR(VkSwapchainKHR swapChain, long timeout, VkSemaphore semaphore,
                                        VkFence fence,
                                        OutRef<Integer> imageIndex) {
        return swapChain.acquireNextImageKHR(timeout, semaphore, fence, imageIndex);
    }

    @Override
    public VkResult resetFences(int fenceCount, VkFence[] fences) {
        return SoftwareDeviceSincronization.resetFences(fenceCount, fences);
    }

    @Override
    public VkResult deviceWaitIdle() {
        // TODO: deviceWaitIdle
        return VkResult.VK_SUCCESS;
    }

    @Override
    public void destroySemaphore(VkSemaphore semaphore, VkAllocationCallbacks allocator) {
        ((SoftwareSemaphore) semaphore).destroy();
    }

    @Override
    public void destroyFence(VkFence fence, VkAllocationCallbacks allocator) {
        ((SoftwareFence) fence).destroy();
    }

    @Override
    public void destroyCommandPool(VkCommandPool commandPool, VkAllocationCallbacks allocator) {
        ((SoftwareCommandPool) commandPool).destroy();
    }

    @Override
    public void destroyFramebuffer(VkFramebuffer framebuffer, VkAllocationCallbacks allocator) {
        ((SoftwareFrameBuffer) framebuffer).destroy();
    }

    @Override
    public void destroyPipeline(VkPipeline pipeline, VkAllocationCallbacks allocator) {
        ((SoftwarePipeline) pipeline).destroy();
    }

    @Override
    public void destroyPipelineLayout(VkPipelineLayout pipelineLayout, VkAllocationCallbacks allocator) {
        ((SoftwarePipelineLayout) pipelineLayout).destroy();
    }

    @Override
    public void destroyRenderPass(VkRenderPass renderPass, VkAllocationCallbacks allocator) {
        ((SoftwareRenderPass) renderPass).destroy();
    }

    @Override
    public void destroyImageView(VkImageView imageView, VkAllocationCallbacks allocator) {
        ((SoftwareImageView) imageView).destroy();
    }

    @Override
    public void destroySwapchainKHR(VkSwapchainKHR swapChain, VkAllocationCallbacks allocator) {
        ((SoftwareSwapchain) swapChain).destroy();
    }

    @Override
    public void destroy(VkAllocationCallbacks allocator) {
        // TODO: SoftwareDevice.destroy
    }
}
