package player;

import board.Board;
import piece.Piece;

import java.util.ArrayList;

public class WhitePlayer extends Player {

    public WhitePlayer(Board board) {
        super(board);
    }

    @Override
    public ArrayList<Piece> getAlivePiece() {
        return board.getAliveWhitePiece();
    }

    @Override
    public String toString() {
        return "White Player";
    }
}
