package life;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public interface Constants {

    int WINDOW_W = 800;
    int WINDOW_H = 600;
    int CELL_SIZE = 5;
    int MENU_GAP_H = 20;
    int MENU_GAP_W = 10;
    int BORDER = 20;
    int DEFAULT_TIMER_DELAY = 500;
    int MIN_TIMER_DELAY = 0;
    int MAX_TIMER_DELAY = 1000;
    int BOARD_SIZE = (int) (WINDOW_W * 0.75 / CELL_SIZE - 16);

    ImageIcon REPLAY_ICON = new ImageIcon("resources\\replay.PNG");
    ImageIcon RESUME_ICON = new ImageIcon("resources\\resume.PNG");
    ImageIcon PAUSE_ICON = new ImageIcon("resources\\pause.PNG");

    EmptyBorder EMPTY_BORDER = new EmptyBorder(new Insets(BORDER, BORDER, BORDER, BORDER));

    Dimension MENU_DIM = new Dimension((int) (WINDOW_W * 0.25), WINDOW_H);
    Dimension BUTTON_DIM = new Dimension(MENU_DIM.width / 3, 28);
    Dimension BUTTONS_PANEL_DIM = new Dimension(MENU_DIM.width + 40, 36);
    Dimension STATISTICS_PANEL_DIM = new Dimension(MENU_DIM.width + 40, 92);

    Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 18);
    Font SMALL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 14);

    String GEN = "Generation: #";
    String ALIVE = "Alive: ";
    String BOARD_NAME = "Board";
    String GEN_LABEL_NAME = "GenerationLabel";
    String ALIVE_LABEL_NAME = "AliveLabel";
    String PAUSE_RESUME_NAME = "PlayToggleButton";
    String REPLAY_NAME = "ResetButton";
    String SPEED_MODE_LABEL = "Speed mode";

}
