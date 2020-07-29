package life;

import java.util.Random;

public class Universe {

    private boolean[][] matrix;

    public Universe(int size, long seed) {
        matrix = new boolean[size][size];
        fill(seed);
    }

    private void fill(long seed) {
        Random random = new Random(seed);
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                matrix[i][j] = random.nextBoolean();
    }

    public void print() {
        for (boolean[] row : matrix) {
            for (int j = 0; j < matrix.length; j++)
                System.out.print(row[j] ? 'O' : ' ');
            System.out.println();
        }
    }
}
