package crawler.app;

import javax.swing.*;
import java.awt.*;

import static crawler.utils.Constants.*;

public class WebCrawler extends JFrame {

    private JPanel mainPanel;

    public WebCrawler() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setTitle(APP_TITLE);
        setLocationRelativeTo(null);
        setComponents();
        setVisible(true);
    }

    private void setComponents() {
        setPanel();
        mainPanel.add(new HtmlArea(), BorderLayout.CENTER);
    }

    private void setPanel() {
        mainPanel = new JPanel(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        add(mainPanel);
    }
}