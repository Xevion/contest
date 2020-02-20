import static java.lang.System.out;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;

class Main {
  public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("radians.dat"));
        while(s.hasNextInt()) {
            int degrees = s.nextInt();
            double rad = Math.toRadians(degrees) / Math.PI;
            String radians;
            if(rad == 1)
                radians = "";
            else if(rad % 1 == 0)
                radians = Integer.toString((int) rad);
            else
                radians = String.format("%.2f", rad);
            out.println(String.format("%s degrees = %sPI radians", degrees, radians));
        }
  }
}