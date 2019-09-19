import static java.lang.System.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Maze {
    int[][] offsets = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    String[][] rawMatrix;
    Set<Point> snake;
    Set<Point> pellets;

    Maze(String[][] rawMatrix) {
        this.rawMatrix = rawMatrix;
        snake = new ArrayList<Point>();

        for(int x = 0; x < 15; x++) {
            for(int y = 0; y < 15; y++) {
                switch(rawMatrix[x][y]) {
                    case " ":
                        break;
                    case "X":
                        snake.add(new Point(x, y));
                        break;
                    case "F":
                        pellets.add(new Point(x, y));
                        break;
                    default:
                        out.println("Possibly faulty Maze Input with item \"" + rawMatrix[x][y] + "\" found.");
                        break;
                }
            }
        }
    }

    // Simulate the snake game using instructions
    String simulate(String input) {
        return "";
    }

    boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < 15 && y < 15;
    }

    // Prints a string representation of the Maze
    String toString() {
        String[] lines = new String[15];
        for(int x = 0; x < 15; x++) {
            String[] temp = "";
            for(int y = 0; y < 15; y++) {
                temp[y] = rawMatrix[x][y];
            }
            lines[x] = String.join(" - ", temp);
        }
        return String.join("\n", lines);
    }
}

public class problem3 {
    public static void main(String[] args ) throws FileNotFoundException {
        // Constants
        File input = new File("input.dat");
        Scanner read = new Scanner(input);
        
        // Read the maze into a String matrix
        char[][] rawMatrix = new String[15][15];
        for(int x = 0; x < 15; x++) {
            String line = read.nextLine();
            for(int y = 0; y < 15; y++) {
                rawMatrix[x][y] = line.charAt(y);
            }
        }
        
        // Read each of the inputs and process inside the maze
        int lines = Integer.parseInt(read.nextLine());
        String[] inputs = new String[lines];
        for(int i = 0; i < lines; i++)
            inputs[i] = read.nextLine();
    }

}