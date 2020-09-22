package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

public class VkCompositeAlphaFlagsKHR extends VkFlags<VkCompositeAlphaFlagBitsKHR> {

    public VkCompositeAlphaFlagsKHR(VkCompositeAlphaFlagBitsKHR[] flags) {
        super(VkCompositeAlphaFlagBitsKHR.class, flags);
    }

    public static VkCompositeAlphaFlagsKHR of(VkCompositeAlphaFlagBitsKHR... flags) {
        return new VkCompositeAlphaFlagsKHR(flags);
    }
}
