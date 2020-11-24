//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - October 1st, 2020
//Class - Computer Science II AP
//Lab  - Maps 08a (SpanishToEnglish)

import java.util.*;

public class SpanishToEnglish
{
    private Map<String, String> pairs;

    public SpanishToEnglish()
    {
        pairs = new TreeMap<>();
    }

    public void putEntry(String entry)
    {
        // Split and place inside Map
        String[] list = entry.split("\\w+");
        pairs.put(list[0], list[1]);
    }

    public String translate(String sent)
    {
        ArrayList<String> words = new ArrayList<>();
        Scanner chop = new Scanner(sent);

        // Translate each word in the dictionary,
        // otherwise default to the original word if not present.
        while (chop.hasNext()) {
            String next = chop.next();
            words.add(pairs.getOrDefault(next, next));
        }

        // Join all owrds into single string with single space
        return String.join(" ", words);
    }

    public String toString()
    {
        // Use Maps .toString, but replace commas with newlines
        return pairs.toString().replaceAll(",", "\n");
    }
}