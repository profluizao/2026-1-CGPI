package Model.MidPointCircle;

import javax.swing.JFrame;

import Model.MidPointCircle.Console.MidPointCircleConsole;
import Model.MidPointCircle.Swing.MidpointCircleSwing;

public class ExecutorMidPointCircle {
    public ExecutorMidPointCircle()
    {}

    public void exemploMidPointCircleConsole(int xc, int yc, int radius){
        MidPointCircleConsole mec = new MidPointCircleConsole(xc, yc, radius);
        mec.drawCircle();
    }

    public void exemploMidPointCircleSwing(int xc, int yc, int radius){
        JFrame frame = new JFrame("Algoritmo do Ponto Médio - Círculo");
        MidpointCircleSwing panel = new MidpointCircleSwing(xc, yc, radius);
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);        
    }
}
