import static java.lang.System.out;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("play.dat"));
    while(s.hasNextInt()) {
        int magnitude = s.nextInt();
        out.println(play(magnitude));
        if(s.hasNextInt())
            out.println();
    }
  }

  // 3 =>     1 3 5 3 1       (5)
  // 4 =>   1 3 5 7 5 3 1     (7)
  // 5 => 1 3 5 7 9 7 5 3 1   (9)
  // https://www.desmos.com/calculator/0gszz81jpx
  // m = 2x - 1 (top)
  // y = m - 2 * abs(x - ((m + 1) / 2))
  public static String play(int mag) {
      int[] counts = new int[(mag * 2) - 1];
      String[] sequence = new String[counts.length];

    for(int i = 1; i <= counts.length; i++)
        counts[i - 1] = counts.length - (2 * Math.abs(i - (counts.length + 1) / 2));
    for(int i = 0; i < counts.length; i++)
        sequence[i] = repeat("*", counts[i]);
    
    return String.join("\n", sequence);
  }

  public static String repeat(String sub, int n) {
    String result = "";
    for(int i = 0; i < n; i++)
        result += sub;
    return result;
  }
}