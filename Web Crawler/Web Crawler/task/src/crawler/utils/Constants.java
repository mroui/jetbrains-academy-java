package crawler.utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public interface Constants {

    int WINDOW_H = 800;
    int WINDOW_W = 600;
    int GAP_SIZE = 10;

    String APP_TITLE = "Web Crawler";

    String HTML_AREA_TXT = "HTML code";
    String TEXT_AREA = "HtmlTextArea";

    String URL_TEXT_FIELD = "UrlTextField";
    String DEFAULT_URL = "http://example.com/";
    String URL_LABEL = "URL:";

    String RUN_BUTTON_TXT = "Run!";
    String RUN_BUTTON = "RunButton";

    String TITLE_LABEL = "TitleLabel";
    String TITLE_LABEL_TXT = "Title: ";
    String DEFAULT_TITLE = "None";
    String TITLE_NOT_FOUND = "NOT FOUND";

    Border SMALL_BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    Border NORMAL_BORDER = BorderFactory.createEmptyBorder(20, 20, 20, 20);

    Dimension VERTICAL_SPACE = new Dimension(10, 0);
    Dimension TRIPLED_VERTICAL_SPACE = new Dimension(30, 0);
    Dimension HORIZONTAL_SPACE = new Dimension(0, 10);

    Font TEXT_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    Font BUTTON_FONT = new Font(Font.MONOSPACED, Font.BOLD, 14);
    Font LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);
    Font HTML_FONT = new Font(Font.MONOSPACED, Font.ITALIC, 12);

    String URL = "URL";
    String TITLE = "Title";
    String TITLES_TABLE = "TitlesTable";
    String HTML_CONTENT_TYPE = "text/html";
    String USER_AGENT_KEY = "User-Agent";
    String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0";

    String EXPORT_LABEL = "Export:";
    String EXPORT_BUTTON = "ExportButton";
    String EXPORT_BUTTON_TXT = "Save";
    String EXPORT_PATH_TEXT_FIELD = "ExportUrlTextField";
    String DEFAULT_PATH = "sub-urls.txt";

    String ERROR = "ERROR: ";

}
