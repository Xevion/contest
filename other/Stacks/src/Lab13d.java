//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - 11 December 2020
//Class - Computer Science II PreAP
//Lab  - Lab 13D

import static java.lang.System.out;

public class Lab13d {
    public static void main(String[] args) {
        IntStack test = new IntStack();
        test.push(5);
        test.push(7);
        test.push(9);
        System.out.println(test);
        System.out.println(test.isEmpty());
        System.out.println(test.pop());
        System.out.println(test.peek());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.isEmpty());
        System.out.println(test);
    }
}