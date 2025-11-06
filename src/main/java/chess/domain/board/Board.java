package chess.domain.board;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Board implements BoardView {
    private final Piece[][] board = new Piece[8][8];


    @Override
    public Piece getPieceAt(Position pos) {
        if (!Position.isValid(pos)) return null;
        return board[pos.getX()][pos.getY()];
    }

    @Override
    public boolean isEmptyBetween(Position from, Position to) {
        int dx = Integer.compare(to.getX(), from.getX());
        int dy = Integer.compare(to.getY(), from.getY());

        int x = from.getX() + dx;
        int y = from.getY() + dy;

        while (x != to.getX() || y != to.getY()) {
            Piece piece = board[x][y];
            if (piece != null) return false;

            x += dx;
            y += dy;
        }
        return true;
    }

    @Override
    public boolean wouldCauseCheck(Position from, Position to, Color color){
        return false;
    }
}
