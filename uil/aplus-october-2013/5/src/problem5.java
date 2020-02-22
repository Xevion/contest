import static java.lang.System.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class problem5 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input.dat");
        Scanner read = new Scanner(input);
        int lines = read.nextInt();
        read.nextLine();

        for(int i = 0; i < lines; i++) {
            String[] inputArr = new String[4];
            for(int j = 0; j < 4; j++)
                inputArr[j] = read.next();
            out.println(process(inputArr));
        }
    }

    static double roundTo(double n, int places) {
        return Math.round(n * Math.pow(10, places)) / Math.pow(10, places);
    }

    static String process(String[] input) {
        double n1 = Double.parseDouble(input[0]);
        double theta1 = Double.parseDouble(input[1]);
        double unknown;
        if(input[2].equals("n")) {
            unknown = Double.parseDouble(input[3]);
            double n2 = (n1 * Math.sin(Math.toRadians(theta1))) / Math.sin(Math.toRadians(unknown));
            return "n = " + roundTo(n2, 4);
        }
        else if(input[3].equals("angle")) {
            unknown = Double.parseDouble(input[2]);
            return "angle = " + (
                    Math.round(Math.toDegrees(Math.asin((n1 * Math.sin(Math.toRadians(theta1))) / unknown)))
                    );
        }
        return "fail";
    }
}
