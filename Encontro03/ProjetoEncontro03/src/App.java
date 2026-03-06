import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import ModeloOtimizado.Line;
import ModeloOtimizado.Renderer;
import ModeloOtimizado.IShape;

public class App {

    public static void main(String[] args) {

        // 1. Cria a cena (lista de formas)
        List<IShape> scene = new ArrayList<>();

        // 2. Adiciona objetos gráficos
        scene.add(new Line(50, 50, 200, 200, Color.BLACK));
        scene.add(new Line(200, 50, 50, 200, Color.RED));

        // 3. Cria o renderer
        Renderer renderer = new Renderer(scene);

        // 4. Cria a janela
        JFrame frame = new JFrame("Imagem Virtual - Java 2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.add(renderer);

        // 5. Exibe
        frame.setVisible(true);
    }
}