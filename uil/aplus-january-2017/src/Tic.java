import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class TTCBoard
{
    int[][] tiles = new int[3][3];

    TTCBoard()
    {
    }

    /**
     * Places a move on the board.
     *
     * @param x      The X position.
     * @param y      The Y position.
     * @param player True for player X, False for player O
     */
    public void move(int x, int y, boolean player)
    {
        tiles[y][x] = player ? 1 : 2;
    }

    /**
     * @return Returns a integer representing the state of the board.
     */
    public int check()
    {
        // Horizontals
        for (int y = 0; y < 3; y++) {
            int initial = tiles[y][0];
            if (initial != 0 && initial == tiles[y][1] && initial == tiles[y][2])
                return initial == 1 ? 1 : 2;
        }

        // Verticals
        for (int x = 0; x < 3; x++) {
            int initial = tiles[0][x];
            if (initial != 0 && initial == tiles[1][x] && initial == tiles[2][x])
                return initial == 1 ? 1 : 2;
        }

        int initialTLBR = tiles[0][0];
        if (initialTLBR != 0 && initialTLBR == tiles[1][1] && initialTLBR == tiles[2][2])
            return initialTLBR == 1 ? 1 : 2;

        int initialTRBL = tiles[2][0];
        if (initialTRBL != 0 && initialTRBL == tiles[1][1] && initialTRBL == tiles[1][2])
            return initialTRBL == 1 ? 1 : 2;

        // If any tiles are empty, game is incomplete
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (tiles[y][x] == 0)
                    return 4;
            }
        }

        return 3;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 5; y++) {
            if (y % 2 == 0) {
                for (int x = 0; x < 5; x++) {
                    // Vertical line
                    if (x % 2 == 1) {
                        sb.append('|');
                    } else {
                        int place = tiles[y / 2][x / 2];
                        sb.append(getSymbol(place));
                    }
                }
            } else {
                for (int i = 0; i < 5; i++)
//                    sb.append(i % 2 == 1 ? "+" : "-");
                    sb.append('-');
            }

            if (y < 4)
                sb.append("\n");
        }
        return sb.toString();
    }

    public static String getSymbol(int place)
    {
        switch (place) {
            case 1:
                return "X";
            case 2:
                return "O";
            default:
                return " ";
        }
    }

    public String translate(int check)
    {
        switch (check) {
            case 1:
                return "X wins!";
            case 2:
                return "Y wins!";
            case 3:
                return "Tie Game!";
            case 4:
                return "Incomplete";
            default:
                return "Invalid";
        }
    }
}

public class Tic
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File("tic.dat"));
        int sets = in.nextInt();
        for (int i = 0; i < sets; i++) {
            int moves = in.nextInt(); // Number of move inputs
            TTCBoard board = new TTCBoard(); // Board which stores and manipulates state
            boolean player = true; // Which player is going

            // Scan in all moves and manipulate board state
            for (int j = 0; j < moves; j++) {
                // Get the position, make the move
                int y = in.nextInt();
                int x = in.nextInt();
                board.move(x, y, player);

                // Alternate current player
                player = !player;
            }

            System.out.println(board);
            System.out.println(board.translate(board.check()));
            System.out.println();
        }
    }
}