import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();
        boolean xPositive = x > 0 && y <= 0 && z <= 0;
        boolean yPositive = x <= 0 && y > 0 && z <= 0;
        boolean zPositive = x <= 0 && y <= 0 && z > 0;
        System.out.println(xPositive || yPositive || zPositive);
    }
}