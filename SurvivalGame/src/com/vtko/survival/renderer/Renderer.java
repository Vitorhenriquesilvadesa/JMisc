package com.vtko.survival.renderer;

import com.vtko.survival.entity.Entity;

import java.io.IOException;
import java.util.List;

public class Renderer {

//    private final Shader shader;

    public Renderer() throws IOException {

    }


    public void render(List<Entity> entities) {

        for (Entity entity : entities) {
            entity.render();
        }

    }

}
