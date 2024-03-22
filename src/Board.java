public class Board {
    private Square[][] squares = new Square[8][8];
    private boolean isFinished;

    public Board() {
        this.isFinished = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 2)
                    squares[i][j] = new Square(true, new Pawn(j ,i));
                else
                    squares[i][j] = new Square(false);
            }
        }
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
