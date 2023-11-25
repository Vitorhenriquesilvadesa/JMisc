package org.vtko.cgi.entities;

public class RawModel {

    private VertexArray vao;

    public RawModel(VertexArray vao){
        this.vao = vao;
    }

    public VertexArray getVao(){
        return this.vao;
    }
}
