package com.github.bazoocaze.vulkancpu4j.vulkan.data;

public class VkClearValue {

    public VkClearColorValue color;
    public VkClearDepthStencilValue depthStencil;

    public static VkClearValue create(float r, float g, float b, float a) {
        VkClearValue ret = new VkClearValue();
        ret.color = VkClearColorValue.create(r, g, b, a);
        return ret;
    }
}
