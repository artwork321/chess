package piece;

import board.Board;
import board.Square;
import java.util.ArrayList;

/**
 * A kind of piece of chess game
 */
public class Queen extends Piece{

    private final int[][] DIRECTION = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, -1}, {1, 1}, {-1, 1}, {-1, -1}};

    public Queen(Square currentCoordinate, String colour) {
        super(currentCoordinate, colour, "Queen");
    }

    /**
     * Find add next move of a queen
     *@param board contains all squares that can be considered to be the next move
     */
    @Override
    public void findAllNextMove(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        // Find the last square that a bishop can go
        for (int[] di : DIRECTION) {
            int newX = getCurrentCoordinate().getX();
            int newY = getCurrentCoordinate().getY();

            while (Square.isValidSquare(newX, newY)) { // Go through all directions
                newX += di[0];
                newY += di[1];

                if (Square.isValidSquare(newX, newY)) {
                    Square stepSquare = board.getSquares()[newY][newX];

                    // Normal move
                    if (!stepSquare.isOccupied()) {
                        possibleMoves.add(new Move(stepSquare, false));
                    }
                    // Attack move
                    else {
                        if (!stepSquare.getPiece().getColour().equals(this.getColour())) {
                            possibleMoves.add(new Move(stepSquare, true));
                        }
                        break;
                    }
                }
            }
        }
        super.setAllMove(possibleMoves);
    }
}
