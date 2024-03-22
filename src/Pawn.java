import java.util.ArrayList;

public class Pawn extends Piece{

    private boolean isFirstMove;

    public Pawn(int x, int y) {
        super(x, y);
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
            isFirstMove = false;
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
        if (isFirstMove && !board.getSquares()[super.getY() + 2][super.getY()].isOccupied()) {
            possibleMoves.add(new Move(super.getX(), super.getY() + 2,  false));
        }
        if (!board.getSquares()[getY() + 1][getX()].isOccupied())
            possibleMoves.add(new Move(super.getX(), super.getY() + 1,  false));

        // Capture
        if (getY() + 1 < 8 && getX() + 1 < 8 && board.getSquares()[getY() + 1][getX()].isOccupied())
            possibleMoves.add(new Move(super.getX() + 1, super.getY() + 1,  true));

        super.setAllMove(possibleMoves);
    }

    public String toString(){
        return "Pawn";
    }
}
