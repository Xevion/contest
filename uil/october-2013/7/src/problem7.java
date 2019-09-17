import static java.lang.System.*;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
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
            String rawGroceryList = "";
            String rawReceiptList = "";
            read.nextLine();
            // Process all lines into words
            for(int x = 0; x < listLines; x++)
                rawGroceryList += read.nextLine() + "\n";
            for(int x = 0; x < listLines; x++)
                rawReceiptList += read.nextLine() + "\n";
            // Split by any whitespace sequence into ArrayLists
            List<String> groceryList = Arrays.asList(rawGroceryList.split("\\s+"));
            List<String> receiptList = Arrays.asList(rawReceiptList.split("\\s+"));
            // Find which to put back
            String putBack = "PUT BACK";
            for(String item : receiptList) {
                if(groceryList.indexOf(item) == -1)
                    putBack += " " + item;
            }
            // Find which to buy more of
            String buyMore = "BUY MORE";
            for(String item : groceryList) {
                if(receiptList.indexOf(item) == -1)
                    buyMore += " " + item;
            }
            // Process and return final putback/buymore consensus
            if(putBack.equals("PUT BACK") && buyMore.equals("BUY MORE"))
                out.println("OK");
            else if (!putBack.equals("PUT BACK") && buyMore.equals("BUY MORE"))
                out.println(putBack);
            else if (putBack.equals("PUT BACK") && !buyMore.equals("BUY MORE"))
                out.println(buyMore);
            else
                out.println(putBack + "\n" + buyMore);
        }
    }
}
