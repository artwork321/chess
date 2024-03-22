public class Move {

    private int x;
    private int y;
    private boolean isAttack;

    public Move(int x, int y, boolean isAttack) {
        this.x = x;
        this.y = y;
        this.isAttack = isAttack;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }

    public static boolean isValidMove(int x, int y) {
        return x <= 7 && y <= 7 && x >= 0 && y >= 0;
    }

    public boolean isValidMove() {
        return x <= 7 && y <= 7 && x >= 0 && y >= 0;
    }

    public String toString() {
        return "(" + x +", " +  y + ")";
    }

}
