package render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import java.util.List;
import java.util.Map;

import entities.Entity;
import math.Mathf;
import math.Matrix4f;
import models.RawModel;
import models.TexturedModel;
import shaders.StaticShader;
import textures.Material;

public class EntityRenderer {

    private StaticShader shader;

    public EntityRenderer(StaticShader shader, Matrix4f projectionMatrix) {

        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(Map<TexturedModel, List<Entity>> entities) {

        for (TexturedModel model : entities.keySet()) {

            prepareTexturedModel(model);
            List<Entity> batch = entities.get(model);

            for (Entity entity : batch) {
                prepareInstance(entity);
                glDrawElements(GL_TRIANGLES, model.getRawModel().getVertexCount(), GL_UNSIGNED_INT, 0);
            }

            unbindTexturedModel();
        }
    }

    private void prepareTexturedModel(TexturedModel model) {
        RawModel rawModel = model.getRawModel();
        glBindVertexArray(rawModel.getID());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        Material texture = model.getMaterial();

        if (texture.isHasTransparency()) {
            MasterRenderer.disableCulling();
        }
        shader.loadFakeLightingVariable(texture.isUseFakeLighting());
        shader.loadShineVariables(texture.getSmothness(), texture.getReflectivity());
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, model.getMaterial().getID());
    }

    private void unbindTexturedModel() {
        MasterRenderer.enableCulling();
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
    }

    private void prepareInstance(Entity entity) {
        Matrix4f matrix = Mathf.createTrasformationMatrix(entity.getPosition(), entity.getRotation(),
                entity.getScale());

        shader.loadTransformationMatrix(matrix);
    }
}
