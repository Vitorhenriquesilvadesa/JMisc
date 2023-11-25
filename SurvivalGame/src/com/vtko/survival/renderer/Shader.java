package com.vtko.survival.renderer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.lwjgl.opengl.GL45.*;

public class Shader {
    private final int id;

    public Shader(String vertexFile, String fragmentFile) throws IOException {
        id = load(vertexFile, fragmentFile);
    }

    public int load(String vertexPath, String fragmentPath) throws IOException {
        int id = glCreateProgram();

        assert id != 0;

        int vertexShader = glCreateShader(GL_VERTEX_SHADER);

        assert vertexShader != 0;

        BufferedReader reader = new BufferedReader(new FileReader(vertexPath));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }

        glShaderSource(vertexShader, sb.toString());
        glCompileShader(vertexShader);

        if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
            String errorLog = glGetShaderInfoLog(vertexShader);
            throw new IOException("Vertex shader compilation failed:\n" + errorLog);
        }

        System.out.println("Vertex shader compilation successful:\n" + sb);

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);

        assert fragmentShader != 0;

        reader = new BufferedReader(new FileReader(fragmentPath));
        sb = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }

        glShaderSource(fragmentShader, sb.toString());
        glCompileShader(fragmentShader);

        if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
            String errorLog = glGetShaderInfoLog(fragmentShader);
            throw new IOException("Fragment shader compilation failed:\n" + errorLog);
        }

        System.out.println("Fragment shader compilation successful:\n" + sb);

        glLinkProgram(id);
        glValidateProgram(id);
        glAttachShader(id, vertexShader);
        glAttachShader(id, fragmentShader);

        return id;
    }
    public void bind() {
        glUseProgram(this.id);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void setUniformBoolean(String name, boolean value) {
        int location = glGetUniformLocation(id, name);
        glUniform1i(location, value ? 1 : 0);
    }

    public void setUniformInteger(String name, int value) {
        int location = glGetUniformLocation(id, name);
        glUniform1i(location, value);
    }

    public void setUniformFloat(String name, float value) {
        int location = glGetUniformLocation(id, name);
        glUniform1f(location, value);
    }
}
