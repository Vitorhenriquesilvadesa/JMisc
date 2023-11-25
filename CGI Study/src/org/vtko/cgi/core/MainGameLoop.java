package org.vtko.cgi.core;

import org.vtko.cgi.entities.Entity;
import org.vtko.cgi.entities.RawModel;
import org.vtko.cgi.entities.Square;
import org.vtko.cgi.entities.Transform;
import org.vtko.cgi.entities.textures.Texture;
import org.vtko.cgi.renderer.ProjectionType;
import org.vtko.cgi.renderer.Renderer;
import org.vtko.cgi.utils.*;
import org.vtko.cgi.window.Window;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class MainGameLoop {

    private final Window window;
    private boolean running;

    public MainGameLoop() {
        this.window = Window.getInstance();
        running = true;
    }

    private static float[] generateSphereVertices(int slices, int stacks) {
        ArrayList<Float> verticesList = new ArrayList<>();

        for (int stack = 0; stack <= stacks; stack++) {
            float stackAngle = (float) Math.PI / 2 - (float) stack * (float) Math.PI / stacks;
            float xy = (float) Math.cos(stackAngle);
            float z = (float) Math.sin(stackAngle);

            for (int slice = 0; slice <= slices; slice++) {
                float sliceAngle = (float) slice * 2 * (float) Math.PI / slices;
                float x = xy * (float) Math.cos(sliceAngle);
                float y = xy * (float) Math.sin(sliceAngle);

                verticesList.add(x);
                verticesList.add(y);
                verticesList.add(z);
            }
        }

        float[] vertices = new float[verticesList.size()];
        for (int i = 0; i < verticesList.size(); i++) {
            vertices[i] = verticesList.get(i);
        }

        return vertices;
    }

    private static int[] generateSphereIndices(int slices, int stacks) {
        ArrayList<Integer> indicesList = new ArrayList<>();

        for (int stack = 0; stack < stacks; stack++) {
            for (int slice = 0; slice < slices; slice++) {
                int first = stack * (slices + 1) + slice;
                int second = first + slices + 1;

                indicesList.add(first);
                indicesList.add(second);
                indicesList.add(first + 1);

                indicesList.add(second);
                indicesList.add(second + 1);
                indicesList.add(first + 1);
            }
        }

        int[] indices = new int[indicesList.size()];
        for (int i = 0; i < indicesList.size(); i++) {
            indices[i] = indicesList.get(i);
        }

        return indices;
    }

    private static float[] generateSphereTextureCoordinates(int slices, int stacks) {
        ArrayList<Float> texCoordsList = new ArrayList<>();

        for (int stack = 0; stack <= stacks; stack++) {
            for (int slice = 0; slice <= slices; slice++) {
                float s = (float) slice / slices;
                float t = (float) stack / stacks;

                texCoordsList.add(s);
                texCoordsList.add(t);
            }
        }

        float[] texCoords = new float[texCoordsList.size()];
        for (int i = 0; i < texCoordsList.size(); i++) {
            texCoords[i] = texCoordsList.get(i);
        }

        return texCoords;
    }

    public void start() {
        mainLoop();
    }

    private void mainLoop() {


        Texture texture = new Texture("C:/Users/vitor/Downloads/Rope_001_basecolor.jpg");
        Renderer renderer = new Renderer();

        Matrix4f projectionMatrix = new Matrix4f();
        projectionMatrix.setIdentity();
        projectionMatrix.setPerspective(60f, 16f / 9f, 0.1f, 100.0f);

        renderer.setProjectionType(ProjectionType.PERSPECTIVE);
        Camera camera = new Camera();

        List<Entity> entities = new ArrayList<>();

        for (int i = 0; i < 48; i++) {
            entities.add(new Square(texture, new Vector2(i * 16f - 16f * 24f, -99f)));
        }

        while (running) {


            if (window.isClosed()) {
                running = false;
            }

            if (glfwGetKey(window.getNativeWindow(), GLFW_KEY_P) == 1) {
                renderer.setProjectionType(ProjectionType.PERSPECTIVE);
            }

            if (glfwGetKey(window.getNativeWindow(), GLFW_KEY_O) == 1) {
                renderer.setProjectionType(ProjectionType.ORTHOGRAPHIC);
            }

            window.clear();
            window.update();

            renderer.render(camera, entities);

            window.swapBuffers();
        }
    }
}
