import static java.lang.System.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void merge(Point other) {
        this.x += other.x;
        this.y += other.y;
    }

    void merge(int x, int y) {
        this.x += x;
        this.y += y;
    }
}

class CheckerBoard {
    List<Point> offsets = Arrays.asList(new Point[]{Point(1, 1), Point(-1, -1), Point(-1, 1), Point(1, -1)});

    CheckerBoard(String[][] matrix) {
        
    }

    List<Point> getPossible(int x, int y) {
        return getPossible(x, y, new ArrayList<Point>());
    }

    List<Point> getPossible(int x, int y, List<Point> blacklist) {}
}

class problem12 {
    public static void main(String[] args) throws FileNotFoundException {
        // Constants
        File input = new File("input.dat");
        Scanner read = new Scanner(input);
        int lines = Integer.parseInt(read.nextLine());

        // Start reading and processing the matrix into
        for(int i = 0; i < lines; i++) {
            String[][] rawMatrix = new String[8][8];
            for(int x = 0; x < 8; x++) {
                String line = read.nextLine();
                for(int y = 0; y < 8; y++)
                    rawMatrix[x][y] = line.substring(y, y+1);
            }

            out.println(Arrays.deepToString(rawMatrix));
            CheckerBoard cb = new CheckerBoard(rawMatrix);
            out.println(cb.scan());
        }

        read.close();
    }
}