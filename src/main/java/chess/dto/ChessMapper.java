package chess.dto;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public final class ChessMapper {
    private ChessMapper() {
    }

    public static ChessGameDto toDto(Board board, Color turn, boolean check, boolean checkmate) {
        Map<String, String> pieceMap = new HashMap<>();

        for (Piece piece : board.getPieces()) {
            Position pos = piece.getPosition();
            String posStr = positionToString(pos);
            pieceMap.put(posStr, piece.toString());
        }

        return new ChessGameDto(pieceMap, turn.name(), check, checkmate);
    }

    private static String positionToString(Position pos) {
        char file = (char) ('a' + pos.getX());
        char rank = (char) ('1' + pos.getY());
        return String.valueOf(file) + rank;
    }
}
