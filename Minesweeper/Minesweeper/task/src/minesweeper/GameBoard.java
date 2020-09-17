package minesweeper;

public class GameBoard extends Board {

    public GameBoard(int rows, int columns) {
        super(rows, columns);
    }

    public GameBoard(int rows, int columns, char[][] array) {
        super(rows, columns, array);
    }

    private void setMine(int x, int y) {
        get()[x][y] = 'X';
    }

    public void addMines(int amount) {
        if (amount < empties()) {
            for (int i = 0; i < amount; i++) {
                int x = rand(0, rows() - 1);
                int y = rand(0, cols() - 1);
                if (!isMineOrExist(x, y))
                    setMine(x, y);
                else i--;
            }
        } else {
            for (int i = 0; i < rows(); i++)
                for (int j = 0; j < cols(); j++)
                    setMine(i, j);
        }
    }

    public void checkMines() {
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                if (!isMineOrExist(i, j)) {
                    int counter = 0;
                    for (int x = i - 1; x <= i + 1; x++)
                        for (int y = j - 1; y <= j + 1; y++)
                            if ((x != i || y != j) && isMineOrExist(x, y))
                                counter++;
                    if (counter > 0)
                        get()[i][j] = isEmpty(i, j) ? (char) (counter + '0') : (char) ((int) (get()[i][j]) + counter);
                }
            }
        }
    }

    private boolean isMineOrExist(int x, int y) {
        return x >= 0 && x < rows() && y >= 0 && y < cols() && get()[x][y] == 'X';
    }

    private boolean isEmpty(int x, int y) {
        return get()[x][y] == '.';
    }

    private int rand(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

}
