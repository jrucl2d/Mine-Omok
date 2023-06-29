package game;


import game.windows.components.FrameBuilder;
import game.windows.components.WindowType;
import game.windows.manager.WindowManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("오목과 지뢰찾기 게임을 시작합니다.");
        resolveBackgroundColorNotShowingProblem();

        FrameBuilder.builder()
                .title("오목/지뢰찾기 게임")
                .width(400)
                .height(400)
                .addWindow(WindowType.GAME_START, WindowManager.buildWindow(WindowType.GAME_START))
                .build()
                .startWith(WindowType.GAME_START);
    }

    // Mac OS 에서 background 설정한 색깔이 나타나지 않는 문제 해결
    private static void resolveBackgroundColorNotShowingProblem() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (final Exception ignore) {}
    }
}
