import static java.lang.System.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
 import java.util.List;

class X implements Comparable<X> {
    String str;
    X(String str) {
        this.str = str;
    }
    public int compareTo(X other) {
        return str.length() == other.str.length()
                ? str.compareTo(other.str)
                : other.str.length() - str.length();
    }
    public String toString() {
        return this.str;
    }
    public int[] getValues() {
        int[] values = new int[this.str.length()];
        for(int i = 0; i < this.str.length(); i++) {
            values[i] = (int) this.str.charAt(i);
        }
        return values;
    }
    public int getSum() {
        int sum = 0;
        for(int val : this.getValues()) {
            sum += val;
        }
        return sum;
    }
}

class question26 {
    public static void main(String[] args) {
        List<X> w = new ArrayList<>();
        String str = "Hi this is the right answer";
        for(String sub : str.split("\\s"))
            w.add(new X(sub));
        Collections.sort(w);
        out.println(w);
        out.println(Arrays.toString(new X("Hello").getValues()));
    }
}