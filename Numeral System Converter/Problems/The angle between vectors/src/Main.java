import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int ux = scanner.nextInt();
        int uy = scanner.nextInt();
        int vx = scanner.nextInt();
        int vy = scanner.nextInt();
        System.out.println(Math.toDegrees(vectorsAngle(ux, uy, vx, vy)));
    }

    public static int scalarProduct(int ux, int uy, int vx, int vy) {
        return ux * vx + uy * vy;
    }

    public static double length(int x, int y) {
        return Math.hypot(x, y);
    }

    public static double vectorsAngle(int ux, int uy, int vx, int vy) {
        return Math.acos(scalarProduct(ux, uy, vx, vy) / (length(ux, uy) * length(vx, vy)));
    }
}