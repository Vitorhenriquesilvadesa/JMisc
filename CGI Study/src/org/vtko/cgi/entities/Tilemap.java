package org.vtko.cgi.entities;

import org.vtko.cgi.engine.config.EngineConfig;

public class Tilemap {

    public static final int TILE_SIZE = 16;
    public static final int SCREEN_WIDTH = EngineConfig.getWindowConfig().width();
    public static final int SCREEN_HEIGHT = EngineConfig.getWindowConfig().height();
    public static final int TILE_SCALE = 8;
    private static VertexArray squareVAO;

    public static VertexArray getSquareVAO() {
        if (squareVAO == null) {
            squareVAO = new VertexArray(generateSquareVertices(), generateSquareIndices(), generateSquareTexCoord());
        }
        return squareVAO;
    }

    private static int[] generateSquareIndices() {
        return new int[]{
                0, 1, 2,
                0, 2, 3,
        };
    }

    private static float[] generateSquareTexCoord() {
        return new float[]{
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
        };
    }

    private static float[] generateSquareVertices() {
        float pixelWidth = (16f / TILE_SCALE / (float) SCREEN_WIDTH) * TILE_SIZE;
        float pixelHeight = (9f / TILE_SCALE / (float) SCREEN_HEIGHT) * TILE_SIZE;

        return new float[]{
                -pixelWidth, -pixelHeight, 0.0f,
                pixelWidth, -pixelHeight, 0.0f,
                pixelWidth, pixelHeight, 0.0f,
                -pixelWidth, pixelHeight, 0.0f,
        };
    }
}
