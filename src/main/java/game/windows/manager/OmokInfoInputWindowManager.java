package game.windows.manager;

import game.logic.domain.OmokGame;
import game.store.GameMemoryStore;
import game.windows.components.Button;
import game.windows.components.TextField;
import game.windows.components.Window;
import game.windows.components.*;
import game.windows.event.WindowEventSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OmokInfoInputWindowManager implements WindowBuilder {
    private static final Logger logger = LoggerFactory.getLogger(OmokInfoInputWindowManager.class);

    private static final int OMOK_MIN_SIZE = 20;
    private static final int OMOK_MAX_SIZE = 50;

    @Override
    public Window build() {
        final Button exitButton = ButtonBuilder.builder()
                .title("나가기")
                .x(130)
                .y(20)
                .width(140)
                .height(30)
                .build();

        exitButton.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        logger.info("오목 게임 나가기 버튼 클릭");
                        logger.info("시작 화면으로 돌아갑니다...");
                        WindowEventSource.windowChangedTo(WindowType.GAME_START);
                    }
                }
        );

        final TextField player1Desc = TextFieldBuilder.builder()
                .text("사용자 1 이름 입력")
                .x(20)
                .y(60)
                .width(150)
                .height(30)
                .editable(false)
                .build();

        final TextField player1NameInput = TextFieldBuilder.builder()
                .text("갑")
                .x(20)
                .y(100)
                .width(150)
                .height(30)
                .build();

        final TextField player2Desc = TextFieldBuilder.builder()
                .text("사용자 2 이름 입력")
                .x(20)
                .y(140)
                .width(150)
                .height(30)
                .editable(false)
                .build();

        final TextField player2NameInput = TextFieldBuilder.builder()
                .text("을")
                .x(20)
                .y(180)
                .width(150)
                .height(30)
                .build();

        final TextField omokSizeDesc = TextFieldBuilder.builder()
                .text("바둑판 크기 입력(" + OMOK_MIN_SIZE+ "~" + OMOK_MAX_SIZE + ")")
                .x(230)
                .y(60)
                .width(150)
                .height(30)
                .editable(false)
                .build();

        final TextField omokSizeInput = TextFieldBuilder.builder()
                .text("20")
                .x(230)
                .y(100)
                .width(150)
                .height(30)
                .build();

        final TextField omokOrderRuleDesc = TextFieldBuilder.builder()
                .text("백이 먼저 돌을 놓습니다")
                .x(100)
                .y(230)
                .width(180)
                .height(30)
                .editable(false)
                .background(Color.red)
                .foreground(Color.yellow)
                .build();

        final Button selectOrderButton = ButtonBuilder.builder()
                .title("순서 정하기")
                .x(120)
                .y(280)
                .width(140)
                .height(30)
                .build();

        selectOrderButton.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        logger.info("오목 게임 순서 정하기 버튼 클릭");
                        logger.info("오목 게임 정보를 저장합니다...");

                        final String player1Name = player1NameInput.getContent();
                        final String player2Name = player2NameInput.getContent();
                        final String omokSize = omokSizeInput.getContent();

                        if (StringUtils.isEmpty(player1Name)) {
                            throw new IllegalArgumentException("사용자 1 이름이 비었습니다.");
                        }
                        if (StringUtils.isEmpty(player2Name)) {
                            throw new IllegalArgumentException("사용자 2 이름이 비었습니다.");
                        }
                        if (StringUtils.isEmpty(omokSize)) {
                            throw new IllegalArgumentException("오목판 사이즈가 비었습니다.");
                        }
                        try {
                            final int size = Integer.parseInt(omokSize);
                            if (size < OMOK_MIN_SIZE || size > OMOK_MAX_SIZE) {
                                throw new IllegalArgumentException("오목판 사이즈에는 20~50만 입력 가능합니다.");
                            }
                        } catch (final NumberFormatException ex) {
                            throw new IllegalArgumentException("오목판 사이즈에는 숫자만 입력 가능합니다.");
                        }

                        logger.info("사용자 1 이름 : {}", player1Name);
                        logger.info("사용자 2 이름 : {}", player2Name);
                        logger.info("바둑판 사이즈 : {}", omokSize);
                        GameMemoryStore.store(
                                OmokGame.init(Integer.parseInt(omokSize), player1Name, player2Name)
                        );
                        WindowEventSource.windowChangedTo(WindowType.OMOK_ORDER);
                    }
                }
        );

        return new Window(Color.gray)
                .put(exitButton)
                .put(player1Desc)
                .put(player1NameInput)
                .put(player2Desc)
                .put(player2NameInput)
                .put(omokSizeDesc)
                .put(omokSizeInput)
                .put(omokOrderRuleDesc)
                .put(selectOrderButton)
                ;
    }
}
