public abstract class Player {
    private final char mark;
    private final String name;
    private int wins;
    private final MoveStrategy moveStrategy;

    public Player(char character, String name, MoveStrategy moveStrategy) {
        this.mark = character;
        this.name = name;
        this.moveStrategy = moveStrategy;
    }

    public int doMove(Board board) {
        return moveStrategy.move(board);
    }

    public String getName() {
        return name;
    }

    public char getMark() {
        return mark;
    }

    public void won() {
        wins++;
    }

    public int getWins() {
        return wins;
    }
}

