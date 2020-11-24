//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - October 1st, 2020
//Class - Computer Science II PreAP
//Lab  - Maps 08b

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.out;

public class Lab08b
{
	public static void main(String[] args) throws IOException
	{
		Scanner scanner = new Scanner(new File("./src/lab08b.dat"));

		// Read all test cases
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();

			// Process and print histogram
			Histogram histogram = new Histogram(line);
			out.println(histogram);

			// Print extra two lines between Histograms
			if(scanner.hasNextLine())
				out.print("\n\n");
		}
	}
}