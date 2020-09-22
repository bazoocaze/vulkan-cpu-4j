package com.github.bazoocaze.vulkancpu4j.vulkan;

import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkColorSpaceKHR;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkFormat;

import java.util.StringJoiner;

public class VkSurfaceFormatKHR {

    /// <summary>Is a VkFormat that is compatible with the specified surface.</summary>
    public VkFormat format;

    /// <summary>Is a presentation VkColorSpaceKHR that is compatible with the surface.</summary>
    public VkColorSpaceKHR colorSpace;

    public static VkSurfaceFormatKHR create(VkFormat format, VkColorSpaceKHR colorSpace) {
        VkSurfaceFormatKHR ret = new VkSurfaceFormatKHR();
        ret.format = format;
        ret.colorSpace = colorSpace;
        return ret;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VkSurfaceFormatKHR.class.getSimpleName() + "[", "]")
                .add("format=" + format)
                .add("colorSpace=" + colorSpace)
                .toString();
    }
}
