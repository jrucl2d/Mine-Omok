package game.windows.manager;

import game.windows.components.Window;
import game.windows.components.WindowType;

public class WindowManager {
    private static final GameStartWindowManager gameStartWindowManager = new GameStartWindowManager();
    private static final OmokGameWindowManager omokGameWindowManager = new OmokGameWindowManager();
    private static final OmokOrderWindowManager omokOrderWindowManager = new OmokOrderWindowManager();
    private static final OmokInfoInputWindowManager omokInfoInputWindowManager = new OmokInfoInputWindowManager();

    public static Window buildWindow(final WindowType windowType) {
        final WindowBuilder windowBuilder = switch (windowType) {
            case GAME_START -> gameStartWindowManager;
            case OMOK_INFO_INPUT -> omokInfoInputWindowManager;
            case OMOK_ORDER -> omokOrderWindowManager;
            case OMOK_GAME -> omokGameWindowManager;
        };
        return windowBuilder.build();
    }
}
