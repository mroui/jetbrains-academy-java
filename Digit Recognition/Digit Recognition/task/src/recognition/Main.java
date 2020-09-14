package recognition;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isMenu = true;
        while (isMenu) {
            System.out.println("1. Learn the network\n2. Guess a number\nYour choice: ");
            int option = Integer.parseInt(new Scanner(System.in).nextLine());
            switch (option) {
                case 1:
                    System.out.println("Learning...");
                    NeuralNetwork.learn(10);
                    NeuralNetwork.saveToFile();
                    System.out.println("Done! Saved to the file.");
                    return;
                case 2:
                    NeuralNetwork.learn(10);
                    System.out.println("Input grid:");
                    Grid grid = Grid.read(5, 3);
                    System.out.println("This number is " + NeuralNetwork.recognise09(grid));
                    return;
                default:
                    isMenu = false;
            }
        }
    }
}
