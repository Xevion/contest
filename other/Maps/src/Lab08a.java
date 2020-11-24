//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - October 1st, 2020
//Class - Computer Science II AP
//Lab  - Maps 08a

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.*;

public class Lab08a
{
    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("./src/lab08a.dat"));
        int lines = file.nextInt(); // Number of entries (before text to translate)
        file.nextLine();

        // Put all spanish/english words inside Map
        SpanishToEnglish test = new SpanishToEnglish();
        for (int i = 0; i < lines; i++)
            test.putEntry(file.nextLine());


        out.println("\n====\tMAP CONTENTS\t====\n\n");
        // Print custom Map wrapper contents
        out.println(test + "\n\n");

        // Translate text
        while(file.hasNextLine())
            out.println(test.translate(file.nextLine()));
    }
}