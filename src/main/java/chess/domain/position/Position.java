package chess.domain.position;

public final class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(String pos) {
        if (pos == null) {
            throw new IllegalArgumentException("포지션 생성 문자열이 비었음");
        }

        String s = pos.trim().toLowerCase();
        if (s.length() != 2) {
            throw new IllegalArgumentException("포지션 생성 문자열 포맷이 옳지 않음: " + pos);
        }

        char x = s.charAt(0);   // a-h
        char y = s.charAt(1);   // 1-8

        if (x < 'a' || x > 'h') {
            throw new IllegalArgumentException("포지션 생성 문자열 범위가 옳지 않음: " + x);
        }
        if (y < '1' || y > '8') {
            throw new IllegalArgumentException("포지션 생성 문자열 범위가 옳지 않음: " + y);
        }

        this.x = x - 'a';
        this.y = y - '1';
    }

    public Position(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
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

    public void add(Position delta) {
        this.x += delta.x;
        this.y += delta.y;
    }

    public void add(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
