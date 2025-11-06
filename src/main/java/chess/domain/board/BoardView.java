package chess.domain.board;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public interface BoardView {
    Piece getPieceAt(Position pos);
    boolean isEmptyBetween(Position from, Position to);
}
