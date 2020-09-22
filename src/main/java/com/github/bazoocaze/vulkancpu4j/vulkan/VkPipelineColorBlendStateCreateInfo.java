package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkBool32;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkLogicOp;

public class VkPipelineColorBlendStateCreateInfo extends VkStructureBase {

    public int flags;
    public VkBool32 logicOpEnable;
    public VkLogicOp logicOp;
    public int attachmentCount;
    public VkPipelineColorBlendAttachmentState[] pAttachments;
    public float[] blendConstants = {0f, 0f, 0f, 0f};
}
