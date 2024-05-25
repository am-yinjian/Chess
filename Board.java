// Edited by Maggie Jiang, Jian0886

public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO*:
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];                        //Set to length of a regular chess board
    }

    // Accessor Methods

    //TODO*:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        if (board[row][col] != null){
            return board[row][col];
        }
        return null;
    }

    //TODO*:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        piece.setPosition(row, col);
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO*:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. A constraint of a legal move is:
    // - there exists a Piece at (startRow, startCol) and the destination square is seizable.
    // Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move is legal,
    // and to execute the move if it is.
    // Your Game class should not directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece.
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

    //TODO*:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
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

    //TODO*:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }
    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    // - where 'start' = (startRow, startCol) and 'end' = (endRow, endCol)
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

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        return Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
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

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
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


    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
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

