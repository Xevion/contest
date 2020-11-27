//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - November 12th, 2020
//Class - Computer Science II Honors
//Lab  - Labs 09a

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.*;

public class Lab09a {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("./src/lab09a.dat"));

        while (input.hasNextLine()) {
            Scanner line = new Scanner(input.nextLine());
            int a = line.nextInt();
            int b = line.nextInt();

            out.println(
                    String.format(
                            "%-16s ----- %d", // Right pad spaces 16 characters
                            String.format("GCF(%d, %d)", a, b),
                            GCF.gcf(a, b) // Calculate GCF recursively
                    )
            );
        }
    }
}