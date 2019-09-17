import static java.lang.System.*;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class problem7 {
    public static void main(String[] args) throws FileNotFoundException {
        // Constants
        File input = new File("input.dat");
        Scanner read = new Scanner(input);
        int lines = read.nextInt();
        read.nextLine();

        // Process all lines
        for(int i = 0; i < lines; i++) {
            // Process all lines into primitive String arrays
            int listLines = read.nextInt();
            read.nextLine();
            String rawGroceryList = "";
            String rawReceiptList = "";
            for(int x = 0; x < listLines; x++) rawGroceryList += read.nextLine();
            for(int x = 0; x < listLines; x++) rawReceiptList += read.nextLine();
            String[] groceryList = rawGroceryList.split("\\s");
            String[] receiptList = rawReceiptList.split("\\s");
            out.println(Arrays.toString(groceryList));
            out.println(Arrays.toString(receiptList));
        }
    }

    // Processes all words in
    static String[] processWords(String[] lines) {
        int length = 0;
        // Count how long the final array should be
        for (String line : lines) {
            Scanner counter = new Scanner(line);
             while (counter.hasNext()) {
                 counter.next();
                 length++;
             }
        }
        // Begin inserting into an array
        String[] output = new String[length];
        int i = 0;
        for (String line : lines) {
            Scanner reader = new Scanner(line);
            while (reader.hasNext()) {
                output[i] = reader.next();
                i++;
            }
        }
        return output;
    }
}
