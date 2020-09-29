package solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {

    private final Row[] rows;

    public Matrix(Row[] rows) {
        this.rows = rows.clone();
    }

    public Row[] get() {
        return rows;
    }

    public static Matrix read(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] dimens = reader.readLine().split("\\s+");
            int equations = Integer.parseInt(dimens[0]);
            List<Row> rows = new ArrayList<>(equations);
            while (equations > 0) {
                String line = reader.readLine();
                double[] coefficients = Arrays.stream(line.split("\\s+")).mapToDouble(Double::parseDouble).toArray();
                LinearEquation equation = new LinearEquation(Arrays.copyOf(coefficients, coefficients.length - 1));
                rows.add(new Row(equation, coefficients[coefficients.length - 1]));
                equations--;
            }
            return new Matrix(rows.toArray(new Row[0]));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public void swapRows(int first, int second) {
        Row temp = rows[first].copy();
        rows[first] = rows[second].copy();
        rows[second] = temp.copy();
    }
}
