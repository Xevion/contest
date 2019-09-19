import static java.lang.System.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Point representing and (X, Y) coordinate
class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Returns a point which is the translated end-point based on the other point (offset).
    Point merge(Point other) {
        return new Point(this.x + other.x, this.y + other.y);
    }

    public String toString() {
        return String.format("Point(%d, %d)", this.x, this.y);
    }

    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    public static boolean collectionContains(Collection<Point> collection, Point point) {
        for(Point other : collection) {
            if(point.x == other.x && point.y == other.y) {
                return true;
            }
        }
        return false;
    }

    // Class method that searches a List
    public static int listContains(List<Point> c, Point p) {
        for(Point o : c) {
            if(p.x == o.x && p.y == o.y)
                return c.indexOf(o);
        }
        return -1;
    }
}

class Maze {
    // Offset Constants
    public List<Point> positionOffsets = Arrays.asList(new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1));
    private List<String> nameOffsets = Arrays.asList("D R U L".split(" "));
    // Maze Data
    List<Point> snake = new ArrayList<Point>();;
    private List<Point> pellets = new ArrayList<Point>();
    private char[][] rawMatrix;

    Maze(char[][] rawMatrix) {
        this.rawMatrix = rawMatrix;

        for(int x = 0; x < 15; x++) {
            for(int y = 0; y < 15; y++) {
                switch(rawMatrix[x][y]) {
                    // Empty Space
                    case ' ':
                        break;
                    // Point Found
                    case 'X':
                        this.snake.add(new Point(x, y));
                        break;
                    // Pellet Found
                    case 'F':
                        this.pellets.add(new Point(x, y));
                        break;
                    // Should not be found based on input data. Something has gone wrong/input data is invalid.
                    default:
                        out.println("Possibly faulty Maze Input with item \"" + rawMatrix[x][y] + "\" found.");
                        break;
                }
            }
        }
    }

    // Simulate the snake game using instructions
    String simulate(String input) {
        String curDirection = "R";
        int score = 0;
        for(int i = 0; i < input.length(); i++) {
            // Calculate the offset based on the current instruction
            Point offset;
            String curOffset = input.substring(i, i + 1);
            if(!curDirection.equals(curOffset) && !curOffset.equals("O"))
                curDirection = curOffset;
            if(curOffset.equals("O"))
                offset = positionOffsets.get(nameOffsets.indexOf(curDirection));
            else
                offset = positionOffsets.get(nameOffsets.indexOf(curOffset));
            // Calculate the new point, ensure it's real
            Point newPoint = snake.get(snake.size() - 1);
            newPoint = newPoint.merge(offset);
            // New Point was out of bounds, cannot continue
            if(!this.inBounds(newPoint))
                return "GAME OVER";
            // Add new point, discard end of snake to simulate it's movement
            snake.remove(0);
            snake.add(newPoint);
            // Check if we're at a pellet, add score.
            int pelletIndex = Point.listContains(pellets, newPoint);
            if(pelletIndex != -1) {
                score += 1;
                this.pellets.remove(pelletIndex);
            }
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
                Point point = new Point(x, y);
                // Priority: Empty Space < Pellet < Snake
                temp[y] = Point.collectionContains(this.snake, point) ? "X" : (Point.collectionContains(this.pellets, point) ? "F" : " ");
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

        // Read each of the inputs
        int lines = Integer.parseInt(read.nextLine());
        String[] inputs = new String[lines];
        for(int i = 0; i < lines; i++)
            inputs[i] = read.nextLine();

        // Simulate inputs
        for(String input : inputs) {
            Maze maze = new Maze(rawMatrix);
            String pellets = maze.simulate(input);
            // Uncomment to display the final position of the maze
//            out.println(maze);
            out.println(pellets);        }
    }
}