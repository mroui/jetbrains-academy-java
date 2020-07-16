package processor;

import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        start();
        scanner.close();
    }

    private static void start() {
        System.out.print(
                "\n1. Add matrices\n" +
                        "2. Multiply matrix to a constant\n" +
                        "3. Multiply matrices\n" +
                        "0. Exit\n" +
                        "Your choice: ");
        switch (scanner.next()) {
            case "1":
                matricesOperation("ADDITION");
                break;
            case "2":
                multiplyMatrixToConstant();
                break;
            case "3":
                matricesOperation("MULTIPLICATION");
                break;
            case "0":
                return;
            default:
                System.out.println("Bad option!");
        }
        start();
    }

    private static void matricesOperation(String operation) {
        Matrix A = loadMatrix("first");
        Matrix B = loadMatrix("second");
        Matrix C = operation.equals("ADDITION") ? A.addMatrix(B) : A.multiplyMatrix(B);
        if (C != null) {
            System.out.println("The multiplication result is: ");
            C.print();
        }
    }

    private static void multiplyMatrixToConstant() {
        Matrix A = loadMatrix("");
        System.out.print("Enter the constant: ");
        try {
            double constant = scanner.nextDouble();
            System.out.println("The multiplication result is: ");
            A.multiplyConstant(constant).print();
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            multiplyMatrixToConstant();
        }
    }

    private static Matrix loadMatrix(String which) {
        try {
            System.out.printf("Enter size of %s matrix: ", which);
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            System.out.printf("Enter %s matrix:\n", which);
            double[][] array = new double[rows][columns];
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < columns; j++)
                    array[i][j] = scanner.nextDouble();
            return new Matrix(rows, columns, array);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return loadMatrix(which);
        }
    }
}
