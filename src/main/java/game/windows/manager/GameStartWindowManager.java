package game.windows.manager;

import game.windows.components.Button;
import game.windows.components.ButtonBuilder;
import game.windows.components.Window;

import java.awt.*;

class GameStartWindowManager {
    static Window buildGameStartWindow() {
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
}
