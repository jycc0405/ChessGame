package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.BoardView;
import chess.domain.position.Position;

public class Queen extends Piece {
    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected boolean canMoveInternal(Position to, BoardView board) {
        int dx = Math.abs(position.getX() - to.getX());
        int dy = Math.abs(position.getY() - to.getY());

        if ((dx == 0 || dy == 0 || dx == dy) && board.isEmptyBetween(position, to)) {
            return true;
        }

        return false;
    }

    @Override
    public Piece copy() {
        Position posCopy = new Position(position);
        return new Queen(this.color, posCopy);
    }

    @Override
    public String toString() {
        return color.getStr() + "Queen";
    }
}
