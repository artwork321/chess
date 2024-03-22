public class Square {

    private boolean isOccupied;

    Piece piece;

    public Square(boolean isOccupied, Piece piece) {
        this.isOccupied = isOccupied;
        this.piece = piece;
    }

    public Square(boolean isOccupied) {
        this.isOccupied = isOccupied;
        this.piece = null;
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
        this.piece = piece;
    }
}
