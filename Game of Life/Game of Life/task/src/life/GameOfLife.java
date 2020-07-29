package life;

import java.io.IOException;
import java.util.Scanner;

public class GameOfLife {

    private final Universe universe;
    private static int epoch;

    public GameOfLife(Universe universe) {
        this.universe = universe;
        epoch = 1;
    }

    public static GameOfLife create() {
        final Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        scanner.close();
        return new GameOfLife(new Universe(size));
    }

    public void play() {
        while(epoch <= 10) {
            clear();
            System.out.println("Generation: #" + epoch);
            System.out.println("Alive: " + universe.nextGen().getAliveAmount());
            universe.nextGen().print();
            universe.generate();
            epoch++;
        }
    }

    public void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (IOException | InterruptedException ignored) {}
    }
}
