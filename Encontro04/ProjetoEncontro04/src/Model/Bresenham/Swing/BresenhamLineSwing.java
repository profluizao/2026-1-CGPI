package Model.Bresenham.Swing;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class BresenhamLineSwing extends JPanel {

    private int coord_x0;
    private int coord_y0;
    private int coord_x1;
    private int coord_y1;

    public BresenhamLineSwing(int x0, int y0, int x1, int y1) {
        this.coord_x0 = x0;
        this.coord_y0 = y0;
        this.coord_x1 = x1;
        this.coord_y1 = y1;
    }

    public void drawLineBresenham(Graphics g, int x0, int y0, int x1, int y1) {

        int dx = x1 - x0;
        int dy = y1 - y0;

        int d = 2 * dy - dx;
        int incE = 2 * dy;
        int incNE = 2 * (dy - dx);

        int x = x0;
        int y = y0;

        for (int i = 0; i <= dx; i++) {

            // desenha o "pixel"
            g.fillRect(x, y, 2, 2);

            if (d <= 0) {
                d = d + incE;
            } else {
                d = d + incNE;
                y = y + 1;
            }

            x = x + 1;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        drawLineBresenham(g, this.coord_x0, this.coord_y0, this.coord_x1, this.coord_y1);
    }    
}
