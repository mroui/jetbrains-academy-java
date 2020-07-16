package processor;

import java.util.Scanner;

public class Matrix {

    private int rows;
    private int columns;
    private int[][] array;

    public Matrix() {
        load();
    }

    public Matrix(int rows, int columns, int[][] array) {
        this.rows = rows;
        this.columns = columns;
        this.array = array.clone();
    }

    private void load() {
        final Scanner scanner = new Scanner(System.in);
        try {
            rows = scanner.nextInt();
            columns = scanner.nextInt();
            array = new int[rows][columns];
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < columns; j++)
                    array[i][j] = scanner.nextInt();
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            load();
        }
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                System.out.print(array[i][j] + " ");
            System.out.println();
        }
    }

    public Matrix addMatrix(Matrix m) {
        if (this.rows != m.rows || this.columns != m.columns) {
            System.out.println("ERROR");
            return null;
        }
        int[][] newArray = new int[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                newArray[i][j] = array[i][j] + m.array[i][j];
        return new Matrix(rows, columns, newArray);
    }

    public Matrix multiplyConstant(int constant) {
        int[][] newArray = new int[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                newArray[i][j] = constant * array[i][j];
        return new Matrix(rows, columns, newArray);
    }
}
