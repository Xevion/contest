//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - October 2nd, 2020
//Class - Computer Science II PreAp
//Lab  - Maps 08e (Acronyms)

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class Acronyms
{
    private Map<String, String> acronymTable;

    public Acronyms()
    {
        this.acronymTable = new TreeMap<>();
    }

    public void putEntry(String entry)
    {
        String[] set = entry.split("\s*-\s*", 2);
        this.acronymTable.put(set[0], set[1]);
    }

    public String getTranslation(String word)
    {
        // Compile and execute RegEx pattern
        Pattern pattern = Pattern.compile("([.!?;,'\"]*)(\\w+)([.!?;,'\"]*)");
        Matcher match = pattern.matcher(word);
        match.find();

        // Combine original punctuation with translated acronym (or original word, if none found)
        return match.group(1) + acronymTable.getOrDefault(match.group(2), match.group(2)) + match.group(3);
    }

    public String convert(String sent)
    {
        return Arrays
                .stream(sent.split("\\s+"))
                .map(this::getTranslation)
                .collect(Collectors.joining(" "));
    }

    public String toString()
    {
        return this.acronymTable.toString().replace(",", "\n");
    }
}