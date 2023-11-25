package org.vtko.cgi.entities;

import org.vtko.cgi.engine.config.EngineConfig;
import org.vtko.cgi.utils.Matrix4f;
import org.vtko.cgi.utils.Vector3;

public class Transform {

    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;

    public Transform(Vector3 position, Vector3 rotation, Vector3 scale) {
        this.position = position;
        position.x /= EngineConfig.getWindowConfig().width();
        position.y /= EngineConfig.getWindowConfig().height();
        this.rotation = rotation;
        this.scale = scale;
    }

    public Transform(){
        this.position = Vector3.zero();
        this.rotation = Vector3.zero();
        this.scale = new Vector3(1f, 1f, 1f);
    }

    public Matrix4f getTransformationMatrix(){
        return Matrix4f.createTransformationMatrix(this);
    }

    public void setPositionInPixels(Vector3 position){
        this.position = position;
        position.x /= EngineConfig.getWindowConfig().width();
        position.y /= EngineConfig.getWindowConfig().height();
    }
}
