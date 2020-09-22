package com.github.bazoocaze.vulkancpu4j.vulkan.data;

public class VkExtent2D {

    public int width;
    public int height;

    public VkExtent2D(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static VkExtent2D create(int width, int height) {
        return new VkExtent2D(width, height);
    }

    @Override
    public String toString() {
        return String.format("VkExtend2D{width=%s, height=%s}", width, height);
    }
}
