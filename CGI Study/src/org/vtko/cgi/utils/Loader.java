package org.vtko.cgi.utils;

import org.vtko.cgi.entities.RawModel;
import org.vtko.cgi.entities.VertexArray;

public class Loader {

    public static RawModel createRawModel(float[] positions, int[] indices, float[] texCoords) {
        VertexArray vao = new VertexArray(positions, indices, texCoords);

        return new RawModel(vao);
    }
}
