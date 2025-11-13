package chess.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class BoardPanel extends JPanel {
    public BoardPanel(MouseListener mouseListener) {
        super(new GridLayout(8, 8));

        for (int y = 7; y >= 0; y--) {
            for (char x = 'a'; x <= 'h'; x++) {
                String posStr = String.valueOf(x) + (y + 1);
                SquareButton square = new SquareButton(posStr);

                square.addMouseListener(mouseListener);
                add(square);
            }
        }
    }

    public SquareButton findSquareByPosition(String pos) {
        for (Component component : getComponents()) {
            if (component instanceof SquareButton square) {
                if (pos.equals(square.getPosition())) {
                    return square;
                }
            }
        }
        return null;
    }
}
