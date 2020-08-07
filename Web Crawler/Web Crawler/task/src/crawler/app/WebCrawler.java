package crawler.app;

import javax.swing.*;
import java.awt.*;

import static crawler.utils.Constants.*;

public class WebCrawler extends JFrame {

    private JPanel mainPanel;

    public WebCrawler() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setBackground(Color.LIGHT_GRAY);
        setTitle(APP_TITLE);
        setLocationRelativeTo(null);
        setComponents();
        setVisible(true);
    }

    private void setComponents() {
        setPanel();
        mainPanel.add(new URLArea(), BorderLayout.NORTH);
        mainPanel.add(new HtmlArea(), BorderLayout.CENTER);
    }

    private void setPanel() {
        mainPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
        mainPanel.setBorder(NORMAL_BORDER);
        add(mainPanel);
    }
}