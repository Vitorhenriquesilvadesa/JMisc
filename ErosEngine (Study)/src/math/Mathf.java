package math;

import entities.Camera;

public class Mathf {

    public static Matrix4f createTrasformationMatrix(Vector3 position, Vector3 rotation,
            Vector3 scale) {
        Matrix4f matrix = new Matrix4f();

        // matrix.elements = Matrix4f.identity().toFloatBuffer().array();
        // matrix.multiply(Matrix4f.translate(position));
        // matrix.multiply(Matrix4f.rotate(rotation));

        matrix.setIdentity();
        matrix.translate(position);
        matrix.rotateX(rotation.x);
        matrix.rotateY(rotation.y);
        matrix.rotateZ(rotation.z);
        matrix.scale(scale);

        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        viewMatrix.rotateX((float) Math.toRadians(camera.getPitch()));
        viewMatrix.rotateY((float) Math.toRadians(camera.getYaw()));

        Vector3 cameraPos = camera.getPosition();
        Vector3 negativeCameraPos = new Vector3(-cameraPos.x, -cameraPos.y, -cameraPos.z);

        viewMatrix.translate(negativeCameraPos);

        return viewMatrix;
    }
}
