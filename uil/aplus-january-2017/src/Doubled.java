import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

class StackerPosition
{
    int x; // Column
    int y; // Row
    Character type; // Nullable character
    Stacker grid; // Reference to a character grid for synchronizing type

    StackerPosition(int x, int y)
    {
        this(null, x, y, null);
    }

    StackerPosition(int x, int y, Character type)
    {
        this(null, x, y, type);
    }

    StackerPosition(Stacker grid, int x, int y)
    {
        this(grid, x, y, grid.safeGetType(x, y));
    }

    StackerPosition(Stacker grid, int x, int y, Character type)
    {
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public static int countUniques(List<Connectable> initial)
    {
        Map<Integer, Integer> counts = new HashMap<>();

        // Increment by one
        for (Connectable connect : initial) {
            int key = connect.hashCode();
            counts.put(
                    key, counts.getOrDefault(key, 0) + 1
            );
        }

        return counts.size();
    }

    public StackerPosition inverse()
    {
        return new StackerPosition(grid, -x, -y);
    }

    public StackerPosition add(StackerPosition other)
    {
        return new StackerPosition(grid, this.x + other.x, this.y + other.y);
    }

    public StackerPosition subtract(StackerPosition other)
    {
        return new StackerPosition(grid, this.x - other.x, this.y - other.y);
    }

    public StackerPosition normalize()
    {
//        int mag = (int) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
//        return new StackerPosition(x / mag, y / mag);
        return new StackerPosition(x != 0 ? x / Math.abs(x) : 0, y != 0 ? y / Math.abs(y) : 0);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StackerPosition that = (StackerPosition) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
}

enum Direction
{
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    /**
     * @param from The position the node starts from
     * @param to   The position the node ends at
     * @return A Direction Enum describing the cardinal/ordinal direction a StackerPosition is from another.
     */
    public static Direction getDirection(StackerPosition from, StackerPosition to)
    {
        StackerPosition offset = from.subtract(to).normalize();
        if (offset.x == 0) {
            if (offset.y == 1) return Direction.NORTH;
            else if (offset.y == -1) return Direction.SOUTH;
        } else if (offset.x == 1) {
            if (offset.y == 0) return Direction.WEST;
            else if (offset.y == 1) return Direction.NORTH_WEST;
            else if (offset.y == -1) return Direction.SOUTH_WEST;
        } else if (offset.x == -1) {
            if (offset.y == 0) return Direction.EAST;
            else if (offset.y == 1) return Direction.NORTH_EAST;
            else if (offset.y == -1) return Direction.SOUTH_EAST;
        }
        return null;
    }
}

class Connectable
{
    StackerPosition[] filled; // Positions in the stacker that have a checker inside them
    StackerPosition last; // The last position in a four-checker line needed to be filled
    char commonType; // The common check type among the filled checkers
    Direction direction; // The direction from the unfilled area to the other filled checkers.

    Connectable(StackerPosition[] filled, StackerPosition last)
    {
        this.filled = filled;
        this.last = last;
        this.commonType = filled[0].type;
        this.direction = Direction.getDirection(this.last, filled[0]);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connectable that = (Connectable) o;
        return last.equals(that.last) &&
                direction == that.direction;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(last);
    }

    @Override
    public String toString()
    {
        return String.format("Connectable{%s, (%d, %d) -> %s}", commonType, last.x, last.y, direction);
    }
}

class Stacker
{
    private char[][] grid = new char[6][];

    public static final char NONE = '.';
    public static final char BLACK = 'B';
    public static final char RED = 'R';

    public static final StackerPosition[] directions = new StackerPosition[]{
            new StackerPosition(1, 0),
            new StackerPosition(0, 1),
            new StackerPosition(1, 1),
            new StackerPosition(1, -1)
    };

    public List<StackerPosition> getOpenSlots()
    {
        List<StackerPosition> open = new ArrayList<>();

        // Search from left to right
        for (int c = 0; c < 7; c++) {
            // Search from bottom to top
            for (int r = 5; r >= 0; r--) {
                // If open, it's open to air all the way up
                if (grid[r][c] == NONE) {
                    open.add(new StackerPosition(this, c, r));
                    break;
                }
            }
        }

        return open;
    }

    /**
     * @return True if the position in the grid can have a checker placed in it directly, considering gravity.
     */
    public boolean isPlaceable(StackerPosition pos)
    {
        return isPlaceable(pos.x, pos.y);
    }

    /**
     * @return True if the position in the grid can have a checker placed in it directly, considering gravity.
     */
    public boolean isPlaceable(int x, int y)
    {
        if (!isValid(x, y)) return false; // Position must be valid
        if (getType(x, y) != NONE) return false; // Position must not have a checker in it
        if (y == 5) return true; // If it's on the bottom row, it's placeable
        else return getType(x, y + 1) != NONE; // If it has something below it, it's placeable
    }

    public List<Connectable> findConnectables(char type)
    {
        List<Connectable> found = new ArrayList<>();

        // Iterate along each checker position in the grid
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {
                // If the checker is of the type we want
                if (grid[r][c] == type) {
                    StackerPosition position = new StackerPosition(this, c, r);

                    // Check each direction if it has three checkers in a row
                    for (StackerPosition direction : directions) {
                        List<StackerPosition> checkQueue = new ArrayList<>();
                        checkQueue.add(position.add(direction));
                        checkQueue.add(position.subtract(direction));

                        // Check if the base triple has been formed
                        if (validateAll(checkQueue, type)) {
                            // Check if right side node is free
                            StackerPosition emptyFront = position.add(direction).add(direction);
                            StackerPosition emptyBack = position.subtract(direction).subtract(direction);

                            // Check if the right/up side node is free
                            if (isValid(emptyFront) && getType(emptyFront) == NONE)
                                found.add(new Connectable(checkQueue.toArray(new StackerPosition[3]), emptyFront));

                            // Check if left/down side node is free
                            if (isValid(emptyBack) && getType(emptyBack) == NONE)
                                found.add(new Connectable(checkQueue.toArray(new StackerPosition[3]), emptyBack));
                        }
                    }
                }
            }
        }

        // Filter out connectables where the last node cannot be placed properly
        found = found.stream().filter(connectable -> isPlaceable(connectable.last)).collect(Collectors.toList());

        return found;
    }

    /**
     * @param positions A list of StackerPosition objects
     * @return True if all positions are valid and of the same type.
     */
    private boolean validateAll(List<StackerPosition> positions)
    {
        StackerPosition first = positions.get(0);
        if (isValid(first))
            return validateAll(positions, getType(first));
        return false;
    }

    /**
     * @param positions A list of StackerPosition objects
     * @param type      The type of checker that all StackerPosition objects must be
     * @return True if all positions are valid and of the same type as requested.
     */
    private boolean validateAll(List<StackerPosition> positions, char type)
    {
        for (StackerPosition next : positions) {
            if (!isValid(next) || getType(next) != type)
                return false;
        }
        return true;
    }

    /**
     * @return Returns true if the given position is valid within the grid.
     */
    private boolean isValid(StackerPosition position)
    {
        return position.x >= 0 && position.x < 7 && position.y >= 0 && position.y < 6;
    }

    /**
     * @return Returns true if the given position is valid within the grid.
     */
    public boolean isValid(int x, int y)
    {
        return x >= 0 && x < 7 && y >= 0 && y < 6;
    }

    /**
     * @return Returns the checker type (char) at the given position in the grid
     */
    private char getType(StackerPosition position)
    {
        return grid[position.y][position.x];
    }

    /**
     * @return Returns the check type (char) at the given position in the grid
     */
    public Character getType(int x, int y)
    {
        return grid[y][x];
    }

    /**
     * Simply checks if the position in the grid is valid, if so, returns the type character at that position.
     *
     * @return Returns the check type (char) at the given position in the grid, if valid. Else null.
     */
    public Character safeGetType(int x, int y)
    {
        return isValid(x, y) ? getType(x, y) : null;
    }

    public void setRow(int r, char[] row)
    {
        grid[r] = row;
    }

    @Override
    public Stacker clone()
    {
        Stacker clone = new Stacker();
        clone.grid = new char[6][];
        for (int i = 0; i < 6; i++)
            clone.grid[i] = Arrays.copyOf(grid[i], grid[i].length);
        return clone;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 6; r++) {
            sb.append(String.valueOf(grid[r]));
            sb.append("\n");
        }

        // Remove the extra newline at the end
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    public void placeChecker(int x, int y, char type)
    {
        grid[y][x] = type;
    }
}

public class Doubled
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File("doubled.dat"));
        int inputs = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < inputs; i++) {
            Stacker stacker = new Stacker();
            char player = scanner.nextLine().charAt(0);
            char opposite = player == 'R' ? 'B' : 'R';

            for (int r = 0; r < 6; r++) {
                char[] row = scanner.nextLine().toCharArray();
                stacker.setRow(r, row);
            }

            List<Connectable> initial = stacker.findConnectables(opposite);
            boolean doubled = false;

            if (StackerPosition.countUniques(initial) > 1) {
                doubled = true;
            } else if (initial.size() == 1) {
                Connectable attempt = initial.get(0);

                // Create a temporary copy of the Connect 4 Stacker
                Stacker temp = stacker.clone();

                // Place a checker in the same place
                temp.placeChecker(attempt.last.x, attempt.last.y, player);

                // See if any new connectables opened up.
                List<Connectable> post = temp.findConnectables(opposite);
                if (post.size() > 0)
                    doubled = true;
            }

            System.out.println(doubled ? "Doubled!" : "There's hope");
        }
    }
}
