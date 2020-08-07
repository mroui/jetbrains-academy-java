package crawler.app;

import javax.swing.*;
import java.awt.*;

import static crawler.utils.Constants.*;

public class URLArea extends JPanel {

    private JTextField urlTextField;
    private JButton runButton;

    public URLArea() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setComponents();
    }

    private void setComponents() {
        setUrlTextField();
        add(Box.createRigidArea(VERTICAL_SPACE));
        setRunButton();
    }

    private void setRunButton() {
        runButton = new JButton(RUN_BUTTON_TXT);
        runButton.setName(RUN_BUTTON);
        add(runButton);
    }

    private void setUrlTextField() {
        urlTextField = new JTextField();
        urlTextField.setName(URL_TEXT_FIELD);
        add(urlTextField);
    }
}
