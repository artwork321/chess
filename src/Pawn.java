import java.util.ArrayList;

public class Pawn extends Piece{

    private boolean isFirstMove;

    public Pawn(Tile position) {
        super(position);
        this.isFirstMove = false;
    }

    public void move(Tile tile) {
        super.move(tile);
        super.getPosition().
    }
    @Override
    public void findAllNextMove(Board board) {
        ArrayList<Tile> moveableTile = new ArrayList<>();
        if(isFirstMove) {
            Tile move = board.getTiles()[getPosition().getY() + 2][getPosition().getX()];
            if (!move.isOccupied()) {
                moveableTile.add(move);
            }
        }
        else {

        }
    }
}
