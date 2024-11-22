public class Tile {
    private boolean isMarked;
    private char mark;

    public Tile() {
        isMarked = false;
        mark = '.';
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }

    public void mark(char mark) {
        setMarked(true);
        setMark(mark);
    }

    @Override
    public String toString() {
        if (!isMarked) return "   ";

        return String.format(" %c ", mark);
    }
}
