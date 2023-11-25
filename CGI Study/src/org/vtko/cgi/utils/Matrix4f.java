package org.vtko.cgi.utils;

import org.vtko.cgi.entities.Transform;

import java.nio.FloatBuffer;
import java.util.Arrays;

public class Matrix4f {
    public float[] elements;

    public Matrix4f() {
        elements = new float[16];
        Arrays.fill(elements, 0.0f);
    }

    public static Matrix4f createTransformationMatrix(Transform transform) {
        Matrix4f matrix = new Matrix4f();

        // matrix.elements = Matrix4f.identity().toFloatBuffer().array();
        // matrix.multiply(Matrix4f.translate(position));
        // matrix.multiply(Matrix4f.rotate(rotation));

        matrix.setIdentity();
        matrix.translate(transform.position);
        matrix.rotateX(transform.rotation.x);
        matrix.rotateY(transform.rotation.y);
        matrix.rotateZ(transform.rotation.z);
        matrix.scale(transform.scale);

        return matrix;
    }

    public float[] getMatrix() {
        return this.elements;
    }

    public void setIdentity() {
        Arrays.fill(elements, 0.0f);
        elements[0] = 1.0f;
        elements[5] = 1.0f;
        elements[10] = 1.0f;
        elements[15] = 1.0f;
    }

    public void setOrthographic(float left, float right, float bottom, float top, float near, float far) {
        elements[0] = 2.0f / (right - left);
        elements[5] = 2.0f / (top - bottom);
        elements[10] = -2.0f / (far - near);
        elements[12] = -(right + left) / (right - left);
        elements[13] = -(top + bottom) / (top - bottom);
        elements[14] = -(far + near) / (far - near);
        elements[15] = 1.0f;
    }

    public void setPerspective(float fov, float aspectRatio, float near, float far) {
        float tanHalfFOV = (float) Math.tan(Math.toRadians(fov / 2.0));
        float range = near - far;

        elements[0] = 1.0f / (aspectRatio * tanHalfFOV);
        elements[5] = 1.0f / tanHalfFOV;
        elements[10] = (near + far) / range;
        elements[11] = -1.0f;
        elements[14] = 2.0f * far * near / range;
    }

    public void scale(Vector3 scale) {
        elements[0] *= scale.x;
        elements[5] *= scale.y;
        elements[10] *= scale.z;
    }

    public void translate(Vector3 translation) {
        elements[12] += translation.x;
        elements[13] += translation.y;
        elements[14] += translation.z;
    }

    public void rotateX(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);

        float temp1 = elements[4] * cos + elements[8] * sin;
        float temp2 = elements[5] * cos + elements[9] * sin;
        float temp3 = elements[6] * cos + elements[10] * sin;
        float temp4 = elements[7] * cos + elements[11] * sin;

        elements[8] = elements[8] * cos - elements[4] * sin;
        elements[9] = elements[9] * cos - elements[5] * sin;
        elements[10] = elements[10] * cos - elements[6] * sin;
        elements[11] = elements[11] * cos - elements[7] * sin;

        elements[4] = temp1;
        elements[5] = temp2;
        elements[6] = temp3;
        elements[7] = temp4;
    }

    public void rotateY(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);

        float temp1 = elements[0] * cos - elements[8] * sin;
        float temp2 = elements[1] * cos - elements[9] * sin;
        float temp3 = elements[2] * cos - elements[10] * sin;
        float temp4 = elements[3] * cos - elements[11] * sin;

        elements[8] = elements[0] * sin + elements[8] * cos;
        elements[9] = elements[1] * sin + elements[9] * cos;
        elements[10] = elements[2] * sin + elements[10] * cos;
        elements[11] = elements[3] * sin + elements[11] * cos;

        elements[0] = temp1;
        elements[1] = temp2;
        elements[2] = temp3;
        elements[3] = temp4;
    }

    public void rotateZ(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);

        float temp1 = elements[0] * cos + elements[4] * sin;
        float temp2 = elements[1] * cos + elements[5] * sin;
        float temp3 = elements[2] * cos + elements[6] * sin;
        float temp4 = elements[3] * cos + elements[7] * sin;

        elements[4] = elements[4] * cos - elements[0] * sin;
        elements[5] = elements[5] * cos - elements[1] * sin;
        elements[6] = elements[6] * cos - elements[2] * sin;
        elements[7] = elements[7] * cos - elements[3] * sin;

        elements[0] = temp1;
        elements[1] = temp2;
        elements[2] = temp3;
        elements[3] = temp4;
    }

    public void add(Matrix4f other) {
        for (int i = 0; i < 16; i++) {
            elements[i] += other.elements[i];
        }
    }

    public void subtract(Matrix4f other) {
        for (int i = 0; i < 16; i++) {
            elements[i] -= other.elements[i];
        }
    }

    public void multiply(Matrix4f other) {
        float[] result = new float[16];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float sum = 0.0f;
                for (int k = 0; k < 4; k++) {
                    sum += elements[i * 4 + k] * other.elements[k * 4 + j];
                }
                result[i * 4 + j] = sum;
            }
        }

        elements = result;
    }

    public void divide(Matrix4f other) {
        float[] result = new float[16];

        for (int i = 0; i < 16; i++) {
            if (other.elements[i] != 0.0f) {
                result[i] = elements[i] / other.elements[i];
            } else {
                // TODO Handle division by zero error
                result[i] = 0.0f;
            }
        }

        elements = result;
    }

    public FloatBuffer toFloatBuffer() {
        FloatBuffer buffer = FloatBuffer.allocate(16);
        buffer.put(elements);
        buffer.flip();
        return buffer;
    }
}
