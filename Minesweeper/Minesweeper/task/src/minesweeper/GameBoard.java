package minesweeper;

public class GameBoard extends Board {

    public GameBoard(int rows, int columns) {
        super(rows, columns);
    }

    public GameBoard(int rows, int columns, char[][] array) {
        super(rows, columns, array);
    }

    public void addMines(int amount) {
        if (amount < empties()) {
            for (int i = 0; i < amount; i++) {
                int x = rand(0, rows() - 1);
                int y = rand(0, cols() - 1);
                if (!get()[x][y])
                    get()[x][y] = true;
                else i--;
            }
        } else {
            for (int i = 0; i < rows(); i++)
                for (int j = 0; j < cols(); j++)
                    get()[i][j] = true;
        }
    }

    private int rand(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

}
