import static java.lang.System.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class problem8 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input.dat");
        Scanner read = new Scanner(input);
        read.nextLine();
        while(read.hasNextInt()) {
            String result = "";
            int n = read.nextInt();
            for(int x = 1; x < n + 1; x++) {
                result += multiply(" ", n - x) + multiply("C", x) + "\n";
            }
            out.println(result);
        }
    }

    static String multiply(String str, int n) {
        String output = "";
        for(int i = 0; i < n; i++) {
            output += str;
        }
        return output;
    }
}