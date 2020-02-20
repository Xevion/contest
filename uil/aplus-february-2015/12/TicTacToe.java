import static java.lang.System.out;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;

class Board {
    private String[][] board;
    
    Board(String input) {
        this.board = new String[3][3];
        for(int i = 0; i < 9; i++)
            this.board[i / 3][i % 3] = input.substring(i, i + 1);
    }
    
    // [[A, B, C], [D, E, F], [G, H, I]]
    // A B C | 0,0 1,0 2,0
    // D E F | 0,1 1,1 2,1
    // G H I | 0,2 1,2 2,2

    public String[] vertical(int x) {
        return this.board[x];
    }
    public String[] horizontal(int y) {
        String[] vert = new String[3];
        for(int x = 0; x < 3; x++)
            vert[x] = this.board[x][y];
        return vert;
    }

    public String[] diagonal(boolean rising) {
        String[] diag = new String[3];
        if(rising) {
            for(int i =  0; i < 3; i++)
                diag[i] = this.board[2 - i][2 - i];
        } else {
            for(int i = 0; i < 3; i++)
                diag[i] = this.board[i][i];
        }
        return diag;
    }

    public boolean equal(String[] line) {
        return line[0].equals(line[1]) && line[1].equals(line[2]) && !line[0].equals("*");
    }

    public String solve() {
        // Verticals
        for(int x = 0; x < 3; x++)
            if(this.equal(this.vertical(x)))
                return this.vertical(x)[0];
        // Horizontals
        for(int y = 0; y < 3; y++)
            if(this.equal(this.horizontal(y)))
                return this.horizontal(y)[0];
        // Diagonals
        if(this.equal(this.diagonal(false)))
            return this.diagonal(false)[0];
        if(this.equal(this.diagonal(true)))
            return this.diagonal(true)[0];
        
        // Check for incompletion
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                if(this.board[x][y].equals("*"))
                    return "INC";
            }
        }
        
        // Tie
        return "TIE";
    }

    public String toString() {
        return String.format("Board(%s)", Arrays.deepToString(this.board));
    }
}

class Main {
  public static void main(String[] args) throws IOException {
      Scanner s = new Scanner(new File("tictactoe.dat"));
      s.nextLine();
      while(s.hasNextLine()) {
            String input = s.nextLine();
            Board board = new Board(input);
            out.println(board.solve());
      }
  }
}