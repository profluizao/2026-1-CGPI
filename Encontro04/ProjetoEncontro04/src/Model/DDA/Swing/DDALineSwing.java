package Model.DDA.Swing;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class DDALineSwing extends JPanel {

    private int coord_x0;
    private int coord_y0;
    private int coord_x1;
    private int coord_y1;

    public DDALineSwing(int x0, int y0, int x1, int y1) {
        this.coord_x0 = x0;
        this.coord_y0 = y0;
        this.coord_x1 = x1;
        this.coord_y1 = y1;
    }

    public void drawLineDDA(Graphics g, int x0, int y0, int x1, int y1) {

        int dx = x1 - x0;
        int dy = y1 - y0;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        double xInc = (double) dx / steps;
        double yInc = (double) dy / steps;

        double x = x0;
        double y = y0;

        for (int i = 0; i <= steps; i++) {

            int px = (int) Math.round(x);
            int py = (int) Math.round(y);

            // desenha um "pixel"
            g.fillRect(px, py, 2, 2);

            x += xInc;
            y += yInc;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        this.drawLineDDA(g, this.coord_x0, this.coord_y0, this.coord_x1, this.coord_y1);
    }
}
