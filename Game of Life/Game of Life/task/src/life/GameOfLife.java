package life;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class GameOfLife extends JFrame implements Constants {

    private Timer timer;
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JLabel generationLabel;
    private JLabel aliveLabel;
    private Board board;
    private boolean paused;

    public static void main(String[] args) {
        new GameOfLife();
    }

    public GameOfLife() {
        paused = false;
        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_W, WINDOW_H);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setComponents();
        setResizable(false);
        setVisible(true);
        play();
    }

    private void play() {
        timer = new Timer(DEFAULT_TIMER_DELAY, evt -> {
            repaint();
            generationLabel.setText(GEN + board.getEpoch());
            aliveLabel.setText(ALIVE + board.getAlive());
            board.generate();
            board.nextEpoch();
        });
        timer.start();
    }

    private void setComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBorder(EMPTY_BORDER);
        add(mainPanel);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(MENU_DIM);
        menuPanel.setMaximumSize(MENU_DIM);
        mainPanel.add(menuPanel);
        addMenu();
        mainPanel.add(Box.createRigidArea(new Dimension(BORDER, 0)));
        createBoard();
    }

    private void createBoard() {
        Component existedBoard = getComponentByName(BOARD_NAME, mainPanel.getComponents());
        if (existedBoard != null)
            mainPanel.remove(existedBoard);

        board = new Board(new Universe(BOARD_SIZE));
        board.setName(BOARD_NAME);
        mainPanel.add(board);
    }

    private void addMenu() {
        addButtons();
        addStatistics();
        addSpeedPanel();
    }

    private void addSpeedPanel() {
        JPanel speedPanel = new JPanel();
        speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.Y_AXIS));

        JLabel speedLabel = new JLabel(SPEED_MODE_LABEL);
        speedLabel.setAlignmentX(CENTER_ALIGNMENT);
        speedLabel.setFont(SMALL_FONT);

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, MIN_TIMER_DELAY, MAX_TIMER_DELAY, DEFAULT_TIMER_DELAY);
        speedSlider.setLayout(new FlowLayout(FlowLayout.CENTER, MENU_GAP_H, MENU_GAP_W));
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel(String.valueOf(MIN_TIMER_DELAY)));
        labelTable.put(DEFAULT_TIMER_DELAY, new JLabel(String.valueOf(DEFAULT_TIMER_DELAY)));
        labelTable.put(MAX_TIMER_DELAY, new JLabel(String.valueOf(MAX_TIMER_DELAY)));
        speedSlider.setLabelTable(labelTable);

        speedSlider.addChangeListener((e) -> {
            speedSlider.setValue((int) Math.round(speedSlider.getValue() / 100.0) * 100);
            timer.setDelay(speedSlider.getValue());
        });

        speedPanel.add(speedLabel);
        speedPanel.add(speedSlider);
        menuPanel.add(speedPanel);
    }

    private void addButtons() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(BUTTONS_PANEL_DIM);
        buttonsPanel.setMaximumSize(BUTTONS_PANEL_DIM);

        JToggleButton pauseResumeButton = new JToggleButton(PAUSE_ICON);
        pauseResumeButton.setName(PAUSE_RESUME_NAME);
        pauseResumeButton.setPreferredSize(BUTTON_DIM);
        pauseResumeButton.addActionListener(e -> {
            pauseResumeButton.setIcon(!paused ? RESUME_ICON : PAUSE_ICON);
            paused = !paused;
            if (paused) timer.stop();
            else timer.restart();
        });
        buttonsPanel.add(pauseResumeButton);

        JButton replayButton = new JButton(REPLAY_ICON);
        replayButton.setName(REPLAY_NAME);
        replayButton.setPreferredSize(BUTTON_DIM);
        replayButton.addActionListener(e -> {
            createBoard();
            if (paused) pauseResumeButton.doClick();
            timer.start();
        });
        buttonsPanel.add(replayButton);

        menuPanel.add(buttonsPanel);
    }

    private void addStatistics() {
        JPanel statisticsPanel = new JPanel();

        statisticsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, MENU_GAP_H, MENU_GAP_W));
        statisticsPanel.setPreferredSize(STATISTICS_PANEL_DIM);
        statisticsPanel.setMaximumSize(STATISTICS_PANEL_DIM);
        generationLabel = new JLabel(GEN + '0');
        generationLabel.setName(GEN_LABEL_NAME);
        generationLabel.setFont(FONT);
        statisticsPanel.add(generationLabel);

        aliveLabel = new JLabel(ALIVE + '0');
        aliveLabel.setName(ALIVE_LABEL_NAME);
        aliveLabel.setFont(FONT);
        statisticsPanel.add(aliveLabel);

        menuPanel.add(statisticsPanel);
    }

    public Component getComponentByName(String name, Component[] list) {
        for (Component component : list) {
            if (component.getName() != null && component.getName().equals(name))
                return component;
        }
        return null;
    }
}
