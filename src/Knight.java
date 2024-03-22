import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(int x, int y, String colour) {
        super(x, y, colour, "knight");
    }

    @Override
    public void findAllNextMove(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        // Normal and capture moves are the same
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 2; j++) {
                if(j != i) {
                    possibleMoves.add(new Move(getX() + i, getY() + j, false));
                    possibleMoves.add(new Move(getX() - i, getY() - j, false));
                    possibleMoves.add(new Move(getX() - i, getY() + j, false));
                    possibleMoves.add(new Move(getX() + i, getY() - j, false));
                }
            }
        }

        // Remove invalid moves
        possibleMoves.removeIf(move -> !move.isValidMove() ||
                (board.getSquares()[move.getY()][move.getX()].isOccupied() &&
                        board.getSquares()[move.getY()][move.getX()].getPiece().getColour().equals(this.getColour())));

        // Set whether a move results in an attack
        possibleMoves.forEach(move -> {
            boolean isAttack = board.getSquares()[move.getY()][move.getX()].isOccupied();
            move.setAttack(isAttack);
        });

        super.setAllMove(possibleMoves);
    }


}
