package com.uniderp.cgpi.encontro09;

import org.joml.Matrix4f;

public class Projection {
    private final Matrix4f projectionMatrix;

    public Projection(int width, int height) {
        this.projectionMatrix = new Matrix4f();
        update(width, height);
    }

    public void update(int width, int height) {
        float aspect = (float) width / height;
        projectionMatrix.setPerspective((float)Math.toRadians(60.0f), aspect, 0.01f, 100.0f);
    }

    public Matrix4f getMatrix() { return projectionMatrix; }
}
