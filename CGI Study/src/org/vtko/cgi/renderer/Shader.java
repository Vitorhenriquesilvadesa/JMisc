package org.vtko.cgi.renderer;

import org.vtko.cgi.utils.FileUtils;
import org.vtko.cgi.utils.Matrix4f;

import static org.lwjgl.opengl.GL45.*;

public class Shader {

    private final int programID;
    private Matrix4f orthographicProjectionMatrix;
    private int locationUniformTexture;
    private int locationUniformProjectionMatrix;
    private int locationUniformTransformationMatrix;
    private int locationUniformViewMatrix;

    public Shader(String vertexPath, String fragmentPath){

        String vertexShaderSource = parseShader(vertexPath);
        String fragmentShaderSource = parseShader(fragmentPath);

        int vertexShader = compileShader(vertexShaderSource, GL_VERTEX_SHADER);
        int fragmentShader = compileShader(fragmentShaderSource, GL_FRAGMENT_SHADER);
        this.programID = linkProgram(vertexShader, fragmentShader);

        getAllUniformLocations();
    }

    public String parseShader(String filePath){
        String result = FileUtils.FileToString(filePath);

        if(result.isEmpty()){
            throw new RuntimeException("Requested parse to file: \"" + filePath + "\" failed because file is empty");
        }

        return result;
    }

    public int compileShader(String source, int shaderType){
        int shader = glCreateShader(shaderType);
        glShaderSource(shader, source);
        glCompileShader(shader);

        int success = glGetShaderi(shader, GL_COMPILE_STATUS);

        if(success == GL_FALSE){
            String error = glGetShaderInfoLog(shader);
            throw new RuntimeException(error);
        }

        return shader;
    }

    public int linkProgram(int vertexShader, int fragmentShader){
        int program = glCreateProgram();
        glAttachShader(program, vertexShader);
        glAttachShader(program, fragmentShader);

        glLinkProgram(program);

        int success = glGetProgrami(program, GL_LINK_STATUS);

        if(success == GL_FALSE){

            String error = glGetProgramInfoLog(program);
            throw new RuntimeException(error);
        }

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);

        return program;
    }

    public void bind(){
        glUseProgram(this.programID);
    }

    public void unbind(){
        glUseProgram(0);
    }

    private int getUniformLocation(String uniformName){
        return glGetUniformLocation(this.programID, uniformName);
    }

    public void getAllUniformLocations(){
        this.locationUniformTexture = getUniformLocation("texture");
        this.locationUniformProjectionMatrix = getUniformLocation("u_ProjectionMatrix");
        this.locationUniformTransformationMatrix = getUniformLocation("u_TransformationMatrix");
        this.locationUniformViewMatrix = getUniformLocation("u_ViewMatrix");
    }

    private void setUniformInt(int uniformLocation, int value){
        glUniform1i(uniformLocation, value);
    }
    private void setUniformMat4(int uniformLocation, float[] value){
        glUniformMatrix4fv(uniformLocation, false, value);
        int error = glGetError();
        if (error != GL_NO_ERROR) {
            throw new RuntimeException("OpenGL error: " + error);
        }
    }

    public void setTransformationMatrix(Matrix4f transformationMatrix){
        setUniformMat4(locationUniformTransformationMatrix, transformationMatrix.toFloatBuffer().array());
    }

    public void setProjectionMatrix(Matrix4f projectionMatrix){
        setUniformMat4(locationUniformProjectionMatrix, projectionMatrix.toFloatBuffer().array());
    }

    public void setViewMatrix(Matrix4f viewMatrix){
        setUniformMat4(locationUniformViewMatrix, viewMatrix.toFloatBuffer().array());
    }
}
