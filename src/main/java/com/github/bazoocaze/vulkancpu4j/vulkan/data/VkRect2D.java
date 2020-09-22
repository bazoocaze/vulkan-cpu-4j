package com.github.bazoocaze.vulkancpu4j.vulkan.data;

public class VkRect2D {

    public VkOffset2D offset;
    public VkExtent2D extent;

    public VkRect2D() {
        offset = new VkOffset2D();
        extent = new VkExtent2D(0, 0);
    }

    @Override
    public String toString() {
        return String.format("VkRect2D{left=%d, top=%d, width=%d, height=%d}", offset.x, offset.y,
                extent.width, extent.height);
    }
}
