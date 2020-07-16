package processor;

public class Matrix {

    private final int rows;
    private final int columns;
    private final double[][] array;

    public Matrix(int rows, int columns, double[][] array) {
        this.rows = rows;
        this.columns = columns;
        this.array = array.clone();
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
            System.out.println("ERROR: Wrong dimensions!");
            return null;
        }
        double[][] newArray = new double[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                newArray[i][j] = array[i][j] + m.array[i][j];
        return new Matrix(rows, columns, newArray);
    }

    public Matrix multiplyConstant(double constant) {
        double[][] newArray = new double[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                newArray[i][j] = constant * array[i][j];
        return new Matrix(rows, columns, newArray);
    }

    public Matrix multiplyMatrix(Matrix m) {
        if (columns != m.rows) {
            System.out.println("ERROR: Wrong dimensions!");
            return null;
        }
        double[][] newArray = new double[rows][m.columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < m.columns; j++)
                for (int k = 0; k < columns; k++)
                    newArray[i][j] += array[i][k] * m.array[k][j];
        return new Matrix(rows, m.columns, newArray);
    }

    public Matrix transpose(String method) {
        if (method.equals("1") || method.equals("2")) {
            double[][] newArray = new double[columns][rows];
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < columns; j++) {
                    newArray[j][i] = method.equals("1") ? array[i][j] : array[rows - i - 1][columns - j - 1];
                }
            return new Matrix(columns, rows, newArray);
        } else {
            double[][] newArray = new double[rows][columns];
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < columns; j++) {
                    newArray[i][j] = method.equals("3") ? array[i][columns - j - 1] : array[rows - i - 1][j];
                }
            return new Matrix(rows, columns, newArray);
        }
    }

    public double determinant() {
        if (rows == columns) {
            return determinant(array, rows);
        } else {
            System.out.println("ERROR: Wrong dimensions!");
            return 0;
        }
    }

    private double determinant(double[][] array, int n) {
        double result = 0;
        if (n == 1)
            result = array[0][0];
        else if (n == 2)
            result = array[0][0] * array[1][1] - array[1][0] * array[0][1];
        else
            for (int i = 0; i < n; i++)
                result += Math.pow(-1, i) * array[i][0] * determinant(coFactor(array, n, i), n - 1);
        return result;
    }

    private double[][] coFactor(double[][] array, int n, int actualIndex) {
        double[][] coFactor = new double[n - 1][n - 1];
        int row = 0, col = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if (i != actualIndex && j != 0) { //j!=0 -> because row 0 is chosen to calculate determinant
                    coFactor[row][col] = array[i][j];
                    col++;
                }
            if (col > n - 2) {
                row++;
                col = 0;
            }
        }
        return coFactor;
    }
}
