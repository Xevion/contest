//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - 15 September 2020
//Class - Computer Science II PreAP
//Lab  - Sets 07a

import static java.lang.System.out;

public class Lab07a
{
    public static void main(String[] args)
    {
        String list = "a b c d e f g h a b c d e f g h i j k";
        System.out.println("Original List : " + list);
        out.println("Uniques : " + UniquesDupes.getUniques(list));
        out.println("Dupes : " + UniquesDupes.getDupes(list) + "\n\n");
    }
}