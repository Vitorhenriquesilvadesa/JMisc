package org.vtko.cgi.utils;

public class Camera {
    public Vector3 position;
    private Vector3 front;
    private Vector3 up;

    public Camera() {
        position = new Vector3(0, 0, 0);
        front = new Vector3(0, 0, -1);
        up = new Vector3(0, 1, 0);
    }

    public Vector3 getFront() {
        return front;
    }

    public void setFront(Vector3 front) {
        this.front = front;
    }

    public Vector3 getUp() {
        return up;
    }

    public void setUp(Vector3 up) {
        this.up = up;
    }
}
