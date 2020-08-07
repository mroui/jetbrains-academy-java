package crawler.utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public interface Constants {

    int WINDOW_H = 800;
    int WINDOW_W = 600;
    int GAP_SIZE = 10;

    String APP_TITLE = "Simple Window";

    String HTML_AREA_TXT = "HTML code";
    String TEXT_AREA = "HtmlTextArea";

    String URL_TEXT_FIELD = "UrlTextField";

    String RUN_BUTTON_TXT = "Get text!";
    String RUN_BUTTON = "RunButton";

    Border SMALL_BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    Border NORMAL_BORDER = BorderFactory.createEmptyBorder(20, 20, 20, 20);

    Dimension VERTICAL_SPACE = new Dimension(10, 0);
    Dimension HORIZONTAL_SPACE = new Dimension(0, 10);

    Font TEXT_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    Font BUTTON_FONT = new Font(Font.MONOSPACED, Font.BOLD, 14);
    Font HTML_FONT = new Font(Font.MONOSPACED, Font.ITALIC, 12);

}
