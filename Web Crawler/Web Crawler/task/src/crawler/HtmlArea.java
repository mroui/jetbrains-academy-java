package crawler;

import javax.swing.*;
import java.awt.*;

import static crawler.Constants.*;

public class HtmlArea extends JPanel {

    private JTextArea textArea;

    public HtmlArea() {
        setLayout(new BorderLayout());
        setBorder(NORMAL_BORDER);
        setTextArea();
        add(textArea);
    }

    private void setTextArea() {
        textArea = new JTextArea(HTML_AREA_TXT);
        textArea.setName(TEXT_AREA);
        textArea.setBorder(SMALL_BORDER);
        textArea.setBackground(Color.WHITE);
        textArea.setEditable(false);
        textArea.setEnabled(false);
    }
}
