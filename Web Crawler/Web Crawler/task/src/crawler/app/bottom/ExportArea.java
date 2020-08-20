package crawler.app.bottom;

import javax.swing.*;

import static crawler.utils.Constants.*;

public class ExportArea extends JPanel {

    private JTextField pathTextField;
    private JButton saveButton;
    private JLabel exportLabel;

    public ExportArea() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setComponents();
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextField getPathTextField() {
        return pathTextField;
    }

    private void setComponents() {
        setExportLabel();
        add(Box.createRigidArea(VERTICAL_SPACE));
        setPathTextField();
        add(Box.createRigidArea(VERTICAL_SPACE));
        setSaveButton();
    }

    private void setExportLabel() {
        exportLabel = new JLabel(EXPORT_LABEL);
        exportLabel.setFont(LABEL_FONT);
        add(exportLabel);
    }

    private void setSaveButton() {
        saveButton = new JButton(EXPORT_BUTTON_TXT);
        saveButton.setName(EXPORT_BUTTON);
        saveButton.setFont(BUTTON_FONT);
        add(saveButton);
    }

    private void setPathTextField() {
        pathTextField = new JTextField(DEFAULT_PATH);
        pathTextField.setName(EXPORT_PATH_TEXT_FIELD);
        pathTextField.setFont(TEXT_FONT);
        add(pathTextField);
    }
}
