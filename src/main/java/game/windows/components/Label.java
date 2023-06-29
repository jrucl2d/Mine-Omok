package game.windows.components;

import javax.swing.*;

public class Label extends Component {
    private final JLabel content;

    Label(final JLabel label) {
        this.content = label;
    }

    @Override
    java.awt.Component getComponent() {
        return this.content;
    }
}
