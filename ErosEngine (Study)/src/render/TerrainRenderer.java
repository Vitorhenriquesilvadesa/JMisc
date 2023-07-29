package render;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.*;

import java.util.List;

import math.Mathf;
import math.Matrix4f;
import math.Vector3;
import models.RawModel;
import shaders.TerrainShader;
import terrains.Terrain;
import textures.Material;

public class TerrainRenderer {

    private TerrainShader shader;

    public TerrainRenderer(TerrainShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(List<Terrain> terrains) {
        for (Terrain terrain : terrains) {
            prepareTerrain(terrain);
            loadModelMatrix(terrain);
            glDrawElements(GL_TRIANGLES, terrain.getModel().getVertexCount(), GL_UNSIGNED_INT, 0);
            unbindTexturedModel();
        }
    }

    private void prepareTerrain(Terrain terrain) {
        RawModel rawModel = terrain.getModel();
        glBindVertexArray(rawModel.getID());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        Material texture = terrain.getTexture();
        shader.loadShineVariables(texture.getSmothness(), texture.getReflectivity());
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture.getID());
    }

    private void unbindTexturedModel() {
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
    }

    private void loadModelMatrix(Terrain terrain) {
        Matrix4f matrix = Mathf.createTrasformationMatrix(new Vector3(terrain.getX(), 0.0f, terrain.getZ()),
                new Vector3(0, 0, 0),
                new Vector3(1, 1, 1));

        shader.loadTransformationMatrix(matrix);
    }
}
