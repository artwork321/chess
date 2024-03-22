import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        String instruction;
        int x = 4;
        int y = 6;
        System.out.println(board);

        while(true) {
            board.getSquares()[y][x].getPiece().findAllNextMove(board);
            System.out.println(board.getSquares()[y][x].getPiece().getAllMove());

            int i = scanner.nextInt();

            scanner.nextLine();
            Move nextMove = board.getSquares()[y][x].getPiece().getAllMove().get(i);
            board.getSquares()[y][x].getPiece().move(board, nextMove);

            System.out.println(board);

            y = nextMove.getY();
            x = nextMove.getX();
        }

    }
}