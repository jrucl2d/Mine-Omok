package game.windows.components;

import javax.swing.*;

public class Button extends Component {
    private final JButton content;

    Button(final JButton button) {
        this.content = button;
    }

    public void show() {
        this.content.setVisible(true);
    }

    @Override
    java.awt.Component getComponent() {
        return this.content;
    }
}
