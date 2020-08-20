package crawler.app;

import crawler.app.center.HtmlArea;
import crawler.app.center.UrlsTableArea;
import crawler.models.Url;
import crawler.app.top.TopPanel;
import crawler.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static crawler.models.Url.*;
import static crawler.utils.Constants.*;

public class WebCrawler extends JFrame {

    private JPanel mainPanel;
    private HtmlArea htmlArea;
    private UrlsTableArea urlsTableArea;
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
        handleParsingUrlsToTable();
    }

    private void setAreas() {
        topPanel = new TopPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        urlsTableArea = new UrlsTableArea();
        mainPanel.add(urlsTableArea, BorderLayout.CENTER);
        //htmlArea = new HtmlArea();
        //mainPanel.add(htmlArea, BorderLayout.CENTER);
    }

    private void setPanel() {
        mainPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
        mainPanel.setBorder(NORMAL_BORDER);
        add(mainPanel);
    }

    /**
     * run button handler
     * extracting subUrls from <a> tag from mainUrl
     * setting them to urls table area & extract their titles to details area
     */

    public void handleParsingUrlsToTable() {
        topPanel.urlArea().getRunButton().addActionListener(e -> {
            final String mainUrl = topPanel.urlArea().getUrlTextField().getText().trim();
            try {
                new URL(mainUrl).openStream();
                urlsTableArea.updateTableData(getUrlsDataFromMain(mainUrl));
                topPanel.detailsArea().setTitle(extractWebTitle(extractHtmlFromUrl(mainUrl)));
            } catch (IOException exception) {
                topPanel.detailsArea().setTitle(Constants.ERROR + exception.getClass());
                urlsTableArea.updateTableData(new Url[]{});
            }
        });
    }

    /**
     * run button handler
     * setting url's html to html area & title to details area
     */

    private void handleParsingUrlToHtml() {
        topPanel.urlArea().getRunButton().addActionListener(e -> {
            final String url = topPanel.urlArea().getUrlTextField().getText();
            try (InputStream inputStream = new BufferedInputStream(new URL(url).openStream())) {
                htmlArea.setText(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));
                topPanel.detailsArea().setTitle(extractWebTitle(htmlArea.getTextArea().getText()));
            } catch (IOException exception) {
                htmlArea.setText(Constants.ERROR + exception.getLocalizedMessage());
            }
        });
    }
}