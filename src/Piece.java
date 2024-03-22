import java.util.*;

public abstract class Piece {
    private int x;
    private int y;
    private boolean isKilled;

    private ArrayList<Move> allMove = new ArrayList<>();

    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
        this.isKilled = false;
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

    public boolean move(Board board, Move move) {

        // Only process valid move
        if(allMove.contains(move)) {
            this.x = move.getX();
            this.y = move.getY();

            // Capture another piece
            if(move.isAttack()) {
                board.getSquares()[x][y].getPiece().setKilled(true);
            }

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
}
