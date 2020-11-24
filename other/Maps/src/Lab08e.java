//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - October 2nd, 2020
//Class - Computer Science II PreAP
//Lab  - Maps 08e

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.*;

public class Lab08e
{
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(new File("./src/lab08e.dat"));
        int lines = scanner.nextInt(); // # of input lines
        scanner.nextLine(); // Flush input

        // Insert acronym inputs
        Acronyms acronyms = new Acronyms();
        for (int i = 0; i < lines; i++)
            acronyms.putEntry(scanner.nextLine());


        // Print current acronym object state
        out.println("====    MAP CONTENTS    ====\n\n");
        out.println(acronyms + "\n\n");


        // Translate the rest of the input
        out.println("====    READ LINES      ====\n\n");
        while (scanner.hasNextLine())
            out.println(acronyms.convert(scanner.nextLine()));
    }
}