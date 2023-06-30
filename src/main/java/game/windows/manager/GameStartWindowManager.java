package game.windows.manager;

import game.common.Images;
import game.windows.components.Button;
import game.windows.components.Label;
import game.windows.components.Window;
import game.windows.components.*;
import game.windows.event.WindowEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class GameStartWindowManager implements WindowBuilder {
    private final static Logger logger = LoggerFactory.getLogger(GameStartWindowManager.class);

    @Override
    public Window build() {
        // 오목 게임
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

        final Label omokImageLabel = LabelBuilder.builder()
                .x(160)
                .y(50)
                .width(280)
                .height(280)
                .image(Images.OMOK_BG)
                .visible(false)
                .build();

        omokGameStartButton.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        logger.info("오목 게임 버튼에 마우스 in");
                        omokImageLabel.show();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        logger.info("오목 게임 버튼에 마우스 out");
                        omokImageLabel.hide();
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        logger.info("오목 게임 버튼 클릭");
                        logger.info("오목 게임을 시작합니다...");
                        WindowEventSource.windowChangedTo(WindowType.OMOK_INFO_INPUT);
                    }
                }
        );

        // 지뢰찾기 게임
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

        final Label mineImageLabel = LabelBuilder.builder()
                .x(160)
                .y(50)
                .width(280)
                .height(280)
                .image(Images.MINE_BG)
                .visible(false)
                .build();

        mineGameStartButton.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        logger.info("지뢰찾기 게임 버튼에 마우스 in");
                        mineImageLabel.show();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        logger.info("지뢰찾기 게임 버튼에 마우스 out");
                        mineImageLabel.hide();
                    }
                }
        );

        // 종료 버튼
        final Button exitButton = ButtonBuilder.builder()
                .title("종료")
                .x(10)
                .y(300)
                .width(140)
                .height(30)
                .background(Color.white)
                .build();

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logger.info("종료 버튼 클릭");
                logger.info("오목과 지뢰찾기 게임을 종료합니다...");
                System.exit(0);
            }
        });

        return new Window(Color.gray)
                .put(omokGameStartButton)
                .put(omokScoreButton)
                .put(mineGameStartButton)
                .put(mineScoreButton)
                .put(omokScoreButton)
                .put(exitButton)
                .put(omokImageLabel)
                .put(mineImageLabel)
                ;
    }
}
