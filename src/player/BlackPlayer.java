package player;

import board.Board;
import piece.Piece;

import java.util.ArrayList;

public class BlackPlayer extends Player {

    public BlackPlayer(Board board) {
        super(board);
    }

    @Override
    public ArrayList<Piece> getAlivePiece() {
        return board.getAliveBlackPiece();
    }

    @Override
    public String toString() {
        return "Black Player";
    }
}
