package com.github.bazoocaze.vulkancpu4j.vulkan.enums;

/**
 * Presentation mode supported for a surface. Provided by VK_KHR_surface
 */
@SuppressWarnings("unused")
public enum VkPresentModeKHR {

    /**
     * Specifies that the presentation engine does not wait for a vertical blanking period to update the current
     * image, meaning this mode may result in visible tearing. No internal queuing of presentation requests is
     * needed, as the requests are applied immediately.
     */
    VK_PRESENT_MODE_IMMEDIATE_KHR,

    /**
     * Specifies that the presentation engine waits for the next vertical blanking period to update the
     * current image. Tearing cannot be observed. An internal single-entry queue is used to hold pending
     * presentation requests. If the queue is full when a new presentation request is received, the new
     * request replaces the existing entry, and any images associated with the prior entry become available
     * for re-use by the application. One request is removed from the queue and processed during each vertical
     * blanking period in which the queue is non-empty.
     */
    VK_PRESENT_MODE_MAILBOX_KHR,

    /**
     * Specifies that the presentation engine waits for the next vertical blanking period to update the
     * current image. Tearing cannot be observed. An internal queue is used to hold pending presentation
     * requests. New requests are appended to the end of the queue, and one request is removed from the beginning
     * of the queue and processed during each vertical blanking period in which the queue is non-empty. This is
     * the only value of presentMode that is required to be supported.
     */
    VK_PRESENT_MODE_FIFO_KHR,

    /**
     * Specifies that the presentation engine generally waits for the next vertical blanking period to update
     * the current image. If a vertical blanking period has already passed since the last update of the current
     * image then the presentation engine does not wait for another vertical blanking period for the update,
     * meaning this mode may result in visible tearing in this case. This mode is useful for reducing visual
     * stutter with an application that will mostly present a new image before the next vertical blanking period,
     * but may occasionally be late, and present a new image just after the next vertical blanking period. An
     * internal queue is used to hold pending presentation requests. New requests are appended to the end of the
     * queue, and one request is removed from the beginning of the queue and processed during or after each
     * vertical blanking period in which the queue is non-empty.
     */
    VK_PRESENT_MODE_FIFO_RELAXED_KHR,

    // Provided by VK_KHR_shared_presentable_image
    VK_PRESENT_MODE_SHARED_DEMAND_REFRESH_KHR,

    // Provided by VK_KHR_shared_presentable_image
    VK_PRESENT_MODE_SHARED_CONTINUOUS_REFRESH_KHR,
}
