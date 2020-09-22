package com.github.bazoocaze.vulkancpu4j.vulkan;

public class VkFramebufferCreateInfo extends VkStructureBase {

    public int flags;
    public VkRenderPass renderPass;
    public int attachmentCount;
    public VkImageView[] pAttachments;
    public int width;
    public int height;
    public int layers;
}
