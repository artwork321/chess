
public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        System.out.println(board);

        board.getSquares()[2][2].getPiece().findAllNextMove(board);

        System.out.println(board.getSquares()[2][2].getPiece().getAllMove());
    }
}