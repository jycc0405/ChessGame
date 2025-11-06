package chess.domain.position;

public final class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static boolean isValid(Position pos) {
        return pos != null && pos.getX() >= 0 && pos.getX() < 8 && pos.getY() >= 0 && pos.getY() < 8;
    }

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    public Position add(Position delta) {
        return new Position(this.x + delta.x, this.y + delta.y);
    }

    public Position add(int dx, int dy) {
        return new Position(this.x + dx, this.y + dy);
    }
}
