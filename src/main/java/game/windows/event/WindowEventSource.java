package game.windows.event;

import game.windows.components.WindowType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class WindowEventSource {
    private final static Logger logger = LoggerFactory.getLogger(WindowEventSource.class);


    private static final List<WindowChangeEventListener> listeners = new ArrayList<>();

    public static void addEventListener(final WindowChangeEventListener listener) {
        listeners.add(listener);
    }

    public static void windowChangedTo(final WindowType windowType) {
        logger.info("window 변경 : {}", windowType);
        listeners.forEach(listener -> listener.listenWindowChangedTo(windowType));
    }
}
