package game.windows.components;

import javax.swing.*;

public class Label extends Component {
    private final JLabel label;

    Label(JLabel label) {
        this.label = label;
    }

    @Override
    ComponentType getType() {
        return ComponentType.LABEL;
    }

    @Override
    Object getComponent() {
        return this.label;
    }
}
