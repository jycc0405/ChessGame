package chess.dto;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;

public final class ChessMapper {
    private ChessMapper() {
    }

    public static ChessGameDto toDto(Board board, Color turn, boolean check, boolean checkmate) {
        List<PieceDto> pieces = board.getPieces().stream()
                .map(ChessMapper::toPieceDto)
                .toList();

        return new ChessGameDto(pieces, turn.name(), check, checkmate);
    }

    private static PieceDto toPieceDto(Piece piece) {
        Position pos = piece.getPosition();
        return new PieceDto(
                piece.getClass().getSimpleName(),
                piece.getColor().name(),
                pos.getX(),
                pos.getY()
        );
    }
}
