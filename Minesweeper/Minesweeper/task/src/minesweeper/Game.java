package minesweeper;

import java.util.Scanner;

public class Game {

    private final GameBoard playerBoard;
    private GameBoard board;
    private int mines;
    private boolean playing;
    private boolean firstFree;

    public Game(int x, int y) {
        board = new GameBoard(x, y);
        playerBoard = new GameBoard(x, y);
        playing = true;
        firstFree = true;
    }

    public void play() {
        readMines();
        initMines();
        while (playing) {
            playerBoard.print();
            interaction();
            if (isVictory()) {
                System.out.println("Congratulations! You found all mines!");
                playing = false;
            }
        }
    }

    private void readMines() {
        System.out.print("How many mines do you want on the field? ");
        mines = new Scanner(System.in).nextInt();
    }

    private void initMines() {
        board.addMines(mines);
        board.checkMines();
    }

    private void interaction() {
        boolean completed = false;
        while (!completed) {
            System.out.print("Set/delete mines marks (x and y coordinates): ");
            try {
                String[] answer = new Scanner(System.in).nextLine().trim().split("\\s+");
                int x = Integer.parseInt(answer[1]) - 1;
                int y = Integer.parseInt(answer[0]) - 1;
                if (playerBoard.isNumber(x, y)) {
                    System.out.println("There is a number here!");
                } else if (!playerBoard.exist(x, y)) {
                    System.out.println("Wrong coordinates!");
                } else {
                    playerBoard.toggleFlag(x, y);
                    completed = true;
                }
            } catch (Exception e) {
                System.out.println("Wrong coordinates!");
            }
        }
    }

    private boolean isVictory() {
        for (int i = 0; i < board.rows(); i++)
            for (int j = 0; j < board.cols(); j++)
                if (board.is(i, j, 'X') && !playerBoard.is(i, j, '*'))
                    return false;
        return true;
    }

}
