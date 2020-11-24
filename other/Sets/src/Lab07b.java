//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - 15 September 2020
//Class - Computer Science II PreAP
//Lab  - Sets 07b

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.System.out;

public class Lab07b
{
    public static void main(String[] args) throws IOException
    {
        // Get input data
        Scanner scanner = new Scanner(new File("./src/lab07b.dat"));
        ArrayList<Integer> input = new ArrayList<>();
        while (scanner.hasNextInt()) input.add(scanner.nextInt());

        // Instantiate output arrays
        Set<Integer> evens = new TreeSet<>();
        Set<Integer> odds = new TreeSet<>();
        Set<Integer> perfects = new TreeSet<>();

        // Read and separate
        for (Integer number : input) {
            if (number % 2 == 0)
                evens.add(number);
            else
                odds.add(number);

            if (isPerfect(number))
                perfects.add(number);
        }

        out.println("ODDS: " + odds + "\nEvens: " + evens + "\nPerfects: " + perfects);
    }

    public static boolean isPerfect(int number)
    {
        int sum = 0;
        Set<Integer> divisors = getDivisors(number);
        for (Integer divisor : divisors) sum += divisor;
        return number == sum;
    }

    public static Set<Integer> getDivisors(int number)
    {
        Set<Integer> divisors = new TreeSet<>();
        divisors.add(1);

        for (int i = 2; i <= Math.sqrt(number); i++) {
            double quotient = number / (float) i;
            if (quotient % 1 == 0) {
                divisors.add(i);
                divisors.add((int) quotient);
            }
        }

        return divisors;
    }
}