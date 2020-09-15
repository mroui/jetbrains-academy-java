package minesweeper;

public class Main {
    public static void main(String[] args) {
        GameBoard board = new GameBoard(3, 3, new boolean[][]{{true, false, false}, {false,false, true}, {false, false, true}});
        board.addMines(3);
        board.print();
    }
}
