package Model.Bresenham;

import javax.swing.JFrame;

import Model.Bresenham.Console.BresenhamLineConsole;
import Model.Bresenham.Swing.BresenhamLineSwing;

public class ExecutorBresenham {
    public ExecutorBresenham()
    {}

    public void exemploBresenhamConsole(int x0, int y0, int x1, int y1){
        BresenhamLineConsole mec = new BresenhamLineConsole(x0, y0, x1, y1);
        mec.drawLineBresenham();
    }

    public void exemploBresenhamSwing(int x0, int y0, int x1, int y1){
        JFrame frame = new JFrame("Algoritmo de Bresenham - Computação Gráfica");

        BresenhamLineSwing panel = new BresenhamLineSwing(x0, y0, x1, y1);

        frame.add(panel);
        frame.setSize(600, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);        
    }
}
