import static java.lang.System.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class problem1 {
    public static void main(String[] args) throws FileNotFoundException {
    	// Main Constants
    	File input = new File("input1.dat");
    	Scanner scan = new Scanner(input);
		List<String> output = new ArrayList<String>();

		// Process the input data
		int lineCount = scan.nextInt();
		scan.nextLine();
		for(int i = 0; i < lineCount; i++) {
			output.add(process(scan.nextLine()));
		}

		// Print out the array of processed inputs
        output.add(0, "mean median mode");
        for(String line : output) {
			out.println(line);
		}
    }

    static String process(String input) {
    	// Sub-constants
    	Scanner read = new Scanner(input);
    	int sum = 0;
		Map<Integer, Integer> mode = new HashMap<Integer, Integer>();
    	List<Integer> median = new ArrayList<Integer>();

		// Read each number in the file in sequence
    	while(read.hasNextInt()) {
    		int newread = read.nextInt();
    		sum += newread;
            // Add key to dictionary and list
            median.add(newread);
    		if(mode.containsKey(newread))
    			mode.put(newread, mode.get(newread) + 1);
    		else
    			mode.put(newread, 1);
    	}

    	// Find the mean and mode
    	double mean = sum / (double) median.size();
    	double finalMode = getBestKey(mode, median);

    	// Find the median
    	Collections.sort(median);
    	double finalMedian;
    	if(median.size() % 2 == 0 && median.size() > 2) {
    		int center = (median.size() / 2) - 1;
			finalMedian = (median.get(center) + median.get(center + 1)) / 2.0;
    	} else
    	    finalMedian = median.get(median.size() / 2);

    	return "" + properRound(mean) + " " + properRound(finalMedian) + " " + properRound(finalMode);
    }

    // Formats the string properly, rounds down to 1 decimal always
    static String properRound(double input) {
    	return "" + (Math.round(input * 10) / 10.0);
    }

    // In a dictionary, finds the key with the highest value, for mode
    // Keys are provided from the list aggregated for the median
    static int getBestKey(Map<Integer, Integer> map, List<Integer> keys) {
    	int curcount = (Integer) 0;
    	int curkey = (Integer) 0;
    	for(Integer key : keys) {
    		if(map.get(key) > curcount) {
    			curcount = map.get(key);
    			curkey = key;
    		}
    	}
    	return curkey;
    }
}