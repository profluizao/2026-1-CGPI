package ModeloSimples;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class Renderer extends JPanel {

    private List<Map<String, Object>> image;

    public Renderer(List<Map<String, Object>> image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Map<String, Object> d : image) {
            if (d.get("type").equals("line")) {

                int x1 = (int) d.get("startX");
                int y1 = (int) d.get("startY");
                int x2 = (int) d.get("endX");
                int y2 = (int) d.get("endY");

                String color = (String) d.get("color");
                g2.setColor(color.equals("red") ? Color.RED : Color.BLACK);

                g2.drawLine(x1 * 50, y1 * 50, x2 * 50, y2 * 50);
            }
        }
    }
}