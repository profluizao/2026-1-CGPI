package ModeloSimples;
import java.util.HashMap;
import java.util.Map;

public class Descriptors {

    public static Map<String, Object> line(
            int x1, int y1, int x2, int y2, String color) {

        Map<String, Object> line = new HashMap<>();
        line.put("type", "line");
        line.put("startX", x1);
        line.put("startY", y1);
        line.put("endX", x2);
        line.put("endY", y2);
        line.put("color", color);

        return line;
    }
}