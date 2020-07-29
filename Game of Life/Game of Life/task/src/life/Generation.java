package life;

import java.util.Arrays;
import java.util.Random;

public class Generation {

    private final int size;
    private final boolean[][] matrix;

    public Generation(boolean[][] matrix) {
        this.size = matrix.length;
        this.matrix = matrix;
    }

    public Generation(int size) {
        this.size = size;
        this.matrix = randomFill(size, -1);
    }

    public Generation(int size, long seed) {
        this.size = size;
        this.matrix = randomFill(size, seed);
    }

    public boolean[][] get() {
        return matrix;
    }

    public int getSize() {
        return size;
    }

    private boolean[][] randomFill(int size, long seed) {
        boolean[][] matrix = new boolean[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                matrix[i][j] = seed == -1 ? new Random().nextBoolean() : new Random(seed).nextBoolean();
        return matrix;
    }

    public boolean isCellAlive(int i, int j) {
        return matrix[i][j];
    }

    public int countNeighbours(int row, int col) {
        int amount = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i != row || j != col) {
                    int tempI = i < 0 ? size - 1 : i == size ? 0 : i;
                    int tempJ = j < 0 ? size - 1 : j == size ? 0 : j;
                    if (isCellAlive(tempI, tempJ))
                        amount++;
                }
            }
        }
        return amount;
    }

    public void print() {
        for (boolean[] row : matrix) {
            for (int j = 0; j < matrix.length; j++)
                System.out.print(row[j] ? 'O' : ' ');
            System.out.println();
        }
        System.out.println();
    }

    public Generation clone() {
        try {
            return (Generation) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Generation(Arrays.stream(matrix).map(boolean[]::clone).toArray(boolean[][]::new));
        }
    }

    public int getAliveAmount() {
        int amount = 0;
        for (boolean[] booleans : matrix)
            for (int j = 0; j < matrix.length; j++)
                if (booleans[j])
                    amount++;
        return amount;
    }
}
