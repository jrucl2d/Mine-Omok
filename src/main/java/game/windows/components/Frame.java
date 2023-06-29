package game.windows.components;

import javax.swing.*;
import java.util.Map;

public class Frame extends Component {
    private final JFrame frame;
    private final Map<WindowType, Window> windows;
    private Window activeWindow;

    Frame(final JFrame frame, final Map<WindowType, Window> windows) {
        this.frame = frame;
        this.windows = windows;
    }

    public void startWith(final WindowType windowType) {
        this.activeWindow = getWindow(windowType);
        updateWindow();
    }

    @Override
    ComponentType getType() {
        return ComponentType.FRAME;
    }

    @Override
    Object getComponent() {
        return this.frame;
    }

    private Window getWindow(final WindowType windowType) {
        return windows.get(windowType);
    }

    private void updateWindow() {
        this.frame.setContentPane((JPanel) this.activeWindow.getComponent());
        this.frame.revalidate();
        this.frame.repaint();
    }
}
