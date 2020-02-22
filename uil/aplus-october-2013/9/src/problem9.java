import static java.lang.System.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class problem9 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input.dat");
        Scanner read = new Scanner(input);

        while (read.hasNextInt()) {
            int x = read.nextInt(); int y = read.nextInt();
            boolean isSexy = (Math.abs(x - y) == 6) && (isPrime(x) && isPrime(y));
            out.println((isSexy) ? "SEXY" : "NOT");
        }
    }

    // Super crap prime function, could be optimized as hell but whatever
    static boolean isPrime(int n) {
        for(int x = n - 1; x > 1; x-=1) {
            if(n % x == 0)
                return false;
        }
        return true;
    }
}
