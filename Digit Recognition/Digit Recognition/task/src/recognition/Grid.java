package recognition;

import java.util.Scanner;

public class Grid {

    private final int rows;
    private final int columns;
    private final boolean[] array;

    public Grid(int rows, int columns, boolean[] array) {
        this.rows = rows;
        this.columns = columns;
        this.array = array.clone();
    }

    public boolean[] get() {
        return array;
    }

    public static Grid read(int rows, int columns) {
        final Scanner scanner = new Scanner(System.in);
        boolean[] array = new boolean[rows * columns];
        for (int i = 0; i < rows; i++) {
            String row = scanner.next();
            for (int j = 0; j < columns; j++) {
                array[i * columns + j] = row.charAt(j) == 'X';
            }
        }
        return new Grid(rows, columns, array.clone());
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                System.out.print(array[i * columns + j] ? 'X' : '_');
            System.out.println();
        }
    }
}
