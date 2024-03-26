package piece;

import board.Board;
import board.Move;
import board.Square;
import java.util.ArrayList;

public class Knight extends Piece{

    private final int[][] DIRECTION = {{1, 2}, {2, 1}, {-1, 2}, {2, -1}, {-2, 1}, {1, -2}, {-2, -1}, {-1, -2}};
    public Knight(Square currentCoordinate, String colour) {
        super(currentCoordinate, colour, "knight");
    }

    @Override
    public void findAllNextMove(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        // Find the all squares that a knight can go
        for (int[] di : DIRECTION) {
            int newX = getCurrentCoordinate().getX();
            int newY = getCurrentCoordinate().getY();

            if (Square.isValidSquare(newX, newY)){
                newX += di[0];
                newY += di[1];

                if (Square.isValidSquare(newX, newY)) {
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
