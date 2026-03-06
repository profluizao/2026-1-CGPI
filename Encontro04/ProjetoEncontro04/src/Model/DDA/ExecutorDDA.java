package Model.DDA;

import javax.swing.JFrame;

import Model.DDA.Console.DDALineConsole;
import Model.DDA.Swing.DDALineSwing;

public class ExecutorDDA {
    public ExecutorDDA()
    {}

    public void exemploDDAConsole(int x0, int y0, int x1, int y1){
        DDALineConsole mec = new DDALineConsole(x0, y0, x1, y1);
        mec.drawLineDDA();
    }

    public void exemploDDASwing(int x0, int y0, int x1, int y1){
        JFrame frame = new JFrame("Algoritmo DDA - Computação Gráfica");
        DDALineSwing panel = new DDALineSwing(x0, y0, x1, y1);
        frame.add(panel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }    
}
