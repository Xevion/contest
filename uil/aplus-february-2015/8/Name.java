import static java.lang.System.out;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("name.dat"));
        while(s.hasNextLine()) {
            String operation = s.next();
            String input = s.next();
            int right = s.nextInt();
            int left = s.nextInt();

            out.println(String.format(
                "%s==>%s", input,
                operation.equals("E") ? encrypt(input, right, left) : decrypt(input, right, left)
            ));
        }
    }

    public static String encrypt(String input, int right, int left) {
        right = input.length() / right;
        left = input.length() / left;
        return leftCircle(rightCircle(reverse(input), right), left);
    }

    public static String decrypt(String input, int right, int left) {
        right = input.length() - (input.length() / right);
        left = input.length() - (input.length() / left);
        return reverse(rightCircle(leftCircle(input, left), right));
    }

    public static String rightCircle(String input, int right) {
        return input.substring(input.length() - right) + input.substring(0, input.length() - right);
    }

    public static String leftCircle(String input, int left) {
        return input.substring(left, input.length()) + input.substring(0, left);
    }

    public static String reverse(String input) {
        String result = "";
        char[] arr = input.toCharArray();
        for(int i = arr.length - 1; i >= 0; i--)
            result += arr[i];
        return result;
    }
}