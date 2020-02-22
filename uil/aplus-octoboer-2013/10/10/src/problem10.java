import static java.lang.System.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Synonym {
    int index = 0;
    List<String> synonyms;
    Synonym(String[] synonyms) {
        this.synonyms = Arrays.asList(synonyms);
    }

    String next() {
        return synonyms.get(this.index++ % synonyms.size());
    }
}


class SynonymOrganizer {
    Map<String, Integer> points = new HashMap<String, Integer>();
    List<Synonym> synonyms = new ArrayList<Synonym>();
    
    // Takes lines of synonyms
    SynonymOrganizer(String[] synlines) {
        int creationIndex = 0;
        // Iterate over each line and process synonyms
        for(String line : synlines) {
            // Get all words in synonym list
            String[] words = line.split("\\s");
            for(String word : words) {
                // Can switch off to using Map<String, ArrayList<Integer>> for multi-value synonyms instead of singular
                if(points.containsKey(word))
                    out.println("Word \"" + word + "\" already used for previous synonym, overwriting.");
                points.put(word, creationIndex);
            }
            // Add the list of synonyms with new Synonym object
            synonyms.add(new Synonym(words));
            // Increment for next pointer
            creationIndex++;
        }
    }

    boolean hasSynonym(String word) {
        return points.containsKey(word);
    }

    // Gets a synonym, increments synonym object index
    String getSynonym(String word) {
        // Pointless in a perfect world
        if(!points.containsKey(word)) {
            out.println("Word\"" + word + "\"had no synonyms!");
        }
        // Retriever integer map pointer, then return next synonym
        int pointer = points.get(word);
        return synonyms.get(pointer).next();
    }
}

public class problem10 {
    public static void main(String[] args) throws FileNotFoundException {
        // Initial Constants
        File input = new File("input.dat");
        Scanner read = new Scanner(input);

        // Read paragraph lines
        String[] rawParagraph = new String[Integer.parseInt(read.nextLine())];
        for(int i = 0; i < rawParagraph.length; i++)
            rawParagraph[i] = read.nextLine();

        // Read synonym lines
        String[] synonymLines = new String[Integer.parseInt(read.nextLine())];
        for(int i = 0; i < synonymLines.length; i++)
            synonymLines[i] = read.nextLine();
        
        // Start processing the paragraph
        SynonymOrganizer organizer = new SynonymOrganizer(synonymLines);
        for(String line : rawParagraph) {
            String[] words = line.split("\\s");
            for(int i = 0; i < words.length; i++) {
                // Replace if organizer has a synonym
                if(organizer.hasSynonym(words[i])) {
                    words[i] = organizer.getSynonym(words[i]);
                }
            }
            out.println(String.join(" ", words));
        }
        read.close();
    }
}
