package game.logic.domain;

public class Gamer {
    public final String name;
    private boolean isFirst;

    public Gamer(final String name) {
        this.name = name;
        this.isFirst = false;
    }

    public void setFirst(final boolean isFirst) {
        this.isFirst = isFirst;
    }
}
