package Transformacoes;

import javax.swing.*;
import java.awt.*;

public class EscalaExemplo extends JPanel {

    // Criar variáveis de posição
    private int x = 50;
    private int y = 50;
    private int largura = 80;
    private int altura = 50;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhar um retângulo
        g.setColor(Color.BLUE);
        g.drawRect(x, y, largura, altura);
        g.drawString("Original", x, y - 10);

        // Criar fatores de escala
        double sx = 1.5;
        double sy = 2.0;

        // Aplicar a fórmula
        int novaLargura = (int)(largura * sx);
        int novaAltura = (int)(altura * sy);

        // Desenhar o objeto escalado
        g.setColor(Color.RED);
        g.drawRect(x, y, novaLargura, novaAltura);
        g.drawString("Escalado", x, y + novaAltura + 15);
    }
}
