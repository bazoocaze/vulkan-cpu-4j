package com.github.bazoocaze.vulkancpu4j.glfw;

import com.github.bazoocaze.vulkancpu4j.util.ByRef;
import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.*;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

import java.util.*;

public class GLFW {

    private static Map<GLFWwindowCreationHints, GLFWwindowCreationHintValue> hints;
    private static GLFWerror lastError = GLFWerror.GLFW_NO_ERROR;
    private static List<GLFWwindow> windows = new ArrayList<>();

    private GLFW() {
        // static class
    }

    public static void glfwInit() {
        hints = new EnumMap<>(GLFWwindowCreationHints.class);
    }

    public static GLFWerror glfwGetError(OutRef<String> description) {
        return lastError;
    }

    public static void glfwWindowHint(GLFWwindowCreationHints hint, GLFWwindowCreationHintValue value) {
        hints.put(hint, value);
    }

    public static GLFWwindow glfwCreateWindow(int width, int height, String title, GLFWmonitor monitor, GLFWwindow share) {
        GLFWwindow glfWwindow = new GLFWwindow(width, height, title);
        windows.add(glfWwindow);
        return glfWwindow;
    }

    public static boolean glfwWindowShouldClose(GLFWwindow window) {
        return window.shouldClose();
    }

    public static void glfwPollEvents() {
        // do nothing
    }

    public static void glfwDestroyWindow(GLFWwindow window) {
        window.destroy();
        windows.remove(window);
    }

    public static void glfwTerminate() {
        GLFWwindow[] windowsList = windows.toArray(GLFWwindow[]::new);
        for (GLFWwindow window : windowsList) {
            glfwDestroyWindow(window);
        }
    }

    public static VkResult glfwCreateWindowSurface(VkInstance instance, GLFWwindow window,
                                                   VkAllocationCallbacks allocator, OutRef<VkSurfaceKHR> surface) {
        VkJavaSwingSurfaceCreateInfoEMU createInfo = new VkJavaSwingSurfaceCreateInfoEMU();
        createInfo.frame = window.handle();
        return Vulkan.vkCreateJavaSwingSurfaceEMU(instance, createInfo, allocator, surface);
    }

    public static Set<String> glfwGetRequiredInstanceExtensions(ByRef<Integer> extensionCount) {
        extensionCount.set(2);
        return Set.of("VK_KHR_surface", "VK_EMU_java_swing_surface");
    }
}
