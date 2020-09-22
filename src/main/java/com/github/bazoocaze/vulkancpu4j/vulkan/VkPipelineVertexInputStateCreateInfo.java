package com.github.bazoocaze.vulkancpu4j.vulkan;

public class VkPipelineVertexInputStateCreateInfo extends VkStructureBase {

    public int flags;
    public int vertexBindingDescriptionCount;
    public VkVertexInputBindingDescription[] pVertexBindingDescriptions;
    public int vertexAttributeDescriptionCount;
    public VkVertexInputAttributeDescription[] pVertexAttributeDescriptions;
}
