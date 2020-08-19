package crawler.app.top;

import javax.swing.*;

import static crawler.utils.Constants.*;

public class DetailsArea extends JPanel {

    private JLabel titleLabel;
    private JLabel title;

    public DetailsArea() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setComponents();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    private void setComponents() {
        setTitleLabel();
        add(Box.createRigidArea(VERTICAL_SPACE));
        setTitleTextLabel();
        add(Box.createHorizontalGlue());
    }

    private void setTitleTextLabel() {
        title = new JLabel(DEFAULT_TITLE);
        title.setName(TITLE_LABEL);
        title.setFont(TEXT_FONT);
        add(title);
    }

    private void setTitleLabel() {
        titleLabel = new JLabel(TITLE_LABEL_TXT);
        titleLabel.setFont(LABEL_FONT);
        add(titleLabel);
    }
}
