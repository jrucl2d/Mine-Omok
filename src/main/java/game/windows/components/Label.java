package game.windows.components;

import javax.swing.*;

public class Label extends Component {
    private final JLabel content;

    Label(final JLabel label) {
        this.content = label;
    }

    public void show() {
        this.content.setVisible(true);
    }

    public void hide() {
        this.content.setVisible(false);
    }

    @Override
    java.awt.Component getComponent() {
        return this.content;
    }
}
