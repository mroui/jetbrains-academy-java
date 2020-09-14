package recognition;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public abstract class NeuralNetwork {

    private static double[][] weights = new double[][]{};

    private static final int[][] digits = new int[][]{
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
        int[] bias = new int[]{-1, 6, 1, 0, 2, 0, -1, 3, -2, -1};
        int[] results = new int[digits.length];

        for (int i = 0; i < digits.length; i++) {
            int x = 0;
            for (int j = 0; j < grid.get().length; j++) {
                x += grid.get()[j] ? digits[i][j] : 0;
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

    public static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    private static void randomGaussian(double[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = new Random().nextGaussian();
            }
        }
    }

    public static void learn(int neurons) {
        final double[][] stdOutput = {
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
        };
        weights = new double[neurons][digits[0].length];
        randomGaussian(weights);
        double epsilon = 50;
        double rate = 0.5;

        while (epsilon > 0.00001) {
            double[][] error = new double[neurons][digits[0].length];
            for (int i = 0; i < neurons; i++) {
                double sum = 0;
                for (int j = 0; j < digits[0].length; j++) {
                    sum += weights[i][j] * digits[i][j];
                }
                for (int j = 0; j < digits[0].length; j++) {
                    error[i][j] += rate * digits[i][j] * (stdOutput[i][i] - sigmoid(sum));
                }
            }
            epsilon = error[0][0];
            for (int i = 0; i < neurons; i++) {
                for (int j = 0; j < digits[0].length; j++) {
                    error[i][j] /= 10;
                    epsilon = Math.max(epsilon, Math.abs(error[i][j]));
                    weights[i][j] += error[i][j];
                }
            }
        }
    }

    public static void saveToFile() {
        try (PrintWriter writer = new PrintWriter("weights.txt", StandardCharsets.UTF_8)) {
            for (double[] weight : weights) {
                for (int j = 0; j < weights[0].length; j++) {
                    writer.print(weight[j] + " ");
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
