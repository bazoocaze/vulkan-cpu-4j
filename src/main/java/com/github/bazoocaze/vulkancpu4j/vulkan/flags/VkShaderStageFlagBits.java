package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

import org.intellij.lang.annotations.MagicConstant;

@SuppressWarnings("EmptyClass")
@MagicConstant(flagsFromClass = VkShaderStageFlagBits.class)
public @interface VkShaderStageFlagBits {

    int VK_SHADER_STAGE_VERTEX_BIT = 0x00000001;
    int VK_SHADER_STAGE_TESSELLATION_CONTROL_BIT = 0x00000002;
    int VK_SHADER_STAGE_TESSELLATION_EVALUATION_BIT = 0x00000004;
    int VK_SHADER_STAGE_GEOMETRY_BIT = 0x00000008;
    int VK_SHADER_STAGE_FRAGMENT_BIT = 0x00000010;
    int VK_SHADER_STAGE_COMPUTE_BIT = 0x00000020;
    int VK_SHADER_STAGE_ALL_GRAPHICS = 0x0000001F;
    int VK_SHADER_STAGE_ALL = 0x7FFFFFFF;
    // Provided by VK_KHR_ray_tracing
    int VK_SHADER_STAGE_RAYGEN_BIT_KHR = 0x00000100;
    // Provided by VK_KHR_ray_tracing
    int VK_SHADER_STAGE_ANY_HIT_BIT_KHR = 0x00000200;
    // Provided by VK_KHR_ray_tracing
    int VK_SHADER_STAGE_CLOSEST_HIT_BIT_KHR = 0x00000400;
    // Provided by VK_KHR_ray_tracing
    int VK_SHADER_STAGE_MISS_BIT_KHR = 0x00000800;
    // Provided by VK_KHR_ray_tracing
    int VK_SHADER_STAGE_INTERSECTION_BIT_KHR = 0x00001000;
    // Provided by VK_KHR_ray_tracing
    int VK_SHADER_STAGE_CALLABLE_BIT_KHR = 0x00002000;
    // Provided by VK_NV_mesh_shader
    int VK_SHADER_STAGE_TASK_BIT_NV = 0x00000040;
    // Provided by VK_NV_mesh_shader
    int VK_SHADER_STAGE_MESH_BIT_NV = 0x00000080;
    // Provided by VK_NV_ray_tracing
    int VK_SHADER_STAGE_RAYGEN_BIT_NV = VK_SHADER_STAGE_RAYGEN_BIT_KHR;
    // Provided by VK_NV_ray_tracing
    int VK_SHADER_STAGE_ANY_HIT_BIT_NV = VK_SHADER_STAGE_ANY_HIT_BIT_KHR;
    // Provided by VK_NV_ray_tracing
    int VK_SHADER_STAGE_CLOSEST_HIT_BIT_NV = VK_SHADER_STAGE_CLOSEST_HIT_BIT_KHR;
    // Provided by VK_NV_ray_tracing
    int VK_SHADER_STAGE_MISS_BIT_NV = VK_SHADER_STAGE_MISS_BIT_KHR;
    // Provided by VK_NV_ray_tracing
    int VK_SHADER_STAGE_INTERSECTION_BIT_NV = VK_SHADER_STAGE_INTERSECTION_BIT_KHR;
    // Provided by VK_NV_ray_tracing
    int VK_SHADER_STAGE_CALLABLE_BIT_NV = VK_SHADER_STAGE_CALLABLE_BIT_KHR;
}
