import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final double PI = 3.14;
        Scanner scanner = new Scanner(System.in);
        switch (scanner.next()) {
            case "triangle":
                double a = scanner.nextDouble();
                double b = scanner.nextDouble();
                double c = scanner.nextDouble();
                double s = (a + b + c) / 2;
                System.out.println(Math.sqrt(s * (s - a) * (s - b) * (s - c)));
                break;
            case "rectangle":
                System.out.println(scanner.nextDouble() * scanner.nextDouble());
                break;
            case "circle":
                System.out.println(PI * Math.pow(scanner.nextDouble(), 2));
                break;
        }
    }
}