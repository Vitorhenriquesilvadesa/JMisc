package shaders;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import math.Matrix4f;
import math.Vector3;
import utils.DirectoryManagement;
import utils.FileUtils;

public abstract class ShaderProgram {

    private String name;
    private String vertexSource;
    private String fragmentSource;
    private int vertexID;
    private int fragmentID;
    private int programID;
    private int success;
    private FloatBuffer matrix;

    public ShaderProgram(String name, String vertPath, String fragPath) {
        this.name = name;
        this.vertexSource = FileUtils.fileToString(DirectoryManagement.workingDirectory + "/" + vertPath);
        this.fragmentSource = FileUtils.fileToString(DirectoryManagement.workingDirectory + "/" + fragPath);

        this.vertexID = glCreateShader(GL_VERTEX_SHADER);
        this.fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(this.vertexID, this.vertexSource);
        glShaderSource(this.fragmentID, this.fragmentSource);

        glCompileShader(this.vertexID);

        success = glGetShaderi(vertexID, GL_COMPILE_STATUS);

        if (success != GL_TRUE) {
            String log = glGetShaderInfoLog(vertexID);
            System.out.println("Error to compile Vertex Shader in " + this.name + ": " + log);
            return;
        }

        glCompileShader(this.fragmentID);

        success = glGetShaderi(this.fragmentID, GL_COMPILE_STATUS);
        if (success != GL_TRUE) {
            String log = glGetShaderInfoLog(vertexID);
            System.out.println("Error to compile Fragment Shader in " + this.name + ": " + log);
            return;
        }

        this.programID = glCreateProgram();
        glAttachShader(this.programID, this.vertexID);
        glAttachShader(this.programID, this.fragmentID);

        bindAttributes();

        glLinkProgram(this.programID);
        glValidateProgram(this.programID);

        success = glGetProgrami(this.programID, GL_LINK_STATUS);

        if (success != GL_TRUE) {
            String log = glGetShaderInfoLog(this.programID);
            System.out.println("Error to create shader program in " + this.name + ": " + log);
        }

        getAllUniformLocations();
    }

    public int getUniformLocation(String uniformName) {
        return glGetUniformLocation(programID, uniformName);
    }

    public abstract void getAllUniformLocations();

    public void loadFloat(int location, float value) {

        glUniform1f(location, value);
    }

    public void loadVector(int location, Vector3 vector) {
        glUniform3f(location, vector.x, vector.y, vector.z);
    }

    public void loadBoolean(int location, boolean value) {
        float toLoad = 0;

        if (value) {
            toLoad = 1;
        }

        glUniform1f(location, toLoad);
    }

    protected void loadMatrix(int location, Matrix4f matrix) {

        glUniformMatrix4fv(location, false, matrix.getMatrix());
    }

    public void start() {
        glUseProgram(this.programID);
    }

    public void stop() {
        glUseProgram(0);
    }

    public void cleanUp() {
        stop();
        glDetachShader(programID, vertexID);
        glDetachShader(programID, vertexID);
        glDeleteShader(vertexID);
        glDeleteShader(fragmentID);
        glDeleteProgram(this.programID);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variableName) {

        glBindAttribLocation(programID, attribute, variableName);
    }

    public FloatBuffer getMatrix() {
        return matrix;
    }
}
