package game.windows.manager;

import game.windows.components.Window;
import game.windows.components.WindowType;

public class WindowManager {
    private static final GameStartWindowManager gameStartWindowManager = new GameStartWindowManager();
    private static final OmokGameWindowManager omokGameWindowManager = new OmokGameWindowManager();

    public static Window buildWindow(final WindowType windowType) {
        final WindowBuilder windowBuilder = switch (windowType) {
            case GAME_START -> gameStartWindowManager;
            case OMOK_GAME -> omokGameWindowManager;
        };
        return windowBuilder.build();
    }
}
