package org.vtko.cgi.entities;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL45.glCreateBuffers;
import static org.lwjgl.opengl.GL45.glCreateVertexArrays;

public class VertexArray {
    private final int vao;
    private final int indicesCount;

    public VertexArray(float[] positions, int[] indices, float[] texCoords) {
        this.vao = createVertexArray();
        this.indicesCount = indices.length;
        sendDataToVertexArray(positions, indices, texCoords);
    }

    private int createVertexArray() {
        int vao = glCreateVertexArrays();
        glBindVertexArray(vao);
        return vao;
    }

    private void sendDataToVertexArray(float[] positions, int[] indices, float[] texCoords) {

        int vboPositions = glCreateBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboPositions);

        FloatBuffer verticesBuffer = sendDataToFloatBuffer(positions);

        int ibo = glCreateBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);

        IntBuffer indicesBuffer = sendDataToIntBuffer(indices);

        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);


        int vboTexCoord = glCreateBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboTexCoord);

        FloatBuffer textCoordBuffer = sendDataToFloatBuffer(texCoords);

        glBufferData(GL_ARRAY_BUFFER, textCoordBuffer, GL_STATIC_DRAW);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(1);
    }

    private FloatBuffer sendDataToFloatBuffer(float[] data) {

        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data).flip();
        return buffer;
    }

    private IntBuffer sendDataToIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data).flip();
        return buffer;
    }

    public int getVaoID() {
        return this.vao;
    }

    public int getCount() {
        return this.indicesCount;
    }
}
