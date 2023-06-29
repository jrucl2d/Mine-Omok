package game.windows.event;

import game.windows.components.WindowType;

public interface WindowChangeEventListener {
    void listenWindowChangedTo(final WindowType windowType);
}
