import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Point
{
    int x;
    int y;

    Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    Point()
    {
        this(0, 0);
    }

    public Point add(Point other)
    {
        return new Point(this.x + other.x, this.y + other.y);
    }

    public Point subtract(Point other)
    {
        return new Point(this.x - other.x, this.y - other.y);
    }

    public Point multiply(int magnitude)
    {
        return new Point(this.x * magnitude, this.y * magnitude);
    }

    public Point multiply(Point other)
    {
        return new Point(this.x * other.x, this.y * other.y);
    }
}

class OthelloBoard
{
    private final int[][] tiles = new int[8][8];

    public static final Point[] directions = new Point[]{
            new Point(0, 1), // Down
            new Point(1, 0), // Right
            new Point(-1, 0), // Left
            new Point(0, -1), // Up
            new Point(1, 1), // Down + Right
            new Point(-1, 1), // Down + Left
            new Point(-1, -1), // Up + Left
            new Point(1, -1), // Up + Right
    };

    public static final int BLACK = -1;
    public static final int EMPTY = 0;
    public static final int WHITE = 1;

    OthelloBoard()
    {
    }

    /**
     * @param data Input row data
     * @param row  The row number to modify.
     */
    public void read(char[] data, int row)
    {
        for (int x = 0; x < 8; x++) {
            char next = data[x];
            tiles[row][x] = next == 'B' ? BLACK : (next == 'W' ? WHITE : EMPTY);
        }
    }

    /**
     * Places a tile and evaluates the result.
     *
     * @param x Column of placement
     * @param y Row of placement
     * @param white Whether or not the tile being placed is white.
     * @return Returns true if any tiles were flipped in the placement of the new tile.
     */
    public boolean place(int x, int y, boolean white)
    {
        final int SELF = white ? WHITE : BLACK; // The tile type we are placing.
        final int OTHER = white ? BLACK : WHITE; // The tile type we will be trying to flip.
        final Point start = new Point(x, y); // The point we start at.
        ArrayList<Point> flipped = new ArrayList<Point>(); // Tiles that will be flipped after move is completed.

        // Start moving as far as possible in each direction
        for (Point direction : directions) {
            ArrayList<Point> between = new ArrayList<Point>(); // The tiles we found in between the end point (if any)
            Point current = start.add(direction);

            // Move forward in each direction as long as the tile is the opposite tile type
            while (getTile(current) == OTHER) {
                between.add(current); // Another tile between the two end points
                current = current.add(direction); // Move forward another in that direction

                // If the current position is no longer valid, break
                if (!isValid(current))
                    break;
            }

            // If the end positio n is valid and it's the same as our placing tile, we've found a valid area
            if (isValid(current) && getTile(current) == SELF) {
                flipped.addAll(between);
            }
        }

        // Set tile down
        tiles[y][x] = white ? WHITE : BLACK;

        // Flip all tiles found
        for (Point unflippedPoint : flipped)
            flip(unflippedPoint);

        return flipped.size() > 0;
    }

    private void flip(Point tile)
    {
        flip(tile.x, tile.y);
    }

    private void flip(int x, int y)
    {
        int current = tiles[y][x];
        if (current == WHITE)
            tiles[y][x] = BLACK;
        else if (current == BLACK)
            tiles[y][x] = WHITE;
    }

    private boolean isValid(Point point)
    {
        return isValid(point.x, point.y);
    }

    private boolean isValid(int x, int y)
    {
        return x >= 0 && y >= 0 && y < tiles.length && x < tiles[0].length;
    }


    public int getTile(Point point)
    {
        return getTile(point.x, point.y);
    }

    public int getTile(int x, int y)
    {
        return tiles[y][x];
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                switch (tiles[y][x]) {
                    case 0:
                        sb.append(".");
                        break;
                    case -1:
                        sb.append("B");
                        break;
                    case 1:
                        sb.append("W");
                        break;
                }
            }

            if (y < tiles.length - 1)
                sb.append("\n");
        }

        return sb.toString();
    }
}

public class Othello
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File("othello.dat"));
        int inputs = in.nextInt();
        in.nextLine();

        for (int i = 0; i < inputs; i++) {
            OthelloBoard board = new OthelloBoard();
            for (int y = 0; y < 8; y++) {
                board.read(in.nextLine().toCharArray(), y);
            }

            int row = in.nextInt();
            int column = in.nextInt();
            char move = in.next("[BW]").charAt(0);
            in.nextLine();

            boolean change = board.place(column, row, move == 'W');
            System.out.println(change ? board : "Invalid Move");
            System.out.println();
        }
    }
}
