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
                if (!is(x, y, 'X'))
                    set(x, y, 'X');
                else i--;
            }
        } else {
            for (int i = 0; i < rows(); i++)
                for (int j = 0; j < cols(); j++)
                    set(i, j, 'X');
        }
    }

    public void checkMines() {
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                if (!is(i, j, 'X')) {
                    int counter = 0;
                    for (int x = i - 1; x <= i + 1; x++)
                        for (int y = j - 1; y <= j + 1; y++)
                            if ((x != i || y != j) && exist(x, y) && is(x, y, 'X'))
                                counter++;
                    if (counter > 0)
                        get()[i][j] = is(i, j, '.') ? (char) (counter + '0') : (char) ((int) (get()[i][j]) + counter);
                }
            }
        }
    }

    public void checkMines(int i, int j, GameBoard board) {
        if (is(i, j, '.') || is(i, j, '/')) {
            for (int x = i - 1; x <= i + 1; x++)
                for (int y = j - 1; y <= j + 1; y++)
                    if (exist(x, y) && !is(x, y, '/')) {
                        get()[x][y] = board.get()[x][y] == '.' ? '/' : board.get()[x][y];
                        if (!board.isNumber(x, y))
                            checkMines(x, y, board);
                    }
        }
    }

    public boolean exist(int x, int y) {
        return x >= 0 && x < rows() && y >= 0 && y < cols();
    }

    public boolean is(int i, int j, char x) {
        return exist(i, j) && get()[i][j] == x;
    }

    public boolean isNumber(int x, int y) {
        return exist(x, y) && get()[x][y] >= '0' && get()[x][y] <= '9';
    }

    public void toggleFlag(int x, int y) {
        get()[x][y] = get()[x][y] == '*' ? '.' : '*';
    }

    private int rand(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    @Override
    public void print() {
        System.out.println("\n │123456789│\n—│—————————│");
        for (int i = 0; i < rows(); i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < cols(); j++)
                System.out.print(exist(i, j) && is(i, j, 'X') ? "." : get()[i][j]);
            System.out.println("|");
        }
        System.out.println("—│—————————│");
    }

    public GameBoard copy() {
        return new GameBoard(rows(), cols(), get());
    }
}
