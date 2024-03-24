package piece;
import board.Square;

public class Move {

    private Square destinationSquare;
    private boolean isAttack;

    public Move(Square destinationSquare, boolean isAttack) {
        this.destinationSquare = destinationSquare;
        this.isAttack = isAttack;
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

    public static boolean isValidMove(int x, int y) {
        return x <= 7 && y <= 7 && x >= 0 && y >= 0;
    }

    public boolean isValidMove() {
        return destinationSquare.getX() <= 7 && destinationSquare.getY() <= 7
                && destinationSquare.getX() >= 0 && destinationSquare.getY() >= 0;
    }

    public String toString() {
        return "(" + destinationSquare.getX() +", " +  destinationSquare.getY() + ")";
    }

}
