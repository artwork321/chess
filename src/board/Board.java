package board;

import piece.*;
import java.util.ArrayList;

/**
 * Represent a chess board
 */
public class Board {
    private final int ROWS = 8;
    private final int COLUMNS = 8;
    private final Square[][] squares = new Square[8][8];
    private final ArrayList<Piece> alivePiece = new ArrayList<>();
    private final ArrayList<Piece> eliminatePiece = new ArrayList<>();

    private final String DEFAULT_COLOR = "White";
    private final String[] NON_MONARCH_COLORS_FOR_BLACK = {"White", "White", "Black", "Black"};
    private final String[] PAWN_MONARCH_COLORS_FOR_BLACK = {"White", "Black"};
    private final String[] NON_MONARCH_COLORS_FOR_WHITE = {"Black", "Black", "White", "White"};
    private final String[] PAWN_MONARCH_COLORS_FOR_WHITE = {"Black", "White"};


    public Board(String side) {

        // Initialize empty squares
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                squares[row][col] = new Square(col, row, null);
            }
        }

        String[] NonMonarchColors = NON_MONARCH_COLORS_FOR_WHITE;
        String[] PawnMonarchColor = PAWN_MONARCH_COLORS_FOR_WHITE;

        if(!side.equals(DEFAULT_COLOR)) {
            NonMonarchColors = NON_MONARCH_COLORS_FOR_BLACK;
            PawnMonarchColor = PAWN_MONARCH_COLORS_FOR_BLACK;
        }

        // Initialise Pawns
        initializePawns(PawnMonarchColor);

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
        initializePiece("Queen", queenRows, queenCols, PawnMonarchColor);

        // Initialise King
        int[] kingRows = {0, 7};
        int[] kingCols = {4, 4};
        initializePiece("King", kingRows, kingCols, PawnMonarchColor);
    }


    /**
     * Create Pawns on their initialised position
     */
    private void initializePawns(String[] color) {
        for (int col = 0; col < 8; col++) {
            squares[1][col].setPiece(new Pawn(squares[1][col], color[0]));
            squares[6][col].setPiece(new Pawn(squares[6][col], color[1]));
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

    public ArrayList<Piece> getAliveWhitePiece() {
        ArrayList<Piece> whitePiece = new ArrayList<>();

        for (Piece piece: alivePiece) {
            if(piece.getColour().equals("White")) {
                whitePiece.add(piece);
            }
        }
        return whitePiece;
    }

    public ArrayList<Piece> getAliveBlackPiece() {
        ArrayList<Piece> blackPiece = new ArrayList<>();

        for (Piece piece: alivePiece) {
            if(piece.getColour().equals("Black")) {
                blackPiece.add(piece);
            }
        }
        return blackPiece;
    }

    public ArrayList<Piece> getEliminatePiece() {
        return eliminatePiece;
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
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < ROWS; i++) {
            line.append("-----");
        }
        line.append("\n");

        StringBuilder textBoard = new StringBuilder("|  0 |  1 |  2 |  3 |  4 |  5 |  6 |  7 |\n");
        textBoard.append(line);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (j == 0) {
                    textBoard.append("|");
                }

                if (squares[i][j].getPiece() == null) {
                    textBoard.append("    ").append("|");
                }
                else textBoard.append(squares[i][j].getPiece()).append("|");
            }
            textBoard.append(" ").append(i).append("\n");
            textBoard.append(line);
        }

        return textBoard.toString();
    }
}
