import static java.lang.System.out;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("climb.dat"));
        for(int i = s.nextInt(); i > 0; i--) {
            int up = s.nextInt();
            int down = s.nextInt();
            int hole = -1 * s.nextInt();

            int attempts = 0;
            int traverse = 0;
            while(hole < 0) {
                attempts++;
                hole += up;
                traverse += up;
                if(hole < 0) {
                    hole -= down;
                    traverse += down;
                }
            }

            out.println(String.format("%s %s", attempts, traverse));
        }
    }
}