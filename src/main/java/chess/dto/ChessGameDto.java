package chess.dto;

import java.util.List;

public record ChessGameDto(List<PieceDto> pieces, String currentTurn, boolean check, boolean checkmate) {
}
