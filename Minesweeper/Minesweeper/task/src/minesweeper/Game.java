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
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            try {
                String[] answer = new Scanner(System.in).nextLine().trim().split("\\s+");
                int x = Integer.parseInt(answer[1]) - 1;
                int y = Integer.parseInt(answer[0]) - 1;
                String command = answer[2];
                if (!command.equals("mine") && !command.equals("free") || !board.exist(x, y))
                    throw new Exception();
                else completed = makeMove(x, y, command);
            } catch (Exception e) {
                System.out.println("Wrong input! [cord.y] [cord.x] [free/mine]");
            }
        }
    }

    private boolean makeMove(int x, int y, String command) {
        if (command.equals("mine")) {
            playerBoard.toggleFlag(x, y);
        } else {
            if (board.is(x, y, 'X')) {
                if (!firstFree) {
                    System.out.println("You stepped on a mine and failed!");
                    showMines();
                    playing = false;
                    return true;
                } else {
                    while (board.is(x, y, 'X')) {
                        board = new GameBoard(board.rows(), board.cols());
                        initMines();
                    }
                    firstFree = false;
                }
            }
            if (board.isNumber(x, y))
                playerBoard.set(x, y, board.get()[x][y]);
            else
                playerBoard.checkMines(x, y, board);
        }
        firstFree = false;
        return true;
    }

    private void showMines() {
        for (int i = 0; i < board.rows(); i++)
            for (int j = 0; j < board.cols(); j++)
                if (board.is(i, j, 'X'))
                    playerBoard.get()[i][j] = 'x';
        playerBoard.print();
    }

    private boolean isVictory() {
        for (int i = 0; i < board.rows(); i++)
            for (int j = 0; j < board.cols(); j++)
                if (board.is(i, j, 'X') && !playerBoard.is(i, j, '*'))
                    return false;
                else if (board.is(i, j, '.') && !playerBoard.is(i, j, '/') ||
                        board.isNumber(i, j) && !playerBoard.isNumber(i, j))
                    return false;
        return true;
    }

}
