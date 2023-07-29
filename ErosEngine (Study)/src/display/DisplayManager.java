package display;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL11.*;

public final class DisplayManager {

    public static void init() {
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
    }

    public static Display createDisplay(String title) {
        return new Display(title);
    }

    protected static long createWindow(String title) {
        long window = glfwCreateWindow(Display.WIDTH, Display.HEIGHT, title, NULL, NULL);

        if (window == NULL) {
            glfwTerminate();
            throw new IllegalStateException("Window creation failed");
        }

        glfwWindowHint(GLFW_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_VERSION_MAJOR, 3);

        glfwMakeContextCurrent(window);
        createCapabilities();

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        return window;

    }

}
