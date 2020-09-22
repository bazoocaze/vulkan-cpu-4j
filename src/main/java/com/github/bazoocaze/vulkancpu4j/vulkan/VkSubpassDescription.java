package com.github.bazoocaze.vulkancpu4j.vulkan;

@SuppressWarnings("unused")
public class VkSubpassDescription {

    public int flags;
    public VkPipelineBindPoint pipelineBindPoint;
    public int inputAttachmentCount;
    public VkAttachmentReference[] pInputAttachments;
    public int colorAttachmentCount;
    public VkAttachmentReference[] pColorAttachments;
    public VkAttachmentReference[] pResolveAttachments;
    public VkAttachmentReference pDepthStencilAttachment;
    public int preserveAttachmentCount;
}
