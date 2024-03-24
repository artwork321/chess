package piece;

import board.Board;
import board.Square;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class King extends Piece {

    private final int[][] DIRECTION = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, -1}, {1, 1}, {-1, 1}, {-1, -1}};
    public King(Square currentCoordinate, String colour) {
        super(currentCoordinate, colour, "King");
    }

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

    @Override
    public void findAllNextMove(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        // Find the last square that a bishop can go
        for (int[] di : DIRECTION) {
            int newX = getCurrentCoordinate().getX();
            int newY = getCurrentCoordinate().getY();

            if (Move.isValidMove(newX, newY)){
                newX += di[0];
                newY += di[1];

                if (Move.isValidMove(newX, newY) && !isUnderAttack(board.getAlivePiece())) {
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
