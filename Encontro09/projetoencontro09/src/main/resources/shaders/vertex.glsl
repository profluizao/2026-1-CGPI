#version 330 core

layout (location = 0) in vec3 position;

// Declaração dos Uniforms (os nomes devem ser idênticos aos do Java)
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

void main() {
    // A GPU faz a multiplicação de matrizes de forma massivamente paralela
    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position, 1.0);
}