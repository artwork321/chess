import java.util.*;

public abstract class Piece {

    private final String pieceType;

    private final int direction;
    private final String colour;
    private int x;
    private int y;
    private boolean isKilled;

    private ArrayList<Move> allMove = new ArrayList<>();

    public Piece(int x, int y, String colour, String pieceType) {
        this.x = x;
        this.y = y;
        this.colour = colour;
        this.isKilled = false;
        this.direction = (colour.equals("Black")) ? -1 : 1;
        this.pieceType = pieceType;
    }

    public int getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getColour() {
        return this.colour;
    }

    /**
     * Make a move
     * @param board
     * @param move
     * @return
     */
    public boolean move(Board board, Move move) {

        // Only process valid move
        if(allMove.contains(move)) {
            board.getSquares()[y][x] = new Square(false, null);

            this.x = move.getX();
            this.y = move.getY();

            // Capture another piece
            if(move.isAttack()) {
                board.getSquares()[y][x].getPiece().setKilled(true);
            }
            board.getSquares()[y][x] = new Square(true, this);

            return true;
        }

        return false;

    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public ArrayList<Move> getAllMove() {
        return allMove;
    }

    public void setAllMove(ArrayList<Move> allMove) {
        this.allMove = allMove;
    }

    /**
        Find all available moves and update allMove arraylist
     */
    public abstract void findAllNextMove(Board board);

    public String toString(){
        return " " + pieceType.charAt(0) + getColour().charAt(0) + " ";
    }
}
