package gui;

import board.Board;
import board.Move;
import board.Square;
import player.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class ChessTable {
    //TODO: make a mode for computer

    // Window components
    final int FRAME_WIDTH = 600;
    final int FRAME_HEIGHT = 700;
    private final JFrame frame = new JFrame("Chess");
    private final JPanel boardPanel = new JPanel();
    private Board board;
    private SquareButton[][] squareButton = new SquareButton[8][8];
    private JPanel textPanel = new JPanel();
    private JLabel informLabel = new JLabel();
    private final JMenuBar settingBar = new JMenuBar();

    // Mechanic components
    private final String DEFAULT_COLOR = "White";
    private Square sourceSquare = null;
    private Square destinationSquare = null;
    private Player currentPlayer;
    private Player otherPlayer;
    private boolean pieceListenerDeactivate;


    /**
     * Show a chess table
     */
    public ChessTable() {
        board = new Board(DEFAULT_COLOR);
        currentPlayer = new WhitePlayer(board);
        otherPlayer = new BlackPlayer(board);
        pieceListenerDeactivate = false;


        // Set up the main window
        setUpFrame();

        //TODO: Set up Menu Bar to reset game
        setUpMenuBar();

        // Set up inform panel to show winner
        setUpInformPanel();

        // Set up board container
        boardPanel.setPreferredSize(new Dimension(400, 400));
        boardPanel.setLayout(new GridLayout(8,8));
        frame.add(boardPanel);

        // Create buttons as a square on board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Set up board
                SquareButton squarePanel = new SquareButton(i, j, board);
                squareButton[i][j] = squarePanel;
                boardPanel.add(squarePanel);

                // Event handler to move a piece from one button to another
                squarePanel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!pieceListenerDeactivate) {
                            // First click
                            if(sourceSquare == null) {
                                sourceSquare = board.getSquares()[squarePanel.ROW][squarePanel.COLUMN];

                                // Disable sourceSquare if player chooses invalid squares and pieces
                                if (sourceSquare.getPiece() == null
                                        || !currentPlayer.getAlivePiece().contains(sourceSquare.getPiece())) {
                                    sourceSquare = null;
                                }else {
                                    // Show all valid moves of that piece
                                    sourceSquare.getPiece().findAllNextMove(board);
                                    highlightSquare(sourceSquare.getPiece().getAllMove());

                                    // Print in console for bug fixing
                                    System.out.println(sourceSquare.getPiece());
                                    System.out.println(sourceSquare.getPiece().getAllMove());
                                }
                            }
                            // Second click
                            else {
                                destinationSquare = board.getSquares()[squarePanel.ROW][squarePanel.COLUMN];

                                // Process the move if it is valid
                                for (Move possibleMove : sourceSquare.getPiece().getAllMove()) {
                                    if(possibleMove.equals(destinationSquare, sourceSquare.getPiece())) {
                                        currentPlayer.makeMove(possibleMove);
                                        switchPlayer();

                                        System.out.println(board);
                                        break;
                                    }
                                }
                                drawBoard();
                                sourceSquare = null;
                                destinationSquare = null;
                            }
                            // Show message
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    if(checkEndGame()) informLabel.setText(currentPlayer.toString() + " LOSE!");
                                    textPanel.revalidate();
                                    textPanel.repaint();
                                }
                            });

                        }

                    }
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                });
            }
        }

    }

    private void setUpInformPanel() {
        informLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        informLabel.setPreferredSize(new Dimension(400, 100));

        textPanel.setLayout(new BorderLayout());
        textPanel.setBackground(Color.white);
        textPanel.add(informLabel, BorderLayout.CENTER); // Add informLabel to the center of textPanel
        frame.add(textPanel, BorderLayout.SOUTH);
    }

    private void setUpMenuBar() {
        JMenu Mode = new JMenu("New Game");
        JMenuItem twoPlayers = new JMenuItem("Two Players");
        JMenuItem onePlayers = new JMenuItem("One Player");
        Mode.add(twoPlayers);
        Mode.add(onePlayers);
        settingBar.add(Mode);
    }

    private void setUpFrame() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); // set initial size
        frame.setLocationRelativeTo(null); // centre of screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setJMenuBar(settingBar);
    }

    private class SquareButton extends JButton {

        protected final int ROW;
        protected final int COLUMN;

        SquareButton(int row, int col, Board board) {
            ROW = row;
            COLUMN = col;
            this.setLayout(new GridBagLayout());
            this.setSize(50, 50);
            setPieceIcon(board);
            setColorSquare();
        }

        /**
         * Initialise pieces on board
         */
        private void setPieceIcon(Board board) {
            Square square = board.getSquares()[ROW][COLUMN];

            if (square.isOccupied()) {
                Image pieceImg = new ImageIcon("img/"
                        + square.getPiece().getColour().substring(0,1)
                        + square.getPiece().getPieceType() + ".gif").getImage();

                ImageIcon pieceIcon = new ImageIcon(pieceImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH));

                this.setIcon(pieceIcon);
            }
            else {
                this.setIcon(null);
            }
        }

        /**
         * Initialise color of pieces based on their position on board
         */
        private void setColorSquare() {
            if (ROW == 0 || ROW == 2 || ROW == 4 || ROW == 6) {
                this.setBackground(COLUMN % 2 != 0 ? Color.gray : Color.white);
            }
            else {
                this.setBackground(COLUMN % 2 == 0 ? Color.gray : Color.white);
            }
        }
    }

    public void play() {
        frame.setVisible(true); // make the frame visible
    }

    /**
     * Change buttons colour to represent valid move
     * @param movedPiece contains all valid move for a piece
     */
    private void highlightSquare(ArrayList<Move> movedPiece) {
        for (Move move : movedPiece) {
            int col = move.getDestinationSquare().getX();
            int row = move.getDestinationSquare().getY();

            squareButton[row][col].setBackground(Color.pink);
        }
    }

    //TODO: Make it work
    private void resetGame(String side) {
        board = new Board(side);
        drawBoard();
        pieceListenerDeactivate = false;
    }

    /**
     * Disable buttons if the game is ended
     * @return true if one side loses
     */
    private boolean checkEndGame() {
        if(currentPlayer.isLose()) {
            pieceListenerDeactivate = true;
            return true;
        }
        return false ;
    }

    /**
     * Draw th board by drawing each square
     */
    private void drawBoard() {
        for (SquareButton[] buttons : squareButton) {
            for (SquareButton button : buttons) {
                button.setColorSquare();
                button.setPieceIcon(board);
            }
        }
    }

    /**
     * Give the next move to other player
     */
    private void switchPlayer() {
        Player tempPlayer = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = tempPlayer;
    }
}
