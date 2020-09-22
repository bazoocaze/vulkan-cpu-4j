package examples.vulkantutorial.com.ex01_presentation;

import com.github.bazoocaze.vulkancpu4j.glfw.GLFW;
import com.github.bazoocaze.vulkancpu4j.glfw.GLFWwindow;
import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.data.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.*;
import examples.FailureException;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.github.bazoocaze.vulkancpu4j.glfw.GLFWwindowCreationHintValue.GLFW_FALSE;
import static com.github.bazoocaze.vulkancpu4j.glfw.GLFWwindowCreationHintValue.GLFW_NO_API;
import static com.github.bazoocaze.vulkancpu4j.glfw.GLFWwindowCreationHints.GLFW_CLIENT_API;
import static com.github.bazoocaze.vulkancpu4j.glfw.GLFWwindowCreationHints.GLFW_RESIZABLE;
import static com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult.VK_SUCCESS;

public class HelloTriangleApplication01 {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static final int MAX_FRAMES_IN_FLIGHT = 2;

    private static final String[] validationLayers = new String[]{"VK_LAYER_KHRONOS_validation"};

    private static final String[] deviceExtensions = new String[]{
            VkExtensionNames.VK_KHR_SWAPCHAIN_EXTENSION_NAME
    };

    private static final boolean ENABLE_VALIDATION_LAYERS = true;

    public static class QueueFamilyIndices {

        public Integer graphicsFamily;
        public Integer presentFamily;

        public boolean isComplete() {
            return graphicsFamily != null && presentFamily != null;
        }
    }

    public static class SwapChainSupportDetails {

        public VkSurfaceCapabilitiesKHR capabilities;
        public VkSurfaceFormatKHR[] formats;
        public VkPresentModeKHR[] presentModes;
    }

    public void run() {
        initWindow();
        initVulkan();
        mainLoop();
        cleanup();
    }

    private GLFWwindow window;

    private VkInstance instance;
    private VkDebugUtilsMessengerEXT debugMessenger;
    private VkSurfaceKHR surface;

    private VkPhysicalDevice physicalDevice;
    private VkDevice device;

    private VkQueue graphicsQueue;
    private VkQueue presentQueue;

    private VkSwapchainKHR swapChain;

    private VkImage[] swapChainImages;
    private VkFormat swapChainImageFormat;
    private VkExtent2D swapChainExtent;
    private VkImageView[] swapChainImageViews;
    private VkFramebuffer[] swapChainFramebuffers;

    private VkRenderPass renderPass;
    private VkPipelineLayout pipelineLayout;
    private VkPipeline graphicsPipeline;

    private VkCommandPool commandPool;
    private VkCommandBuffer[] commandBuffers;

    private VkSemaphore[] imageAvailableSemaphores;
    private VkSemaphore[] renderFinishedSemaphores;
    private VkFence[] inFlightFences;
    private VkFence[] imagesInFlight;
    private int currentFrame = 0;

    private void initWindow() {
        GLFW.glfwInit();

        GLFW.glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API);
        GLFW.glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "Vulkan", null, null);
    }

    private void initVulkan() {
        createInstance();
        setupDebugMessenger();
        createSurface();
        pickPhysicalDevice();
        createLogicalDevice();
        createSwapChain();
        createImageViews();
        createRenderPass();
        createGraphicsPipeline();
        createFramebuffers();
        createCommandPool();
        createCommandBuffers();
        createSyncObjects();
    }

    private void mainLoop() {
        while (!GLFW.glfwWindowShouldClose(window)) {
            GLFW.glfwPollEvents();
            drawFrame();
        }

        Vulkan.vkDeviceWaitIdle(device);
    }

    private void cleanup() {
        for (int i = 0; i < MAX_FRAMES_IN_FLIGHT; i++) {
            Vulkan.vkDestroySemaphore(device, renderFinishedSemaphores[i], null);
            Vulkan.vkDestroySemaphore(device, imageAvailableSemaphores[i], null);
            Vulkan.vkDestroyFence(device, inFlightFences[i], null);
        }

        Vulkan.vkDestroyCommandPool(device, commandPool, null);

        for (VkFramebuffer framebuffer : swapChainFramebuffers) {
            Vulkan.vkDestroyFramebuffer(device, framebuffer, null);
        }

        Vulkan.vkDestroyPipeline(device, graphicsPipeline, null);
        Vulkan.vkDestroyPipelineLayout(device, pipelineLayout, null);
        Vulkan.vkDestroyRenderPass(device, renderPass, null);

        for (VkImageView imageView : swapChainImageViews) {
            Vulkan.vkDestroyImageView(device, imageView, null);
        }

        Vulkan.vkDestroySwapchainKHR(device, swapChain, null);
        Vulkan.vkDestroyDevice(device, null);

        if (ENABLE_VALIDATION_LAYERS) {
            Vulkan.vkDestroyDebugUtilsMessengerEXT(instance, debugMessenger, null);
        }

        Vulkan.vkDestroySurfaceKHR(instance, surface, null);
        Vulkan.vkDestroyInstance(instance, null);

        GLFW.glfwDestroyWindow(window);

        GLFW.glfwTerminate();
    }

    private void createInstance() {
        if (ENABLE_VALIDATION_LAYERS && !checkValidationLayerSupport()) {
            throw new FailureException("validation layers requested, but not available!");
        }

        VkApplicationInfo appInfo = new VkApplicationInfo();
        appInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_APPLICATION_INFO;
        appInfo.pApplicationName = "Hello Triangle";
        appInfo.applicationVersion = VkApiVersion.VK_MAKE_VERSION(1, 0, 0);
        appInfo.pEngineName = "No Engine";
        appInfo.engineVersion = VkApiVersion.VK_MAKE_VERSION(1, 0, 0);
        appInfo.apiVersion = VkApiVersion.VK_API_VERSION_1_0;

        VkInstanceCreateInfo createInfo = new VkInstanceCreateInfo();
        createInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO;
        createInfo.pApplicationInfo = appInfo;

        List<String> extensions = getRequiredExtensions();
        createInfo.enabledExtensionCount = extensions.size();
        createInfo.ppEnabledExtensionNames = extensions.toArray(new String[]{});

        if (ENABLE_VALIDATION_LAYERS) {
            createInfo.enabledLayerCount = validationLayers.length;
            createInfo.ppEnabledLayerNames = validationLayers;

            createInfo.pNext = populateDebugMessengerCreateInfo();
        } else {
            createInfo.enabledLayerCount = 0;
            createInfo.pNext = null;
        }

        if (Vulkan.vkCreateInstance(createInfo, null, v -> instance = v) != VK_SUCCESS) {
            throw new FailureException("failed to create instance!");
        }
    }

    private VkDebugUtilsMessengerCreateInfoEXT populateDebugMessengerCreateInfo() {
        VkDebugUtilsMessengerCreateInfoEXT createInfo = new VkDebugUtilsMessengerCreateInfoEXT();
        createInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_DEBUG_UTILS_MESSENGER_CREATE_INFO_EXT;
        createInfo.messageSeverity = VkDebugUtilsMessageSeverityFlagsEXT.of(
                VkDebugUtilsMessageSeverityFlagBitsEXT.VK_DEBUG_UTILS_MESSAGE_SEVERITY_VERBOSE_BIT_EXT,
                VkDebugUtilsMessageSeverityFlagBitsEXT.VK_DEBUG_UTILS_MESSAGE_SEVERITY_WARNING_BIT_EXT,
                VkDebugUtilsMessageSeverityFlagBitsEXT.VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT);
        createInfo.messageType = VkDebugUtilsMessageTypeFlagsEXT.of(
                VkDebugUtilsMessageTypeFlagBitsEXT.VK_DEBUG_UTILS_MESSAGE_TYPE_GENERAL_BIT_EXT,
                VkDebugUtilsMessageTypeFlagBitsEXT.VK_DEBUG_UTILS_MESSAGE_TYPE_VALIDATION_BIT_EXT,
                VkDebugUtilsMessageTypeFlagBitsEXT.VK_DEBUG_UTILS_MESSAGE_TYPE_PERFORMANCE_BIT_EXT);
        createInfo.pfnUserCallback = HelloTriangleApplication01::debugCallback;
        return createInfo;
    }

    private void setupDebugMessenger() {
        if (!ENABLE_VALIDATION_LAYERS) return;

        VkDebugUtilsMessengerCreateInfoEXT createInfo = populateDebugMessengerCreateInfo();

        if (Vulkan.vkCreateDebugUtilsMessengerEXT(instance, createInfo, null, v -> debugMessenger = v) != VK_SUCCESS) {
            throw new FailureException("failed to set up debug messenger!");
        }
    }

    private void createSurface() {
        if (GLFW.glfwCreateWindowSurface(instance, window, null, v -> surface = v) != VK_SUCCESS) {
            throw new FailureException("failed to create window surface!");
        }
    }

    private void pickPhysicalDevice() {
        int deviceCount;
        ByRef<Integer> outDeviceCount = new ByRef<>(0);
        Vulkan.vkEnumeratePhysicalDevices(instance, outDeviceCount, null);
        deviceCount = outDeviceCount.get();

        if (deviceCount == 0) {
            throw new FailureException("failed to find GPUs with Vulkan support!");
        }

        VkPhysicalDevice[] physicalDevices = new VkPhysicalDevice[deviceCount];
        Vulkan.vkEnumeratePhysicalDevices(instance, outDeviceCount, physicalDevices);

        for (VkPhysicalDevice testPhysicalDevice : physicalDevices) {
            if (isDeviceSuitable(testPhysicalDevice)) {
                this.physicalDevice = testPhysicalDevice;
                break;
            }
        }

        if (physicalDevice == null) {
            throw new FailureException("failed to find a suitable GPU!");
        }
    }

    private void createLogicalDevice() {
        QueueFamilyIndices indices = findQueueFamilies(physicalDevice);

        List<VkDeviceQueueCreateInfo> queueCreateInfos = new ArrayList<>();
        Set<Integer> uniqueQueueFamilies = new HashSet<>(Arrays.asList(indices.graphicsFamily, indices.presentFamily));

        float queuePriority = 1.0f;
        for (int queueFamily : uniqueQueueFamilies) {
            VkDeviceQueueCreateInfo queueCreateInfo = new VkDeviceQueueCreateInfo();
            queueCreateInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO;
            queueCreateInfo.queueFamilyIndex = queueFamily;
            queueCreateInfo.queueCount = 1;
            queueCreateInfo.pQueuePriorities = queuePriority;

            queueCreateInfos.add(queueCreateInfo);
        }

        VkPhysicalDeviceFeatures deviceFeatures = new VkPhysicalDeviceFeatures();

        VkDeviceCreateInfo createInfo = new VkDeviceCreateInfo();
        createInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO;

        createInfo.queueCreateInfoCount = queueCreateInfos.size();
        createInfo.pQueueCreateInfos = queueCreateInfos.toArray(new VkDeviceQueueCreateInfo[]{});

        createInfo.pEnabledFeatures = deviceFeatures;

        createInfo.enabledExtensionCount = deviceExtensions.length;
        createInfo.ppEnabledExtensionNames = deviceExtensions;

        if (ENABLE_VALIDATION_LAYERS) {
            createInfo.enabledLayerCount = validationLayers.length;
            createInfo.ppEnabledLayerNames = validationLayers;
        } else {
            createInfo.enabledLayerCount = 0;
        }

        if (Vulkan.vkCreateDevice(physicalDevice, createInfo, null, s -> device = s) != VK_SUCCESS) {
            throw new FailureException("failed to create logical device!");
        }

        Vulkan.vkGetDeviceQueue(device, indices.graphicsFamily, 0, s -> graphicsQueue = s);
        Vulkan.vkGetDeviceQueue(device, indices.presentFamily, 0, s -> presentQueue = s);
    }

    private void createSwapChain() {
        SwapChainSupportDetails swapChainSupport = querySwapChainSupport(physicalDevice);

        VkSurfaceFormatKHR surfaceFormat = chooseSwapSurfaceFormat(swapChainSupport.formats);
        VkPresentModeKHR presentMode = chooseSwapPresentMode(swapChainSupport.presentModes);
        VkExtent2D extent = chooseSwapExtent(swapChainSupport.capabilities);

        int imageCount = swapChainSupport.capabilities.minImageCount + 1;
        if (swapChainSupport.capabilities.maxImageCount > 0 && imageCount > swapChainSupport.capabilities.maxImageCount) {
            imageCount = swapChainSupport.capabilities.maxImageCount;
        }

        VkSwapchainCreateInfoKHR createInfo = new VkSwapchainCreateInfoKHR();
        createInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_SWAPCHAIN_CREATE_INFO_KHR;
        createInfo.surface = surface;

        createInfo.minImageCount = imageCount;
        createInfo.imageFormat = surfaceFormat.format;
        createInfo.imageColorSpace = surfaceFormat.colorSpace;
        createInfo.imageExtent = extent;
        createInfo.imageArrayLayers = 1;
        createInfo.imageUsage = VkImageUsageFlags.of(VkImageUsageFlagBits.VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT);

        QueueFamilyIndices indices = findQueueFamilies(physicalDevice);
        int[] queueFamilyIndices = new int[]{indices.graphicsFamily, indices.presentFamily};

        if (indices.graphicsFamily.equals(indices.presentFamily)) {
            createInfo.imageSharingMode = VkSharingMode.VK_SHARING_MODE_CONCURRENT;
            createInfo.queueFamilyIndexCount = 2;
            createInfo.pQueueFamilyIndices = queueFamilyIndices;
        } else {
            createInfo.imageSharingMode = VkSharingMode.VK_SHARING_MODE_EXCLUSIVE;
        }

        createInfo.preTransform = swapChainSupport.capabilities.currentTransform;
        createInfo.compositeAlpha = VkCompositeAlphaFlagBitsKHR.VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR;
        createInfo.presentMode = presentMode;
        createInfo.clipped = VkBool32.VK_TRUE;

        createInfo.oldSwapchain = null;

        if (Vulkan.vkCreateSwapchainKHR(device, createInfo, null, s -> swapChain = s) != VK_SUCCESS) {
            throw new FailureException("failed to create swap chain!");
        }

        ByRef<Integer> refImageCount = new ByRef<>(imageCount);
        Vulkan.vkGetSwapchainImagesKHR(device, swapChain, refImageCount, null);
        swapChainImages = new VkImage[refImageCount.get()];
        Vulkan.vkGetSwapchainImagesKHR(device, swapChain, refImageCount, swapChainImages);

        swapChainImageFormat = surfaceFormat.format;
        swapChainExtent = extent;
    }

    private void createImageViews() {
        swapChainImageViews = new VkImageView[swapChainImages.length];

        for (int i = 0; i < swapChainImages.length; i++) {
            VkImageViewCreateInfo createInfo = new VkImageViewCreateInfo();

            createInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_IMAGE_VIEW_CREATE_INFO;
            createInfo.image = swapChainImages[i];
            createInfo.viewType = VkImageViewType.VK_IMAGE_VIEW_TYPE_2D;
            createInfo.format = swapChainImageFormat;
            createInfo.components.r = VkComponentSwizzle.VK_COMPONENT_SWIZZLE_IDENTITY;
            createInfo.components.g = VkComponentSwizzle.VK_COMPONENT_SWIZZLE_IDENTITY;
            createInfo.components.b = VkComponentSwizzle.VK_COMPONENT_SWIZZLE_IDENTITY;
            createInfo.components.a = VkComponentSwizzle.VK_COMPONENT_SWIZZLE_IDENTITY;
            createInfo.subresourceRange.aspectMask = VkImageAspectFlagBits.VK_IMAGE_ASPECT_COLOR_BIT;
            createInfo.subresourceRange.baseMipLevel = 0;
            createInfo.subresourceRange.levelCount = 1;
            createInfo.subresourceRange.baseArrayLayer = 0;
            createInfo.subresourceRange.layerCount = 1;

            final int pos = i;

            if (Vulkan.vkCreateImageView(device, createInfo, null, s -> swapChainImageViews[pos] = s) != VK_SUCCESS) {
                throw new FailureException("failed to create image views!");
            }
        }
    }

    private void createRenderPass() {
        VkAttachmentDescription colorAttachment = new VkAttachmentDescription();
        colorAttachment.format = swapChainImageFormat;
        colorAttachment.samples = VkSampleCountFlagBits.VK_SAMPLE_COUNT_1_BIT;
        colorAttachment.loadOp = VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_CLEAR;
        colorAttachment.storeOp = VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_STORE;
        colorAttachment.stencilLoadOp = VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_DONT_CARE;
        colorAttachment.stencilStoreOp = VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_DONT_CARE;
        colorAttachment.initialLayout = VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED;
        colorAttachment.finalLayout = VkImageLayout.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR;

        VkAttachmentReference colorAttachmentRef = new VkAttachmentReference();
        colorAttachmentRef.attachment = 0;
        colorAttachmentRef.layout = VkImageLayout.VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL;

        VkSubpassDescription subpass = new VkSubpassDescription();
        subpass.pipelineBindPoint = VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS;
        subpass.colorAttachmentCount = 1;
        subpass.pColorAttachments = new VkAttachmentReference[]{colorAttachmentRef};

        VkSubpassDependency dependency = new VkSubpassDependency();
        dependency.srcSubpass = Vulkan.VK_SUBPASS_EXTERNAL;
        dependency.dstSubpass = 0;
        dependency.srcStageMask = VkPipelineStageFlagBits.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT;
        dependency.srcAccessMask = 0;
        dependency.dstStageMask = VkPipelineStageFlagBits.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT;
        dependency.dstAccessMask = VkAccessFlagBits.VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT;

        VkRenderPassCreateInfo renderPassInfo = new VkRenderPassCreateInfo();
        renderPassInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_RENDER_PASS_CREATE_INFO;
        renderPassInfo.attachmentCount = 1;
        renderPassInfo.pAttachments = new VkAttachmentDescription[]{colorAttachment};
        renderPassInfo.subpassCount = 1;
        renderPassInfo.pSubpasses = new VkSubpassDescription[]{subpass};
        renderPassInfo.dependencyCount = 1;
        renderPassInfo.pDependencies = new VkSubpassDependency[]{dependency};

        if (Vulkan.vkCreateRenderPass(device, renderPassInfo, null, v -> renderPass = v) != VK_SUCCESS) {
            throw new FailureException("failed to create render pass!");
        }
    }

    private void createGraphicsPipeline() {
        VkShaderModule vertShaderModule = createShaderModule(ShaderVert01.class);
        VkShaderModule fragShaderModule = createShaderModule(ShaderFrag01.class);

        VkPipelineShaderStageCreateInfo vertShaderStageInfo = new VkPipelineShaderStageCreateInfo();
        vertShaderStageInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO;
        vertShaderStageInfo.stage = VkShaderStageFlagBits.VK_SHADER_STAGE_VERTEX_BIT;
        vertShaderStageInfo.module = vertShaderModule;
        vertShaderStageInfo.pName = "main";

        VkPipelineShaderStageCreateInfo fragShaderStageInfo = new VkPipelineShaderStageCreateInfo();
        fragShaderStageInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO;
        fragShaderStageInfo.stage = VkShaderStageFlagBits.VK_SHADER_STAGE_FRAGMENT_BIT;
        fragShaderStageInfo.module = fragShaderModule;
        fragShaderStageInfo.pName = "main";

        VkPipelineShaderStageCreateInfo[] shaderStages = {vertShaderStageInfo, fragShaderStageInfo};

        VkPipelineVertexInputStateCreateInfo vertexInputInfo = new VkPipelineVertexInputStateCreateInfo();
        vertexInputInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_VERTEX_INPUT_STATE_CREATE_INFO;
        vertexInputInfo.vertexBindingDescriptionCount = 0;
        vertexInputInfo.vertexAttributeDescriptionCount = 0;

        VkPipelineInputAssemblyStateCreateInfo inputAssembly = new VkPipelineInputAssemblyStateCreateInfo();
        inputAssembly.sType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_INPUT_ASSEMBLY_STATE_CREATE_INFO;
        inputAssembly.topology = VkPrimitiveTopology.VK_PRIMITIVE_TOPOLOGY_TRIANGLE_LIST;
        inputAssembly.primitiveRestartEnable = VkBool32.VK_FALSE;

        VkViewport viewport = new VkViewport();
        viewport.x = 0.0f;
        viewport.y = 0.0f;
        viewport.width = (float) swapChainExtent.width;
        viewport.height = (float) swapChainExtent.height;
        viewport.minDepth = 0.0f;
        viewport.maxDepth = 1.0f;

        VkRect2D scissor = new VkRect2D();
        scissor.offset = VkOffset2D.create(0, 0);
        scissor.extent = swapChainExtent;

        VkPipelineViewportStateCreateInfo viewportState = new VkPipelineViewportStateCreateInfo();
        viewportState.sType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_VIEWPORT_STATE_CREATE_INFO;
        viewportState.viewportCount = 1;
        viewportState.pViewports = new VkViewport[]{viewport};
        viewportState.scissorCount = 1;
        viewportState.pScissors = new VkRect2D[]{scissor};

        VkPipelineRasterizationStateCreateInfo rasterizer = new VkPipelineRasterizationStateCreateInfo();
        rasterizer.sType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_RASTERIZATION_STATE_CREATE_INFO;
        rasterizer.depthClampEnable = VkBool32.VK_FALSE;
        rasterizer.rasterizerDiscardEnable = VkBool32.VK_FALSE;
        rasterizer.polygonMode = VkPolygonMode.VK_POLYGON_MODE_FILL;
        rasterizer.lineWidth = 1.0f;
        rasterizer.cullMode = VkCullModeFlagBits.VK_CULL_MODE_BACK_BIT;
        rasterizer.frontFace = VkFrontFace.VK_FRONT_FACE_CLOCKWISE;
        rasterizer.depthBiasEnable = VkBool32.VK_FALSE;

        VkPipelineMultisampleStateCreateInfo multisampling = new VkPipelineMultisampleStateCreateInfo();
        multisampling.sType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_MULTISAMPLE_STATE_CREATE_INFO;
        multisampling.sampleShadingEnable = VkBool32.VK_FALSE;
        multisampling.rasterizationSamples = VkSampleCountFlagBits.VK_SAMPLE_COUNT_1_BIT;

        VkPipelineColorBlendAttachmentState colorBlendAttachment = new VkPipelineColorBlendAttachmentState();
        colorBlendAttachment.colorWriteMask = VkColorComponentFlagBits.VK_COLOR_COMPONENT_R_BIT |
                VkColorComponentFlagBits.VK_COLOR_COMPONENT_G_BIT |
                VkColorComponentFlagBits.VK_COLOR_COMPONENT_B_BIT |
                VkColorComponentFlagBits.VK_COLOR_COMPONENT_A_BIT;
        colorBlendAttachment.blendEnable = VkBool32.VK_FALSE;

        VkPipelineColorBlendStateCreateInfo colorBlending = new VkPipelineColorBlendStateCreateInfo();
        colorBlending.sType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_COLOR_BLEND_STATE_CREATE_INFO;
        colorBlending.logicOpEnable = VkBool32.VK_FALSE;
        colorBlending.logicOp = VkLogicOp.VK_LOGIC_OP_COPY;
        colorBlending.attachmentCount = 1;
        colorBlending.pAttachments = new VkPipelineColorBlendAttachmentState[]{colorBlendAttachment};
        colorBlending.blendConstants[0] = 0.0f;
        colorBlending.blendConstants[1] = 0.0f;
        colorBlending.blendConstants[2] = 0.0f;
        colorBlending.blendConstants[3] = 0.0f;

        VkPipelineLayoutCreateInfo pipelineLayoutInfo = new VkPipelineLayoutCreateInfo();
        pipelineLayoutInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_LAYOUT_CREATE_INFO;
        pipelineLayoutInfo.setLayoutCount = 0;
        pipelineLayoutInfo.pushConstantRangeCount = 0;

        if (Vulkan.vkCreatePipelineLayout(device, pipelineLayoutInfo, null, v -> pipelineLayout = v) != VK_SUCCESS) {
            throw new FailureException("failed to create pipeline layout!");
        }

        VkGraphicsPipelineCreateInfo pipelineInfo = new VkGraphicsPipelineCreateInfo();
        pipelineInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_GRAPHICS_PIPELINE_CREATE_INFO;
        pipelineInfo.stageCount = 2;
        pipelineInfo.pStages = shaderStages;
        pipelineInfo.pVertexInputState = vertexInputInfo;
        pipelineInfo.pInputAssemblyState = inputAssembly;
        pipelineInfo.pViewportState = viewportState;
        pipelineInfo.pRasterizationState = rasterizer;
        pipelineInfo.pMultisampleState = multisampling;
        pipelineInfo.pColorBlendState = colorBlending;
        pipelineInfo.layout = pipelineLayout;
        pipelineInfo.renderPass = renderPass;
        pipelineInfo.subpass = 0;
        pipelineInfo.basePipelineHandle = null;

        VkPipeline[] result = {null};
        if (Vulkan.vkCreateGraphicsPipelines(device, null, 1, new VkGraphicsPipelineCreateInfo[]{pipelineInfo},
                null, result) != VK_SUCCESS) {
            throw new FailureException("failed to create graphics pipeline!");
        }
        graphicsPipeline = result[0];

        Vulkan.vkDestroyShaderModule(device, fragShaderModule, null);
        Vulkan.vkDestroyShaderModule(device, vertShaderModule, null);
    }

    private void createFramebuffers() {
        swapChainFramebuffers = new VkFramebuffer[swapChainImageViews.length];

        for (int i = 0; i < swapChainImageViews.length; i++) {
            VkFramebufferCreateInfo framebufferInfo = new VkFramebufferCreateInfo();
            framebufferInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_FRAMEBUFFER_CREATE_INFO;
            framebufferInfo.renderPass = renderPass;
            framebufferInfo.attachmentCount = 1;
            framebufferInfo.pAttachments = new VkImageView[]{swapChainImageViews[i]};
            framebufferInfo.width = swapChainExtent.width;
            framebufferInfo.height = swapChainExtent.height;
            framebufferInfo.layers = 1;

            final int pos = i;

            if (Vulkan.vkCreateFramebuffer(device, framebufferInfo, null, v -> swapChainFramebuffers[pos] = v) != VK_SUCCESS) {
                throw new FailureException("failed to create framebuffer!");
            }
        }
    }

    private void createCommandPool() {
        QueueFamilyIndices queueFamilyIndices = findQueueFamilies(physicalDevice);

        VkCommandPoolCreateInfo poolInfo = new VkCommandPoolCreateInfo();
        poolInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO;
        poolInfo.queueFamilyIndex = queueFamilyIndices.graphicsFamily;

        if (Vulkan.vkCreateCommandPool(device, poolInfo, null, v -> commandPool = v) != VK_SUCCESS) {
            throw new FailureException("failed to create command pool!");
        }
    }

    private void createCommandBuffers() {
        commandBuffers = new VkCommandBuffer[swapChainFramebuffers.length];

        VkCommandBufferAllocateInfo allocInfo = new VkCommandBufferAllocateInfo();
        allocInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO;
        allocInfo.commandPool = commandPool;
        allocInfo.level = VkCommandBufferLevel.VK_COMMAND_BUFFER_LEVEL_PRIMARY;
        allocInfo.commandBufferCount = commandBuffers.length;

        if (Vulkan.vkAllocateCommandBuffers(device, allocInfo, commandBuffers) != VK_SUCCESS) {
            throw new FailureException("failed to allocate command buffers!");
        }

        for (int i = 0; i < commandBuffers.length; i++) {
            VkCommandBufferBeginInfo beginInfo = new VkCommandBufferBeginInfo();
            beginInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO;

            if (Vulkan.vkBeginCommandBuffer(commandBuffers[i], beginInfo) != VK_SUCCESS) {
                throw new FailureException("failed to begin recording command buffer!");
            }

            VkRenderPassBeginInfo renderPassInfo = new VkRenderPassBeginInfo();
            renderPassInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_RENDER_PASS_BEGIN_INFO;
            renderPassInfo.renderPass = renderPass;
            renderPassInfo.framebuffer = swapChainFramebuffers[i];
            renderPassInfo.renderArea.offset = VkOffset2D.create(0, 0);
            renderPassInfo.renderArea.extent = swapChainExtent;

            VkClearValue clearColor = VkClearValue.create(0.0f, 0.0f, 0.0f, 1.0f);
            renderPassInfo.clearValueCount = 1;
            renderPassInfo.pClearValues = new VkClearValue[]{clearColor};

            Vulkan.vkCmdBeginRenderPass(commandBuffers[i], renderPassInfo, VkSubpassContents.VK_SUBPASS_CONTENTS_INLINE);

            Vulkan.vkCmdBindPipeline(commandBuffers[i], VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS, graphicsPipeline);

            Vulkan.vkCmdDraw(commandBuffers[i], 3, 1, 0, 0);

            Vulkan.vkCmdEndRenderPass(commandBuffers[i]);

            if (Vulkan.vkEndCommandBuffer(commandBuffers[i]) != VK_SUCCESS) {
                throw new FailureException("failed to record command buffer!");
            }
        }
    }

    private void createSyncObjects() {
        imageAvailableSemaphores = new VkSemaphore[MAX_FRAMES_IN_FLIGHT];
        renderFinishedSemaphores = new VkSemaphore[MAX_FRAMES_IN_FLIGHT];
        inFlightFences = new VkFence[MAX_FRAMES_IN_FLIGHT];
        imagesInFlight = new VkFence[swapChainImages.length];

        VkSemaphoreCreateInfo semaphoreInfo = new VkSemaphoreCreateInfo();
        semaphoreInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_SEMAPHORE_CREATE_INFO;

        VkFenceCreateInfo fenceInfo = new VkFenceCreateInfo();
        fenceInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_FENCE_CREATE_INFO;
        fenceInfo.flags = VkFenceCreateFlagBits.VK_FENCE_CREATE_SIGNALED_BIT;

        for (int i = 0; i < MAX_FRAMES_IN_FLIGHT; i++) {
            final int pos = i;
            if (Vulkan.vkCreateSemaphore(device, semaphoreInfo, null, v -> imageAvailableSemaphores[pos] = v) != VK_SUCCESS ||
                    Vulkan.vkCreateSemaphore(device, semaphoreInfo, null, v -> renderFinishedSemaphores[pos] = v) != VK_SUCCESS ||
                    Vulkan.vkCreateFence(device, fenceInfo, null, v -> inFlightFences[pos] = v) != VK_SUCCESS) {
                throw new FailureException("failed to create synchronization objects for a frame!");
            }
        }
    }

    private void drawFrame() {
        Vulkan.vkWaitForFences(device, 1, new VkFence[]{inFlightFences[currentFrame]}, VkBool32.VK_TRUE, Long.MAX_VALUE);

        final ByRef<Integer> refImageIndex = new ByRef<>();
        int imageIndex;
        Vulkan.vkAcquireNextImageKHR(device, swapChain, Long.MAX_VALUE,
                imageAvailableSemaphores[currentFrame], null, refImageIndex);
        imageIndex = refImageIndex.get();

        if (imagesInFlight[imageIndex] != null) {
            Vulkan.vkWaitForFences(device, 1, new VkFence[]{imagesInFlight[imageIndex]},
                    VkBool32.VK_TRUE, Long.MAX_VALUE);
        }
        imagesInFlight[imageIndex] = inFlightFences[currentFrame];

        VkSubmitInfo submitInfo = new VkSubmitInfo();
        submitInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_SUBMIT_INFO;

        VkSemaphore[] waitSemaphores = {imageAvailableSemaphores[currentFrame]};
        int[] waitStages = {VkPipelineStageFlagBits.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT};
        submitInfo.waitSemaphoreCount = 1;
        submitInfo.pWaitSemaphores = waitSemaphores;
        submitInfo.pWaitDstStageMask = waitStages;

        submitInfo.commandBufferCount = 1;
        submitInfo.pCommandBuffers = new VkCommandBuffer[]{commandBuffers[imageIndex]};

        VkSemaphore[] signalSemaphores = {renderFinishedSemaphores[currentFrame]};
        submitInfo.signalSemaphoreCount = 1;
        submitInfo.pSignalSemaphores = signalSemaphores;

        Vulkan.vkResetFences(device, 1, new VkFence[]{inFlightFences[currentFrame]});

        if (Vulkan.vkQueueSubmit(graphicsQueue, 1, new VkSubmitInfo[]{submitInfo}, inFlightFences[currentFrame])
                != VK_SUCCESS) {
            throw new FailureException("failed to submit draw command buffer!");
        }

        VkPresentInfoKHR presentInfo = new VkPresentInfoKHR();
        presentInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_PRESENT_INFO_KHR;

        presentInfo.waitSemaphoreCount = 1;
        presentInfo.pWaitSemaphores = signalSemaphores;

        VkSwapchainKHR[] swapChains = {swapChain};
        presentInfo.swapchainCount = 1;
        presentInfo.pSwapchains = swapChains;

        presentInfo.pImageIndices = new int[]{imageIndex};

        Vulkan.vkQueuePresentKHR(presentQueue, presentInfo);

        currentFrame = (currentFrame + 1) % MAX_FRAMES_IN_FLIGHT;
    }

    private VkShaderModule createShaderModule(Class<?> shaderCode) {
        VkShaderModuleCreateInfo createInfo = new VkShaderModuleCreateInfo();
        createInfo.sType = VkStructureType.VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO;
        createInfo.pCode = shaderCode;

        ByRef<VkShaderModule> shaderModule = new ByRef<>();
        if (Vulkan.vkCreateShaderModule(device, createInfo, null, shaderModule) != VK_SUCCESS) {
            throw new FailureException("failed to create shader module!");
        }

        return shaderModule.get();
    }

    private VkSurfaceFormatKHR chooseSwapSurfaceFormat(VkSurfaceFormatKHR[] availableFormats) {
        for (VkSurfaceFormatKHR availableFormat : availableFormats) {
            if (availableFormat.format == VkFormat.VK_FORMAT_B8G8R8A8_SRGB && availableFormat.colorSpace == VkColorSpaceKHR.VK_COLOR_SPACE_SRGB_NONLINEAR_KHR) {
                return availableFormat;
            }
        }

        return availableFormats[0];
    }

    private VkPresentModeKHR chooseSwapPresentMode(VkPresentModeKHR[] availablePresentModes) {
        for (VkPresentModeKHR availablePresentMode : availablePresentModes) {
            if (availablePresentMode == VkPresentModeKHR.VK_PRESENT_MODE_MAILBOX_KHR) {
                return availablePresentMode;
            }
        }

        return VkPresentModeKHR.VK_PRESENT_MODE_FIFO_KHR;
    }

    private VkExtent2D chooseSwapExtent(VkSurfaceCapabilitiesKHR capabilities) {
        if (capabilities.currentExtent.width != Integer.MAX_VALUE) {
            return capabilities.currentExtent;
        } else {
            VkExtent2D actualExtent = VkExtent2D.create(WIDTH, HEIGHT);

            actualExtent.width = Math.max(capabilities.minImageExtent.width, Math.min(capabilities.maxImageExtent.width, actualExtent.width));
            actualExtent.height = Math.max(capabilities.minImageExtent.height, Math.min(capabilities.maxImageExtent.height, actualExtent.height));

            return actualExtent;
        }
    }

    private SwapChainSupportDetails querySwapChainSupport(VkPhysicalDevice device) {
        final SwapChainSupportDetails details = new SwapChainSupportDetails();
        Vulkan.vkGetPhysicalDeviceSurfaceCapabilitiesKHR(device, surface, v -> details.capabilities = v);

        ByRef<Integer> formatCount = new ByRef<>();
        Vulkan.vkGetPhysicalDeviceSurfaceFormatsKHR(device, surface, formatCount, null);

        if (formatCount.get() != 0) {
            details.formats = new VkSurfaceFormatKHR[formatCount.get()];
            Vulkan.vkGetPhysicalDeviceSurfaceFormatsKHR(device, surface, formatCount, details.formats);
        }

        ByRef<Integer> presentModeCount = new ByRef<>();
        Vulkan.vkGetPhysicalDeviceSurfacePresentModesKHR(device, surface, presentModeCount, null);

        if (presentModeCount.get() != 0) {
            details.presentModes = new VkPresentModeKHR[presentModeCount.get()];
            Vulkan.vkGetPhysicalDeviceSurfacePresentModesKHR(device, surface, presentModeCount, details.presentModes);
        }

        return details;
    }

    private boolean isDeviceSuitable(VkPhysicalDevice device) {
        QueueFamilyIndices indices = findQueueFamilies(device);

        boolean extensionsSupported = checkDeviceExtensionSupport(device);

        boolean swapChainAdequate = false;
        if (extensionsSupported) {
            SwapChainSupportDetails swapChainSupport = querySwapChainSupport(device);
            swapChainAdequate = (swapChainSupport.formats.length != 0) && (swapChainSupport.presentModes.length != 0);
        }

        return indices.isComplete() && extensionsSupported && swapChainAdequate;
    }

    private boolean checkDeviceExtensionSupport(VkPhysicalDevice device) {
        ByRef<Integer> extensionCount = new ByRef<>();
        Vulkan.vkEnumerateDeviceExtensionProperties(device, null, extensionCount, null);

        VkExtensionProperties[] availableExtensions = new VkExtensionProperties[extensionCount.get()];
        Vulkan.vkEnumerateDeviceExtensionProperties(device, null, extensionCount, availableExtensions);

        Set<String> requiredExtensions = new HashSet<>(Arrays.asList(deviceExtensions));

        for (VkExtensionProperties extension : availableExtensions) {
            requiredExtensions.remove(extension.extensionName);
        }

        return requiredExtensions.isEmpty();
    }

    private QueueFamilyIndices findQueueFamilies(VkPhysicalDevice device) {
        QueueFamilyIndices indices = new QueueFamilyIndices();

        ByRef<Integer> queueFamilyCount = new ByRef<>();
        Vulkan.vkGetPhysicalDeviceQueueFamilyProperties(device, queueFamilyCount, null);

        VkQueueFamilyProperties[] queueFamilies = new VkQueueFamilyProperties[queueFamilyCount.get()];
        Vulkan.vkGetPhysicalDeviceQueueFamilyProperties(device, queueFamilyCount, queueFamilies);

        int i = 0;
        for (VkQueueFamilyProperties queueFamily : queueFamilies) {
            if (queueFamily.queueFlags.contains(VkQueueFlagBits.VK_QUEUE_GRAPHICS_BIT)) {
                indices.graphicsFamily = i;
            }

            ByRef<VkBool32> presentSupport = new ByRef<>(VkBool32.VK_FALSE);
            Vulkan.vkGetPhysicalDeviceSurfaceSupportKHR(device, i, surface, presentSupport);

            if (presentSupport.get() == VkBool32.VK_TRUE) {
                indices.presentFamily = i;
            }

            if (indices.isComplete()) {
                break;
            }

            i++;
        }

        return indices;
    }

    private List<String> getRequiredExtensions() {
        ByRef<Integer> glfwExtensionCount = new ByRef<>();
        List<String> extensions = new ArrayList<>(GLFW.glfwGetRequiredInstanceExtensions(glfwExtensionCount));

        if (ENABLE_VALIDATION_LAYERS) {
            extensions.add(VkExtensionNames.VK_EXT_DEBUG_UTILS_EXTENSION_NAME);
        }

        return extensions;
    }

    private boolean checkValidationLayerSupport() {
        ByRef<Integer> layerCount = new ByRef<>();
        Vulkan.vkEnumerateInstanceLayerProperties(layerCount, null);

        VkLayerProperties[] availableLayers = new VkLayerProperties[layerCount.get()];
        Vulkan.vkEnumerateInstanceLayerProperties(layerCount, availableLayers);

        for (String layerName : validationLayers) {
            boolean layerFound = false;

            for (VkLayerProperties layerProperties : availableLayers) {
                if (layerName.equals(layerProperties.layerName)) {
                    layerFound = true;
                    break;
                }
            }

            if (!layerFound) {
                return false;
            }
        }

        return true;
    }

    private static VkBool32 debugCallback(VkDebugUtilsMessageSeverityFlagBitsEXT messageSeverity,
                                          VkDebugUtilsMessageTypeFlagsEXT messageType,
                                          VkDebugUtilsMessengerCallbackDataEXT pCallbackData,
                                          Object pUserData) {
        LoggerFactory
                .getLogger(HelloTriangleApplication01.class)
                .error("validation layer: {}", pCallbackData.pMessage);
        return VkBool32.VK_FALSE;
    }

    public static void main(String[] args) {
        HelloTriangleApplication01 app = new HelloTriangleApplication01();
        try {
            app.run();
        } finally {
            GLFW.glfwTerminate();
        }
    }
}