package minesweeper;

import java.util.Scanner;

public class Board {

    private final int rows;
    private final int columns;
    private final boolean[][] array;
    private int empties;

    public Board(int rows, int columns) {
        this(rows, columns, new boolean[rows][columns]);
    }

    public Board(int rows, int columns, boolean[][] array) {
        this.rows = rows;
        this.columns = columns;
        this.array = array.clone();
        countEmpties();
    }

    protected int rows() {
        return rows;
    }

    protected int cols() {
        return columns;
    }

    protected boolean[][] get() {
        return array;
    }

    protected int empties() {
        return empties;
    }

    public static Board read(int rows, int columns) {
        final Scanner scanner = new Scanner(System.in);
        boolean[][] array = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            String row = scanner.next();
            for (int j = 0; j < columns; j++)
                array[i][j] = row.charAt(j) == 'X';
        }
        return new Board(rows, columns, array.clone());
    }

    private void countEmpties() {
        empties = rows * columns;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                if (array[i][j])
                    empties--;
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                System.out.print(array[i][j] ? 'X' : '.');
            System.out.println();
        }
    }
}
