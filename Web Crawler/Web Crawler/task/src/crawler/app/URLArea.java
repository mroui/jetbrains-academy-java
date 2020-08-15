package crawler.app;

import javax.swing.*;

import static crawler.utils.Constants.*;

public class URLArea extends JPanel {

    private JTextField urlTextField;
    private JButton runButton;
    private JLabel urlLabel;

    public URLArea() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setComponents();
    }

    public JButton getRunButton() {
        return runButton;
    }

    public JTextField getUrlTextField() {
        return urlTextField;
    }

    private void setComponents() {
        setUrlLabel();
        add(Box.createRigidArea(TRIPLED_VERTICAL_SPACE));
        setUrlTextField();
        add(Box.createRigidArea(VERTICAL_SPACE));
        setRunButton();
    }

    private void setUrlLabel() {
        urlLabel = new JLabel(URL_LABEL);
        urlLabel.setFont(LABEL_FONT);
        add(urlLabel);
    }

    private void setRunButton() {
        runButton = new JButton(RUN_BUTTON_TXT);
        runButton.setName(RUN_BUTTON);
        runButton.setFont(BUTTON_FONT);
        add(runButton);
    }

    private void setUrlTextField() {
        urlTextField = new JTextField(DEFAULT_URL);
        urlTextField.setName(URL_TEXT_FIELD);
        urlTextField.setFont(TEXT_FONT);
        add(urlTextField);
    }
}
