
import java.util.Scanner;
public class Game {
    private static boolean blackTurn = false;           //Start with white turn

    public static void main(String[] args){
        Scanner move = new Scanner(System.in);
        Board board = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
        while (board.isGameOver()) {               //While game isn't over
            System.out.println(board);
            String playerColor = blackTurn ? "Black" : "White";
            System.out.println("It is currently " + playerColor + "'s turn to play.");      //Announce whose turn it is
            System.out.println("What is your move? (format: [start row] [start col] [end row] [end col]");      //Ask for player move
            String moveInput = move.nextLine();
            String[] moveInputParts = moveInput.split(" ");         //Split string into 4 parts
            if (moveInputParts.length != 4) {
                System.out.println("Invalid Move");
                continue;
            }
            int startRow = Integer.parseInt(moveInputParts[0]);         //Changes the string into intergers
            int startCol = Integer.parseInt(moveInputParts[1]);
            int endRow = Integer.parseInt(moveInputParts[2]);
            int endCol = Integer.parseInt(moveInputParts[3]);
            Piece startPiece = board.getPiece(startRow, startCol);      //Assign the input to a piece
            boolean moveSuccess = false;

            if (startPiece != null && startPiece.getIsBlack() == blackTurn) {
                moveSuccess = board.movePiece(startRow, startCol, endRow, endCol);
                if (blackTurn && endRow == 7 && startPiece.character == '\u265f') {             //Promotes black pawn if it reaches Row 7
                    startPiece.promotePawn(endRow, true);
                }else
                if (startPiece.character == '\u2659' && endRow == 0 && !blackTurn){             //Promotes white pawn if it reaches Row 0
                    startPiece.promotePawn(endRow, false);
                }
                if (!moveSuccess) {     //If invalid move then tells player to retry
                    System.out.println("Invalid move");
                    continue;
                }
                blackTurn = !blackTurn;                         //Changes to opposite side turn when move is successful
            } else {
                System.out.println("Invalid piece");                //If they try to use other opponite piece or if piece is null
            }
        }
        System.out.println(board);                  //When game ends
        String winnerColor = blackTurn ? "White" : "Black";
        System.out.println("Game Over");
        System.out.println(winnerColor + " has won the game!!!");
        move.close();
    }
}
