package game;


import game.windows.components.FrameBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("오목과 지뢰찾기 게임을 시작합니다.");

        FrameBuilder.builder()
                .title("오목/지뢰찾기 게임")
                .width(400)
                .height(400)
                .build();
    }
}
