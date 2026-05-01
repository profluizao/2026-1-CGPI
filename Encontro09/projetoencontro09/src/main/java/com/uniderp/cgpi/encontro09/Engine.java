package com.uniderp.cgpi.encontro09;

import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Engine {
    private long window;
    private int programId;
    private FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
    
    private Camera camera;
    private Projection projection;
    private Matrix4f modelMatrix;

    private int vaoId;
    private int vboId;

    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        // Configuração de Erros
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) throw new IllegalStateException("Falha ao inicializar GLFW");

        // Configurações da Janela
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        window = glfwCreateWindow(800, 600, "Aula: Modelos de Câmera", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Falha ao criar janela");

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwShowWindow(window);

        glClearColor(0.2f, 0.3f, 0.3f, 1.0f); // Um tom de verde petróleo (TI Verde!)
        glEnable(GL_DEPTH_TEST); // Ativa o teste de profundidade para 3D

        // Inicialização dos Objetos Lógicos
        camera = new Camera();
        projection = new Projection(800, 600);
        modelMatrix = new Matrix4f(); // Objeto na origem

        // Inicialização do Shader
        setupShaders();

        setupGeometry();
    }

    private void setupShaders() {
        String vertexCode = ShaderUtils.loadShader("src/main/resources/shaders/vertex.glsl");
        int vShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vShader, vertexCode);
        glCompileShader(vShader);
        
        // Verificação simples de erro de compilação
        if (glGetShaderi(vShader, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new RuntimeException("Erro Shader: " + glGetShaderInfoLog(vShader));
        }

        programId = glCreateProgram();
        glAttachShader(programId, vShader);
        glLinkProgram(programId);
        glUseProgram(programId);

        if (glGetProgrami(programId, GL_LINK_STATUS) == GL_FALSE) {
            throw new RuntimeException("Erro ao Linkar Shader: " + glGetProgramInfoLog(programId));
        }        
    }

    private void loop() {
        int locModel = glGetUniformLocation(programId, "modelMatrix");
        int locView = glGetUniformLocation(programId, "viewMatrix");
        int locProj = glGetUniformLocation(programId, "projectionMatrix");

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Upload das Matrizes
            uploadMatrix(locProj, projection.getMatrix());
            uploadMatrix(locView, camera.getViewMatrix());
            uploadMatrix(locModel, modelMatrix);

            while (!glfwWindowShouldClose(window)) {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

                // ... Upload das matrizes (Model, View, Projection) ...

                // 3. Desenhar o Triângulo
                glBindVertexArray(vaoId); // Ativa o triângulo
                glDrawArrays(GL_TRIANGLES, 0, 3); // Desenha 3 vértices começando do 0
                glBindVertexArray(0); // Desativa

                glfwSwapBuffers(window);
                glfwPollEvents();
            }

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void uploadMatrix(int location, Matrix4f matrix) {
        matrix.get(matrixBuffer);
        glUniformMatrix4fv(location, false, matrixBuffer);
    }

    private void cleanup() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    // Para testar se CPU e GPU estão se falando.
    private void setupGeometry() {
        // Definimos 3 vértices (x, y, z) para formar um triângulo centralizado
        float[] vertices = new float[]{
            0.0f,  0.5f, 0.0f,  // Topo
            -0.5f, -0.5f, 0.0f,  // Esquerda
            0.5f, -0.5f, 0.0f   // Direita
        };

        // 1. Criar o VAO (O "contêiner" das configurações)
        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        // 2. Criar o VBO (O buffer de memória na GPU)
        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        
        // 3. Enviar os dados do array Java para o buffer da GPU
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        // 4. Explicar ao Shader como ler esses dados (Atributo 0: Posição)
        // Coordenada 0, tamanho 3 (x,y,z), tipo float, sem normalização, salto 0, offset 0
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);

        // Desvincular para evitar modificações acidentais
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }    
}
