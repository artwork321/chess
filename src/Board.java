public class Board {
    private Square[][] squares = new Square[8][8];
    private boolean isFinished;

    public Board() {
        this.isFinished = false;

        // Initialise Pawns and Empty squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 1) {
                    squares[i][j] = new Square(j, i, null);
                    squares[i][j].setPiece(new Pawn(squares[i][j], "White"));
                }
                else if (i == 6) {
                    squares[i][j] = new Square(j, i, null);
                    squares[i][j].setPiece(new Pawn(squares[i][j], "Black"));
                }
                else
                    squares[i][j] = new Square(j, i,null);
            }
        }

        // Initialise Knights
        squares[0][1] = new Square(1, 0,null);
        squares[0][1].setPiece(new Knight(squares[0][1], "White"));

        squares[0][6] = new Square(6, 0, null);
        squares[0][6].setPiece(new Knight(squares[0][6], "White"));

        squares[7][1] = new Square(1, 7, null);
        squares[7][1].setPiece(new Knight(squares[7][1], "Black"));

        squares[7][6] = new Square(6, 7, null);
        squares[7][6].setPiece(new Knight(squares[7][6], "Black"));

        // Initialise Bishops
        squares[0][2] = new Square(2, 0, null);
        squares[0][2].setPiece(new Bishop( squares[0][2], "White"));

        squares[0][5] = new Square(5, 0, null);
        squares[0][5].setPiece(new Bishop(squares[0][5], "White"));

        squares[7][2] = new Square(2, 7, null);
        squares[7][2].setPiece(new Bishop(squares[7][2], "Black"));

        squares[7][5] = new Square(5, 7, null);
        squares[7][5].setPiece( new Bishop(squares[7][5], "Black"));

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

    public Square getSquare(Square square) {
        return squares[square.getY()][square.getX()];
    }

    public String toString() {
        StringBuilder textBoard = new StringBuilder("|  0 |  1 |  2 |  3 |  4 |  5 |  6 |  7 |\n|");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                textBoard.append(squares[i][j].getPiece()).append("|");
            }
            if (i != 7)
                textBoard.append(" ").append(i).append("\n|");
            else
                textBoard.append(" ").append(i).append("\n");
        }

        return textBoard.toString();
    }
}
