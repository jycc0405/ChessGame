package chess.app;

import chess.controller.ChessController;
import chess.domain.ChessGame;

public class ChessApplication {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();

        ChessController chessController = new ChessController(chessGame);

        chessController.start();
    }
}
