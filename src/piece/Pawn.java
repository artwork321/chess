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
        DIRECTION = (colour.equals("White")) ? -1 : 1;
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

        // Pawns can go ahead two steps if it is their first move
        if (isFirstMove && Square.isValidSquare(newX, newY2)) {
            Square destinationSquare2 = board.getSquares()[newY2][newX];

            if (!destinationSquare2.isOccupied()) {
                possibleMoves.add(new Move(this, destinationSquare2, false));
            }
        }


        // Normal move
        if (Square.isValidSquare(newX, newY)) {
            Square destinationSquare = board.getSquares()[newY][newX];
            if (!destinationSquare.isOccupied()) {
                possibleMoves.add(new Move(this, destinationSquare, false));
            }
        }

        // Capture moves
        int[] captureXArray = {getCurrentCoordinate().getX() - DIRECTION, getCurrentCoordinate().getX() + DIRECTION};
        int captureY = getCurrentCoordinate().getY() + DIRECTION;

        for (int captureX : captureXArray) {
            if (Square.isValidSquare(captureX, captureY)) {
                Square destinationSquare = board.getSquares()[captureY][captureX];
                if (destinationSquare.isOccupied() && !destinationSquare.getPiece().sameSide(this)) {
                    possibleMoves.add(new Move(this, destinationSquare, true));
                }
            }
        }

        super.setAllMove(possibleMoves);
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }
}
