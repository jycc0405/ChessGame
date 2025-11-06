package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.BoardView;
import chess.domain.position.Position;

public class King extends Piece {
    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected boolean canMoveInternal(Position to, BoardView board) {
        int dx = Math.abs(position.getX() - to.getX());
        int dy = Math.abs(position.getY() - to.getY());

        if (Math.max(dx, dy) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return color.getStr() + "King";
    }
}
