package gui;

import board.Board;
import board.Square;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessTable {
    final int FRAME_WIDTH = 600;
    final int FRAME_HEIGHT = 600; // Window size
    private final JFrame frame = new JFrame("Chess"); //create a frame window
    private final JPanel boardPanel = new JPanel();
    private final Board board;
    private JButton[][] squareButton = new JButton[8][8];

    public ChessTable() {
        board = new Board();

        frame.setVisible(true); // make the frame visible
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); // set initial size
        frame.setLocationRelativeTo(null); // centre of screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        // Board container
        boardPanel.setSize(400,400);
        boardPanel.setLayout(new GridLayout(8,8));
        frame.add(boardPanel); // not set any BorderLayout so that it occupies all space

        // Create buttons as a square on board
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                JButton squarePanel = new JButton();
                squareButton[i - 1][j - 1] = squarePanel;

                boardPanel.add(squarePanel);
                squarePanel.setLayout(new GridBagLayout());
                squarePanel.setSize(50, 50);

                setColorSquare(squarePanel, i, j);
                setPieceIcon(squarePanel, i, j, board);
            }
        }
    }

    /**
     * Initialise pieces on board
     */
    private void setPieceIcon(JButton button, int row, int col, Board board) {
        Square square = board.getSquares()[row - 1][col - 1];

        if (square.isOccupied()) {
            Image pieceImg = new ImageIcon("img/"
                    + square.getPiece().getColour().substring(0,1)
                    + square.getPiece().getPieceType() + ".gif").getImage();

            ImageIcon pieceIcon = new ImageIcon(pieceImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH));

            button.setIcon(pieceIcon);
        }
    }

    /**
     * Initialise color of pieces based on their position on board
     */
    private void setColorSquare(JButton button, int row, int col) {
        if (row == 1 || row == 3 || row == 5 || row == 7) {
            button.setBackground(col % 2 == 0 ? Color.gray : Color.white);
        }
        else {
            button.setBackground(col % 2 != 0 ? Color.gray : Color.white);
        }
    }
}
