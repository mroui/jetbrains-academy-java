package crawler.app.center;

import crawler.models.Url;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static crawler.utils.Constants.*;

public class UrlsTableArea extends JPanel {

    private JTextArea textArea;
    private JTable table;
    private Url[] subUrls;

    public UrlsTableArea() {
        setLayout(new BorderLayout());
        setTableArea();
        subUrls = new Url[] {};
    }

    public Url[] getSubUrls() {
        return subUrls;
    }

    /**
     * table area methods
     */

    public void updateTableData(Url[] newData) {
        subUrls = newData.clone();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn(URL);
        tableModel.addColumn(TITLE);
        if (subUrls.length > 0) {
            for (Url url : subUrls) {
                tableModel.addRow(new Object[]{url.getLink(), url.getTitle()});
            }
        } else {
            tableModel.setNumRows(0);
        }
        table.setModel(tableModel);
    }

    private void setTableArea() {
        table = new JTable(new Object[][]{}, new Object[]{URL, TITLE});
        table.setFillsViewportHeight(true);
        table.setName(TITLES_TABLE);
        table.setEnabled(false);
        add(new JScrollPane(table));
    }

    /**
     * text area methods
     */

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
