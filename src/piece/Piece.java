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
