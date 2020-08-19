package crawler.app.center;

import crawler.app.models.Url;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static crawler.utils.Constants.*;
import static crawler.utils.Constants.HTML_FONT;

public class UrlsTableArea extends JPanel {

    private JTextArea textArea;

    private JTable table;

    public UrlsTableArea() {
        setLayout(new BorderLayout());
        setTableArea();
    }

    public void updateTableData(Url[] newData) {
        if (newData.length > 0) {
            DefaultTableModel tableModel = new DefaultTableModel(0, 2);
            for (Url url : newData) {
                tableModel.addRow(new Object[]{url.getLink(), url.getTitle()});
            }
            table.setModel(tableModel);
        }
    }

    private void setTableArea() {
        table = new JTable(new Object[][]{}, URL_TABLE_COLUMN_NAMES);
        table.setFillsViewportHeight(true);
        table.setName(TITLES_TABLE);
        table.setEnabled(false);
        add(new JScrollPane(table));
    }

    public JTextArea getTextArea() {
        return textArea;
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
