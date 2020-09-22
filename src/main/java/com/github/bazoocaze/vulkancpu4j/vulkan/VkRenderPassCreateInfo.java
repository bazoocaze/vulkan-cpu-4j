package com.github.bazoocaze.vulkancpu4j.vulkan;

public class VkRenderPassCreateInfo extends VkStructureBase {

    public int flags;
    public int attachmentCount;
    public VkAttachmentDescription[] pAttachments;
    public int subpassCount;
    public VkSubpassDescription[] pSubpasses;
    public int dependencyCount;
    public VkSubpassDependency[] pDependencies;
}
