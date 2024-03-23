import java.util.Scanner;

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

            board.getSquares()[y][x].getPiece().findAllNextMove(board);
            System.out.println(board.getSquares()[y][x].getPiece().getAllMove());

            System.out.println("Choose move");
            int i = scanner.nextInt();
            scanner.nextLine();

            if (!board.getSquares()[y][x].getPiece().getAllMove().isEmpty()) {
                Move nextMove = board.getSquares()[y][x].getPiece().getAllMove().get(i);
                board.getSquares()[y][x].getPiece().move(board, nextMove);
            }

            System.out.println(board);
        }

    }
}