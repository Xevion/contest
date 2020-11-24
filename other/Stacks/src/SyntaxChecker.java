//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - 11 December 2020
//Class - Computer Science II PreAP
//Lab  - Lab 13B (SyntaxChecker.java)

import java.util.Stack;

import static java.lang.System.*;

public class SyntaxChecker {
    private String exp;
    private Stack<Character> symbols;

    private final char[] opening = {'[', '{', '(', '<'};
    private final char[] closing = {']', '}', ')', '>'};

    public SyntaxChecker() {
        exp = "";
    }

    public SyntaxChecker(String s) {
        exp = s;
    }

    public void setExpression(String s) {
        exp = s;
    }

    public boolean checkExpression() {
        // create stack, stack matching symbols on
        symbols = new Stack<Character>();
        for (int i = 0; i < exp.length(); i++) {
            char possibleSymbol = exp.charAt(i);
            switch (symbolType(possibleSymbol)) {
                case 0:
                    break;
                case 1:
                    symbols.add(possibleSymbol);
                    break;
                case 2:
                    // Find the index to find the correlating open symbol
                    int index = -1;
                    for (int j = 0; j < closing.length; j++) {
                        if (closing[j] == possibleSymbol)
                            index = j;
                    }

                    // If the closing symbol matches (and thus the array wasn't empty)
                    if (!symbols.isEmpty() && opening[index] == symbols.peek())
                        symbols.pop();
                    else
                        return false;
            }
        }

        return symbols.isEmpty();
    }

    /**
     * @param character The character to identify
     * @return A integer representing the character's symbol. 1 is open, 2 is closed, 0 is not a symbol.
     */
    private int symbolType(char character) {
        // Check if it's a opening character
        for (char c : opening) {
            if (character == c)
                return 1;
        }

        // Check if it's a closing character
        for (char c : closing) {
            if (character == c)
                return 2;
        }

        return 0;
    }
}