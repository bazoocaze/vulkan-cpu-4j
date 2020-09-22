package com.github.bazoocaze.vulkancpu4j.vulkan.enums;

@SuppressWarnings("unused")
/// <summary>Bitmask specifying features supported by a buffer (VERTEX_BUFFER, COLOR_ATTACHMENT, SAMPLED_IMAGE).</summary>
public enum VkFormatFeatureFlagBits {
    /// <summary>Specifies that an image view can be sampled from.</summary>
    VK_FORMAT_FEATURE_SAMPLED_IMAGE_BIT(0x00000001),

    /// <summary>Specifies that an image view can be used as a storage images.</summary>
    VK_FORMAT_FEATURE_STORAGE_IMAGE_BIT(0x00000002),

    /// <summary>Specifies that an image view can be used as storage image that supports atomic operations.</summary>
    VK_FORMAT_FEATURE_STORAGE_IMAGE_ATOMIC_BIT(0x00000004),

    /// <summary>Specifies that the format can be used to create a buffer view that can be bound to a VK_DESCRIPTOR_TYPE_UNIFORM_TEXEL_BUFFER descriptor.</summary>
    VK_FORMAT_FEATURE_UNIFORM_TEXEL_BUFFER_BIT(0x00000008),

    /// <summary>Specifies that the format can be used to create a buffer view that can be bound to a VK_DESCRIPTOR_TYPE_STORAGE_TEXEL_BUFFER descriptor.</summary>
    VK_FORMAT_FEATURE_STORAGE_TEXEL_BUFFER_BIT(0x00000010),

    /// <summary>Specifies that atomic operations are supported on VK_DESCRIPTOR_TYPE_STORAGE_TEXEL_BUFFER with this format.</summary>
    VK_FORMAT_FEATURE_STORAGE_TEXEL_BUFFER_ATOMIC_BIT(0x00000020),

    /// <summary>Specifies that the format can be used as a vertex attribute format (VkVertexInputAttributeDescription::format).</summary>
    VK_FORMAT_FEATURE_VERTEX_BUFFER_BIT(0x00000040),

    /// <summary>Specifies that an image view can be used as a framebuffer color attachment and as an input attachment.</summary>
    VK_FORMAT_FEATURE_COLOR_ATTACHMENT_BIT(0x00000080),

    /// <summary>Specifies that an image view can be used as a framebuffer color attachment that supports blending and as an input attachment.</summary>
    VK_FORMAT_FEATURE_COLOR_ATTACHMENT_BLEND_BIT(0x00000100),

    /// <summary>Specifies that an image view can be used as a framebuffer depth/stencil attachment and as an input attachment.</summary>
    VK_FORMAT_FEATURE_DEPTH_STENCIL_ATTACHMENT_BIT(0x00000200),

    /// <summary>Specifies that an image can be used as srcImage for the vkCmdBlitImage command.</summary>
    VK_FORMAT_FEATURE_BLIT_SRC_BIT(0x00000400),

    /// <summary>Specifies that an image can be used as dstImage for the vkCmdBlitImage command.</summary>
    VK_FORMAT_FEATURE_BLIT_DST_BIT(0x00000800),

    /// <summary>
    /// Specifies that if VK_FORMAT_FEATURE_SAMPLED_IMAGE_BIT is also set, an image view can be used with a sampler
    /// that has either of magFilter or minFilter set to VK_FILTER_LINEAR, or mipmapMode set to VK_SAMPLER_MIPMAP_MODE_LINEAR.
    /// If VK_FORMAT_FEATURE_BLIT_SRC_BIT is also set, an image can be used as the srcImage to vkCmdBlitImage with
    /// a filter of VK_FILTER_LINEAR. This bit must only be exposed for formats that also support the
    /// VK_FORMAT_FEATURE_SAMPLED_IMAGE_BIT or VK_FORMAT_FEATURE_BLIT_SRC_BIT.
    /// </summary>
    VK_FORMAT_FEATURE_SAMPLED_IMAGE_FILTER_LINEAR_BIT(0x00001000),
    ;

    private final int value;

    VkFormatFeatureFlagBits(int value) {
        this.value = value;
    }
}
