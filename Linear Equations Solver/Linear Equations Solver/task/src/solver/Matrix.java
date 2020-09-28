package solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {

    private Row[] rows;

    public Matrix(Row[] rows) {
        this.rows = rows.clone();
    }

    public static Matrix read(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int equations = Integer.parseInt(reader.readLine());
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

}
