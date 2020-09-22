package com.github.bazoocaze.vulkancpu4j.vulkan;

@SuppressWarnings("unused")
public enum VkPipelineBindPoint {
    VK_PIPELINE_BIND_POINT_GRAPHICS,
    VK_PIPELINE_BIND_POINT_COMPUTE,

    // Provided by VK_KHR_ray_tracing
    VK_PIPELINE_BIND_POINT_RAY_TRACING_KHR;

    // Provided by VK_NV_ray_tracing
    public static final VkPipelineBindPoint VK_PIPELINE_BIND_POINT_RAY_TRACING_NV =
            VK_PIPELINE_BIND_POINT_RAY_TRACING_KHR;
}
