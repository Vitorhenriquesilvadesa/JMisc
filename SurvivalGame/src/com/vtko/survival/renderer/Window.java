package com.vtko.survival.renderer;

import com.vtko.survival.entity.Entity;
import com.vtko.survival.entity.EntityManager;
import org.lwjgl.glfw.GLFWVidMode;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL45.*;

public class Window {

    private long id;
    private int width;
    private int height;
    private final String title;
    public Window(int width, int height, String title) throws IOException {
        this.width = width;
        this.height = height;
        this.title = title;

        long monitor = glfwGetPrimaryMonitor();
        GLFWVidMode mode = glfwGetVideoMode(monitor);
        assert mode != null;

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        this.id = glfwCreateWindow(width, height, title, monitor, 0);

        glfwMakeContextCurrent(id);
        createCapabilities();

        glfwSwapInterval(1);
        glfwShowWindow(id);
    }

    public Window(String title) {
        this.title = title;

        long monitor = getMaxMonitorResolution();

        //glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        this.id = glfwCreateWindow(width, height, title, monitor, 0);

        glfwMakeContextCurrent(id);
        createCapabilities();

        glfwSwapInterval(1);
        glfwShowWindow(id);
    }

    public void update() {
        glfwSwapBuffers(id);
        glfwPollEvents();
    }

    public void render() throws IOException {
        Entity entity = new Entity();
        Renderer renderer = new Renderer();
        renderer.render(EntityManager.getEntities());

        glClearColor(0, 0, 0, 1);
        glClear(GL_COLOR_BUFFER_BIT);
    }

    private long getMaxMonitorResolution() {
        long monitor = glfwGetPrimaryMonitor();
        GLFWVidMode mode = glfwGetVideoMode(monitor);
        assert mode != null;

        this.width = mode.width();
        this.height = mode.height();

        System.out.println("Max monitor resolution: " + width + "x" + height);

        return monitor;
    }


    public boolean shouldClose() {
        return glfwWindowShouldClose(id);
    }

    public void destroy(){
        glfwDestroyWindow(id);
    }

    public long getID() {
        return this.id;
    }

    public long getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }
}
