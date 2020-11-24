//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - 11 December 2020
//Class - Computer Science II PreAP
//Lab  - Lab 13B

import java.util.Locale;

import static java.lang.System.out;

public class Lab13b {
    public static void main(String[] args) {
        String sampleData = "(abc(*def) \n" +
                "[{}]\n" +
                "[\n" +
                "[{<()>}]\n" +
                "{<html[value=4]*(12)>{$x}}\n" +
                "[one]<two>{three}(four)\n" +
                "car(cdr(a)(b)))\n" +
                "car(cdr(a)(b))\n";

        SyntaxChecker sc = new SyntaxChecker();
        for (String line : sampleData.split("\n")) {
            sc.setExpression(line);
            out.println(String.format(Locale.ENGLISH, "%s is %s", line, sc.checkExpression() ? "correct" : "incorrect"));
        }
    }
}