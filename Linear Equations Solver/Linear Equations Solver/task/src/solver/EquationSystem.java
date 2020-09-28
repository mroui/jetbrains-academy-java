package solver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EquationSystem {

    private final Matrix matrix;
    private double[] results;

    public EquationSystem(Matrix matrix) {
        this.matrix = matrix;
        results = new double[matrix.get().length];
    }

    public void solve() {
        System.out.println("Start solving the equation.");
        solveToEchelonForm();
        if (results.length > 0) {
            System.out.println("The solution is: ");
            for (double solution : results)
                System.out.print(solution + " ");
            System.out.println();
        } else System.out.println("No solutions");
    }

    private void solveToEchelonForm() {
        for (int k = 0; k < matrix.get().length; k++) {
            //find pivot row
            int max = k;
            for (int i = k + 1; i < matrix.get().length; i++)
                if (Math.abs(matrix.get()[i].get(k)) > Math.abs(matrix.get()[max].get(k)))
                    max = i;

            matrix.swapRows(k, max);

            //pivot within matrix & results
            for (int i = k + 1; i < matrix.get().length; i++) {
                double factor = matrix.get()[i].get(k) / matrix.get()[k].get(k);
                matrix.get()[i].result(matrix.get()[i].result() - (factor * matrix.get()[k].result()));
                for (int j = k; j < matrix.get()[i].size(); j++)
                    matrix.get()[i].set(j, matrix.get()[i].get(j) - (factor * matrix.get()[k].get(j)));
            }
        }
        //back substitution
        try {
            for (int i = matrix.get().length - 1; i >= 0; i--) {
                double sum = 0.0;
                for (int j = i + 1; j < matrix.get()[i].size(); j++)
                    sum += matrix.get()[i].get(j) * results[j];
                results[i] = (matrix.get()[i].result() - sum) / matrix.get()[i].get(i);
            }
        } catch (Exception e) {
            results = new double[] {};
        }
    }

    public void saveResultsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            if (results.length > 0) {
                for (double result : results)
                    writer.write(result + "\n");
            } else writer.write("No solutions");
            System.out.println("Saved to file " + filename);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
