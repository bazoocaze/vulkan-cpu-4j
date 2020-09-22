package com.github.bazoocaze.vulkancpu4j.vulkan.data;

public class VkOffset2D {

    public int x;
    public int y;

    public static VkOffset2D create(int x, int y) {
        VkOffset2D ret = new VkOffset2D();
        ret.x = x;
        ret.y = y;
        return ret;
    }

    @Override
    public String toString() {
        return String.format("VkOffset2D{x=%d, y=%d}", x, y);
    }
}
