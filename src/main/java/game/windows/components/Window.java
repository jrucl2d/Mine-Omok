package game.windows.components;

import javax.swing.*;
import java.awt.*;

public class Window extends Component {
    private final JPanel p;

    public Window(final Color background) {
        this.p = new JPanel();
        this.p.setLayout(null); // 절대 위치
        this.p.setBackground(background);
    }

    public void put(final Component component) {
        switch (component.getType()) {
            case FRAME -> this.p.add((JFrame) component.getComponent());
            case WINDOW -> this.p.add((JPanel) component.getComponent());
            case LABEL -> this.p.add((JLabel) component.getComponent());
            case BUTTON -> this.p.add((JButton) component.getComponent());
        }
    }

    @Override
    ComponentType getType() {
        return ComponentType.WINDOW;
    }

    @Override
    Object getComponent() {
        return this.p;
    }
}
