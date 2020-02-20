import static java.lang.System.out;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;
import java.io.File;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("mmm.dat"));
        while(s.hasNextLine()) {
            String line = s.nextLine();
            Scanner r = new Scanner(line);
            int i = 0;
            while(r.hasNextDouble()) {
                i++;
                r.nextDouble();
            }
            double[] arr = new double[i];
            r = new Scanner(line);
            i = 0;
            while(r.hasNextDouble())
                arr[i++] = r.nextDouble();
            out.println(String.format("%.2f %.2f %.2f %.2f", mean(arr), median(arr), mode(arr), mmm(arr)));
        }
    }

    public static double mean(double[] values) {
        double sum = 0;
        for(double value : values)
            sum += value;
        return sum / values.length;
    }

    // 1 2 3 4 5 6
    //     ^ ^ mid = (7/2) - 1
    //     med = ([mid]+[mid+1]) / 2
    public static double median(double[] values) {
        Arrays.sort(values);
        int center = values.length / 2 - 1;
        if(values.length % 2 == 0)
            return (values[center] + values[center + 1]) / 2.0;
        else
            return values[center + 1];
    }

    public static double mode(double[] values) {
        if(values.length == 0)
            return -1.0;
        HashMap<Double, Integer> map = new HashMap<Double, Integer>();
        int max = 1;
        double temp = values[0];
        for(double value : values) {
            if(map.get(value) == null) {
                map.put(value, 1);
            } else {
                int cur = map.get(value);
                map.put(value, ++cur);
                if(max < cur) {
                    max = cur;
                    temp = value;
                }
            }
        }
        return temp;
    }

    public static double mmm(double[] values) {
        return (mean(values) + median(values) + mode(values)) / 3.0;
    }
}