package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.BoardView;
import chess.domain.position.Position;

public class Pawn extends Piece {
    public Pawn(Color color, Position position) {
        super(color, position);
    }

    public Pawn(Color color, Position position, boolean hasMoved) {
        super(color, position);
        this.hasMoved = hasMoved;
    }

    boolean hasMoved = false;

    @Override
    protected boolean canMoveInternal(Position to, BoardView board) {
        int dx = position.getX() - to.getX();
        int dy = position.getY() - to.getY();
        int forward = (color == Color.WHITE) ? -1 : 1;

        Piece target = board.getPieceAt(to);

        if (dy == forward && dx == 0 && target == null) {
            return true;
        }

        if ((!hasMoved && dy == 2 * forward && dx == 0)) {
            if (board.isEmptyBetween(position, to) && target == null) {
                return true;
            }

        }

        if (dy == forward && Math.abs(dx) == 1 && target != null && target.getColor() != color) {
            return true;
        }

        return false;
    }

    public void moved() {
        this.hasMoved = true;
    }

    @Override
    public Piece copy() {
        Position posCopy = new Position(position);
        return new Pawn(this.color, posCopy, this.hasMoved);
    }

    @Override
    public String toString() {
        return color.getStr() + "Pawn";
    }
}
