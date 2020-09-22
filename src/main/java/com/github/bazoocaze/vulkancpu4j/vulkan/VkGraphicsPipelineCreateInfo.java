package com.github.bazoocaze.vulkancpu4j.vulkan;

public class VkGraphicsPipelineCreateInfo extends VkStructureBase {

    public int flags;
    public int stageCount;
    public VkPipelineShaderStageCreateInfo[] pStages;
    public VkPipelineVertexInputStateCreateInfo pVertexInputState;
    public VkPipelineInputAssemblyStateCreateInfo pInputAssemblyState;
    public VkPipelineTessellationStateCreateInfo pTessellationState;
    public VkPipelineViewportStateCreateInfo pViewportState;
    public VkPipelineRasterizationStateCreateInfo pRasterizationState;
    public VkPipelineMultisampleStateCreateInfo pMultisampleState;
    public VkPipelineDepthStencilStateCreateInfo pDepthStencilState;
    public VkPipelineColorBlendStateCreateInfo pColorBlendState;
    public VkPipelineDynamicStateCreateInfo pDynamicState;
    public VkPipelineLayout layout;
    public VkRenderPass renderPass;
    public int subpass;
    public VkPipeline basePipelineHandle;
    public int basePipelineIndex;
}
