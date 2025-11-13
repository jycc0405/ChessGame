package chess.dto;

import java.util.Map;

public record ChessGameDto(Map<String, String> pieceMap, String currentTurn, boolean check, boolean checkmate) {
}
