package crawler.app;

import javax.swing.*;
import java.awt.*;

import static crawler.utils.Constants.*;

public class HtmlArea extends JPanel {

    private JTextArea textArea;

    public HtmlArea() {
        setLayout(new BorderLayout());
        setTextArea();
    }

    private void setTextArea() {
        textArea = new JTextArea(HTML_AREA_TXT);
        textArea.setName(TEXT_AREA);
        textArea.setBorder(SMALL_BORDER);
        textArea.setBackground(Color.WHITE);
        textArea.setFont(HTML_FONT);
        textArea.setDisabledTextColor(Color.GRAY);
        textArea.setEditable(false);
        textArea.setEnabled(false);
        add(textArea);
    }

    public void setText(String text) {
        textArea.setText(text);
    }
}
