package solver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            double a = Double.parseDouble(scanner.next());
            double b = Double.parseDouble(scanner.next());
            System.out.println(linearEquation(a, b));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static double linearEquation(double a, double b) {
        return b / a;
    }
}
