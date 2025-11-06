package chess.domain;

public enum Color {
    WHITE("White"),
    BLACK("Black");

    private final String str;

    Color(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
