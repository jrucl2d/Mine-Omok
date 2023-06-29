package game.windows.components;

import javax.swing.*;

public class Frame extends Component {
    private final JFrame frame;

    Frame(JFrame frame) {
        this.frame = frame;
    }

    @Override
    ComponentType getType() {
        return ComponentType.FRAME;
    }

    @Override
    Object getComponent() {
        return this.frame;
    }
}
