//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - 11 December 2020
//Class - Computer Science II PreAP
//Lab  - Lab 13C

import static java.lang.System.out;

public class Lab13c
{
	public static void main ( String[] args )
	{
		String sampleData = "2 7 + 1 2 + +\n" +
				"1 2 3 4 + + +\n" +
				"9 3 * 8 / 4 +\n" +
				"3 3 + 7 * 9 2 / +\n" +
				"9 3 / 2 * 7 9 * + 4 -\n" +
				"5 5 + 2 * 4 / 9 +\n";

		PostFix postFix = new PostFix();
		for (String line : sampleData.split("\n")) {
			postFix.setExpression(line);
			postFix.solve();
			out.println(postFix);
		}
	}
}