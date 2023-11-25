package com.vtko.survival.entity;

import java.util.ArrayList;
import java.util.List;

public final class EntityManager {

    private static List<Entity> entities = new ArrayList<>();

    public static void addEntity(Entity entity) {
        entities.add(entity);
    }

    public static void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public static List<Entity> getEntities() {
        return entities;
    }
}
