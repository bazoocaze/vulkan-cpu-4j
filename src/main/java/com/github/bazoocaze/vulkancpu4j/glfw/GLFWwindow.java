package com.github.bazoocaze.vulkancpu4j.glfw;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GLFWwindow {

    private final JFrame frame;
    private boolean shouldClose = false;

    public GLFWwindow(int width, int height, String title) {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new GLFWwindowListener());
    }

    public boolean shouldClose() {
        return this.shouldClose;
    }

    public void destroy() {
        frame.dispose();
    }

    public JFrame handle() {
        return frame;
    }

    private class GLFWwindowListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            GLFWwindow.this.shouldClose = true;
        }
    }
}
