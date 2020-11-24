//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - 11 December 2020
//Class - Computer Science II PreAP
//Lab  - Lab 13D (IntStack.java)

import java.util.ArrayList;

public class IntStack {
    private ArrayList<Integer> fakeStack;

    public IntStack() {
        fakeStack = new ArrayList<Integer>();
    }

    public void push(int item) {
        fakeStack.add(item);
    }

    public int pop() {
        return fakeStack.remove(fakeStack.size() - 1);
    }

    public boolean isEmpty() {
        return fakeStack.size() == 0;
    }

    public int peek() {
        return fakeStack.get(fakeStack.size() - 1);
    }

    public String toString() {
        return fakeStack.toString();
    }
}