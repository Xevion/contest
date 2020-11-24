//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - 15 September 2020
//Class - Computer Science II PreAP
//Lab  - Sets 07c

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.System.out;

public class Lab07c
{
	public static void main(String[] args) throws IOException
	{
		// Get input data
		Scanner scanner = new Scanner(new File("./src/lab07b.dat"));
		while (scanner.hasNextLine()) {
			Set<Integer> a = getIntegerSet(scanner.nextLine());
			Set<Integer> b = getIntegerSet(scanner.nextLine());

			out.println("Set one: " + a + "\nSet two: " + b);
			out.println("\nunion - " + union(a, b));
			out.println("intersection - " + intersection(a, b));
			out.println("difference A-B - " + difference(a, b));
			out.println("difference B-A - " + difference(b, a));
			out.println("symmetric difference - " + symmetric(a, b));

			// Don't add unnecessary newline on the final sets processing
			if(scanner.hasNextLine())
				out.println();
		}
	}

	/**
	 * @param line A string containing integers
	 * @return a Set containing parsed integers from the given String
	 */
	public static Set<Integer> getIntegerSet(String line) {
		Scanner scanner = new Scanner(line);
		Set<Integer> set = new TreeSet<>();
		while (scanner.hasNextInt())
			set.add(scanner.nextInt());
		return set;
	}

	/**
	 * @return Integers that are in either set.
	 */
	public static Set<Integer> union(Set<Integer> a, Set<Integer> b) {
		Set<Integer> union = new TreeSet<>(a);
		union.addAll(b);
		return union;
	}

	/**
	 * @return Integers that are in both Sets
	 */
	public static Set<Integer> intersection(Set<Integer> a, Set<Integer> b) {
		Set<Integer> intersection = new TreeSet<>(a);
		intersection.retainAll(b);
		return intersection;
	}

	/**
	 * @return Integers that are in Set A but not Set B
	 */
	public static Set<Integer> difference(Set<Integer> a, Set<Integer> b) {
		Set<Integer> difference = new TreeSet<>(a);
		difference.removeAll(b);
		return difference;
	}

	/**
	 * @return Integers that appear in either set but NOT both.
	 */
	public static Set<Integer> symmetric(Set<Integer> a, Set<Integer> b) {
		Set<Integer> symmetric = new TreeSet<>(union(a, b));
		symmetric.removeAll(intersection(a, b));
		return symmetric;
	}
}