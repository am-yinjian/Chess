
import java.util.HashMap;
import java.util.Map;
public class Fen {
    /**
     * Method for populating a Board object with chess
     * pieces based on the FEN code passed in.
     *
     * @param fen A FEN code string. Must not include movement commands or erroneous characters
     * @param b A Board object to load with chess position
     */
    public static void load(String fen, Board b) {
        int rank = 0;   // Rank or row of the board
        int square = 0; // Square in 'rank'
        char query;     // Temp holder for current char

        // begin hashmap solution
        Character[][] keysVals = new Character[][] {
                {'p', '\u265f'}, {'P', '\u2659'}, {'r', '\u265c'}, {'R', '\u2656'}, {'n', '\u265e'}, {'N', '\u2658'},
                {'b', '\u265d'}, {'B', '\u2657'}, {'q', '\u265b'}, {'Q', '\u2655'}, {'k', '\u265a'}, {'K', '\u2654'}
        };

        Map<Character, Character> map = new HashMap<>();

        for (Character[] keyVal: keysVals) {
            map.put(keyVal[0], keyVal[1]);
        }

        // Iterate over FEN code chars, updating Board object accordingly
        for(int i = 0; i < fen.length(); i++) {
            query = fen.charAt(i);

            if(query == '/') { // If a '/': proceed to next row
                rank++;
                square = 0;
            } else if (Character.isDigit(query)) { // If a digit, n: proceed n squares forward
                square += Character.getNumericValue(query);
            } else { // Consult the map
                b.setPiece(rank, square, new Piece(map.get(query), rank, square++, !Character.isUpperCase(query)));
            }
        }
    }
}
