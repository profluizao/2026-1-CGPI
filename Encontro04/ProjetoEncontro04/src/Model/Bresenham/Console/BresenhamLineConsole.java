package Model.Bresenham.Console;

public class BresenhamLineConsole {
    private int coord_x0;
    private int coord_y0;
    private int coord_x1;
    private int coord_y1;

    public BresenhamLineConsole(int x0, int y0, int x1, int y1) {
        this.coord_x0 = x0;
        this.coord_y0 = y0;
        this.coord_x1 = x1;
        this.coord_y1 = y1;
    }

    public void drawLineBresenham() {
        int x0, y0, x1, y1;
        x0 = this.coord_x0;
        y0 = this.coord_y0;
        x1 = this.coord_x1;
        y1 = this.coord_y1;

        int dx = x1 - x0;
        int dy = y1 - y0;

        int d = 2 * dy - dx;      // parâmetro de decisão inicial
        int incE = 2 * dy;        // incremento para movimento horizontal
        int incNE = 2 * (dy - dx);// incremento para movimento diagonal

        int x = x0;
        int y = y0;

        System.out.println("Passo | x | y | d (decisão)");
        System.out.println("--------------------------------");

        for (int i = 0; i <= dx; i++) {

            System.out.printf("%5d | %d | %d | %d\n", i, x, y, d);

            if (d <= 0) {
                // movimento horizontal (E)
                d = d + incE;
            } else {
                // movimento diagonal (NE)
                d = d + incNE;
                y = y + 1;
            }

            x = x + 1;
        }
    }    
}
