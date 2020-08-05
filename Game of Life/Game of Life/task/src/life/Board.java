package life;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Board extends JPanel {

    private final Universe universe;
    private int epoch;

    public Board(Universe universe) {
        super();
        this.universe = universe;
        epoch = 1;
    }

    public static Board create() {
        final Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        scanner.close();
        return new Board(new Universe(size));
    }

    public int getEpoch() {
        return epoch;
    }

    public void nextEpoch() {
        epoch++;
    }

    public int getAlive() {
        return universe.nextGen().getAliveAmount();
    }

    public void generate() {
        universe.generate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        int size = universe.nextGen().getSize();
        int cs = GameOfLife.CELL_SIZE;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (universe.nextGen().get()[i][j]) {
                    g2d.fillRect(j * cs, i * cs, cs, cs);
                } else {
                    g2d.drawRect(j * cs, i * cs, cs, cs);
                }
            }
        }
    }
}