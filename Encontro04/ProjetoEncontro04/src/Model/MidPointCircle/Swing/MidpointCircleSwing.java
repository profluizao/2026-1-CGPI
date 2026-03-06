package Model.MidPointCircle.Swing;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class MidpointCircleSwing extends JPanel {

    private int coord_xc;
    private int coord_yc;
    private int coord_r;

    public MidpointCircleSwing(int xc, int yc, int r){
        this.coord_xc = xc;
        this.coord_yc = yc;
        this.coord_r = r;
    }

    public void drawCircle(Graphics g) {

        int x = 0;
        int y = this.coord_r;

        int p = 1 - this.coord_r;

        drawSymmetricPoints(g, this.coord_xc, this.coord_yc, x, y);

        while (x < y) {

            x++;

            if (p < 0) {
                p = p + 2 * x + 1;
            } else {
                y--;
                p = p + 2 * (x - y) + 1;
            }

            drawSymmetricPoints(g, this.coord_xc, this.coord_yc, x, y);
        }
    }

    public void drawSymmetricPoints(Graphics g, int xc, int yc, int x, int y) {

        // simulação de pixels com pequenos retângulos
        int pixelSize = 3;

        g.fillRect(xc + x, yc + y, pixelSize, pixelSize);
        g.fillRect(xc - x, yc + y, pixelSize, pixelSize);
        g.fillRect(xc + x, yc - y, pixelSize, pixelSize);
        g.fillRect(xc - x, yc - y, pixelSize, pixelSize);

        g.fillRect(xc + y, yc + x, pixelSize, pixelSize);
        g.fillRect(xc - y, yc + x, pixelSize, pixelSize);
        g.fillRect(xc + y, yc - x, pixelSize, pixelSize);
        g.fillRect(xc - y, yc - x, pixelSize, pixelSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        drawCircle(g);
    }    
}
