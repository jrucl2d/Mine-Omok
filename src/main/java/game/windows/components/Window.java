package game.windows.components;

import javax.swing.*;
import java.awt.*;

public class Window extends Component {
    private final JPanel content;

    public Window(final Color background) {
        final JPanel p = new JPanel();
        p.setLayout(null); // 절대 위치
        p.setBackground(background);
        this.content = p;
    }

    public Window put(final Component component) {
        this.content.add(component.getComponent());
        return this;
    }

    @Override
    java.awt.Component getComponent() {
        return this.content;
    }
}
