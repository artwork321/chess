package piece;

import board.Board;
import board.Square;
import java.util.ArrayList;

/**
 * A kind of piece of chess game
 * If one side has this piece killed, the player will lose
 */
public class King extends Piece {

    private final int[][] DIRECTION = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, -1}, {1, 1}, {-1, 1}, {-1, -1}};
    public King(Square currentCoordinate, String colour) {
        super(currentCoordinate, colour, "King");
    }

    /**
     * Indicate if the piece is under attack of another piece
     * @param alivePiece contains all alive pieces on the board
     * @return true if it is under attack
     */
    public boolean isUnderAttack(ArrayList<Piece> alivePiece) {
        Square position = this.getCurrentCoordinate();
        String color = this.getColour();

        for (Piece piece : alivePiece) {
            if (!piece.getColour().equals(color)) {
                for(Move move : piece.getAllMove()) {
                    if (move.getDestinationSquare() == position) {
                        return true;
                    }
                }
            }

        }

        return false;
    }

    /**
     * Find add next move of a king
     *@param board contains all squares that can be considered to be the next move
     */
    @Override
    public void findAllNextMove(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        // Find the last square that a bishop can go
        for (int[] di : DIRECTION) {
            int newX = getCurrentCoordinate().getX();
            int newY = getCurrentCoordinate().getY();

            if (Square.isValidSquare(newX, newY)){
                newX += di[0];
                newY += di[1];

                if (Square.isValidSquare(newX, newY) && !isUnderAttack(board.getAlivePiece())) {
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
                    }

                }
            }
        }
        super.setAllMove(possibleMoves);
    }
}
