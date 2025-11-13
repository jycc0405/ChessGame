package chess.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import chess.controller.ChessController;
import chess.dto.ChessGameDto;

public class ChessGameFrame extends JFrame {
    private final ChessController controller;
    private final BoardPanel boardPanel;
    private final JLabel statusLabel;
    private String selectedPosition = null;

    public ChessGameFrame(ChessController controller) {
        this.controller = controller;

        setTitle("Java Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("게임을 시작합니다. White 턴입니다.", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(statusLabel, BorderLayout.NORTH);

        boardPanel = new BoardPanel(new SquareClickHandler());
        add(boardPanel, BorderLayout.CENTER);

        updateBoard(controller.getGameStatus());
        setVisible(true);
    }

    public void updateBoard(ChessGameDto dto) {
        String turn = dto.currentTurn();
        String message = turn + " 턴입니다. ";

        if (dto.check()) {
            message += "⚠️ Check!";
        }
        statusLabel.setText(message);

        for (Component component : boardPanel.getComponents()) {
            if (component instanceof SquareButton square) {
                String posStr = square.getPosition();
                String pieceSymbol = dto.pieceMap().getOrDefault(posStr, "");

                if (pieceSymbol.isEmpty()) {
                    square.setIcon(null);
                } else {
                    ImageIcon icon = ImageLoader.getPieceIcon(pieceSymbol);
                    square.setIcon(icon);
                }
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private class SquareClickHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!(e.getSource() instanceof SquareButton clickedSquare)) {
                return;
            }

            String posStr = clickedSquare.getPosition();

            if (selectedPosition == null) {
                if (controller.isPieceSelectable(posStr)) {
                    selectedPosition = posStr;
                    statusLabel.setText("선택: " + selectedPosition + ". 이동할 위치를 선택하세요.");
                    clickedSquare.setSelected(true);
                } else {
                    statusLabel.setText("오류: " + posStr + " 위치의 기물은 움직일 수 없습니다. " + controller.getGameStatus().currentTurn() + " 턴입니다.");
                }
            } else {
                SquareButton fromSquare = boardPanel.findSquareByPosition(selectedPosition);
                if (fromSquare != null) {
                    fromSquare.setSelected(false);
                }

                String from = selectedPosition;

                boolean success = controller.movePiece(from, posStr);
                selectedPosition = null;

                if (success) {
                    updateBoard(controller.getGameStatus());
                } else {
                    statusLabel.setText("오류: " + from + "에서 " + posStr + "로 이동 불가능합니다. 다시 선택하세요. " + controller.getGameStatus().currentTurn() + " 턴입니다.");
                }
            }
        }
    }

    public void showGameEndMessage(String winnerColor) {
        statusLabel.setText("♟️ 체크메이트! " + winnerColor + " 승리! 🎉");

        JOptionPane.showMessageDialog(
                this,
                winnerColor + "플레이어가 체크메이트로 승리했습니다!",
                "게임 종료",
                JOptionPane.INFORMATION_MESSAGE
        );
        this.dispose();
    }
}
