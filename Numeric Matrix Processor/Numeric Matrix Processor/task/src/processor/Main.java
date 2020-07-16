package processor;

import java.util.Arrays;
import java.util.InputMismatchException;
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
                        "4. Transpose matrix\n" +
                        "5. Calculate a determinant\n" +
                        "6. Inverse matrix\n" +
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
            case "4":
                transposeMatrix();
                break;
            case "5":
                calculateDeterminant();
                break;
            case "6":
                inverseMatrix();
                break;
            case "0":
                return;
            default:
                System.out.println("ERROR: Bad option!");
        }
        start();
    }

    private static void inverseMatrix() {
        Matrix A = loadMatrix("");
        Matrix B = A.inverse();
        if (B != null) {
            System.out.println("The result is:");
            B.print();
        }
    }

    private static void calculateDeterminant() {
        Matrix A = loadMatrix("");
        double determinant = A.determinant();
        System.out.println("The result is:\n" + determinant);
    }

    private static void transposeMatrix() {
        System.out.print(
                "\n1. Main diagonal\n" +
                        "2. Side diagonal\n" +
                        "3. Vertical line\n" +
                        "4. Horizontal line\n" +
                        "Your choice: ");
        String choice = scanner.next();
        if (Arrays.asList("1", "2", "3", "4").contains(choice)) {
            Matrix A = loadMatrix("");
            System.out.println("The result is:");
            A.transpose(choice).print();
        } else {
            System.out.println("ERROR: Bad option!");
            scanner.next();
            transposeMatrix();
        }
    }

    private static void matricesOperation(String operation) {
        Matrix A = loadMatrix("first");
        Matrix B = loadMatrix("second");
        Matrix C = operation.equals("ADDITION") ? A.addMatrix(B) : A.multiplyMatrix(B);
        if (C != null) {
            System.out.println("The multiplication result is:");
            C.print();
        }
    }

    private static void multiplyMatrixToConstant() {
        Matrix A = loadMatrix("");
        System.out.print("Enter the constant: ");
        try {
            double constant = scanner.nextDouble();
            System.out.println("The multiplication result is:");
            A.multiplyConstant(constant).print();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Bad input format!");
            scanner.next();
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
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Bad input format!");
            scanner.next();
            return loadMatrix(which);
        }
    }
}
