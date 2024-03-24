package piece;

import board.*;
import java.util.*;

/**
 * General properties of any piece in chess
 */
public abstract class Piece {

    private final String pieceType;
    private final String colour;
    private Square currentCoordinate;
    private boolean isKilled;
    private ArrayList<Move> allMove = new ArrayList<>();

    public Piece(Square currentCoordinate, String colour, String pieceType) {
        this.currentCoordinate = currentCoordinate;
        this.colour = colour;
        this.isKilled = false;
        this.pieceType = pieceType;
    }

    public Square getCurrentCoordinate() {
        return currentCoordinate;
    }

    public void setCurrentCoordinate(Square newCoordinate) {
        this.currentCoordinate = newCoordinate;
    }

    public String getColour() {
        return this.colour;
    }

    /**
     * Make a move and return whether the move is processed
     * @return true if a move is successfully processed
     */
    public boolean move(Board board, Move move) {

        // Only process valid move
        if(!allMove.isEmpty() && allMove.contains(move)) {
            board.getSquare(currentCoordinate).setPiece(null); // Remove piece from old square
            board.getSquare(currentCoordinate).setOccupied(false);

            this.currentCoordinate = move.getDestinationSquare();

            // Capture another piece
            if(move.isAttack()) {
                // Kill the piece which is current on that square
                board.getSquare(currentCoordinate).getPiece().setKilled(true);
                board.getAlivePiece().remove(board.getSquare(currentCoordinate).getPiece());
                board.getEliminatePiece().add(board.getSquare(currentCoordinate).getPiece());
            }
            currentCoordinate.setPiece(this);
            currentCoordinate.setOccupied(true);

            return true;
        }
        return false;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public ArrayList<Move> getAllMove() {
        return allMove;
    }

    public void setAllMove(ArrayList<Move> allMove) {
        this.allMove = allMove;
    }

    /**
        Find all available moves and update allMove arraylist
     */
    public abstract void findAllNextMove(Board board);

    public String toString(){
        return " " + pieceType.charAt(0) + getColour().charAt(0) + " ";
    }
}
