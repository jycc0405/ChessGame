package chess.domain.board;

import chess.domain.Color;
import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Board implements BoardView {
    private final Map<Position, Piece> board = new HashMap<>();

    private Board() {
    }

    public static Board initialize() {
        Board board = new Board();
        board.initPieces();
        return board;
    }

    private void initPieces() {
        //백 기물
        placePiece(new Position("a1"), Rook::new, Color.WHITE);
        placePiece(new Position("b1"), Knight::new, Color.WHITE);
        placePiece(new Position("c1"), Bishop::new, Color.WHITE);
        placePiece(new Position("d1"), King::new, Color.WHITE);
        placePiece(new Position("e1"), Queen::new, Color.WHITE);
        placePiece(new Position("f1"), Bishop::new, Color.WHITE);
        placePiece(new Position("g1"), Knight::new, Color.WHITE);
        placePiece(new Position("h1"), Rook::new, Color.WHITE);

        for (char file = 'a'; file <= 'h'; file++) {
            placePiece(new Position(file + "2"), Pawn::new, Color.WHITE);
        }

        //흑 기물
        placePiece(new Position("a8"), Rook::new, Color.BLACK);
        placePiece(new Position("b8"), Knight::new, Color.BLACK);
        placePiece(new Position("c8"), Bishop::new, Color.BLACK);
        placePiece(new Position("d8"), King::new, Color.BLACK);
        placePiece(new Position("e8"), Queen::new, Color.BLACK);
        placePiece(new Position("f8"), Bishop::new, Color.BLACK);
        placePiece(new Position("g8"), Knight::new, Color.BLACK);
        placePiece(new Position("h8"), Rook::new, Color.BLACK);

        for (char file = 'a'; file <= 'h'; file++) {
            placePiece(new Position(file + "7"), Pawn::new, Color.BLACK);
        }
    }

    private void placePiece(Position pos, BiFunction<Color, Position, Piece> ctor, Color color) {
        board.put(pos, ctor.apply(color, pos));
    }

    public boolean isEmpty(Position pos) {
        return !board.containsKey(pos);
    }

    public boolean isMovable(Position from, Position to, Color turn) {
        Piece piece = board.get(from);

        if (piece == null) {
            return false;
        }
        if (piece.getColor() != turn) {
            return false;
        }
        if (!piece.canMove(to, this)) {
            return false;
        }
        if (!Position.isValid(to)) {
            return false;
        }

        Piece target = board.get(to);
        if (target != null && target.getColor() == turn) {
            return false;
        }

        Board copyBoard = this.copy();
        copyBoard.movePiece(from, to);
        return !copyBoard.isCheck(turn);
    }

    public void movePiece(Position from, Position to) {
        Piece piece = board.get(from);
        if (piece == null) {
            throw new IllegalArgumentException("이동할 기물 없음");
        }

        if (!piece.canMove(to, this)) {
            throw new IllegalArgumentException("불가능한 이동");
        }

        board.remove(from);
        piece.setPosition(to);
        board.put(to, piece);

        if (piece instanceof Pawn pawn) {
            pawn.moved();
            if (pawn.canPromotion()) {
                piece = new Queen(piece.getColor(), to);
                piece.setPosition(to);
                board.put(to, piece);
            }
        }
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }

    public Board copy() {
        Board copy = new Board();
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            copy.board.put(entry.getKey(), entry.getValue().copy());
        }
        return copy;
    }

    @Override
    public Piece getPieceAt(Position pos) {
        if (!Position.isValid(pos)) return null;
        return board.get(pos);
    }

    @Override
    public boolean isEmptyBetween(Position from, Position to) {
        int dx = Integer.compare(to.getX(), from.getX());
        int dy = Integer.compare(to.getY(), from.getY());

        int x = from.getX() + dx;
        int y = from.getY() + dy;

        Position pos = new Position(x, y);

        while (pos.getX() != to.getX() || pos.getY() != to.getY()) {
            Piece piece = board.get(pos);
            if (piece != null) return false;

            pos.add(dx, dy);
        }
        return true;
    }

    public boolean isCheck(Color color) {
        Position kingPos = findKing(color);
        if (kingPos == null) {
            return false;
        }
        for (Piece piece : board.values()) {
            if (piece.getColor() != color && piece.canMove(kingPos, this)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCheckmate(Color color) {
        if (!isCheck(color)) {
            return false;
        }

        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            Piece piece = entry.getValue();
            if (piece.getColor() != color) continue;
            Position from = entry.getKey();

            for (int x = 0; x < 8; ++x) {
                for (int y = 0; y < 8; ++y) {
                    Position to = new Position(x, y);

                    if (!isMovable(from, to, color)) continue;

                    Board simulated = this.copy();
                    simulated.movePiece(from, to);
                    if (!simulated.isCheck(color)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Position findKing(Color color) {
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            Piece piece = entry.getValue();
            if (piece instanceof King && piece.getColor() == color) {
                return entry.getKey();
            }
        }
        return null;
    }

    public List<Piece> getAllPiecesByColor(Color color) {
        List<Piece> piecesOfColor = new ArrayList<>();
        for (Piece piece : board.values()) {
            if (piece.getColor() == color) {
                piecesOfColor.add(piece);
            }
        }
        return piecesOfColor;
    }
}
