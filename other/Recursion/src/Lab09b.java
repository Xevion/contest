//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - November 12th, 2020
//Class - Computer Science II Honors
//Lab  - Labs 09b

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.out;

public class Lab09b {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner input = new Scanner(new File("./src/lab09b.dat"));
        while (input.hasNextLine()) {
            Scanner line = new Scanner(input.nextLine());
            // Get inputs, begin counting at defined coordinate
            int r = line.nextInt();
            int c = line.nextInt();

            out.println(String.format("%d %d has %d @s connected.", r, c, AtCounter.countAts(r, c)));
            AtCounter.reset();
        }
    }
}