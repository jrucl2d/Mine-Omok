package game;


import game.windows.components.*;
import game.windows.components.Button;
import game.windows.components.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class Main {
    final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("오목과 지뢰찾기 게임을 시작합니다.");
        resolveBackgroundColorNotShowingProblem();

        FrameBuilder.builder()
                .title("오목/지뢰찾기 게임")
                .width(400)
                .height(400)
                .addWindow(WindowType.GAME_START, buildGameStartWindow())
                .build()
                .startWith(WindowType.GAME_START);
    }

    private static Window buildGameStartWindow() {
        final Button omokGameStartButton = ButtonBuilder.builder()
                .title("오목 게임")
                .x(10)
                .y(20)
                .width(140)
                .height(30)
                .background(Color.yellow)
                .build();

        final Button omokScoreButton = ButtonBuilder.builder()
                .title("오목 게임 점수")
                .x(10)
                .y(60)
                .width(140)
                .height(30)
                .background(Color.yellow)
                .build();

        final Button mineGameStartButton = ButtonBuilder.builder()
                .title("지뢰찾기 게임")
                .x(10)
                .y(100)
                .width(140)
                .height(30)
                .background(Color.green)
                .build();

        final Button mineScoreButton = ButtonBuilder.builder()
                .title("지뢰찾기 게임 점수")
                .x(10)
                .y(140)
                .width(140)
                .height(30)
                .background(Color.green)
                .build();

        final Button exitButton = ButtonBuilder.builder()
                .title("종료")
                .x(10)
                .y(300)
                .width(140)
                .height(30)
                .background(Color.white)
                .build();

        return new Window(Color.gray)
                .put(omokGameStartButton)
                .put(omokScoreButton)
                .put(mineGameStartButton)
                .put(mineScoreButton)
                .put(omokScoreButton)
                .put(exitButton);
    }

    // Mac OS 에서 background 설정한 색깔이 나타나지 않는 문제 해결
    private static void resolveBackgroundColorNotShowingProblem() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (final Exception ignore) {}
    }
}
