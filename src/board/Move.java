package board;
import board.Square;
import piece.Piece;

/**
 * Represent a move in chess game
 */
public class Move {
    protected final Piece movedPiece;
    private Square destinationSquare;
    private boolean isAttack;

    public Move(Piece movedPiece, Square destinationSquare, boolean isAttack) {
        this.movedPiece = movedPiece;
        this.destinationSquare = destinationSquare;
        this.isAttack = isAttack;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public Square getDestinationSquare() {
        return destinationSquare;
    }

    public void setDestinationSquare(Square destinationSquare) {
        this.destinationSquare = destinationSquare;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }

    /**
     * Return true if a move is within the board
     */
    public boolean isValidMove() {
        return destinationSquare.getX() <= 7 && destinationSquare.getY() <= 7
                && destinationSquare.getX() >= 0 && destinationSquare.getY() >= 0;
    }

    public boolean equals(Move move) {
        return move.getMovedPiece() == movedPiece
                && move.getDestinationSquare() == this.destinationSquare && move.isAttack == this.isAttack;
    }

    public String toString() {
        return "(" + destinationSquare.getX() +", " +  destinationSquare.getY() + ")";
    }

}
