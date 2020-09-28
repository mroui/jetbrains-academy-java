package solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LinearEquation {

    private final String INSUFFICIENT_COE = "Insufficient number of coefficients";
    private final double[] coefficients;

    public LinearEquation(double... c) {
        coefficients = c.clone();
    }

    public double c(int i) {
        return coefficients[i];
    }

    public void setC(int i, double value) {
        coefficients[i] = value;
    }

    public int cSize() {
        return coefficients.length;
    }

    public static LinearEquation read(int coeAmount) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Double> coefficients = new ArrayList<>();
            while (coeAmount > 0) {
                coefficients.add(Double.parseDouble(scanner.next()));
                coeAmount--;
            }
            return new LinearEquation(coefficients.stream().mapToDouble(Double::doubleValue).toArray());
        } catch (Exception e) {
            out(e.toString());
        }
        return null;
    }

    public void calculate() {
        out(coefficients.length > 1 ? coefficients[1] / coefficients[0] : INSUFFICIENT_COE);
    }

    public void calculateSystem(LinearEquation e) {
        if (coefficients.length > 2 && e.coefficients.length > 2) {
            double y = (e.c(2) - c(2) * e.c(0) / c(0)) / (e.c(1) - c(1) * e.c(0) / c(0));
            double x = (c(2) - c(1) * y) / c(0);
            out(x + " " + y);
        } else out(INSUFFICIENT_COE);
    }

    private static void out(Object msg) {
        System.out.println(msg);
    }

    public LinearEquation copy() {
        return new LinearEquation(coefficients.clone());
    }
}
