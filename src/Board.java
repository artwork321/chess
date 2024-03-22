public class Board {
    private Square[][] squares = new Square[8][8];
    private boolean isFinished;

    public Board() {
        this.isFinished = false;

        // Initialise Pawns and Empty squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 1)
                    squares[i][j] = new Square(true, new Pawn(j ,i, "White"));
                else if (i == 6)
                    squares[i][j] = new Square(true, new Pawn(j ,i, "Black"));
                else
                    squares[i][j] = new Square(false);
            }
        }

        // Initialise Knights
        squares[0][1] = new Square(true, new Knight(1 ,0, "White"));
        squares[0][6] = new Square(true, new Knight(6 ,0, "White"));
        squares[7][1] = new Square(true, new Knight(1 ,7, "Black"));
        squares[7][6] = new Square(true, new Knight(6 ,7, "Black"));

        // Initialise Bishops

        // Initialise Queen

        // Initialise Rocks

        // Initialise King
    }

    /**
     *
     * @param isFinished
     */
    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
    public Square[][] getSquares() {
        return squares;
    }

    public String toString() {
        String textBoard = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                textBoard = textBoard + squares[i][j].getPiece() + "|";
            }
            textBoard += "\n";
        }

        return textBoard;
    }
}
