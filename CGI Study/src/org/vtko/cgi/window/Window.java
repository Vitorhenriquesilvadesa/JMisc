package org.vtko.cgi.window;

import org.vtko.cgi.engine.config.EngineConfig;
import org.vtko.cgi.engine.config.WindowProps;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL45.*;

public class Window {


    private static Window window;
    private final long nativeWindow;

    private Window(int width, int height, String title) {

        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW!");
        }

        this.nativeWindow = glfwCreateWindow(width, height, title, 0, 0);

        glfwMakeContextCurrent(this.nativeWindow);

        createCapabilities();
    }

    public void update(){
        glfwPollEvents();
    }

    public void clear(){
        glEnable(GL_DEPTH_TEST);
        glClearColor(89f / 255f, 169f / 255f, 255f / 255f, 1);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void swapBuffers(){
        glfwSwapBuffers(nativeWindow);
    }

    public boolean isClosed(){
        return glfwWindowShouldClose(this.nativeWindow);
    }

    public static Window getInstance() {
        if (window == null) {
            WindowProps props = EngineConfig.getWindowConfig();
            window = new Window(props.width(), props.height(), props.title());
            window.setCallbacks();
        }

        return window;
    }

    public long getNativeWindow(){
        return this.nativeWindow;
    }

    private void setCallbacks(){
        glfwSetFramebufferSizeCallback(nativeWindow, this::frameBufferSizeCallback);
    }

    private void frameBufferSizeCallback(long window, int width, int height){
        glViewport(0, 0, width, height);
    }
}
