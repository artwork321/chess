package player;

import board.Board;
import piece.King;
import board.Move;
import piece.Pawn;
import piece.Piece;

import java.util.ArrayList;

public abstract class Player {
    private ArrayList<Move> legalMove;
    private final King king;
    protected final Board board;
    private final boolean isInCheck;

    public Player(Board board) {
        this.legalMove = new ArrayList<>();
        this.board = board;
        this.king = getKing();
        this.isInCheck = isInCheck();
    }

    private King getKing() {
        ArrayList<Piece> alivePiece =  getAlivePiece();

        for (Piece piece : alivePiece) {
            if (piece instanceof King) {
                return (King) piece;
            }
        }

        throw new RuntimeException("Impossible board without a King");
    }

    public abstract ArrayList<Piece> getAlivePiece();

    public boolean isInCheck() {
        return king.isUnderAttack(getAlivePiece());
    }

    public void findAllLegalMove() {
        ArrayList<Move> possibleMove = new ArrayList<>();

        for (Piece piece : getAlivePiece()) {
            piece.findAllNextMove(board);
            possibleMove.addAll(piece.getAllMove());
        }

        legalMove = possibleMove;
    }

    /**
     * Make a move and return whether the move is processed
     */
    public void makeMove(Move move) {

        // Only process valid move
        ArrayList<Move> allMove = move.getMovedPiece().getAllMove();

        if(!allMove.isEmpty() && allMove.contains(move)) {
            board.getSquare(move.getMovedPiece().getCurrentCoordinate()).setPiece(null); // Remove piece from old square
            board.getSquare(move.getMovedPiece().getCurrentCoordinate()).setOccupied(false);

            move.getMovedPiece().setCurrentCoordinate(move.getDestinationSquare()); // Set new position for moved piece

            // Capture another piece
            if(move.isAttack()) {
                // Kill the piece which is current on that square
                board.getSquare(move.getDestinationSquare()).getPiece().setKilled(true);
                board.getAlivePiece().remove(board.getSquare(move.getDestinationSquare()).getPiece());
                board.getEliminatePiece().add(board.getSquare(move.getDestinationSquare()).getPiece());
            }
            move.getDestinationSquare().setPiece(move.getMovedPiece());
            move.getDestinationSquare().setOccupied(true);

            // Handle first move for pawns
            if (move.getMovedPiece() instanceof Pawn && ((Pawn) move.getMovedPiece()).isFirstMove()) {
                ((Pawn) move.getMovedPiece()).setFirstMove(false);
            }

            // Check if the move causes the game to end
            if (king.isKilled()) {
                board.setFinished(true);
            }

        }
    }
}
