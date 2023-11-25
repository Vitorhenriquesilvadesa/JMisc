package org.vtko.cgi.entities;

import org.vtko.cgi.entities.textures.Texture;
import org.vtko.cgi.utils.Vector3;

public class Entity {

    public Transform transform;
    private RawModel model;
    private Texture texture;
    public Vector3 velocity = Vector3.zero();

    public Entity(RawModel model, Texture texture, Transform transform) {
        this.texture = texture;
        this.transform = transform;
        this.model = model;
    }

    public Entity(Texture texture, Transform transform) {
        this.texture = texture;
        this.transform = transform;
    }

    public RawModel getModel() {
        return this.model;
    }

    public void setModel(RawModel model) {
        this.model = model;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
