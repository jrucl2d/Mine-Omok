package game.windows.manager;

import game.windows.components.Window;
import game.windows.components.WindowType;

public class WindowManager {
    public static Window buildWindow(final WindowType windowType) {
        return switch (windowType) {
            case GAME_START -> GameStartWindowManager.buildGameStartWindow();
        };
    }
}
