package render;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import display.Display;
import entities.Camera;
import entities.Entity;
import entities.Light;
import math.Matrix4f;
import math.Vector3;
import models.TexturedModel;
import shaders.StaticShader;
import shaders.TerrainShader;
import terrains.Terrain;

public class MasterRenderer {

    private static float FOV = 70f;
    private static float NEAR_PLANE = 1f;
    private static float FAR_PLANE = 1000f;

    private Matrix4f projectionMatrix;
    private Vector3 backgroundColor = new Vector3();

    private StaticShader shader = new StaticShader();
    private EntityRenderer renderer;

    private TerrainRenderer terrainRenderer;
    private TerrainShader terrainShader = new TerrainShader();

    private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
    private List<Terrain> terrains = new ArrayList<Terrain>();

    public MasterRenderer() {
        enableCulling();
        createProjectionMatrix();
        renderer = new EntityRenderer(shader, projectionMatrix);
        terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
    }

    public static void enableCulling() {
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
    }

    public static void disableCulling() {
        glDisable(GL_CULL_FACE);
    }

    public void render(Light sun, Camera camera) {

        prepare();
        shader.start();
        shader.loadSkyColor(backgroundColor);
        shader.loadLight(sun);
        shader.loadViewMatrix(camera);
        renderer.render(entities);
        shader.stop();

        terrainShader.start();
        terrainShader.loadSkyColor(backgroundColor);
        terrainShader.loadLight(sun);
        terrainShader.loadViewMatrix(camera);
        terrainRenderer.render(terrains);
        terrainShader.stop();
        terrains.clear();
        entities.clear();
    }

    public void processTerrain(Terrain terrain) {
        terrains.add(terrain);
    }

    public void processEntity(Entity entity) {
        TexturedModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);

        if (batch != null) {
            batch.add(entity);
        } else {
            List<Entity> newBatch = new ArrayList<Entity>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);
        }
    }

    public void prepare() {
        glEnable(GL_DEPTH_TEST);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(backgroundColor.x, backgroundColor.y, backgroundColor.z, 1.0f);
    }

    public void cleanUp() {

        shader.cleanUp();
        terrainShader.cleanUp();
    }

    private void createProjectionMatrix() {

        float aspectRatio = (float) Display.WIDTH / Display.HEIGHT;
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = (float) y_scale / aspectRatio;
        float frustrum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.elements[0] = x_scale;
        projectionMatrix.elements[5] = y_scale;
        projectionMatrix.elements[10] = -((FAR_PLANE + NEAR_PLANE) / frustrum_length);
        projectionMatrix.elements[11] = -1;
        projectionMatrix.elements[14] = -((2 * FAR_PLANE + NEAR_PLANE) / frustrum_length);
        projectionMatrix.elements[15] = 0;
    }

    public void setBackGroundColor(Vector3 color) {
        backgroundColor = color;
    }
}
