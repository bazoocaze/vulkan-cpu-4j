package com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkJavaSwingSurfaceCreateInfoEMU;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkSurfaceCapabilitiesKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkSurfaceFormatKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkSurfaceKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.data.VkExtent2D;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkColorSpaceKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkFormat;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkPresentModeKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;
import com.github.bazoocaze.vulkancpu4j.vulkan.flags.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.internal.VkArrayUtil;

import javax.swing.*;

public class SoftwareJavaSwingSurface implements VkSurfaceKHR {

    private static final VkSurfaceFormatKHR[] SURFACE_FORMATS = new VkSurfaceFormatKHR[]{
            VkSurfaceFormatKHR.create(
                    VkFormat.VK_FORMAT_B8G8R8A8_UNORM,
                    VkColorSpaceKHR.VK_COLOR_SPACE_SRGB_NONLINEAR_KHR)
    };

    private static final VkPresentModeKHR[] PRESENT_MODES = new VkPresentModeKHR[]{
            VkPresentModeKHR.VK_PRESENT_MODE_FIFO_KHR,
            VkPresentModeKHR.VK_PRESENT_MODE_IMMEDIATE_KHR
    };

    private final SoftwareInstance instance;
    private final VkJavaSwingSurfaceCreateInfoEMU createInfo;
    private final JFrame frame;
    private final VkExtent2D originalExtents;
    private VkSurfaceCapabilitiesKHR capabilities;
    private VkExtent2D currentSurfaceExtents;

    public SoftwareJavaSwingSurface(SoftwareInstance instance, VkJavaSwingSurfaceCreateInfoEMU createInfo) {
        this.instance = instance;
        this.createInfo = createInfo;
        this.frame = createInfo.frame;
        this.originalExtents = new VkExtent2D(frame.getWidth(), frame.getHeight());
        this.currentSurfaceExtents = this.originalExtents;

        populateCapabilities();
    }

    private void populateCapabilities() {
        capabilities = new VkSurfaceCapabilitiesKHR(
                1, 8, originalExtents, originalExtents, originalExtents, 1,
                VkSurfaceTransformFlagsKHR.of(VkSurfaceTransformFlagBitsKHR.VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR),
                VkSurfaceTransformFlagBitsKHR.VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR,
                VkCompositeAlphaFlagsKHR.of(VkCompositeAlphaFlagBitsKHR.VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR),
                VkImageUsageFlags.of(VkImageUsageFlagBits.VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT));
    }

    @Override
    public VkResult presentImage(SoftwareImage image, VkExtent2D imageExtent) {
        return VkResult.VK_ERROR_DEVICE_LOST;
    }

    @Override
    public VkResult getCapabilities(OutRef<VkSurfaceCapabilitiesKHR> surfaceCapabilities) {
        VkSurfaceCapabilitiesKHR ret = new VkSurfaceCapabilitiesKHR(capabilities);
        ret.currentExtent = currentSurfaceExtents;
        ret.maxImageExtent = capabilities.currentExtent;
        ret.minImageExtent = capabilities.currentExtent;
        surfaceCapabilities.set(ret);
        return VkResult.VK_SUCCESS;
    }

    @Override
    public VkResult getSurfaceFormats(ByRef<Integer> surfaceFormatCount, VkSurfaceFormatKHR[] surfaceFormats) {
        return VkArrayUtil.copyArray(SURFACE_FORMATS, surfaceFormatCount, surfaceFormats);
    }

    @Override
    public VkResult getPresentModes(ByRef<Integer> presentModeCount, VkPresentModeKHR[] presentModes) {
        return VkArrayUtil.copyArray(PRESENT_MODES, presentModeCount, presentModes);
    }

    @Override
    public void destroySurface() {
        // undo surface hooks
    }
}
