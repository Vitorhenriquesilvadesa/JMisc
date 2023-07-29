package display;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Display {

    public static int WIDTH = 1280;
    public static int HEIGHT = 720;
    private long ID;

    public Display(String title) {

        this.ID = DisplayManager.createWindow(title);

        GLFWFramebufferSizeCallback callback = new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                glViewport(0, 0, width, height);
            }
        };

        glfwSetFramebufferSizeCallback(ID, callback);

    }

    public void update() {
        glfwPollEvents();
    }

    public void render() {
        glfwSwapBuffers(ID);
    }

    public void resize(int width, int height) {
        glViewport(0, 0, width, height);
    }

    public void destroy() {
        glfwDestroyWindow(ID);
    }

    public boolean isClosed() {
        return glfwWindowShouldClose(ID);
    }

    public void close() {
        glfwSetWindowShouldClose(ID, true);
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public long getID() {
        return ID;
    }
}
