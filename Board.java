

public class Board {

    // Instance variables
    private Piece[][] board;

  \
    public Board() {
        board = new Piece[8][8];                        //Set to length of a regular chess board
    }

   
    public Piece getPiece(int row, int col) {
        if (board[row][col] != null){
            return board[row][col];
        }
        return null;
    }

 
    public void setPiece(int row, int col, Piece piece) {
        piece.setPosition(row, col);
        board[row][col] = piece;
    }


    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece curP = board[startRow][startCol];
        if (curP != null && curP.isMoveLegal(this, endRow, endCol)) {
            board[startRow][startCol] = null;
            board[endRow][endCol] = curP;
            curP.setPosition(endRow, endCol);
            return true;
        }
        return false;
    }


    public boolean checkWKing(){            //Helper to check if there exist a White King return true if yes false, if no
        for (int i = 0; i < 8; i ++){
            for (int j = 0; j< 8; j++){
                Piece curP = board[i][j];
                if (curP != null && !curP.getIsBlack() && curP.character == '\u2654') {
                    return true;
                }
            }
        }return false;
    }

    public boolean checkBKing(){                //Helper to check if there exist a Black King return true if yes false, if no
        for (int i = 0; i < 8; i ++){
            for (int j = 0; j< 8; j++){
                Piece curP = board[i][j];
                if (curP != null && curP.getIsBlack() && curP.character == '\u265a') {
                    return true;
                }
            }
        }return false;
    }
    public boolean isGameOver() {
        return (checkBKing() && checkWKing());
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append("  ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2003|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }


    public void clear() {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }
    }


    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        //Check bounds
        if (!(0 <= startRow && startRow <= 7 && 0 <= startCol && startCol <= 7 && 0 <= endRow && endRow <= 7 && 0 <= endCol && endCol <= 7)) {
            return false;
        }
        Piece start = board[startRow][startCol];
        Piece end = board[endRow][endCol];
        if (start == null){             //See if there's a piece at start
            return false;
        }
        if (end != null && end.getIsBlack() == isBlack) {               //Check if end piece is null or other color
            return false;
        }
        if(start.getIsBlack() != isBlack){
            return false;
        }
        return true;
    }


    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        return Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1;
    }


    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow) {
            return false;
        }else if(startCol == endCol){
            return true;
        }
        else if (startCol > endCol) {           //Moving to the left
            for (int i = startCol - 1 ; i > endCol; i--) {
                if (board[startRow][i] != null) {
                    return false;
                }
            }
        } else if (startCol < endCol) {         //Moving to the right
            for (int i = startCol + 1; i < endCol; i++) {
                if (board[startRow][i] != null) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) {
            return false;
        } else if (startRow == endRow) {
            return true;
        } else if (startRow > endRow) {         //Moving Down
            for (int i = startRow - 1; i > endRow; i--) {
                if (board[i][startCol] != null) {
                    return false;
                }
            }
        } else if (startRow < endRow) {         //Moving Up
            for (int i = startRow + 1; i < endRow; i++) {
                if (board[i][startCol] != null) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        boolean diagonal = Math.abs(startCol - endCol) == Math.abs(startRow - endRow);
        boolean isNull = true;
        if (startRow < endRow && startCol < endCol) { // Checks down right direction
            int row = startRow + 1;
            int col = startCol + 1;
            while (row < endRow && col < endCol) {
                if (board[row][col] != null) {
                    isNull = false;
                    break;
                }
                row++;
                col++;
            }
        } else if (startRow > endRow && startCol < endCol) { // Checks up right direction
            int row = startRow - 1;
            int col = startCol + 1;
            while (row > endRow && col < endCol) {
                if (board[row][col] != null) {
                    isNull = false;
                    break;
                }
                row--;
                col++;
            }
        } else if (startRow > endRow && startCol > endCol) { // Checks left up direction
            int row = startRow - 1;
            int col = startCol - 1;
            while (row > endRow && col > endCol) {
                if (board[row][col] != null) {
                    isNull = false;
                    break;
                }
                row--;
                col--;
            }
        } else if (startRow < endRow && startCol > endCol) { // Checks left down direction
            int row = startRow + 1;
            int col = startCol - 1;
            while (row < endRow && col > endCol) {
                if (board[row][col] != null) {
                    isNull = false;
                    break;
                }
                row++;
                col--;
            }
        }
        return isNull && diagonal;
    }
}

