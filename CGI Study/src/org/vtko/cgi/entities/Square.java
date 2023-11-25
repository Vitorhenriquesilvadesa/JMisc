package org.vtko.cgi.entities;

import org.vtko.cgi.entities.textures.Texture;
import org.vtko.cgi.utils.Vector2;
import org.vtko.cgi.utils.Vector3;

import static org.vtko.cgi.entities.Tilemap.*;

public class Square extends Entity{

    public Square(Texture texture, Vector2 position) {
        super(texture, new Transform());
        float pixelWidth = 2f / (float) SCREEN_WIDTH;
        float pixelHeight = 2f / (float) SCREEN_HEIGHT;
        transform.position = new Vector3(2f * position.x * pixelWidth, 2f * position.y * pixelHeight, 0.0f);
        setModel(generateModel());
    }

    private RawModel generateModel(){
        return new RawModel(Tilemap.getSquareVAO());
    }
}
