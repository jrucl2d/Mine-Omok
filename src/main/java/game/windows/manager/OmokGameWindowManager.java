package game.windows.manager;

import game.windows.components.Window;

import java.awt.*;

public class OmokGameWindowManager implements WindowBuilder {

    @Override
    public Window build() {
        return new Window(Color.gray)
                ;
    }
}
