package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

public class VkSurfaceTransformFlagsKHR extends VkFlags<VkSurfaceTransformFlagBitsKHR> {

    public VkSurfaceTransformFlagsKHR(VkSurfaceTransformFlagBitsKHR[] flags) {
        super(VkSurfaceTransformFlagBitsKHR.class, flags);
    }

    public static VkSurfaceTransformFlagsKHR of(VkSurfaceTransformFlagBitsKHR... flags) {
        return new VkSurfaceTransformFlagsKHR(flags);
    }
}
