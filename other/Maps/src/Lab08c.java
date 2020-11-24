//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - October 2nd, 2020
//Class - Computer Science II PreAP
//Lab  - Maps 08c

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;

public class Lab08c
{
    public static void main(String args[]) throws IOException
    {
        Scanner scanner = new Scanner(new File("./src/lab08c.dat"));
        int lines = scanner.nextInt(); // Number of inputs
        scanner.nextLine(); // Flush to next line

        Relatives relatives = new Relatives();

        // Input all relationships into Relatives object
        for (int i = 0; i < lines; i++)
            relatives.put(scanner.nextLine());

        // Print out all relationships asked
        while (scanner.hasNextLine())
            out.println(relatives.get(scanner.nextLine()));

        // out.println("\n\n" + relatives);
    }
}