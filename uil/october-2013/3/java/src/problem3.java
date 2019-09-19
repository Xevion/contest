import static java.lang.System.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;

class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    void merge(Point other) {
        this.x += other.x;
        this.y += other.y;
    }
    public String toString() {
        return String.format("Point(%d, %d)", this.x, this.y);
    }
}

class Maze {
    // Offset Constants
    List<Point> positionOffsets = Arrays.asList(new Point(0, 1), new Point(1, 0), new Point(0, -1), new Point(-1, 0));
    List<String> nameOffsets = Arrays.asList("U R D L".split(" "));
    // Maze Data
    private List<Point> snake = new ArrayList<Point>();;
    private List<Point> pellets = new ArrayList<Point>();
    private char[][] rawMatrix;

    Maze(char[][] rawMatrix) {
        this.rawMatrix = rawMatrix;

        for(int x = 0; x < 15; x++) {
            for(int y = 0; y < 15; y++) {
                switch(rawMatrix[x][y]) {
                    case ' ':
                        break;
                    case 'X':
                        this.snake.add(new Point(x, y));
                        break;
                    case 'F':
                        this.pellets.add(new Point(x, y));
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
        char curDirection = 'R';
        int score = 0;
        for(int i = 0; i < input.length(); i++) {
            // Calculate the offset based on the current instruction
            Point offset;
            char curOffset = input.charAt(i);
            if(curOffset == 'O')
                offset = positionOffsets.get(nameOffsets.indexOf(curDirection));
            else
                offset = positionOffsets.get(nameOffsets.indexOf(input.substring(i, i+1)));
            out.println(offset);
            // Calculate the new point, ensure it's real
            Point newPoint = snake.get(snake.size() -1);
            newPoint.merge(offset);
            if(!this.inBounds(newPoint))
                return "GAME OVER @ " + newPoint;
            // Add new point, discard end of snake to simulate it's movement
            snake.add(newPoint);
            snake.remove(0);
            // Check if we're at a pellet
            score += pellets.contains(newPoint) ? 1 : 0;
        }
        return Integer.toString(score);
    }

    // Methods for testing whether a position is in the maze's boundaries
    public boolean inBounds(Point point) {return this.inBounds(point.x, point.y);}
    private boolean inBounds(int x, int y) {return x >= 0 && y >= 0 && x < 15 && y < 15;}

    // Prints a string representation of the Maze
    public String toString() {
        String[] lines = new String[15];
        for(int x = 0; x < 15; x++) {
            String[] temp = new String[15];
            for(int y = 0; y < 15; y++) {
                temp[y] = Character.toString(rawMatrix[x][y]);
            }
            lines[x] = String.join(" - ", temp);
        }
        return String.join("\n", lines);
    }
}

public class problem3 {
    public static void main(String[] args ) throws FileNotFoundException {
        // Constants
        File fileInput = new File("input.dat");
        Scanner read = new Scanner(fileInput);
        
        // Read the maze into a String matrix
        char[][] rawMatrix = new char[15][15];
        for(int x = 0; x < 15; x++) {
            String line = read.nextLine();
            for(int y = 0; y < 15; y++) {
                rawMatrix[x][y] = line.charAt(y);
            }
        }
        Maze originalMaze = new Maze(rawMatrix);
        
        // Read each of the inputs
        int lines = Integer.parseInt(read.nextLine());
        String[] inputs = new String[lines];
        for(int i = 0; i < lines; i++)
            inputs[i] = read.nextLine();

        // Simulate inputs
        for(String input : inputs) {
            Maze temp = originalMaze;
            out.println(temp.simulate(input));
            out.println(temp);
        }
    }

}