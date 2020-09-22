package com.github.bazoocaze.vulkancpu4j.vulkan.enums;

public enum VkImageLayout {
    VK_IMAGE_LAYOUT_UNDEFINED(0),
    VK_IMAGE_LAYOUT_GENERAL(1),
    VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL(2),
    VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL(3),
    VK_IMAGE_LAYOUT_DEPTH_STENCIL_READ_ONLY_OPTIMAL(4),
    VK_IMAGE_LAYOUT_SHADER_READ_ONLY_OPTIMAL(5),
    VK_IMAGE_LAYOUT_TRANSFER_SRC_OPTIMAL(6),
    VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL(7),
    VK_IMAGE_LAYOUT_PREINITIALIZED(8),
    // Provided by VK_VERSION_1_1
    VK_IMAGE_LAYOUT_DEPTH_READ_ONLY_STENCIL_ATTACHMENT_OPTIMAL(1000117000),
    // Provided by VK_VERSION_1_1
    VK_IMAGE_LAYOUT_DEPTH_ATTACHMENT_STENCIL_READ_ONLY_OPTIMAL(1000117001),
    // Provided by VK_VERSION_1_2
    VK_IMAGE_LAYOUT_DEPTH_ATTACHMENT_OPTIMAL(1000241000),
    // Provided by VK_VERSION_1_2
    VK_IMAGE_LAYOUT_DEPTH_READ_ONLY_OPTIMAL(1000241001),
    // Provided by VK_VERSION_1_2
    VK_IMAGE_LAYOUT_STENCIL_ATTACHMENT_OPTIMAL(1000241002),
    // Provided by VK_VERSION_1_2
    VK_IMAGE_LAYOUT_STENCIL_READ_ONLY_OPTIMAL(1000241003),
    // Provided by VK_KHR_swapchain
    VK_IMAGE_LAYOUT_PRESENT_SRC_KHR(1000001002),
    // Provided by VK_KHR_shared_presentable_image
    VK_IMAGE_LAYOUT_SHARED_PRESENT_KHR(1000111000),
    // Provided by VK_NV_shading_rate_image
    VK_IMAGE_LAYOUT_SHADING_RATE_OPTIMAL_NV(1000164003),
    // Provided by VK_EXT_fragment_density_map
    VK_IMAGE_LAYOUT_FRAGMENT_DENSITY_MAP_OPTIMAL_EXT(1000218000);

    // Provided by VK_KHR_maintenance2
    public static final VkImageLayout VK_IMAGE_LAYOUT_DEPTH_READ_ONLY_STENCIL_ATTACHMENT_OPTIMAL_KHR =
            VK_IMAGE_LAYOUT_DEPTH_READ_ONLY_STENCIL_ATTACHMENT_OPTIMAL;

    // Provided by VK_KHR_maintenance2
    public static final VkImageLayout VK_IMAGE_LAYOUT_DEPTH_ATTACHMENT_STENCIL_READ_ONLY_OPTIMAL_KHR =
            VK_IMAGE_LAYOUT_DEPTH_ATTACHMENT_STENCIL_READ_ONLY_OPTIMAL;

    // Provided by VK_KHR_separate_depth_stencil_layouts
    public static final VkImageLayout VK_IMAGE_LAYOUT_DEPTH_ATTACHMENT_OPTIMAL_KHR =
            VK_IMAGE_LAYOUT_DEPTH_ATTACHMENT_OPTIMAL;

    // Provided by VK_KHR_separate_depth_stencil_layouts
    public static final VkImageLayout VK_IMAGE_LAYOUT_DEPTH_READ_ONLY_OPTIMAL_KHR =
            VK_IMAGE_LAYOUT_DEPTH_READ_ONLY_OPTIMAL;

    // Provided by VK_KHR_separate_depth_stencil_layouts
    public static final VkImageLayout VK_IMAGE_LAYOUT_STENCIL_ATTACHMENT_OPTIMAL_KHR =
            VK_IMAGE_LAYOUT_STENCIL_ATTACHMENT_OPTIMAL;

    // Provided by VK_KHR_separate_depth_stencil_layouts
    public static final VkImageLayout VK_IMAGE_LAYOUT_STENCIL_READ_ONLY_OPTIMAL_KHR =
            VK_IMAGE_LAYOUT_STENCIL_READ_ONLY_OPTIMAL;

    private final int value;

    VkImageLayout(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
