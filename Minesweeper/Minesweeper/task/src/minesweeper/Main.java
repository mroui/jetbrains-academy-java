package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameBoard board = new GameBoard(9, 9);
        System.out.println("How many mines do you want on the field?");
        int mines = new Scanner(System.in).nextInt();
        board.addMines(mines);
        board.print();
    }
}
