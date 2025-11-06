package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import chess.domain.board.BoardView;

public abstract class Piece {
    protected final Color color;
    protected Position position;

    boolean hasMoved = false;

    protected Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public boolean canMove(Position to, BoardView board) {
        if (!Position.isValid(to)) return false;
        if (!position.equals(to)) return false;

        Piece target = board.getPieceAt(to);
        if (target != null && target.getColor() == this.color) return false;

        return canMoveInternal(to, board);
    }

    protected abstract boolean canMoveInternal(Position to, BoardView board);

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public abstract String toString();
}
