package Engine.Tracing;

public class Vector3 {
        double x, y, z;
        Vector3(double x, double y, double z) { this.x = x; this.y = y; this.z = z; }
        Vector3 add(Vector3 v) { return new Vector3(x + v.x, y + v.y, z + v.z); }
        Vector3 subtract(Vector3 v) { return new Vector3(x - v.x, y - v.y, z - v.z); }
        Vector3 multiply(double s) { return new Vector3(x * s, y * s, z * s); }
        double dot(Vector3 v) { return x * v.x + y * v.y + z * v.z; }
        double length() { return Math.sqrt(x * x + y * y + z * z); }
        Vector3 normalize() { double len = length(); return new Vector3(x / len, y / len, z / len); }
}
