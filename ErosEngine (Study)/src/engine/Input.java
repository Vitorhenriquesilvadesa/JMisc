package engine;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

    private long window;

    public Input(long window) {
        this.window = window;
    }

    public boolean getKey(int key) {

        if (glfwGetKey(window, key) == GLFW_PRESS) {
            return true;
        }

        return false;
    }
}