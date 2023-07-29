package models;

import textures.Material;

public class TexturedModel {

    private RawModel rawModel;
    private Material material;

    public TexturedModel(RawModel model, Material material) {
        this.rawModel = model;
        this.material = material;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public Material getMaterial() {
        return material;
    }

    public void setmaterial(Material mat) {
        this.material = mat;
    }
}
