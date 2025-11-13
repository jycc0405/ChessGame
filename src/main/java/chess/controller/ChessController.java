package chess.controller;

import javax.swing.SwingUtilities; // GUI 시작을 위한 스윙 유틸리티

import chess.domain.ChessGame;
import chess.domain.position.Position;
import chess.dto.ChessGameDto;
import chess.view.ChessGameFrame;

public class ChessController {
    private final ChessGame game;
    private ChessGameFrame view;

    public ChessController(ChessGame game) {
        this.game = game;
    }

    public ChessGameDto getGameStatus() {
        return game.toDto();
    }

    public boolean movePiece(String fromStr, String toStr) {
        try {
            Position from = new Position(fromStr);
            Position to = new Position(toStr);

            boolean success = game.movePiece(from, to);

            ChessGameDto currentStatus = game.toDto();
            if (currentStatus.checkmate()) {
                System.out.println("게임 종료: 체크메이트!");

                String loserTurn = currentStatus.currentTurn();
                String winner = loserTurn.equals("WHITE") ? "BLACK" : "WHITE";

                // View에 게임 종료를 요청하면서 승자를 전달
                view.showGameEndMessage(winner); // ⭐️ View 메서드 호출 추가
            }

            return success;

        } catch (IllegalArgumentException e) {
            System.err.println("이동 오류 발생: " + e.getMessage());
            return false;
        }
    }

    public boolean isPieceSelectable(String posStr) {
        try {
            Position pos = new Position(posStr);

            if (!game.hasPieceAt(pos)) {
                return false;
            }

            return game.getBoard().getPieceAt(pos).getColor() == game.getCurrentTurn();

        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            this.view = new ChessGameFrame(this);
        });
    }
}
