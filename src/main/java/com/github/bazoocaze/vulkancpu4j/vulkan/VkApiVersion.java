package com.github.bazoocaze.vulkancpu4j.vulkan;

public class VkApiVersion {

    /** Vulkan 1.0 version number */
    public static final int VK_API_VERSION_1_0 = VK_MAKE_VERSION(1, 0, 0);

    /** Vulkan 1.1 version number */
    public static final int VK_API_VERSION_1_1 = VK_MAKE_VERSION(1, 1, 0);

    /** Vulkan 1.2 version number */
    public static final int VK_API_VERSION_1_2 = VK_MAKE_VERSION(1, 2, 0);

    private VkApiVersion() {
        // static class
    }

    public static int VK_VERSION_MAJOR(int version) {
        return (version >> 22);
    }

    public static int VK_VERSION_MINOR(int version) {
        return ((version >> 12) & 0x3ff);
    }

    public static int VK_VERSION_PATCH(int version) {
        return (version & 0xfff);
    }

    public static Integer VK_MAKE_VERSION(int major, int minor, int patch) {
        return ((major << 22) | (minor << 12) | patch);
    }
}
