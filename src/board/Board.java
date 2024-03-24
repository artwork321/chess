package board;

import piece.*;
import java.util.ArrayList;

/**
 * Represent a chess board
 */
public class Board {
    private final Square[][] squares = new Square[8][8];
    private final ArrayList<Piece> alivePiece = new ArrayList<>();
    private final ArrayList<Piece> eliminatePiece = new ArrayList<>();
    private boolean isFinished;

    public Board() {
        this.isFinished = false;

        // Initialize empty squares
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new Square(col, row, null);
            }
        }

        String[] NonMonarchColors = {"White", "White", "Black", "Black"};
        String[] MonarchColor = {"White", "Black"};

        // Initialise Pawns
        initializePawns();

        // Initialise Knights
        int[] knightRows = {0, 0, 7, 7};
        int[] knightCols = {1, 6, 1, 6};
        initializePiece("Knight", knightRows, knightCols, NonMonarchColors);

        // Initialize bishops
        int[] bishopRows = {0, 0, 7, 7};
        int[] bishopCols = {2, 5, 2, 5};
        initializePiece("Bishop", bishopRows, bishopCols, NonMonarchColors);

        // Initialize rooks
        int[] rookRows = {0, 0, 7, 7};
        int[] rookCols = {0, 7, 0, 7};
        initializePiece("Rook", rookRows, rookCols, NonMonarchColors);

        // Initialise Queen
        int[] queenRows = {0, 7};
        int[] queenCols = {3, 3};
        initializePiece("Queen", queenRows, queenCols, MonarchColor);

        // Initialise King
        int[] kingRows = {0, 7};
        int[] kingCols = {4, 4};
        initializePiece("King", kingRows, kingCols, MonarchColor);
    }

    /**
     * Create Pawns on their initialised position
     */
    private void initializePawns() {
        for (int col = 0; col < 8; col++) {
            squares[1][col].setPiece(new Pawn(squares[1][col], "White"));
            squares[6][col].setPiece(new Pawn(squares[6][col], "Black"));
            alivePiece.add(squares[1][col].getPiece());
            alivePiece.add(squares[6][col].getPiece());
        }
    }

    /**
     * Create other pieces on their initialised position
     * @param pieceClass indicates kinds of piece
     * @param rows contains rows for pieces to be initialised
     * @param cols contains cols for pieces to be initialised
     */
    private void initializePiece(String pieceClass, int[] rows, int[] cols, String[] colors) {

        for (int i = 0; i < rows.length; i++) {
            int row = rows[i];
            int col = cols[i];
            String color = colors[i];

            switch (pieceClass) {
                case "Rook" -> squares[row][col].setPiece(new Rook(squares[row][col], color));
                case "Bishop" -> squares[row][col].setPiece(new Bishop(squares[row][col], color));
                case "Knight" -> squares[row][col].setPiece(new Knight(squares[row][col], color));
                case "King" -> squares[row][col].setPiece(new King(squares[row][col], color));
                case "Queen" -> squares[row][col].setPiece(new Queen(squares[row][col], color));
            }
            alivePiece.add(squares[row][col].getPiece());
        }
    }

    public ArrayList<Piece> getAlivePiece() {
        return alivePiece;
    }

    public ArrayList<Piece> getEliminatePiece() {
        return eliminatePiece;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
    public Square[][] getSquares() {
        return squares;
    }

    public Square getSquare(Square square) {
        return squares[square.getY()][square.getX()];
    }

    /**
     * Return the board game and pieces in the form of text
     */
    public String toString() {
        StringBuilder textBoard = new StringBuilder("|  0 |  1 |  2 |  3 |  4 |  5 |  6 |  7 |\n|");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                textBoard.append(squares[i][j].getPiece()).append("|");
            }
            if (i != 7)
                textBoard.append(" ").append(i).append("\n|");
            else
                textBoard.append(" ").append(i).append("\n");
        }

        return textBoard.toString();
    }
}
