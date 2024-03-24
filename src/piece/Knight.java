package piece;

import board.Board;
import board.Square;
import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(Square currentCoordinate, String colour) {
        super(currentCoordinate, colour, "knight");
    }

    @Override
    public void findAllNextMove(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        // Normal and capture moves are the same
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 2; j++) {
                if(j != i) {
                    int currentX = getCurrentCoordinate().getX();
                    int currentY = getCurrentCoordinate().getY();
                    if (Move.isValidMove(currentX + j, currentY + i))
                        possibleMoves.add(new Move(board.getSquares()[currentY + i][currentX + j], false));
                    if (Move.isValidMove(currentX - j, currentY - i))
                        possibleMoves.add(new Move(board.getSquares()[currentY - i][currentX - j], false));
                    if (Move.isValidMove(currentX + j, currentY - i))
                        possibleMoves.add(new Move(board.getSquares()[currentY - i][currentX + j], false));
                    if (Move.isValidMove(currentX - j, currentY + i))
                        possibleMoves.add(new Move(board.getSquares()[currentY + i][currentX - j], false));
                }
            }
        }

        // Remove invalid moves
        possibleMoves.removeIf(move -> !move.isValidMove() ||
                (move.getDestinationSquare().isOccupied() &&
                        move.getDestinationSquare().getPiece().getColour().equals(this.getColour())));

        // Set whether a move results in an attack
        possibleMoves.forEach(move -> {
            boolean isAttack = move.getDestinationSquare().isOccupied();
            move.setAttack(isAttack);
        });

        super.setAllMove(possibleMoves);
    }


}
