package solver;

import java.util.Scanner;

public class Equation {

    private double a, b, c;

    public Equation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static Equation read() {
        Scanner scanner = new Scanner(System.in);
        try {
            double a = Double.parseDouble(scanner.next());
            double b = Double.parseDouble(scanner.next());
            double c = Double.parseDouble(scanner.next());
            return new Equation(a, b, c);
        } catch (Exception e) {
            out(e.toString());
        }
        return null;
    }

    public void calculateLinear() {
        out(b / a);
    }

    public void calculate(Equation e) {
        double y = (e.c - c * e.a / a) / (e.b - b * e.a / a);
        double x = (c - b * y) / a;
        out(x + " " + y);
    }

    public static void out(Object msg) {
        System.out.println(msg);
    }
}
