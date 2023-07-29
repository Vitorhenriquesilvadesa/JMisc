package utils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import models.RawModel;
import textures.Texture;

public class Loader {

    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();
    private List<Integer> textures = new ArrayList<Integer>();

    public RawModel loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices) {
        int vaoID = createVAO();
        bindIndexBuffer(indices);
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(1, 2, textureCoords);
        storeDataInAttributeList(2, 3, normals);
        unbindVAO();
        return new RawModel(vaoID, indices.length); // Divide por 3 porque cada vertice possui 3 valores
    }

    public int loadTexture(String filename) {

        Texture texture = new Texture(DirectoryManagement.workingDirectory + "/res/" + filename + ".png");

        int textureID = texture.getTextureID();
        textures.add(textureID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        return textureID;
    }

    public int createVAO() {

        int vaoID = glGenVertexArrays();
        vaos.add(vaoID);
        glBindVertexArray(vaoID);
        return vaoID;
    }

    public void cleanUp() {
        for (int vao : vaos) {
            glDeleteVertexArrays(vao);
        }

        for (int vbo : vbos) {
            glDeleteBuffers(vbo);
        }

        for (int texture : textures) {
            glDeleteTextures(texture);
        }
    }

    public void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data) {

        int vboID = glGenBuffers();
        vbos.add(vboID);
        glBindBuffer(GL_ARRAY_BUFFER, vboID);

        FloatBuffer buffer = storeDataInFloatBuffer(data);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(attributeNumber, coordinateSize, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void unbindVAO() {

        glBindVertexArray(0);
    }

    public void bindIndexBuffer(int[] indices) {
        int vboID = glGenBuffers();
        vbos.add(vboID);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
    }

    private IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();

        return buffer;
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data) {

        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();

        return buffer;
    }

}
