import static java.lang.System.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class problem4 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input.dat");
        Scanner read = new Scanner(input);
        int lines = read.nextInt();

        for(int i = 0; i < lines; i++) {
            read.nextLine();
            int[] orig = new int[3];
            int[] partial = new int[3];
            orig[0] = read.nextInt();
            orig[1] = read.nextInt();
            orig[2] = read.nextInt();
            partial[0] = read.nextInt();
            partial[1] = read.nextInt();
            out.println(process(orig, partial));
        }
    }

    static String process(int[] orig, int[] partial) {
        double multiplier = partial[0] / (double) orig[0];
        String output = "";
        partial[2] = (int)(orig[2] * multiplier);
        for(int sub : partial)
            output += sub + " ";
        return output;
    }
}
