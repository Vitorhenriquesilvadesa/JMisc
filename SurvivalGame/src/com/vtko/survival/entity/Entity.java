package com.vtko.survival.entity;

import static org.lwjgl.opengl.GL45.*;

public class Entity {

    private static final int POSITION_ATTRIBUTE_INDEX = 0;

    private int vao, vbo, ibo;
    private int count;

    public Entity() {
        EntityManager.addEntity(this);

        setupVertexArray();
        setupVertexBuffer();
    }

    private void setupVertexArray() {
        vao = glCreateVertexArrays();
        glBindVertexArray(vao);
        glEnableVertexAttribArray(POSITION_ATTRIBUTE_INDEX);
        glVertexAttribPointer(POSITION_ATTRIBUTE_INDEX, 3, GL_FLOAT, false, 0, 0);
    }

    private void setupVertexBuffer() {

        float[] vertices = {
                -0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.5f, 0.5f, 0.0f
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        vbo = glCreateBuffers();

        assert vbo != 0;

        glBindBuffer(GL_ARRAY_BUFFER, vbo);

        this.count = indices.length;

        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        ibo = glCreateBuffers();

        assert ibo != 0;

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        glBindVertexArray(0);
    }

    public void update() {
        // Implement entity update logic if needed
    }

    public void render() {
        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0);
    }

    public void destroy() {
        glDeleteBuffers(vbo);
        glDeleteBuffers(ibo);
        glDeleteVertexArrays(vao);
        EntityManager.removeEntity(this);
    }
}
