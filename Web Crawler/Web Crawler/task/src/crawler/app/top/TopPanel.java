package crawler.app.top;

import javax.swing.*;

import static crawler.utils.Constants.HORIZONTAL_SPACE;

public class TopPanel extends JPanel {

    private URLArea urlArea;
    private DetailsArea detailsArea;

    public TopPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setComponents();
    }

    public URLArea urlArea() {
        return urlArea;
    }

    public DetailsArea detailsArea() {
        return detailsArea;
    }

    private void setComponents() {
        urlArea = new URLArea();
        add(urlArea);
        add(Box.createRigidArea(HORIZONTAL_SPACE));
        detailsArea = new DetailsArea();
        add(detailsArea);
    }
}
