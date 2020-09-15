package minesweeper;

public class GameBoard extends Board {

    public GameBoard(int rows, int columns, boolean[][] array) {
        super(rows, columns, array);
    }

    public void addMines(int amount) {
        for (int i = 0; i < amount; i++) {
            int x = rand(0, rows() - 1);
            int y = rand(0, cols() - 1);
            if (!get()[x][y])
                get()[x][y] = true;
            else i--;
        }
    }

    private int rand(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

}
