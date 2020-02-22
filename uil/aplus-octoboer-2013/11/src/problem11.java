import static java.lang.System.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class problem11 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input.dat");
        Scanner read = new Scanner(input);
        while(read.hasNextInt())
            out.println(read.nextInt() * 3);
        read.close();
    }
}