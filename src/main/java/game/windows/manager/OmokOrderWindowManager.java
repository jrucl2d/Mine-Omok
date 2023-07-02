package game.windows.manager;

import game.common.Images;
import game.store.GameMemoryStore;
import game.windows.components.Button;
import game.windows.components.Label;
import game.windows.components.TextField;
import game.windows.components.Window;
import game.windows.components.*;
import game.windows.event.WindowEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OmokOrderWindowManager implements WindowBuilder {
    private static final Logger logger = LoggerFactory.getLogger(OmokOrderWindowManager.class);

    @Override
    public Window build() {
        final OmokOrderWindowStatus status = new OmokOrderWindowStatus();

        final Button backForeSelect = ButtonBuilder.builder()
                .title("앞 뒤 선택")
                .x(120)
                .y(20)
                .width(140)
                .height(30)
                .background(Color.white)
                .build();

        final TextField player1CoinDesc = TextFieldBuilder.builder()
                .x(10)
                .y(60)
                .width(150)
                .height(30)
                .editable(false)
                .visible(false)
                .build();

        final Label player1Coin = LabelBuilder.builder()
                .x(30)
                .y(60)
                .width(200)
                .height(200)
                .visible(false)
                .build();

        final TextField player2CoinDesc = TextFieldBuilder.builder()
                .x(240)
                .y(60)
                .width(150)
                .height(30)
                .editable(false)
                .visible(false)
                .build();

        final Label player2Coin = LabelBuilder.builder()
                .x(240)
                .y(60)
                .width(200)
                .height(200)
                .visible(false)
                .build();

        final Button whoIsFirstButton = ButtonBuilder.builder()
                .title("선공은?")
                .x(10)
                .y(240)
                .width(140)
                .height(30)
                .visible(false)
                .background(Color.white)
                .build();

        backForeSelect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logger.info("동전 앞뒤 선택 버튼 클릭");
                status.player1Name = GameMemoryStore.getOmokGame().getPlayer1Name();
                status.player2Name = GameMemoryStore.getOmokGame().getPlayer2Name();
                player1CoinDesc.setText(status.player1Name + "의 동전");
                player2CoinDesc.setText(status.player2Name + "의 동전");
                player1CoinDesc.show();
                player2CoinDesc.show();

                if (!status.isBackForeSelected) {
                    final boolean isPlayer1Fore = new Random().nextBoolean();
                    if (isPlayer1Fore) {
                        player1Coin.setImage(Images.COIN_FORE_BG);
                        player2Coin.setImage(Images.COIN_BACK_BG);
                    } else {
                        player1Coin.setImage(Images.COIN_BACK_BG);
                        player2Coin.setImage(Images.COIN_FORE_BG);
                    }
                    status.isPlayer1Fore = isPlayer1Fore;
                    status.isBackForeSelected = true;

                    logger.info("player1 {} : {}", status.player1Name, isPlayer1Fore ? "앞" : "뒤");
                    logger.info("player2 {} : {}", status.player1Name, isPlayer1Fore ? "뒤" : "앞");
                }
                player1Coin.show();
                player2Coin.show();

                whoIsFirstButton.show();
            }
        });

        final Label firstCoin = LabelBuilder.builder()
                .x(200)
                .y(200)
                .width(180)
                .height(200)
                .visible(false)
                .build();

        final Button gameStartButton = ButtonBuilder.builder()
                .title("게임 시작")
                .x(10)
                .y(300)
                .width(140)
                .height(30)
                .background(Color.white)
                .visible(false)
                .build();

        whoIsFirstButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!status.isFirstSelected) {
                    final boolean isForeFirst = new Random().nextBoolean();
                    if (isForeFirst) {
                        firstCoin.setImage(Images.COIN_FORE_BG);
                        logger.info("앞이 선공입니다.");
                    } else {
                        firstCoin.setImage(Images.COIN_BACK_BG);
                        logger.info("뒤가 선공입니다.");
                    }
                    status.isFirstSelected = true;
                    status.isForeFirst = isForeFirst;
                }
                firstCoin.show();
                gameStartButton.show();
            }
        });

        gameStartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (status.isForeFirst) {
                    if (status.isPlayer1Fore) {
                        GameMemoryStore.getOmokGame().setPlayer1First();
                        logger.info("player1 {}의 선공으로 시작합니다.", status.player1Name);
                    } else {
                        GameMemoryStore.getOmokGame().setPlayer2First();
                        logger.info("player2 {}의 선공으로 시작합니다.", status.player2Name);
                    }
                } else {
                    if (status.isPlayer1Fore) {
                        GameMemoryStore.getOmokGame().setPlayer2First();
                        logger.info("player2 {}의 선공으로 시작합니다.", status.player2Name);
                    } else {
                        GameMemoryStore.getOmokGame().setPlayer1First();
                        logger.info("player1 {}의 선공으로 시작합니다.", status.player1Name);
                    }
                }
                WindowEventSource.windowChangedTo(WindowType.OMOK_GAME);
            }
        });

        return new Window(Color.gray)
                .put(backForeSelect)
                .put(player1CoinDesc)
                .put(player2CoinDesc)
                .put(player1Coin)
                .put(player2Coin)
                .put(whoIsFirstButton)
                .put(firstCoin)
                .put(gameStartButton)
                ;
    }

    private static class OmokOrderWindowStatus {
        String player1Name = "";
        String player2Name = "";
        boolean isBackForeSelected = false;
        boolean isFirstSelected = false;

        boolean isPlayer1Fore = false;
        boolean isForeFirst = false;
    }
}
