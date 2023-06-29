package game.windows.components;

import javax.swing.*;
import java.util.Map;

public class Frame extends Component {
    private final JFrame content;

    private final Map<WindowType, Window> windows;
    private Window activeWindow;

    Frame(final JFrame frame, final Map<WindowType, Window> windows) {
        this.content = frame;
        this.windows = windows;
    }

    public void startWith(final WindowType windowType) {
        this.activeWindow = getWindow(windowType);
        updateWindow();
    }

    private Window getWindow(final WindowType windowType) {
        return windows.get(windowType);
    }

    private void updateWindow() {
        this.content.setContentPane((JPanel) this.activeWindow.getComponent());
        this.content.revalidate();
        this.content.repaint();
    }

    @Override
    java.awt.Component getComponent() {
        return this.content;
    }
}
