//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.ArrayList;

import static java.lang.System.*;

public class UniquesDupes
{
    public static Set<String> getUniques(String input)
    {
        return new TreeSet<String>(Arrays.asList(input.split("\\s+")));
    }

    public static Set<String> getDupes(String input)
    {
        Set<String> consider = new TreeSet<String>(); // Values that have been seen at least once.
        Set<String> dupes = new TreeSet<String>(); // Values that appear two or more times.

        for (String substring : input.split("\\s+")) {
            // One has already been seen, add to dupes set.
            if (consider.contains(substring))
                dupes.add(substring);
            else
                // Never been seen before, add to 'considering' set
                consider.add(substring);
        }

        return dupes;
    }
}