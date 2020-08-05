package life;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameOfLife extends JFrame {

    public static final int CELL_SIZE = 5;

    private JLabel generationLabel;
    private JLabel aliveLabel;
    private Board board;

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
        game.play();
    }

    public GameOfLife() {
        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setComponents();
        setResizable(false);
        setVisible(true);
    }

    private void play() {
        while (board.getEpoch() <= 10) {
            repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
            generationLabel.setText("Generation: #" + board.getEpoch());
            aliveLabel.setText("Alive: " + board.getAlive());
            board.generate();
            board.nextEpoch();
        }
    }

    private void setComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
        add(mainPanel);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension((int) (getWidth() * 0.25), menuPanel.getHeight()));
        mainPanel.add(menuPanel);
        addMenu(menuPanel);

        board = new Board(new Universe((int) (getWidth() * 0.75 / CELL_SIZE - 12)));
        board.setName("Board");
        mainPanel.add(board);
    }

    private void addMenu(JPanel panel) {
        generationLabel = new JLabel("Generation #0");
        generationLabel.setName("GenerationLabel");
        panel.add(generationLabel);

        aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");
        panel.add(aliveLabel);
    }
}
