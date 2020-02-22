import static java.lang.System.out;

public class problem6 {
    public static void main(String[] args) {
        int[][] data = {
            {15},
            {1, 5, 3, 5, 1},
            {1, 5, 3, 5, 1},
            {0, 6, 3, 6},
            {0, 3, 9, 3},
            {0, 3, 1, 2, 3, 2, 1, 3},
            {0, 6, 3, 6},
            {0, 6, 3, 6},
            {0, 6, 3, 6},
            {0, 6, 3, 6}};
        out.println(process(data));
    }

    static String process(int[][] data) {
        String output = "";
        for(int[] sub : data) {
            boolean doT = true;
            for(int sequenceN : sub) {
                output += doT
                        ? multiply("T", sequenceN)
                        : multiply(" ", sequenceN);
                doT = !doT;
            }
            output += "\n";
        }
        return output;
    }

    static String multiply(String str, int n) {
        String result = "";
        for(int i = 0; i < n; i++) {
            result += str;
        }
        return result;
    }
}
