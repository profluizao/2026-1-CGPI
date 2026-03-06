package Model.DDA.Console;

public class DDALineConsole {
    private int coord_x0;
    private int coord_y0;
    private int coord_x1;
    private int coord_y1;

    public DDALineConsole(int x0, int y0, int x1, int y1) {
        this.coord_x0 = x0;
        this.coord_y0 = y0;
        this.coord_x1 = x1;
        this.coord_y1 = y1;
    }

    public void drawLineDDA() {
        int x0, y0, x1, y1;
        x0 = this.coord_x0;
        y0 = this.coord_y0;
        x1 = this.coord_x1;
        y1 = this.coord_y1;

        int dx = x1 - x0;
        int dy = y1 - y0;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        double xInc = (double) dx / steps;
        double yInc = (double) dy / steps;

        double x = x0;
        double y = y0;

        System.out.println("Pixels gerados pelo algoritmo DDA:\n");

        for (int i = 0; i <= steps; i++) {

            System.out.println("Passo " + i + " -> Pixel: (" 
                               + Math.round(x) + ", " 
                               + Math.round(y) + ")");

            x += xInc;
            y += yInc;
        }
    }    
}
