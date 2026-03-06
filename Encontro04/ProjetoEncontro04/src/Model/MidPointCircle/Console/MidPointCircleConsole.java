package Model.MidPointCircle.Console;

public class MidPointCircleConsole {

    private int coord_xc;
    private int coord_yc;
    private int coord_r;

    public MidPointCircleConsole(int xc, int yc, int r){
        this.coord_xc = xc;
        this.coord_yc = yc;
        this.coord_r = r;
    }

    public void drawCircle() {

        int x = 0;
        int y = this.coord_r;

        int p = 1 - this.coord_r; // parâmetro inicial de decisão

        System.out.println("Passo | x | y | p (decisão)");
        System.out.println("--------------------------------");

        int step = 0;

        while (x <= y) {

            System.out.printf("%5d | %d | %d | %d\n", step, x, y, p);

            printSymmetricPoints(this.coord_xc, this.coord_yc, x, y);

            x++;

            if (p < 0) {
                p = p + 2 * x + 1;
            } else {
                y--;
                p = p + 2 * (x - y) + 1;
            }

            step++;
        }
    }

    public void printSymmetricPoints(int xc, int yc, int x, int y) {

        System.out.println("Pontos gerados:");

        System.out.println("(" + (xc + x) + ", " + (yc + y) + ")");
        System.out.println("(" + (xc - x) + ", " + (yc + y) + ")");
        System.out.println("(" + (xc + x) + ", " + (yc - y) + ")");
        System.out.println("(" + (xc - x) + ", " + (yc - y) + ")");
        System.out.println("(" + (xc + y) + ", " + (yc + x) + ")");
        System.out.println("(" + (xc - y) + ", " + (yc + x) + ")");
        System.out.println("(" + (xc + y) + ", " + (yc - x) + ")");
        System.out.println("(" + (xc - y) + ", " + (yc - x) + ")");

        System.out.println();
    }    
}
