//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - October 2nd, 2020
//Class - Computer Science II PreAP
//Lab  - Maps 08c (Relatives)

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Relatives
{
    private Map<String, Set<String>> map;

    public Relatives()
    {
        this.map = new TreeMap<>();
    }

    public void put(String line)
    {
        String[] relation = line.split(" ");

        // Ensure Set exists for parent relative
        if (!this.map.containsKey(relation[0]))
            this.map.put(relation[0], new TreeSet<>());

        // Add relationship
        this.map.get(relation[0]).add(relation[1]);
    }


    public String get(String person)
    {
        return String.format(
                "%s is related to %s",
                person,
                String.join(" ", this.map.get(person))
        );
    }


    // Using all keys within the map, turn it into a stream which is mapped to the
    // getRelatives function, and join into a String by line breaks
    public String toString()
    {
        return this.map
                .keySet()
                .stream()
                .map(this::get)
                .collect(Collectors.joining("\n"));
    }
}