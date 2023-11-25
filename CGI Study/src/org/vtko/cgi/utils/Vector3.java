package org.vtko.cgi.utils;

public class Vector3 {

    public float x;
    public float y;
    public float z;

    public Vector3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private Vector3(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3(Vector3 other){
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public Vector2 xy(){
        return new Vector2(x, y);
    }

    public float length(){
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public static Vector3 add(Vector3 a, Vector3 b){
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static float dot(Vector3 a, Vector3 b){
        return (a.x * b.x + a.y * b.y + a.z * b.z);
    }

    public static Vector3 zero(){
        return new Vector3(0f, 0f, 0f);
    }

    public static Vector3 subtract(Vector3 a, Vector3 b) {
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Vector3 cross(Vector3 a, Vector3 b) {
        return new Vector3(
                a.y * b.z - a.z * b.y,
                a.z * b.x - a.x * b.z,
                a.x * b.y - a.y * b.x
        );
    }

    public static float distance(Vector3 a, Vector3 b) {
        float dx = b.x - a.x;
        float dy = b.y - a.y;
        float dz = b.z - a.z;
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static Vector3 multiply(Vector3 a, float c){
        return new Vector3(a.x * c, a.y * c, a.z * c);
    }
    public static Vector3 normalize(Vector3 a) {
        float length = (float) Math.sqrt(a.x * a.x + a.y * a.y + a.z * a.z);
        return new Vector3(a.x / length, a.y / length, a.z / length);
    }
    public static Vector3 multiply(Vector3 a, Vector3 b){
        return new Vector3(a.x * b.x, b.y * b.y, a.z * b.z);
    }

    public static Vector3 forward(){
        return new Vector3(0, 0, 1);
    }

    public static Vector3 back(){
        return new Vector3(0, 0, -1);
    }

    public static Vector3 left(){
        return new Vector3(-1, 0, 0);
    }

    public static Vector3 right(){
        return new Vector3(1, 0, 0);
    }

    public static Vector3 up(){
        return new Vector3(0, 1, 0);
    }

    public static Vector3 down(){
        return new Vector3(0, -1, 0);
    }

    @Override
    public String toString(){
        return "Vector3 { " + this.x + ", " + this.y + ", " + this.z + " }";
    }
}

