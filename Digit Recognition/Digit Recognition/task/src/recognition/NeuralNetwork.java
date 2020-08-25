package recognition;

public abstract class NeuralNetwork {

    public static int recognise01(Grid grid) {
        int[] weights = new int[]{2, 1, 2, 4, -4, 4, 2, -1, 2};
        int bias = -5;
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += grid.get()[i] ? weights[i] : 0;
        }
        return sum + bias < 0 ? 1 : 0;
    }

    public static int recognise09(Grid grid) {
        int[][] weights = new int[][]{
                {+1, +1, +1, +1, -1, +1, +1, -1, +1, +1, -1, +1, +1, +1, +1},//0
                {-1, +1, -1, -1, +1, -1, -1, +1, -1, -1, +1, -1, -1, +1, -1},//1
                {+1, +1, +1, -1, -1, +1, +1, +1, +1, +1, -1, -1, +1, +1, +1},//2
                {+1, +1, +1, -1, -1, +1, +1, +1, +1, -1, -1, +1, +1, +1, +1},//3
                {+1, -1, +1, +1, -1, +1, +1, +1, +1, -1, -1, +1, -1, -1, +1},//4
                {+1, +1, +1, +1, -1, -1, +1, +1, +1, -1, -1, +1, +1, +1, +1},//5
                {+1, +1, +1, +1, -1, -1, +1, +1, +1, +1, -1, +1, +1, +1, +1},//6
                {+1, +1, +1, -1, -1, +1, -1, -1, +1, -1, -1, +1, -1, -1, +1},//7
                {+1, +1, +1, +1, -1, +1, +1, +1, +1, +1, -1, +1, +1, +1, +1},//8
                {+1, +1, +1, +1, -1, +1, +1, +1, +1, -1, -1, +1, +1, +1, +1},//9
        };
        int[] bias = new int[]{-1, 6, 1, 0, 2, 0, -1, 3, -2, -1};
        int[] results = new int[weights.length];

        for (int i = 0; i < weights.length; i++) {
            int x = 0;
            for (int j = 0; j < grid.get().length; j++) {
                x += grid.get()[j] ? weights[i][j] : 0;
            }
            x += bias[i];
            results[i] = x;
        }

        int maxValue = -1;
        int maxIndex = -1;
        for (int i = 0; i < results.length; i++) {
            if (maxValue < results[i]) {
                maxValue = results[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
