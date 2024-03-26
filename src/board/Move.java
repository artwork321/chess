package board;
import board.Square;
import piece.Piece;

/**
 * Represent a move in chess game
 */
public class Move {
    protected final Piece movedPiece;
    private final Square destinationSquare;
    private final boolean isAttack;


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

    public boolean isAttack() {
        return isAttack;
    }

    public boolean equals(Square destinationSquare, Piece movedPiece) {

        // Require the same reference
        return this.movedPiece == movedPiece
                && this.destinationSquare == destinationSquare;
    }

    public String toString() {
        return "(" + destinationSquare.getX() +", " +  destinationSquare.getY() + ")";
    }

}
