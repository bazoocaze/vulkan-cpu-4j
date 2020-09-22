package com.github.bazoocaze.vulkancpu4j.vulkan.data;

public class VkExtent3D {

    public int width;
    public int height;
    public int depth;

    public VkExtent3D(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public static VkExtent3D create(int width, int height, int depth) {
        return new VkExtent3D(width, height, depth);
    }
}
