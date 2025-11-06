package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.BoardView;
import chess.domain.position.Position;

public class Knight extends Piece {
    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected boolean canMoveInternal(Position to, BoardView board) {
        int dx = Math.abs(position.getX() - to.getX());
        int dy = Math.abs(position.getY() - to.getY());

        if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return color.getStr() + "Knight";
    }
}
