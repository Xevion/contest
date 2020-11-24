//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - 11 December 2020
//Class - Computer Science II PreAP
//Lab  - Lab 13A

import java.util.Scanner;
import java.util.Stack;

import static java.lang.System.out;

public class Lab13a {
    public static void main(String[] args) {
        String sampleData = "a b c d e f g h i\n" +
                "1 2 3 4 5 6 7 8 9 10\n" +
                "# $ % ^ * ( ) ) _\n";

        for (String line : sampleData.split("\n")) {
            // Read in the scanner data, place into stack
            Scanner scanner = new Scanner(line);
            Stack<String> stack = new Stack<String>();
            while (scanner.hasNext())
                stack.add(scanner.next());

            // Extract all items from the stack, build a new string
            StringBuilder stackOut = new StringBuilder(stack.size() * 2);
            while (!stack.isEmpty()) {
                stackOut.append(stack.pop());
                stackOut.append(" ");
            }

            out.println(stackOut.toString());
        }

        out.println();
    }
}
