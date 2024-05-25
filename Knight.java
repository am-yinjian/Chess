// Written by Maggie Jiang, Jian0886
public class Knight {
    private int row;
    private int col;
    private boolean isBlack;
    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        int rowJump = Math.abs(endRow - row);
        int colJump = Math.abs(endCol - col);
        return (rowJump == 2 && colJump==1) || (rowJump == 1 && colJump == 2) && board.verifySourceAndDestination(row, col, endRow, endCol, isBlack);
    }

}