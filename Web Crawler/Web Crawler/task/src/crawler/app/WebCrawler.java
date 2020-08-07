package crawler.app;

import crawler.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static crawler.utils.Constants.*;

public class WebCrawler extends JFrame {

    private JPanel mainPanel;
    private URLArea urlArea;
    private HtmlArea htmlArea;

    public WebCrawler() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_W, WINDOW_H);
        setBackground(Color.LIGHT_GRAY);
        setTitle(APP_TITLE);
        setLocationRelativeTo(null);
        setComponents();
        setVisible(true);
    }

    private void setComponents() {
        setPanel();
        setAreas();
        setAction();
    }

    private void setAreas() {
        urlArea = new URLArea();
        mainPanel.add(urlArea, BorderLayout.NORTH);
        htmlArea = new HtmlArea();
        mainPanel.add(htmlArea, BorderLayout.CENTER);
    }

    private void setPanel() {
        mainPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
        mainPanel.setBorder(NORMAL_BORDER);
        add(mainPanel);
    }

    private void setAction() {
        urlArea.getRunButton().addActionListener(e -> {
            final String url = urlArea.getUrlTextField().getText();
            try (InputStream inputStream = new BufferedInputStream(new URL(url).openStream())) {
                htmlArea.setText(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));
            } catch (IOException exception) {
                htmlArea.setText(Constants.ERROR + exception.getLocalizedMessage());
            }
        });
    }
}