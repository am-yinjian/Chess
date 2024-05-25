
public class Queen {
    private int row;
    private int col;
    private boolean isBlack;
    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        return (board.verifyVertical(row, col, endRow, endCol)
                || board.verifyHorizontal(row, col, endRow, endCol)
                || board.verifyDiagonal(row, col, endRow, endCol)
                || board.verifyAdjacent(row, col, endRow, endCol))
                && board.verifySourceAndDestination(row, col, endRow, endCol, isBlack);

    }

}
