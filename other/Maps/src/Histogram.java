//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - October 2nd, 2020
//Class - Computer Science II PreAP
//Lab  - Maps 08b (Histogram)

import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Histogram
{
    private Map<String, Integer> histogram;
    private int minSpacing;

    public Histogram()
    {
        histogram = new TreeMap<>();
    }

    public Histogram(String sent, int minSpacing)
    {
        this();
        this.loadSentence(sent);
        this.minSpacing = minSpacing;
    }

    public Histogram(String sent)
    {
        this(sent, 4);
    }


    public void loadSentence(String sent)
    {
        Scanner scanner = new Scanner(sent);
        while (scanner.hasNext()) {
            String next = scanner.next();
            // Start with 0 if not found, increment
            histogram.put(next, histogram.getOrDefault(next, 0) + 1);
        }
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder();
        output
                .append("char")
                .append(" ".repeat(this.minSpacing));

        // Get the length of the star header, in 5 char sections
        int n = (int) Math.ceil(Collections.max(this.histogram.values()) / 5.0);
        n = Math.max(n, 3); // Min 1-15 value markers

        // Base line header
        output.append("-".repeat(n * 5));

        // Place markers
        for(int i = 0; i < n; i++) {
            int value = Math.max(i * 5, 1);
            int index = 4 + this.minSpacing + value - 1;
            output.replace(index, index + 2, String.valueOf(value));
        }

        // Trim end line down to last value marker
        while(output.charAt(output.length() - 1) == '-')
            output.deleteCharAt(output.length() - 1);

        output.append("\n");

        // For each key in the set, add a line describing the number of stars
        for (String key : histogram.keySet())
            output
                    .append(key) // Character in question
                    .append(" ".repeat((4 + this.minSpacing) - key.length())) // Minimum spacing
                    .append("*".repeat(Math.min(histogram.get(key), 100))) // Number of occurrences, max 100
                    .append("\n");

        // Remove trailing newline
        return output.substring(0, output.length() - 1);
    }
}