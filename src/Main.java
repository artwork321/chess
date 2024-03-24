import board.TextBaseChess;
import gui.ChessTable;

public class Main {
    public static void main(String[] args) {
        ChessTable table = new ChessTable();

        TextBaseChess game = new TextBaseChess();
        game.run();
    }

}