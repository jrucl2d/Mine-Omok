package game.windows.components;

import java.awt.event.MouseListener;

abstract class Component {
    abstract java.awt.Component getComponent();

    public void addMouseListener(final MouseListener mouseListener) {
        getComponent().addMouseListener(mouseListener);
    }
}
