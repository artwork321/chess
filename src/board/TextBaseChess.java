package board;

import piece.Piece;
import player.*;
import java.util.Scanner;

public class TextBaseChess {
    private Player currentPlayer;
    private final Player blackPlayer;
    private final Player whitePlayer;
    private final Board board;

    public TextBaseChess() {
        board = new Board();
        whitePlayer = new WhitePlayer(board);
        blackPlayer = new BlackPlayer(board);
        currentPlayer = whitePlayer;
    }

    private void showBoard() {
        System.out.println(board);
    }

    private Piece choosePiece() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose piece (x, y) to move: ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        scanner.nextLine();

        if(Square.isValidSquare(x, y) && currentPlayer.getAlivePiece().contains(board.getSquares()[y][x].getPiece())) {
            System.out.format("You choose: %s\n", board.getSquares()[y][x].getPiece());
            return board.getSquares()[y][x].getPiece();
        }

        return null;
    }

    public void checkCheckMate() {
        if(blackPlayer.isInCheck()) System.out.println("Black King is checked");
        if(whitePlayer.isInCheck()) System.out.println("Black King is checked");
    }

    public boolean executeMove(Piece piece) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(piece.getAllMove());
        System.out.println("Choose move among these: ");
        int i = scanner.nextInt();
        scanner.nextLine();

        if (piece.getAllMove().isEmpty() ||(i < 0 || i > piece.getAllMove().size()) ) {
            return false;
        }
        else {
            Move nextMove = piece.getAllMove().get(i);
            currentPlayer.makeMove(nextMove);
            checkCheckMate();

            return true;
        }
    }

    public void switchPlayer() {
        if(currentPlayer == whitePlayer) currentPlayer = blackPlayer;
        else currentPlayer = whitePlayer;

        System.out.format("Current player: %s\n", currentPlayer.getClass());
    }

    public void run() {
        showBoard();
        System.out.format("Current player: %s\n", currentPlayer.getClass());

        while(!board.isFinished()) {

            currentPlayer.findAllLegalMove();

            while (true) {
                Piece piece = choosePiece();
                if(piece == null || !executeMove(piece)) {
                    continue;
                }
                break;
            }

            switchPlayer();
            showBoard();
        }

        System.out.println("END GAME");
    }
}
