package crawler.app;

import crawler.app.center.HtmlArea;
import crawler.app.top.TopPanel;
import crawler.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static crawler.utils.Constants.*;

public class WebCrawler extends JFrame {

    private JPanel mainPanel;
    private HtmlArea htmlArea;
    private TopPanel topPanel;

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
        topPanel = new TopPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        htmlArea = new HtmlArea();
        mainPanel.add(htmlArea, BorderLayout.CENTER);
    }

    private void setPanel() {
        mainPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
        mainPanel.setBorder(NORMAL_BORDER);
        add(mainPanel);
    }

    private String extractWebTitle() {
        String html = htmlArea.getTextArea().getText();
        Pattern titlePattern = Pattern.compile("<title>(.*?)</title>");
        Matcher titleMatcher = titlePattern.matcher(html);
        return titleMatcher.find() ? titleMatcher.group(1) : TITLE_NOT_FOUND;
    }

    private void setAction() {
        topPanel.urlArea().getRunButton().addActionListener(e -> {
            final String url = topPanel.urlArea().getUrlTextField().getText();
            try (InputStream inputStream = new BufferedInputStream(new URL(url).openStream())) {
                htmlArea.setText(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));
                topPanel.detailsArea().setTitle(extractWebTitle());
            } catch (IOException exception) {
                htmlArea.setText(Constants.ERROR + exception.getLocalizedMessage());
            }
        });
    }
}