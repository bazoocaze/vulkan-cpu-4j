package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.enums.*;

public class VkAttachmentDescription {

    public int flags;
    public VkFormat format;
    public VkSampleCountFlagBits samples;
    public VkAttachmentLoadOp loadOp;
    public VkAttachmentStoreOp storeOp;
    public VkAttachmentLoadOp stencilLoadOp;
    public VkAttachmentStoreOp stencilStoreOp;
    public VkImageLayout initialLayout;
    public VkImageLayout finalLayout;
}
