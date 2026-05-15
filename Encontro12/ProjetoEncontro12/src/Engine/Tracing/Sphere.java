package Engine.Tracing;

public class Sphere {
    Vector3 center;
    double radius;
    Vector3 color;
    double reflectivity; // 0.0 (fosco) a 1.0 (espelho perfeito)

    public Sphere(Vector3 center, double radius, Vector3 color, double reflectivity) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.reflectivity = reflectivity;
    }

        // Retorna a distância 't' do raio até a interseção, ou -1 se não houver
    public double intersect(Vector3 rayOrig, Vector3 rayDir) {
        Vector3 oc = rayOrig.subtract(center);
        double a = rayDir.dot(rayDir);
        double b = 2.0 * oc.dot(rayDir);
        double c = oc.dot(oc) - radius * radius;
        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) return -1.0; // O raio não intersecta a esfera

        // Calcula a menor raiz positiva (ponto de impacto mais próximo)
        double t0 = (-b - Math.sqrt(discriminant)) / (2.0 * a);
        if (t0 > 0) return t0;
        
        double t1 = (-b + Math.sqrt(discriminant)) / (2.0 * a);
        if (t1 > 0) return t1;

        return -1.0;
    }
}
