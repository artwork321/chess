package piece;

import board.*;
import java.util.ArrayList;

/**
 * A kind of piece in chess game
 */
public class Pawn extends Piece{

    private final int DIRECTION;
    private boolean isFirstMove;

    public Pawn(Square currentCoordinate, String colour) {
        super(currentCoordinate, colour, "pawn");
        this.isFirstMove = true;
        DIRECTION = (colour.equals("Black")) ? -1 : 1;
    }

    /**
     * Make only valid move and update property of pawn
     * @param board is the game board
     * @param move contains tile and type of move
     */
    @Override
    public boolean move(Board board, Move move) {
        if(super.move(board, move)) {
            if (isFirstMove) isFirstMove = false;
            return true;
        }

        return false;
    }


    /**
        Find all available moves and update the allMove arraylist
     */
    @Override
    public void findAllNextMove(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        // Normal move
        int newX = getCurrentCoordinate().getX();
        int newY = getCurrentCoordinate().getY() + DIRECTION;
        int newY2 = newY + DIRECTION;
        Square destinationSquare = board.getSquares()[newY][newX];
        Square destinationSquare2 = board.getSquares()[newY2][newX];

        // Pawns can go ahead two steps if it is their first move
        if (isFirstMove && Square.isValidSquare(newX, newY2) && !destinationSquare2.isOccupied()) {
            possibleMoves.add(new Move(destinationSquare2, false));
        }

        if (Square.isValidSquare(newX, newY) && !destinationSquare.isOccupied()) {
            possibleMoves.add(new Move(destinationSquare, false));
        }

        // Capture move
        int captureX = getCurrentCoordinate().getX() - DIRECTION;
        int captureY = getCurrentCoordinate().getY() + DIRECTION;
        Square destinationSquare3 = board.getSquares()[captureY][captureX];

        if (Square.isValidSquare(captureX, captureY) &&
                destinationSquare3.isOccupied() &&
                !destinationSquare3.getPiece().getColour().equals(this.getColour())) {
            possibleMoves.add(new Move(destinationSquare3, true));
        }
        super.setAllMove(possibleMoves);
    }

}
