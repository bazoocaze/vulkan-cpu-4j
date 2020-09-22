/*
MIT License
Copyright (c) 2020 J Ferreira (Bazoocaze)
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkPresentModeKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkSubpassContents;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.VkArrayUtil;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.VkPreconditions;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.VulkanContext;

public class Vulkan {

    private Vulkan() {
        // static class
    }

    public static final int VK_SUBPASS_EXTERNAL = 0xFFFFFFF;

    public static <T> T VK_NULL_HANDLE() {
        return null;
    }

    /**
     * Create a debug messenger object. A debug messenger triggers a debug callback with a debug
     * message when an event of interest occurs.
     *
     * @param instance       is the instance the messenger will be used with.
     * @param createInfo     is a pointer to a {@link VkDebugUtilsMessengerCreateInfoEXT} structure containing the
     *                       callback pointer, as well as defining conditions under which this messenger will trigger
     *                       the callback.
     * @param pAllocator     controls host memory allocation as described in the
     *                       <a href="https://www.khronos.org/registry/vulkan/specs/1.2-extensions/html/vkspec.html#memory-allocation">Memory Allocation</a>
     *                       chapter.
     * @param debugMessenger is a pointer to a {@link VkDebugUtilsMessengerEXT} handle in which the created object
     *                       is returned.
     * @return On success, this command returns {@link VkResult#VK_SUCCESS}. On failure,
     * this command returns {@link VkResult#VK_ERROR_OUT_OF_HOST_MEMORY}.
     */
    public static VkResult vkCreateDebugUtilsMessengerEXT(VkInstance instance,
                                                          VkDebugUtilsMessengerCreateInfoEXT createInfo,
                                                          VkAllocationCallbacks pAllocator,
                                                          OutRef<VkDebugUtilsMessengerEXT> debugMessenger) {
        return instance.createDebugUtilsMessengerEXT(createInfo, pAllocator, debugMessenger);
    }

    /**
     * vkDestroyDebugUtilsMessengerEXT
     *
     * @param instance  is the instance where the callback was created.
     * @param messenger is the {@link VkDebugUtilsMessengerEXT} object to destroy. messenger is an externally
     *                  synchronized object and must not be used on more than one thread at a time.
     *                  This means that vkDestroyDebugUtilsMessengerEXT must not be called when a callback
     *                  is active.
     * @param allocator controls host memory allocation as described in the
     *                  <a href="https://www.khronos.org/registry/vulkan/specs/1.2-extensions/html/vkspec.html#memory-allocation">Memory Allocation</a>
     *                  chapter.
     */
    public static void vkDestroyDebugUtilsMessengerEXT(VkInstance instance,
                                                       VkDebugUtilsMessengerEXT messenger,
                                                       VkAllocationCallbacks allocator) {
        instance.destroyDebugUtilsMessengerEXT(messenger, allocator);
    }

    public static VkResult vkDeviceWaitIdle(VkDevice device) {
        return device.deviceWaitIdle();
    }

    public static void vkDestroyFence(VkDevice device, VkFence fence, VkAllocationCallbacks allocator) {
        device.destroyFence(fence, allocator);

    }

    public static void vkDestroySemaphore(VkDevice device, VkSemaphore semaphore, VkAllocationCallbacks allocator) {
        device.destroySemaphore(semaphore, allocator);
    }

    public static void vkDestroyCommandPool(VkDevice device, VkCommandPool commandPool,
                                            VkAllocationCallbacks allocator) {
        device.destroyCommandPool(commandPool, allocator);
    }

    public static void vkDestroyFramebuffer(VkDevice device, VkFramebuffer framebuffer,
                                            VkAllocationCallbacks allocator) {
        device.destroyFramebuffer(framebuffer, allocator);
    }

    public static void vkDestroyPipeline(VkDevice device, VkPipeline pipeline, VkAllocationCallbacks allocator) {
        device.destroyPipeline(pipeline, allocator);
    }

    public static void vkDestroyPipelineLayout(VkDevice device, VkPipelineLayout pipelineLayout,
                                               VkAllocationCallbacks allocator) {
        device.destroyPipelineLayout(pipelineLayout, allocator);
    }

    public static void vkDestroyRenderPass(VkDevice device, VkRenderPass renderPass, VkAllocationCallbacks allocator) {
        device.destroyRenderPass(renderPass, allocator);
    }

    public static void vkDestroyImageView(VkDevice device, VkImageView imageView, VkAllocationCallbacks allocator) {
        device.destroyImageView(imageView, allocator);
    }

    public static void vkDestroySwapchainKHR(VkDevice device, VkSwapchainKHR swapChain,
                                             VkAllocationCallbacks allocator) {
        device.destroySwapchainKHR(swapChain, allocator);
    }

    public static void vkDestroyDevice(VkDevice device, VkAllocationCallbacks allocator) {
        device.destroy(allocator);
    }

    public static void vkDestroySurfaceKHR(VkInstance instance, VkSurfaceKHR surface,
                                           VkAllocationCallbacks allocator) {
        instance.destroySurfaceKHR(surface, allocator);
    }

    public static void vkDestroyInstance(VkInstance instance, VkAllocationCallbacks allocator) {
        instance.destroyInstance();
    }

    public static VkResult vkCreateInstance(VkInstanceCreateInfo createInfo,
                                            VkAllocationCallbacks allocator,
                                            OutRef<VkInstance> instance) {
        return VulkanContext.DEFAULT.instanceFactory().create(createInfo, instance);
    }

    public static VkResult vkEnumeratePhysicalDevices(VkInstance instance, ByRef<Integer> physicalDeviceCount,
                                                      VkPhysicalDevice[] physicalDevices) {
        VkPreconditions.checkNull(instance, "instance");
        return instance.enumeratePhysicalDevices(physicalDeviceCount, physicalDevices);
    }

    public static VkResult vkCreateDevice(VkPhysicalDevice physicalDevice,
                                          VkDeviceCreateInfo createInfo,
                                          VkAllocationCallbacks allocator,
                                          OutRef<VkDevice> device) {
        return physicalDevice.createDevice(createInfo, allocator, device);
    }

    public static void vkGetDeviceQueue(VkDevice device, int queueFamilyIndex, int queueIndex, OutRef<VkQueue> queue) {
        device.getQueue(queueFamilyIndex, queueIndex, queue);
    }

    public static VkResult vkCreateSwapchainKHR(VkDevice device, VkSwapchainCreateInfoKHR createInfo,
                                                VkAllocationCallbacks allocator, OutRef<VkSwapchainKHR> swapChain) {
        return device.createSwapchainKHR(createInfo, allocator, swapChain);
    }

    public static VkResult vkGetSwapchainImagesKHR(VkDevice device, VkSwapchainKHR swapchain,
                                                   ByRef<Integer> swapchainImageCount, VkImage[] swapchainImages) {
        return device.getSwapchainImagesKHR(swapchain, swapchainImageCount, swapchainImages);
    }

    public static VkResult vkCreateImageView(VkDevice device, VkImageViewCreateInfo createInfo,
                                             VkAllocationCallbacks allocator, OutRef<VkImageView> view) {
        return device.createImageView(createInfo, allocator, view);
    }

    public static VkResult vkCreateRenderPass(VkDevice device, VkRenderPassCreateInfo createInfo,
                                              VkAllocationCallbacks allocator, OutRef<VkRenderPass> renderPass) {
        return device.createRenderPass(createInfo, allocator, renderPass);
    }

    public static VkResult vkCreatePipelineLayout(VkDevice device, VkPipelineLayoutCreateInfo createInfo,
                                                  VkAllocationCallbacks allocator,
                                                  OutRef<VkPipelineLayout> pipelineLayout) {
        return device.createPipelineLayout(createInfo, allocator, pipelineLayout);
    }

    public static VkResult vkCreateGraphicsPipelines(VkDevice device, VkPipelineCache pipelineCache,
                                                     int createInfoCount, VkGraphicsPipelineCreateInfo[] createInfos,
                                                     VkAllocationCallbacks allocator, VkPipeline[] pipelines) {
        return device.createGraphicsPipelines(pipelineCache, createInfoCount, createInfos, allocator, pipelines);
    }

    public static void vkDestroyShaderModule(VkDevice device, VkShaderModule shaderModule,
                                             VkAllocationCallbacks allocator) {
        device.destroyShaderModule(shaderModule, allocator);
    }

    public static VkResult vkCreateFramebuffer(VkDevice device, VkFramebufferCreateInfo createInfo,
                                               VkAllocationCallbacks allocator, OutRef<VkFramebuffer> frameBuffer) {
        return device.createFramebuffer(createInfo, allocator, frameBuffer);
    }

    public static VkResult vkCreateCommandPool(VkDevice device, VkCommandPoolCreateInfo createInfo,
                                               VkAllocationCallbacks allocator, OutRef<VkCommandPool> commandPool) {
        return device.createCommandPool(createInfo, allocator, commandPool);
    }

    public static VkResult vkAllocateCommandBuffers(VkDevice device, VkCommandBufferAllocateInfo allocateInfo,
                                                    VkCommandBuffer[] commandBuffers) {
        return device.allocateCommandBuffers(allocateInfo, commandBuffers);
    }

    public static VkResult vkBeginCommandBuffer(VkCommandBuffer commandBuffer, VkCommandBufferBeginInfo beginInfo) {
        return commandBuffer.beginCommandBuffer(beginInfo);
    }

    public static void vkCmdBeginRenderPass(VkCommandBuffer commandBuffer, VkRenderPassBeginInfo renderPassBegin,
                                            VkSubpassContents contents) {
        commandBuffer.cmdBeginRenderPass(renderPassBegin, contents);
    }

    public static void vkCmdBindPipeline(VkCommandBuffer commandBuffer, VkPipelineBindPoint pipelineBindPoint,
                                         VkPipeline pipeline) {
        commandBuffer.cmdBindPipeline(pipelineBindPoint, pipeline);
    }

    public static void vkCmdDraw(VkCommandBuffer commandBuffer,
                                 int vertexCount, int instanceCount,
                                 int firstVertex, int firstInstance) {
        commandBuffer.cmdDraw(vertexCount, instanceCount, firstVertex, firstInstance);
    }

    public static void vkCmdEndRenderPass(VkCommandBuffer commandBuffer) {
        commandBuffer.cmdEndRenderPass();
    }

    public static VkResult vkEndCommandBuffer(VkCommandBuffer commandBuffer) {
        return commandBuffer.endCommandBuffer();
    }

    public static VkResult vkCreateSemaphore(VkDevice device, VkSemaphoreCreateInfo createInfo,
                                             VkAllocationCallbacks allocator, OutRef<VkSemaphore> semaphore) {
        return device.createSemaphore(createInfo, allocator, semaphore);
    }

    public static VkResult vkCreateFence(VkDevice device, VkFenceCreateInfo createInfo,
                                         VkAllocationCallbacks allocator, OutRef<VkFence> fence) {
        return device.createFence(createInfo, allocator, fence);
    }

    public static VkResult vkWaitForFences(VkDevice device, int fenceCount, VkFence[] fences,
                                           VkBool32 waitAll, long timeout) {
        return device.waitForFences(fenceCount, fences, waitAll, timeout);
    }

    public static VkResult vkAcquireNextImageKHR(VkDevice device, VkSwapchainKHR swapChain, long timeout,
                                                 VkSemaphore semaphore, VkFence fence, OutRef<Integer> imageIndex) {
        return device.acquireNextImageKHR(swapChain, timeout, semaphore, fence, imageIndex);
    }

    public static VkResult vkResetFences(VkDevice device, int fenceCount, VkFence[] fences) {
        return device.resetFences(fenceCount, fences);
    }

    public static VkResult vkQueueSubmit(VkQueue queue, int submitCount, VkSubmitInfo[] submits, VkFence fence) {
        return queue.queueSubmit(submitCount, submits, fence);
    }

    public static VkResult vkQueuePresentKHR(VkQueue queue, VkPresentInfoKHR presentInfo) {
        return queue.queuePresentKHR(presentInfo);
    }

    public static VkResult vkCreateShaderModule(VkDevice device, VkShaderModuleCreateInfo createInfo,
                                                VkAllocationCallbacks allocator, OutRef<VkShaderModule> shaderModule) {
        return device.createShaderModule(createInfo, allocator, shaderModule);
    }

    public static VkResult vkGetPhysicalDeviceSurfaceCapabilitiesKHR(VkPhysicalDevice physicalDevice,
                                                                     VkSurfaceKHR surface,
                                                                     OutRef<VkSurfaceCapabilitiesKHR> surfaceCapabilities) {
        return physicalDevice.getPhysicalDeviceSurfaceCapabilitiesKHR(surface, surfaceCapabilities);
    }

    public static VkResult vkGetPhysicalDeviceSurfaceFormatsKHR(VkPhysicalDevice physicalDevice,
                                                                VkSurfaceKHR surface,
                                                                ByRef<Integer> surfaceFormatCount,
                                                                VkSurfaceFormatKHR[] surfaceFormats) {
        return physicalDevice.getPhysicalDeviceSurfaceFormatsKHR(surface, surfaceFormatCount, surfaceFormats);
    }

    public static VkResult vkGetPhysicalDeviceSurfacePresentModesKHR(VkPhysicalDevice physicalDevice,
                                                                     VkSurfaceKHR surface,
                                                                     ByRef<Integer> presentModeCount,
                                                                     VkPresentModeKHR[] presentModes) {
        return physicalDevice.getPhysicalDeviceSurfacePresentModesKHR(surface, presentModeCount, presentModes);
    }

    public static VkResult vkEnumerateDeviceExtensionProperties(VkPhysicalDevice physicalDevice,
                                                                String layerName,
                                                                ByRef<Integer> propertyCount,
                                                                VkExtensionProperties[] properties) {
        return physicalDevice.enumerateDeviceExtensionProperties(layerName, propertyCount, properties);
    }

    public static VkResult vkGetPhysicalDeviceQueueFamilyProperties(VkPhysicalDevice physicalDevice,
                                                                    ByRef<Integer> queueFamilyCount,
                                                                    VkQueueFamilyProperties[] queueFamilies) {
        return physicalDevice.getPhysicalDeviceQueueFamilyProperties(queueFamilyCount, queueFamilies);
    }

    public static VkResult vkGetPhysicalDeviceSurfaceSupportKHR(VkPhysicalDevice physicalDevice,
                                                                int queueFamilyIndex,
                                                                VkSurfaceKHR surface,
                                                                OutRef<VkBool32> supported) {
        return physicalDevice.getPhysicalDeviceSurfaceSupportKHR(queueFamilyIndex, surface, supported);
    }

    /**
     * Returns up to requested number of global layer properties.
     * <p>
     * If pProperties is NULL, then the number of layer properties available is returned in pPropertyCount.
     * Otherwise, pPropertyCount must point to a variable set by the user to the number of elements in the
     * pProperties array, and on return the variable is overwritten with the number of structures actually
     * written to pProperties. If pPropertyCount is less than the number of layer properties available, at
     * most pPropertyCount structures will be written. If pPropertyCount is smaller than the number of
     * layers available, VK_INCOMPLETE will be returned instead of VK_SUCCESS, to indicate that not all the
     * available layer properties were returned.
     * <p>
     * The list of available layers may change at any time due to actions outside of the Vulkan
     * implementation, so two calls to vkEnumerateInstanceLayerProperties with the same parameters may
     * return different results, or retrieve different pPropertyCount values or pProperties contents. Once
     * an instance has been created, the layers enabled for that instance will continue to be enabled and
     * valid for the lifetime of that instance, even if some of them become unavailable for future instances.
     *
     * @param pPropertyCount is a pointer to an integer related to the number of layer properties available
     *                       or queried, as described below.
     * @param pProperties    is either NULL or a pointer to an array of VkLayerProperties structures.
     * @return On success, this command returns: {@link VkResult#VK_SUCCESS}, {@link VkResult#VK_INCOMPLETE}. On
     * failure, this command returns: {@link VkResult#VK_ERROR_OUT_OF_HOST_MEMORY}, {@link VkResult#VK_ERROR_OUT_OF_DEVICE_MEMORY}
     */
    public static VkResult vkEnumerateInstanceLayerProperties(ByRef<Integer> pPropertyCount, VkLayerProperties[] pProperties) {
        VkLayerProperties[] layerProperties = new VkLayerProperties[]{
                new VkLayerProperties("VK_LAYER_KHRONOS_validation", VkApiVersion.VK_MAKE_VERSION(1, 0, 131), 1,
                        "VK_LAYER_KHRONOS_validation"),
                new VkLayerProperties("VK_LAYER_LUNARG_standard_validation",
                        VkApiVersion.VK_MAKE_VERSION(1, 0, 131), 1,
                        "LunarG Standard Validation Layer")
        };
        return VkArrayUtil.copyArray(layerProperties, pPropertyCount, pProperties);
    }

    public static VkResult vkCreateJavaSwingSurfaceEMU(VkInstance instance,
                                                       VkJavaSwingSurfaceCreateInfoEMU createInfo,
                                                       VkAllocationCallbacks allocator,
                                                       OutRef<VkSurfaceKHR> surface) {
        return instance.createJavaSwingSurfaceEMU(createInfo, allocator, surface);
    }
}
