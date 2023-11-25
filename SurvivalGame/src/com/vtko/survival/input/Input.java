package com.vtko.survival.input;

import static org.lwjgl.glfw.GLFW.*;

public final class Input {

    private static long window;

    public static void init(long window) {
        Input.window = window;
    }

    public static boolean getKey(int key) {
        int state = glfwGetKey(window, key);
        if (state == GLFW_PRESS || state == GLFW_REPEAT) {
            return true;
        } else if (state == GLFW_RELEASE) {
            return false;
        }
        return false;
    }

    public static boolean getMouseButton(int button) {
        return glfwGetMouseButton(window, button) == GLFW_PRESS;
    }
}
