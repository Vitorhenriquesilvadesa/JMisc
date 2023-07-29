package entities;

import engine.Input;
import engine.KeyCode;
import math.Vector3;

public class Camera {
    private Vector3 position = new Vector3(0, 0, 0);
    private float pitch; // How your camera can view in top and bottom;
    private float yaw; // how your camera can view in left and right;
    private float roll;
    private Input input;
    private float moveSpeed;

    public Camera(long window, float moveSpeed) {
        input = new Input(window);
        this.moveSpeed = moveSpeed;
    }

    public void move() {
        if (input.getKey(KeyCode.W)) {
            position.z -= moveSpeed;
        }

        if (input.getKey(KeyCode.S)) {
            position.z += moveSpeed;
        }

        if (input.getKey(KeyCode.A)) {
            position.x -= moveSpeed;
        }

        if (input.getKey(KeyCode.D)) {
            position.x += moveSpeed;
        }

        if (input.getKey(KeyCode.Q)) {
            position.y += moveSpeed;
        }

        if (input.getKey(KeyCode.Z)) {
            position.y -= moveSpeed;
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
