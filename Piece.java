import java.util.Scanner;
public class Piece {

    char character;
    int row;
    int col;
    boolean isBlack;

    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }


    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

 
    public boolean getIsBlack() {
        return isBlack;
    }

    
    public void promotePawn(int row, boolean isBlack) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your promotion: \n Queen \n Rook \n Knight \n Bishop");      //Gives player the options
        String upgCho = scanner.nextLine().trim().toLowerCase();        //Turns input into lowercase in case of uppercase inputs
        switch (upgCho) {
            case "queen":
                this.character = isBlack ? '\u265b' : '\u2655';
                break;
            case "rook":
                this.character = isBlack ? '\u265c' : '\u2656';
                break;
            case "knight":
                this.character = isBlack ? '\u265e' : '\u2658';
                break;
            case "bishop":
                this.character = isBlack ? '\u265d' : '\u2657';
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Queen.");
                this.character = isBlack ? '\u265b' : '\u2655';
                break;

        }
    }


    public String toString() {
        return String.valueOf(this.character);
    }

}
