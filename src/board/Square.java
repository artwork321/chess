package board;
import piece.*;

public class Square {

    private final int X;
    private final int Y;
    private boolean isOccupied;
    private Piece piece;

    public Square(int x, int y, boolean isOccupied, Piece piece) {
        this.X = x;
        this.Y = y;
        this.isOccupied = isOccupied;
        this.piece = piece;
    }

    public Square(int x, int y, Piece piece) {
        this.X = x;
        this.Y = y;
        if (piece != null) this.isOccupied = true;
        this.piece = piece;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        if (piece != null)
            setOccupied(true);

        this.piece = piece;
    }
}
