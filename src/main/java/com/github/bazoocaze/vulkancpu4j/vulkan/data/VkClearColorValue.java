package com.github.bazoocaze.vulkancpu4j.vulkan.data;

public class VkClearColorValue {

    public float[] float32;
    public int[] int32;
    public int[] uint32;

    /**
     * BBGGRRAA / 0xAARRGGBB
     */
    public int uint32_scalar;

    public static VkClearColorValue create(float r, float g, float b, float a) {
        VkClearColorValue ret = new VkClearColorValue();

        setFloat32(ret, r, g, b, a);
        setInt32(ret, (int) (r * 255), (int) (g * 255), (int) (b * 255), (int) (a * 255));

        return ret;
    }

    private static void setFloat32(VkClearColorValue ret, float r, float g, float b, float a) {
        ret.float32 = new float[4];
        ret.float32[0] = r;
        ret.float32[1] = g;
        ret.float32[2] = b;
        ret.float32[3] = a;
    }

    private static void setInt32(VkClearColorValue ret, int r, int g, int b, int a) {
        ret.int32 = new int[4];
        ret.int32[0] = r;
        ret.int32[1] = g;
        ret.int32[2] = b;
        ret.int32[3] = a;

        ret.uint32 = new int[4];
        ret.uint32[0] = r;
        ret.uint32[1] = g;
        ret.uint32[2] = b;
        ret.uint32[3] = a;

        ret.uint32_scalar = (a << 24) | (r << 16) | (g << 8) | b;
    }

    @Override
    public String toString() {
        return String.format("VkClearColorValue{color=0x%08X}", uint32_scalar);
    }
}
