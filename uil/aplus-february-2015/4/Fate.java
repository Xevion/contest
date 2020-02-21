import static java.lang.System.out;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.io.File;
import java.io.IOException;

class Hand {
    private ArrayList<String> hand;

    Hand() {hand = new ArrayList<String>();}
    Hand(String[] initial) {
        this();
        for(String card : initial)
            hand.add(card);
    }

    public void print() {
        Collections.sort(this.hand);
        out.println(String.join(" ", this.hand));
    }

    public void add(String card) {
        this.hand.add(card);
        // Begin process to remove a card from hand if exceeding 5 card limit
        if(this.hand.size() > 5) {
            HashMap<String, Integer> map = new HashMap<String, Integer>();
        
            // Count each card into map
            for(String next : this.hand)
                if(map.get(next) != null)
                    map.put(next, map.get(next) + 1);
                else
                    map.put(next, 1);
            
            // Search for what card to remove
            int min = 0;
            String mintype = "";
            for(String type : map.keySet()) {
                int cur = map.get(type);
                // if found is less, or found is equal, but foundtype is of less value than curmintype
                if(cur < min || (cur == min && !mintype.equals(type) && type.compareTo(mintype) < 0) || mintype.equals("")) {
                    min = cur;
                    mintype = type;
                }
            }

            // Remove card from map
            hand.remove(mintype);
        }
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("fate.dat"));
        while(s.hasNextLine()) {
            String input = s.nextLine();
            Scanner r = new Scanner(input);
            
            Hand hand = new Hand();
            while(r.hasNext())
                hand.add(r.next());
            hand.print();
        }
    }
}