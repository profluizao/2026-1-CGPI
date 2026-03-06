import Model.Bresenham.ExecutorBresenham;
import Model.DDA.ExecutorDDA;
import Model.MidPointCircle.ExecutorMidPointCircle;

public class App {

    private static int x0 = 2;
    private static int y0 = 3;
    private static int x1 = 10;
    private static int y1 = 7;

    public static void main(String[] args) {
        // exemploDDA();
        // exemploBresenham();
        // exemploMidPointCircle();
    }

    //#region DDA Line
    private static void exemploDDA(){
        ExecutorDDA exe = new ExecutorDDA();
        exe.exemploDDAConsole(x0, y0, x1, y1);

        x0 *= 25;
        y0 *= 25;
        x1 *= 25;
        y1 *= 25;
        exe.exemploDDASwing(x0, y0, x1, y1);
    }
    //#endregion

    //#region Bresenham Line
    private static void exemploBresenham(){
        ExecutorBresenham exe = new ExecutorBresenham();
        exe.exemploBresenhamConsole(x0, y0, x1, y1);

        x0 *= 25;
        y0 *= 25;
        x1 *= 25;
        y1 *= 25;
        exe.exemploBresenhamSwing(x0, y0, x1, y1);        
    }
    //#endregion

    //#region MidPointCircle

    private static int xc = 0;
    private static int yc = 0;
    private static int radius = 8;

    private static void exemploMidPointCircle(){
        ExecutorMidPointCircle exe = new ExecutorMidPointCircle();
        exe.exemploMidPointCircleConsole(xc, yc, radius);

        xc += 200;
        yc += 150;
        radius = 100;
        exe.exemploMidPointCircleSwing(xc, yc, radius);
    }

    //#endregion
}
