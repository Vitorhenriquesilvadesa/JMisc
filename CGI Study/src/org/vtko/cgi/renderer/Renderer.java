package org.vtko.cgi.renderer;

import org.vtko.cgi.entities.Entity;
import org.vtko.cgi.entities.VertexArray;
import org.vtko.cgi.utils.Camera;
import org.vtko.cgi.utils.Matrix4f;
import org.vtko.cgi.utils.OrthographicProjectionProps;
import org.vtko.cgi.utils.PerspectiveProjectionProps;

import java.util.List;

import static org.lwjgl.opengl.GL45.*;

public class Renderer {

    private Shader shader;
    private Matrix4f projectionMatrix;

    public Renderer() {
        this.shader = new Shader("src/org/vtko/cgi/shaders/vertex.glsl", "src/org/vtko/cgi/shaders/fragment.glsl");
        this.projectionMatrix = new Matrix4f();
        this.projectionMatrix.setIdentity();
    }

    public void render(Camera camera, List<Entity> entities) {
        this.shader.bind();

        for (Entity entity : entities) {
            VertexArray vao = entity.getModel().getVao();
            this.shader.setProjectionMatrix(this.projectionMatrix);
            this.shader.setTransformationMatrix(entity.transform.getTransformationMatrix());
            entity.getTexture().bind();
            glBindVertexArray(vao.getVaoID());
            glEnableVertexAttribArray(0);
            glEnableVertexAttribArray(1);
            glDrawElements(GL_TRIANGLES, vao.getCount(), GL_UNSIGNED_INT, 0);
            glDisableVertexAttribArray(0);
            glDisableVertexAttribArray(1);
            entity.getTexture().unbind();
        }

        this.shader.unbind();
    }

    public void setProjectionType(ProjectionType type) {
        switch (type) {
            case PERSPECTIVE: {
                this.projectionMatrix.setPerspective(PerspectiveProjectionProps.FOV, PerspectiveProjectionProps.ASPECT_RATIO,
                        PerspectiveProjectionProps.NEAR, PerspectiveProjectionProps.FAR);
                break;
            }

            case ORTHOGRAPHIC: {
                this.projectionMatrix.setOrthographic(
                        OrthographicProjectionProps.LEFT, OrthographicProjectionProps.RIGHT,
                        OrthographicProjectionProps.BOTTOM, OrthographicProjectionProps.TOP,
                        OrthographicProjectionProps.NEAR, OrthographicProjectionProps.FAR);
                break;
            }

            case NONE: {
                this.projectionMatrix.setIdentity();
                break;
            }
        }
    }
}
