import java.util.*;

public abstract class Piece {
    private Tile position;
    private boolean isKilled;

    private ArrayList<Tile> allMove = new ArrayList<>();

    public Piece(Tile position) {
        this.position = position;
        this.isKilled = false;
    }

    public Tile getPosition() {
        return position;
    }

    public void move(Tile position) {
        this.position = position;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public ArrayList<Tile> getAllMove() {
        return allMove;
    }

    public abstract void findAllNextMove(Board board);
}
