package chess.view;

import javax.swing.*;
import java.awt.*;

public class SquareButton extends JButton {
    private static final java.awt.Color LIGHT_SQUARE = new java.awt.Color(240, 217, 181);
    private static final java.awt.Color DARK_SQUARE = new java.awt.Color(181, 136, 99);

    public SquareButton(String posStr) {
        setPreferredSize(new Dimension(80, 80));
        setOpaque(true);
        setBorderPainted(true);

        int x = posStr.charAt(0) - 'a';
        int y = posStr.charAt(1) - '1';

        if ((x + y) % 2 == 0) {
            setBackground(LIGHT_SQUARE);
        } else {
            setBackground(DARK_SQUARE);
        }

        putClientProperty("position", posStr);
    }

    public String getPosition() {
        return (String) getClientProperty("position");
    }

    public void setSelected(boolean selected) {
        if (selected) {
            setBorder(BorderFactory.createLineBorder(java.awt.Color.RED, 3));
        } else {
            setBorder(UIManager.getBorder("Button.border"));
        }
    }
}
