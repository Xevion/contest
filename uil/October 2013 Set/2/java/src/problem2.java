import static java.lang.System.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class problem2 {
    public static void main(String[] args ) throws FileNotFoundException {
        // Constants
        File input = new File("input1.dat");
        Scanner read = new Scanner(input);
        int lines = read.nextInt();
        read.nextLine();
        // Driver Code
        for (int i = 0; i < lines; i++)
            out.println(read.nextLine().length() <= 140 ? "tweet" : "not a tweet");
    }

}