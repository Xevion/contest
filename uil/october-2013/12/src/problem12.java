import static java.lang.System.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collection;
import java.util.Collections;

// Point representing and (X, Y) coordinate
class Point {
    String type;
    int x, y;

    // Untyped Point Constructor
    Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = "";
    }

    // Typed Point Constructor
    Point(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type.equals(" ") ? "" : type;
    }

    // Returns a point which is the translated end-point based on the other point (offset).
    Point merge(Point other) {
        return new Point(this.x + other.x, this.y + other.y, this.type);
    }
    
    void offset(Point other) {
        this.x += other.x;
        this.y += other.y;
    }

    // Point string representation
    public String toString() {
        return String.format(
            "Point(\"%s\", %d, %d)",
            (this.type.equals("") ? "?" : this.type),
            this.x,
            this.y
        );
    }

    boolean isEmpty() {
        return this.type.equals("");
    }

    // equals() method for testing equality of two Point() objects
    // positional untyped equality test, see fullEquals()
    boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    // tests equality of x, y and type
    public boolean fullEquals(Point other) {
        return this.equals(other) && this.type == other.type;
    }

    // Returns whether a point could be found in a collection
    boolean collectionContains(Collection<Point> collection) {
        for(Point other : collection) {
            if(this.x == other.x && this.y == other.y) {
                return true;
            }
        }
        return false;
    }

    // Class method that searches a List
    // Returns -1 if item not found in list
    public int findPoint(List<Point> list) {
        for(Point other : list) {
            if(this.x == other.x && this.y == other.y)
                return list.indexOf(other);
        }
        return -1;
    }

    static List<Point> asList(int[][] primitivePoints) {
        List<Point> points = new ArrayList<Point>();
        for (int[] primPoint: primitivePoints) {
            points.add(new Point(primPoint[0], primPoint[1]));
        }
        return points;
    }
}

// Represents a checkers checkerboard
class CheckerBoard {
    // Offsets
    private List<Point> team1offsets = Point.asList(new int[][]{{-1, -1}, {1, -1}});
    private List<Point> team2offsets = Point.asList(new int[][]{{1, 1}, {-1, 1}});
//    private List<Point> offsets = Point.asList(new int[][]{{1, 1}, {-1, -1}, {-1, 1}, {1, -1}});
    private Point[][] matrix;
    // Team Constants, Team1 is default team.
    private String team1 = "R";
    private String team2 = "B";

    CheckerBoard(String[][] matrix) {
        // Build the point matrix
        this.matrix = new Point[8][8];
        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[x].length; y++) {
                this.matrix[x][y] = new Point(x, y, matrix[x][y]);
            }
        }
    }

    public String toString() {
        String result = "";
        for(int x = 0; x < this.matrix.length; x++) {
            String[] temp = new String[this.matrix[x].length];
            for(int y = 0; y < this.matrix[x].length; y++) {
                temp[y] = this.matrix[x][y].type.equals("") ? " " : this.matrix[x][y].type;  }
            result += String.join(" - ", temp) + "\n";
        }
        return result;
    }

    public static boolean inBounds(Point point) {return CheckerBoard.inBounds(point.x, point.y);}
    public static boolean inBounds(int x, int y) {return x >= 0 && y >= 0 && x < 8 && y < 8;}
    Point getPoint(Point point) {return this.matrix[point.x][point.y];}
    String getType(Point point) {return this.matrix[point.x][point.y].type;}
    boolean isReverse(Point first, Point second) {return (first.type.equals(team1) && second.type.equals(team2)) || (first.type.equals(team2) && second.type.equals(team1));}

    // Just returns all Points with type designated
    List<Point> getCheckers(String type) {
        List<Point> found = new ArrayList<Point>();
        for(int x = 0; x < this.matrix.length; x++) {
            for(int y = 0; y < this.matrix[x].length; y++) {
                if(this.matrix[x][y].type.equals(type))
                    found.add(this.matrix[x][y]);
            }
        }
        return found;
    }

    List<Point> getOffsets(Point point) {
        if(point.type.equals(team1))
            return team1offsets;
        else if(point.type.equals(team2))
            return team2offsets;
        else {
            out.println(String.format("Invalid Team Type Detected - \"%s\"", point.type));
            List<Point> offsets = new ArrayList<Point>();
            offsets.addAll(team1offsets);
            offsets.addAll(team2offsets);
            return offsets;
        }
    }

    int getMaxJumps(Point point) {
        List<Point> previous = new ArrayList<Point>();
        previous.add(point);
        return getMaxJumps(point, 0, previous);
    }
    int getMaxJumps(Point point, int score, List<Point> previous) {
        List<Integer> offsetScores = new ArrayList<Integer>();
        offsetScores.add(score);
        for(Point offset : this.getOffsets(point)) {
            Point newPoint = point.merge(offset);
            // Ensure new point is in matrix bounds
            if(CheckerBoard.inBounds(newPoint)) {
                // Ensure new point is of reverse type
                Point realPoint = this.getPoint(newPoint);
                if(this.isReverse(point, realPoint)) {
                    newPoint.offset(offset);
                    // Ensure second new point is inBounds & Empty
                    if(CheckerBoard.inBounds(newPoint)) {
                        if(this.getPoint(newPoint).isEmpty()) {
                            if(!newPoint.collectionContains(previous)) {
                                // add new point to blacklist, ensuring we don't get a infinite recursive nightmare
                                previous.add(new Point(newPoint.x, newPoint.y));
                                // will return at least 1
                                offsetScores.add(getMaxJumps(newPoint, score + 1, previous));
                            }
                        }
                    }
                }
            }
        }
        return Collections.max(offsetScores);
    }

    // Scanning methods for best jumps
    String scan() {return this.scan(this.team1);} // default to Red checkers
    String scan(String team) {
        List<Point> entries = getCheckers(team);
        List<Integer> jumps = new ArrayList<Integer>();
        // Scan each entry point for jumps
        for(Point entry : entries) {
            jumps.add(getMaxJumps(entry));
        }


        // Find the best origin jump and it's score
        Point bestpoint = new Point(-1, -1);
        int bestscore = 0;
        for(int i = 0; i < entries.size(); i++) {
            if(bestscore < jumps.get(i)) {
                bestscore = jumps.get(i);
                bestpoint = entries.get(i);
            }
        }

        return String.format("%s %s %s", bestpoint.x, bestpoint.y, bestscore);
    }
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

            CheckerBoard cb = new CheckerBoard(rawMatrix);
            out.println(cb);

                out.println(cb.scan());
        }

        read.close();
    }
}