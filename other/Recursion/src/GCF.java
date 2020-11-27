//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - November 12th, 2020
//Class - Computer Science II Honors
//Lab  - Labs

public class GCF {
    public static int gcf(int n1, int n2) {
        if (n2 != 0)
            return gcf(n2, n1 % n2);
        return n1;
    }
}