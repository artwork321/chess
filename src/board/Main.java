package board;
import java.util.Scanner;
import board.*;
import piece.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        String instruction;

        System.out.println(board);

        while(true) {
            System.out.println("Choose piece (coordinate) to move");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            scanner.nextLine();

            Piece piece = board.getSquares()[y][x].getPiece();
            if (piece == null) {
                continue;
            }

            piece.findAllNextMove(board);
            System.out.println(piece);
            System.out.println(piece.getAllMove());

            System.out.println("Choose move");
            int i = scanner.nextInt();
            scanner.nextLine();

            if (!piece.getAllMove().isEmpty()) {
                Move nextMove = piece.getAllMove().get(i);
                piece.move(board, nextMove);
            }

            System.out.println(board);
        }

    }
}