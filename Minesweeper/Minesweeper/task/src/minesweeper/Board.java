package minesweeper;

import java.util.Scanner;

public class Board {

    private final int rows;
    private final int columns;
    private final char[][] array;
    private int empties;

    public Board(int rows, int columns) {
        this(rows, columns, createEmptyBoard(rows, columns));
    }

    public Board(int rows, int columns, char[][] array) {
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

    protected char[][] get() {
        return array;
    }

    protected void set(int row, int col, char ch) {
        if (row >= 0 && row < rows && col >= 0 && col < columns)
            array[row][col] = ch;
    }

    protected int empties() {
        return empties;
    }

    public static Board read(int rows, int columns) {
        final Scanner scanner = new Scanner(System.in);
        char[][] array = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            String row = scanner.next();
            for (int j = 0; j < columns; j++)
                array[i][j] = row.charAt(j);
        }
        return new Board(rows, columns, array.clone());
    }

    private void countEmpties() {
        empties = rows * columns;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                if (array[i][j] == 'X')
                    empties--;
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                System.out.print(array[i][j]);
            System.out.println();
        }
    }

    private static char[][] createEmptyBoard(int rows, int columns) {
        char[][] array = new char[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                array[i][j] = '.';
        return array.clone();
    }
}
