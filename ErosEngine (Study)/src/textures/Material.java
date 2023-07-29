package textures;

public class Material {

    private int textureID;
    private float smothness = 1;
    private float reflectivity = 0;
    private boolean hasTransparency = false;
    private boolean useFakeLighting = false;

    public Material(int id, float smothness, float reflectivity, boolean transparency, boolean fakeLighting) {
        this.textureID = id;
        this.smothness = smothness;
        this.reflectivity = reflectivity;
        this.hasTransparency = transparency;
        this.useFakeLighting = fakeLighting;
    }

    public Material(int id) {
        this.textureID = id;
    }

    public int getID() {
        return this.textureID;
    }

    public float getSmothness() {
        return this.smothness;
    }

    public void setSmothness(float smothness) {
        this.smothness = smothness;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }

    public boolean isHasTransparency() {
        return hasTransparency;
    }

    public void setHasTransparency(boolean hasTransparency) {
        this.hasTransparency = hasTransparency;
    }

    public boolean isUseFakeLighting() {
        return useFakeLighting;
    }

    public void setUseFakeLighting(boolean useFakeLighting) {
        this.useFakeLighting = useFakeLighting;
    }
}
