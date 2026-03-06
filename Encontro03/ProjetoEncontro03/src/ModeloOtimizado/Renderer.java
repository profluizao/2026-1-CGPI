package ModeloOtimizado;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

public class Renderer extends JPanel {

    private List<IShape> shapes;

    public Renderer(List<IShape> shapes) {
        this.shapes = shapes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (IShape s : shapes) {
            s.draw(g2);
        }
    }
}
