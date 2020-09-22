/*
MIT License
Copyright (c) 2020 Jose Ferreira (Bazoocaze)
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.github.bazoocaze.vulkancpu4j.vulkan.enums;

/**
 * Vulkan command return codes.
 * <p>
 * Successful completion codes are returned when a command needs to communicate success or status information.
 * All successful completion codes are non-negative values.
 * <p>
 * Run time error codes are returned when a command needs to communicate a failure that could only be detected
 * at run time. All run time error codes are negative values.
 */
public enum VkResult {
    /**
     * Command successfully completed
     */
    VK_SUCCESS(0),

    /**
     * A fence or query has not yet completed
     */
    VK_NOT_READY(1),

    /**
     * A wait operation has not completed in the specified time
     */
    VK_TIMEOUT(2),

    /**
     * An event is signaled
     */
    VK_EVENT_SET(3),

    /**
     * An event is unsignaled
     */
    VK_EVENT_RESET(4),

    /**
     * A return array was too small for the result
     */
    VK_INCOMPLETE(5),

    /**
     * A host memory allocation has failed.
     */
    VK_ERROR_OUT_OF_HOST_MEMORY(-1),

    /**
     * A device memory allocation has failed.
     */
    VK_ERROR_OUT_OF_DEVICE_MEMORY(-2),

    /**
     * Initialization of an object could not be completed for implementation-specific
     * /** reasons.
     */
    VK_ERROR_INITIALIZATION_FAILED(-3),

    /**
     * The logical or physical device has been lost. See Lost Device.
     */
    VK_ERROR_DEVICE_LOST(-4),

    /**
     * Mapping of a memory object has failed.
     */
    VK_ERROR_MEMORY_MAP_FAILED(-5),

    /**
     * A requested layer is not present or could not be loaded.
     */
    VK_ERROR_LAYER_NOT_PRESENT(-6),

    /**
     * A requested extension is not supported.
     */
    VK_ERROR_EXTENSION_NOT_PRESENT(-7),

    /**
     * A requested feature is not supported.
     */
    VK_ERROR_FEATURE_NOT_PRESENT(-8),

    /**
     * The requested version of Vulkan is not supported by the driver or is otherwise
     * /** incompatible for implementation-specific reasons.
     */
    VK_ERROR_INCOMPATIBLE_DRIVER(-9),

    /**
     * Too many objects of the type have already been created.
     */
    VK_ERROR_TOO_MANY_OBJECTS(-10),

    /**
     * A requested format is not supported on this device.
     */
    VK_ERROR_FORMAT_NOT_SUPPORTED(-11),

    /**
     * A requested pool allocation has failed due to fragmentation of the pool’s
     * /** memory.
     */
    VK_ERROR_FRAGMENTED_POOL(-12),

    /**
     * A surface is no longer available.
     */
    VK_ERROR_SURFACE_LOST_KHR(-1000000000),

    /**
     * The requested window is already connected to a VkSurfaceKHR), or to some other
     * /** non-Vulkan API.
     */
    VK_ERROR_NATIVE_WINDOW_IN_USE_KHR(-1000000001),

    /**
     * A swapchain no longer matches the surface properties exactly), but can still be
     * /** used to present to the surface successfully.
     */
    VK_SUBOPTIMAL_KHR(1000001003),

    /**
     * A surface has changed in such a way that it is no longer compatible with the
     * /** swapchain), and further presentation requests using the swapchain will fail. Applications
     * /** must query the new surface properties and recreate their swapchain if they wish to
     * /** continue presenting to the surface.
     */
    VK_ERROR_OUT_OF_DATE_KHR(-1000001004),

    /**
     * The display used by a swapchain does not use the same presentable image layout),
     * /** or is incompatible in a way that prevents sharing an image.
     */
    VK_ERROR_INCOMPATIBLE_DISPLAY_KHR(-1000003001),

    VK_ERROR_VALIDATION_FAILED_EXT(-1000011001),

    /* ----- EXCLUSIVE FOR THIS WRAPPER ----- */
    VK_ERROR_BUFFER_COMPILATION(-1000),
    ;

    private final int value;

    VkResult(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
