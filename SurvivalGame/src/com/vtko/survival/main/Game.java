package com.vtko.survival.main;

import com.vtko.survival.entity.Entity;
import com.vtko.survival.entity.EntityManager;
import com.vtko.survival.input.Input;
import com.vtko.survival.input.KeyCode;
import com.vtko.survival.renderer.Renderer;
import com.vtko.survival.renderer.Window;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

public final class Game {

    private static final Game instance = new Game();
    private static Window window;
    private static boolean running;

    private Game() {
    }

    public static Game getInstance() {
        return instance;
    }

    public void start() throws IOException {

        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW");
        }

        window = new Window("Survival");
        Input.init(window.getID());
        running = true;

        while (running && !window.shouldClose()) {

            glClear(GL_COLOR_BUFFER_BIT);

            window.update();
            window.render();

            if (Input.getKey(KeyCode.ESCAPE)) {
                stop();
            }
        }
    }

    public void stop() {
        window.destroy();
        running = false;
    }
}
