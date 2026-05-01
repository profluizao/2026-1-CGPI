package com.uniderp.cgpi.encontro09;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {
    public Vector3f position = new Vector3f(0, 0, 5);
    public Vector3f rotation = new Vector3f(0, 0, 0);

    public Matrix4f getViewMatrix() {
        return new Matrix4f()
            .rotateX((float)Math.toRadians(rotation.x))
            .rotateY((float)Math.toRadians(rotation.y))
            .translate(-position.x, -position.y, -position.z);
    }
}