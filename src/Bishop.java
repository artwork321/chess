import java.util.ArrayList;

public class Bishop extends Piece{
    private final int[][] DIRECTION = {{1,-1}, {1,1}, {-1, 1}, {-1, -1}};
    public Bishop(Square currentCoordinate, String colour) {
        super(currentCoordinate, colour, "bishop");
    }


    @Override
    public void findAllNextMove(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        // Find the last square that a bishop can go
        for (int[] di : DIRECTION) {
            int newX = getCurrentCoordinate().getX();
            int newY = getCurrentCoordinate().getY();

            while (Move.isValidMove(newX, newY)) { // Go through all directions
                newX += di[0];
                newY += di[1];

                if (Move.isValidMove(newX, newY)) {
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
