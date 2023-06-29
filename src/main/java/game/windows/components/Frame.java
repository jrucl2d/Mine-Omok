package game.windows.components;

import game.windows.event.WindowChangeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Map;

public class Frame extends Component implements WindowChangeEventListener {
    private static final Logger logger = LoggerFactory.getLogger(Frame.class);

    private final JFrame content;

    private final Map<WindowType, Window> windows;

    Frame(final JFrame frame, final Map<WindowType, Window> windows) {
        this.content = frame;
        this.windows = windows;
    }

    public Frame startWith(final WindowType windowType) {
        updateWindow(windowType);
        return this;
    }

    private void updateWindow(final WindowType windowType) {
        final Window activeWindow = windows.get(windowType);
        this.content.setContentPane((JPanel) activeWindow.getComponent());
        this.content.revalidate();
        this.content.repaint();
        logger.info("window 변경 완료 : {}", windowType);
    }

    @Override
    java.awt.Component getComponent() {
        return this.content;
    }

    @Override
    public void listenWindowChangedTo(final WindowType windowType) {
        updateWindow(windowType);
    }
}
