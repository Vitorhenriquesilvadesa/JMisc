package engine;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;
import java.util.List;

import display.Display;
import display.DisplayManager;
import entities.Camera;
import entities.Entity;
import entities.Light;
import math.Vector3;
import models.RawModel;
import models.TexturedModel;
import render.MasterRenderer;
import terrains.Terrain;
import textures.Material;
import utils.Loader;
import utils.OBJLoader;

public class App {
        public static void main(String[] args) {

                DisplayManager.init();
                Display window = DisplayManager.createDisplay("Eros Engine Debug");
                Loader loader = new Loader();
                RawModel model = OBJLoader.loadObjModel("dragon", loader);
                TexturedModel staticModel = new TexturedModel(model,
                                new Material(loader.loadTexture("texture002")));

                List<Entity> entities = new ArrayList<Entity>();

                Light sun = new Light(new Vector3(0, 20, 0), new Vector3(1, 1, 1));
                Input input = new Input(window.getID());
                Terrain terrain = new Terrain(-1, 0, loader, new Material(loader.loadTexture("texture002")));
                Camera camera = new Camera(window.getID(), 0.06f);
                MasterRenderer renderer = new MasterRenderer();

                entities.add(new Entity(staticModel, new Vector3(0, 0, -25), new Vector3(0, 0, 0),
                                new Vector3(1, 1, 1)));
                entities.get(0).getModel().setmaterial(
                                new Material(entities.get(0).getModel().getMaterial().getID(), 30f, 1, false,
                                                false));
                renderer.setBackGroundColor(new Vector3(1, 1, 1));

                while (!window.isClosed()) {

                        if (input.getKey(KeyCode.ESCAPE)) {
                                window.close();
                        }

                        renderer.processTerrain(terrain);

                        for (Entity e : entities) {
                                e.setRotation(new Vector3(0, e.getRotation().y + 0.004f,
                                                0));
                                renderer.processEntity(e);
                        }

                        renderer.render(sun, camera);

                        camera.move();

                        window.update();
                        window.render();
                }

                renderer.cleanUp();
                loader.cleanUp();
                window.destroy();

                glfwTerminate();
        }
}
