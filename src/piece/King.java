package piece;

import board.Board;
import board.Move;
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

    //TODO: decide when king is checkmate
    public boolean hasEscapeMove(Board board, ArrayList<Piece> alivePiece) {
        return true;
    }

    /**
     * Indicate if the piece is under attack of another piece
     * @param alivePiece contains all alive pieces on the board
     * @return true if it is under attack
     */
    public boolean isUnderAttack(ArrayList<Piece> alivePiece, Square position, Board board) {
        //TODO: check king is in check

        return false;
    }

    /**
     * Find add next move of a king
     *@param board contains all squares that can be considered to be the next move
     */
    @Override
    public void findAllNextMove(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        // Find the last square that a king can go
        for (int[] di : DIRECTION) {
            int newX = getCurrentCoordinate().getX();
            int newY = getCurrentCoordinate().getY();

            if (Square.isValidSquare(newX, newY)){
                newX += di[0];
                newY += di[1];

                if (Square.isValidSquare(newX, newY) && !isUnderAttack(board.getAlivePiece(),
                        this.getCurrentCoordinate(), board)) {
                    Square stepSquare = board.getSquares()[newY][newX];

                    // Normal move
                    if (!stepSquare.isOccupied()) {
                        possibleMoves.add(new Move(this, stepSquare, false));
                    }
                    // Attack move
                    else {
                        if (!stepSquare.getPiece().getColour().equals(this.getColour())) {
                            possibleMoves.add(new Move(this, stepSquare, true));
                        }
                    }

                }
            }
        }
        super.setAllMove(possibleMoves);
    }
}
