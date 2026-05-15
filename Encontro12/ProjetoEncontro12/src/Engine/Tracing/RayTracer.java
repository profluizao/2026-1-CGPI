package Engine.Tracing;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RayTracer extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MAX_DEPTH = 3; // Profundidade da recursão de reflexos

    // Definição dos Objetos da Cena
    private static final Sphere[] SPHERES = {
        new Sphere(new Vector3(0, 0, -5), 1.0, new Vector3(1.0, 0.2, 0.2), 0.6),    // Esfera Vermelha (Espelhada)
        new Sphere(new Vector3(-2, 1, -6), 1.5, new Vector3(0.2, 0.2, 1.0), 0.2),   // Esfera Azul
        new Sphere(new Vector3(2, -0.5, -4), 0.5, new Vector3(0.2, 1.0, 0.2), 0.8),  // Esfera Verde (Muito espelhada)
        new Sphere(new Vector3(0, -51, -5), 50.0, new Vector3(0.6, 0.6, 0.6), 0.1)  // Esfera Gigante atuando como o Chão
    };

    // Posição da Fonte de Luz Pontual
    private static final Vector3 LIGHT_POS = new Vector3(5, 10, -2);

    private BufferedImage image;

    public RayTracer() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        renderScene();
    }

    private void renderScene() {
        Vector3 cameraPos = new Vector3(0, 0, 0);
        double fov = Math.PI / 3; // 60 graus
        double aspectRatio = (double) WIDTH / HEIGHT;
        double angle = Math.tan(fov / 2);

        // Varre cada pixel da tela
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                
                // Mapeia o pixel para o espaço da tela (-1 a 1)
                double screenX = (2.0 * (x + 0.5) / WIDTH - 1.0) * angle * aspectRatio;
                double screenY = (1.0 - 2.0 * (y + 0.5) / HEIGHT) * angle;

                // Direção do raio primário
                Vector3 rayDir = new Vector3(screenX, screenY, -1).normalize();

                // Traça o raio e obtém a cor final
                Vector3 color = trace(cameraPos, rayDir, 0);

                // Converte o vetor de cor (0.0 a 1.0) para formato RGB inteiro
                int r = (int) (Math.min(1.0, color.x) * 255);
                int g = (int) (Math.min(1.0, color.y) * 255);
                int b = (int) (Math.min(1.0, color.z) * 255);
                int rgb = (r << 16) | (g << 8) | b;

                image.setRGB(x, y, rgb);
            }
        }
    }

    private Vector3 trace(Vector3 rayOrig, Vector3 rayDir, int depth) {
        if (depth > MAX_DEPTH) return new Vector3(0, 0, 0); // Fundo preto se estourar o limite

        Sphere hitSphere = null;
        double tNear = Double.MAX_VALUE;

        // Procura a interseção mais próxima
        for (Sphere sphere : SPHERES) {
            double t = sphere.intersect(rayOrig, rayDir);
            if (t > 0 && t < tNear) {
                tNear = t;
                hitSphere = sphere;
            }
        }

        // Se não atingiu nada, retorna a cor do céu (gradiente suave)
        if (hitSphere == null) {
            return new Vector3(0.5, 0.7, 1.0).multiply(0.5 + 0.5 * rayDir.y);
        }

        // Pontos de impacto e vetores normais
        Vector3 hitPoint = rayOrig.add(rayDir.multiply(tNear));
        Vector3 normal = hitPoint.subtract(hitSphere.center).normalize();

        // Vetor em direção à luz
        Vector3 lightDir = LIGHT_POS.subtract(hitPoint).normalize();

        // --- CÁLCULO DE SOMBRA (Shadow Ray) ---
        boolean inShadow = false;
        Vector3 shadowOrig = hitPoint.add(normal.multiply(0.001)); // Pequeno offset para evitar auto-interseção
        for (Sphere sphere : SPHERES) {
            if (sphere.intersect(shadowOrig, lightDir) > 0) {
                inShadow = true;
                break;
            }
        }

        // Iluminação Difusa (Lambertiana)
        double diffuseIntensity = Math.max(0.0, normal.dot(lightDir));
        Vector3 diffuseColor = hitSphere.color.multiply(diffuseIntensity);
        if (inShadow) diffuseColor = diffuseColor.multiply(0.2); // Escurece se estiver em sombra (luz ambiente)

        // --- CÁLCULO DE REFLEXO RECURSIVO ---
        Vector3 reflectColor = new Vector3(0, 0, 0);
        if (hitSphere.reflectivity > 0) {
            // R = I - 2 * (N . I) * N
            Vector3 reflectDir = rayDir.subtract(normal.multiply(2.0 * rayDir.dot(normal))).normalize();
            Vector3 reflectOrig = hitPoint.add(normal.multiply(0.001));
            reflectColor = trace(reflectOrig, reflectDir, depth + 1);
        }

        // Combina a cor difusa do objeto com a cor do reflexo baseado na taxa de refletividade
        return diffuseColor.multiply(1.0 - hitSphere.reflectivity).add(reflectColor.multiply(hitSphere.reflectivity));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}