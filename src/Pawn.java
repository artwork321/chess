import java.util.ArrayList;

public class Pawn extends Piece{

    private boolean isFirstMove;

    public Pawn(int x, int y, String colour) {
        super(x, y, colour, "pawn");
        this.isFirstMove = true;
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
        int newX = super.getX();
        int newY = super.getY() + getDirection();
        int newY2 = newY + getDirection();

        if (isFirstMove && Move.isValidMove(newX, newY2) && !board.getSquares()[newY2][newX].isOccupied()) {
            possibleMoves.add(new Move(newX, newY2, false));
        }

        if (Move.isValidMove(newX, newY) && !board.getSquares()[newY][newX].isOccupied()) {
            possibleMoves.add(new Move(newX, newY, false));
        }

        int captureX = super.getX() - getDirection();
        int captureY = super.getY() + getDirection();

        if (Move.isValidMove(captureX, captureY) &&
                board.getSquares()[captureY][captureX].isOccupied() &&
                !board.getSquares()[captureY][captureX].getPiece().getColour().equals(this.getColour())) {
            possibleMoves.add(new Move(captureX, captureY, true));
        }
        super.setAllMove(possibleMoves);
    }

}
