//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - 11 December 2020
//Class - Computer Science II PreAP
//Lab  - Lab 13C (PostFix.java)

import java.util.Stack;
import java.util.Scanner;

import static java.lang.System.*;

public class PostFix {
    private Stack<Double> stack;
    private String expression;

    public PostFix() {
        expression = "";
        stack = new Stack<Double>();
    }

    public PostFix(String exp) {
        expression = exp;
    }

    public void setExpression(String exp) {
        expression = exp;
    }

    public double calc(double one, double two, char op) {
        switch (op) {
            case '+':
                return one + two;
            case '-':
                return one - two;
            case '*':
                return one * two;
            case '/':
                return one / two;
        }
        return 0.0;
    }

    public void solve() {
        stack = new Stack<Double>();
        for (char next : expression.replace(" ", "").toCharArray()) {
            if (isOperator(next)) {
                double second = stack.pop();
                double first = stack.pop();
                stack.push(calc(first, second, next));
            } else {
                stack.push((double) Integer.parseInt(String.valueOf(next)));
            }
        }
    }

    private boolean isOperator(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/';
    }

    @Override
    public String toString() {
        return expression + " = " + stack.peek();
    }
}