package chess.domain;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.dto.ChessGameDto;
import chess.dto.ChessMapper;

public class ChessGame {
    private Color currentTurn;
    private Board board;

    public ChessGame() {
        this.board = Board.initialize();
        this.currentTurn = Color.WHITE;
    }

    public boolean movePiece(Position from, Position to) {
        if (!board.isMovable(from, to, currentTurn)) {
            return false;
        }

        board.movePiece(from, to);

        switchTurn();
        return true;
    }

    private void switchTurn() {
        currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public void reset() {
        this.board = Board.initialize();
        this.currentTurn = Color.WHITE;
    }

    public ChessGameDto toDto() {
        boolean check = board.isCheck(currentTurn);
        boolean checkmate = board.isCheckmate(currentTurn);
        return ChessMapper.toDto(board, currentTurn, check, checkmate);
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    public Board getBoard() {
        return board;
    }

    public boolean hasPieceAt(Position position) {
        return board.getPieceAt(position) != null;
    }
}
