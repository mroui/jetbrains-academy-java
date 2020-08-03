package life;

import javax.swing.*;

public class GameOfLife extends JFrame {

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
        setSize(300, 300);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        setComponents();
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
        generationLabel = new JLabel("Generation #0");
        generationLabel.setName("GenerationLabel");
        add(generationLabel);

        aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");
        add(aliveLabel);

        board = new Board(new Universe(10));
        board.setName("Board");
        add(board);
    }
}
