package Transformacoes;
import javax.swing.*;
import java.awt.*;

public class TranslacaoExemplo extends JPanel{
    // Coordenadas do objeto original
    private int x = 50;
    private int y = 50;

    // Valores de translação
    private int dx = 100;
    private int dy = 80;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cor do objeto original
        g.setColor(Color.BLUE);
        g.drawRect(x, y, 80, 50);
        g.drawString("Original", x, y - 10);

        // Aplicando translação
        int xNovo = x + dx;
        int yNovo = y + dy;

        // Cor do objeto transladado
        g.setColor(Color.RED);
        g.drawRect(xNovo, yNovo, 80, 50);
        g.drawString("Transladado", xNovo, yNovo - 10);

        // Vetor de deslocamento (visual)
        g.setColor(Color.BLACK);
        g.drawLine(x + 40, y + 25, xNovo + 40, yNovo + 25);
    }    
}
