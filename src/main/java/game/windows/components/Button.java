package game.windows.components;

import javax.swing.*;

public class Button extends Component {
    private final JButton button;

    Button(final JButton button) {
        this.button = button;
    }

    @Override
    ComponentType getType() {
        return ComponentType.BUTTON;
    }

    @Override
    Object getComponent() {
        return this.button;
    }
}
