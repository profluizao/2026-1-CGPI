package ModeloSimples;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Scene {

    public static List<Map<String, Object>> createImage() {
        return new ArrayList<>();
    }

    public static void addDescriptor(
            List<Map<String, Object>> image,
            Map<String, Object> descriptor) {

        image.add(descriptor);
    }
}