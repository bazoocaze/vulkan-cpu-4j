package com.github.bazoocaze.vulkancpu4j.vulkan.internal.devices.software;

import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkImage;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkSwapchainCreateInfoKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkExtent3D;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkImageUsageFlagBits;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.VkImageUsageFlags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class SoftwareImage implements VkImage {

    private static final Set<VkFormat> SUPPORTED_FORMATS = Set.of(
            VkFormat.VK_FORMAT_B8G8R8A8_UNORM,
            VkFormat.VK_FORMAT_R8G8B8A8_UNORM,
            VkFormat.VK_FORMAT_D32_SFLOAT);

    private final SoftwareDevice device;
    private final VkImageCreateInfo createInfo;

    public SoftwareImage(SoftwareDevice device, VkImageCreateInfo createInfo) {
        this.device = device;
        this.createInfo = createInfo;
    }

    public static VkResult createSwapchainImage(SoftwareDevice device,
                                                VkSwapchainCreateInfoKHR swapchainInfo,
                                                OutRef<VkImage> image) {
        VkResult validationResult = validateCreateSwapChainImage(swapchainInfo);
        if (validationResult != VkResult.VK_SUCCESS) {
            return validationResult;
        }

        VkImageCreateInfo createInfo = new VkImageCreateInfo();
        createInfo.extent = VkExtent3D.create(swapchainInfo.imageExtent.width, swapchainInfo.imageExtent.height, 1);
        createInfo.format = swapchainInfo.imageFormat;
        createInfo.imageType = VkImageType.VK_IMAGE_TYPE_2D;
        createInfo.arrayLayers = swapchainInfo.imageArrayLayers;
        createInfo.sharingMode = swapchainInfo.imageSharingMode;
        createInfo.usage = VkImageUsageFlags.of(
                VkImageUsageFlagBits.VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT,
                VkImageUsageFlagBits.VK_IMAGE_USAGE_INPUT_ATTACHMENT_BIT);
        createInfo.samples = VkSampleCountFlagBits.VK_SAMPLE_COUNT_1_BIT;
        createInfo.mipLevels = 1;
        createInfo.initialLayout = VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED;

        return createImage(device, createInfo, image);
    }

    private static VkResult validateCreateSwapChainImage(VkSwapchainCreateInfoKHR swapchainInfo) {
        if (swapchainInfo.imageColorSpace != VkColorSpaceKHR.VK_COLOR_SPACE_SRGB_NONLINEAR_KHR) {
            getLogger().warn("Color space [{}] not supported. Expected: {}",
                    swapchainInfo.imageColorSpace,
                    VkColorSpaceKHR.VK_COLOR_SPACE_SRGB_NONLINEAR_KHR);
        }
        return VkResult.VK_SUCCESS;
    }

    private static Logger getLogger() {
        return LoggerFactory.getLogger(SoftwareImage.class);
    }

    public static VkResult createImage(SoftwareDevice device,
                                       VkImageCreateInfo createInfo,
                                       OutRef<VkImage> image) {
        VkResult validationResult = validate(createInfo);
        if (validationResult != VkResult.VK_SUCCESS) {
            return validationResult;
        }
        image.set(new SoftwareImage(device, createInfo));
        return VkResult.VK_SUCCESS;
    }

    private static VkResult validate(VkImageCreateInfo createInfo) {
        if (!SUPPORTED_FORMATS.contains(createInfo.format)) {
            getLogger().warn("Image format [{}] not supported", createInfo.format);
            return VkResult.VK_ERROR_FORMAT_NOT_SUPPORTED;
        }
        return VkResult.VK_SUCCESS;
    }
}
