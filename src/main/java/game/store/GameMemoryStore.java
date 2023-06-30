package game.store;

import game.logic.domain.OmokGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameMemoryStore {
    private static final Logger logger = LoggerFactory.getLogger(GameMemoryStore.class);

    private static OmokGame omokGame;

    private GameMemoryStore() {}

    public static void store(final OmokGame newOmokGame) {
        if (omokGame != null) {
            logger.warn("이미 진행중이던 오목 게임 정보가 있습니다. 삭제처리합니다. {}", omokGame.getInfo());
        }
        logger.info("새로운 오목 게임 정보를 추가합니다. {}", newOmokGame.getInfo());
        omokGame = newOmokGame;
    }

    public static OmokGame getOmokGame() {
        if (omokGame == null) {
            throw new IllegalStateException("오목 게임 정보가 없습니다.");
        }
        return omokGame;
    }
}
