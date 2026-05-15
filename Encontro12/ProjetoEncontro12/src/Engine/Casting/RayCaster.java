package Engine.Casting;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RayCaster extends JPanel implements Runnable, KeyListener {

    // Mapa do cenário (1 = Parede, 0 = Caminho livre)
    private final int[][] MAP = {
        {1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,1},
        {1,0,1,0,0,1,0,1},
        {1,0,0,0,0,0,0,1},
        {1,0,1,0,0,0,0,1},
        {1,0,0,0,1,1,0,1},
        {1,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1}
    };
    private final int MAP_SIZE = 8;
    private final int TILE_SIZE = 64;

    // Posição inicial do jogador (centralizada em um espaço vazio)
    private double posX = 120;
    private double posY = 120;
    private double dirAngle = 0; // Ângulo em radianos

    // Estados do teclado para movimentação suave
    private boolean keyLeft, keyRight, keyW, keyS;

    public RayCaster() {
        setPreferredSize(new Dimension(640, 480));
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        
        // Inicializa a thread principal do jogo
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // Game Loop simples
        while (true) {
            updateLogic();
            repaint(); // Solicita a renderização do Swing
            try { 
                Thread.sleep(16); // Aproximadamente 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Atualiza a física e os inputs antes de desenhar
    private void updateLogic() {
        if (keyLeft)  dirAngle -= 0.05;
        if (keyRight) dirAngle += 0.05;
        
        // Garante que o ângulo fique no intervalo de 0 a 2PI
        if (dirAngle < 0) dirAngle += 2 * Math.PI;
        if (dirAngle > 2 * Math.PI) dirAngle -= 2 * Math.PI;

        double newX = posX;
        double newY = posY;

        if (keyW) {
            newX += Math.cos(dirAngle) * 3;
            newY += Math.sin(dirAngle) * 3;
        }
        if (keyS) {
            newX -= Math.cos(dirAngle) * 3;
            newY -= Math.sin(dirAngle) * 3;
        }

        // Colisão básica com as paredes do mapa para o jogador não sair do cenário
        int gridX = (int) (newX / TILE_SIZE);
        int gridY = (int) (newY / TILE_SIZE);
        
        if (gridX >= 0 && gridX < MAP_SIZE && gridY >= 0 && gridY < MAP_SIZE) {
            if (MAP[gridY][gridX] == 0) {
                posX = newX;
                posY = newY;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int screenWidth = getWidth();
        int screenHeight = getHeight();
        
        double fieldOfView = Math.PI / 3; // 60 graus de campo de visão
        double stepAngle = fieldOfView / screenWidth;

        // Renderização coluna por coluna (Raio por Raio)
        for (int i = 0; i < screenWidth; i++) {
            // Calcula o ângulo do raio atual baseado no FOV
            double rayAngle = (dirAngle - fieldOfView / 2) + i * stepAngle;
            
            double distance = 0;
            boolean hitWall = false;

            double eyeX = Math.cos(rayAngle);
            double eyeY = Math.sin(rayAngle);

            // Avanço do raio no cenário
            while (!hitWall && distance < 500) {
                distance += 2; // Tamanho do passo do raio
                
                int testX = (int) ((posX + eyeX * distance) / TILE_SIZE);
                int testY = (int) ((posY + eyeY * distance) / TILE_SIZE);

                // Checa limites da matriz
                if (testX < 0 || testX >= MAP_SIZE || testY < 0 || testY >= MAP_SIZE) {
                    hitWall = true;
                    distance = 500;
                } else if (MAP[testY][testX] == 1) {
                    hitWall = true;
                }
            }

            // Correção do efeito olho de peixe (essencial para projeções retas)
            double correctedDistance = distance * Math.cos(rayAngle - dirAngle);
            if (correctedDistance < 1) correctedDistance = 1; // Evita divisão por zero

            // Calcula o tamanho vertical da parede na tela
            int columnHeight = (int) ((TILE_SIZE * screenHeight) / correctedDistance);
            if (columnHeight > screenHeight) columnHeight = screenHeight; // Limita ao tamanho da tela

            // Define os pontos Y de início e fim da parede
            int wallTop = (screenHeight - columnHeight) / 2;
            int wallBottom = (screenHeight + columnHeight) / 2;

            // 1. Desenha o Teto (Cinza Escuro)
            g2.setColor(Color.DARK_GRAY);
            g2.drawLine(i, 0, i, wallTop);

            // 2. Desenha a Parede (Garante variação de tom com a distância para simular profundidade)
            int colorIntensity = (int) (200 - (correctedDistance * 0.35));
            if (colorIntensity < 30) colorIntensity = 30; // Limite para não ficar totalmente preto
            g2.setColor(new Color(0, colorIntensity / 2, colorIntensity)); 
            g2.drawLine(i, wallTop, i, wallBottom);

            // 3. Desenha o Chão (Cinza Claro)
            g2.setColor(Color.GRAY);
            g2.drawLine(i, wallBottom, i, screenHeight);
        }
    }

    // Gerenciamento de eventos do teclado (Evita o lag do arrasto de repetição do SO)
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)  keyLeft = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) keyRight = true;
        if (e.getKeyCode() == KeyEvent.VK_W) keyW = true;
        if (e.getKeyCode() == KeyEvent.VK_S) keyS = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)  keyLeft = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) keyRight = false;
        if (e.getKeyCode() == KeyEvent.VK_W) keyW = false;
        if (e.getKeyCode() == KeyEvent.VK_S) keyS = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

}