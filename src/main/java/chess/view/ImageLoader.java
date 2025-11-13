package chess.view;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Map;

import static java.util.Map.entry;

public class ImageLoader {
    private static final Map<String, String> PIECE_SYMBOL_TO_FILENAME = Map.ofEntries(
            entry("WhitePawn", "WhitePawn.png"),
            entry("WhiteRook", "WhiteRook.png"),
            entry("WhiteKnight", "WhiteKnight.png"),
            entry("WhiteBishop", "WhiteBishop.png"),
            entry("WhiteQueen", "WhiteQueen.png"),
            entry("WhiteKing", "WhiteKing.png"),
            entry("BlackPawn", "BlackPawn.png"),
            entry("BlackRook", "BlackRook.png"),
            entry("BlackKnight", "BlackKnight.png"),
            entry("BlackBishop", "BlackBishop.png"),
            entry("BlackQueen", "BlackQueen.png"),
            entry("BlackKing", "BlackKing.png")
    );

    private static final int ICON_SIZE = 60;

    private ImageLoader() {
    }

    public static ImageIcon getPieceIcon(String pieceSymbol) {
        String filename = PIECE_SYMBOL_TO_FILENAME.get(pieceSymbol);
        if (filename == null) {
            return null;
        }

        java.net.URL imgURL = ImageLoader.class.getResource("/sprites/" + filename);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } else {
            System.err.println("에러: 기물 이미지를 찾을 수 없습니다: /sprites/" + filename);
            return null;
        }
    }
}
