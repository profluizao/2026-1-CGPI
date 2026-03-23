package Transformacoes;

import javax.swing.*;
import java.awt.*;

public class RotacaoExemplo extends JPanel{
    // Coordenadas do objeto original
    private int x = 150;
    private int y = 100;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhar um retângulo
        g.setColor(Color.BLUE);
        g.drawRect(x, y, 80, 50);
        g.drawString("Original", x, y - 10);

        // Definir ângulo de rotação
        double angulo = Math.toRadians(45);
        int xNovo = (int)(x * Math.cos(angulo) - y * Math.sin(angulo));
        int yNovo = (int)(x * Math.sin(angulo) + y * Math.cos(angulo));

        // Desenhar o objeto rotacionado
        g.setColor(Color.RED);
        g.drawRect(xNovo, yNovo, 80, 50);
        g.drawString("Rotacionado", xNovo, yNovo - 10);

        // Desenhar linha entre posições
        g.setColor(Color.BLACK);
        g.drawLine(x, y, xNovo, yNovo);
    }
}
