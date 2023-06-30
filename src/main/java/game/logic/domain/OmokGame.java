package game.logic.domain;

public class OmokGame {
    private final int omokSize;
    private final Gamer player1;
    private final Gamer player2;

    private OmokGame(final int omokSize, final Gamer player1, final Gamer player2) {
        this.omokSize = omokSize;
        this.player1 = player1;
        this.player2 = player2;
    }

    public static OmokGame init(final int omokSize, final String player1Name, final String player2Name) {
        return new OmokGame(
                omokSize,
                new Gamer(player1Name),
                new Gamer(player2Name)
        );
    }

    public String getInfo() {
        return "[바둑판 사이즈=" + omokSize + ", player1=" + player1.name + ", player2=" + player2.name + "]";
    }
}
