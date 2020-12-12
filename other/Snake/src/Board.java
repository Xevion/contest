import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

class SnakeSimulationException extends SnakeException
{
    public SnakeSimulationException(String errorMessage)
    {
        super(errorMessage);
    }
}

class OutsideBoundariesException extends SnakeSimulationException
{
    public OutsideBoundariesException(Vector2Int position)
    {
        super(String.format(Locale.ENGLISH, "The snake crossed the grid boundary at (%d, %d).", position.x, position.y));
    }
}

class InsideSelfException extends SnakeSimulationException
{
    public InsideSelfException(Vector2Int position)
    {
        super(String.format(Locale.ENGLISH, "The snake crossed into itself at (%d, %d).", position.x, position.y));
    }
}

public class Board
{
    private final Snake snake;
    private final char[][] board;
    private int pelletCount;

    public Board(char[][] board) throws UnfindableSnakeException
    {
        char[][] copyBoard = new char[15][15];
        for (int y = 0; y < 15; y++)
            copyBoard[y] = board[y].clone();

        this.board = copyBoard;
        snake = new Snake(copyBoard);
    }

    public static char[][] GetCharBoard(Scanner input)
    {
        String[] lines = new String[15];

        for (int i = 0; i < 15; i++) {
            String line = input.nextLine();

            // Ensure at least 15 characters available to read
            int left = Math.max(0, 15 - line.length());
            while (left-- > 0)
                line += " ";

            lines[i] = line;
        }
        return GetCharBoard(lines);
    }

    public static char[][] GetCharBoard(String[] lines)
    {
        char[][] board = new char[15][15];

        int y = 0;
        for (String line : lines) {
            for (int x = 0; x < 15; x++)
                board[y][x] = line.charAt(x);
            y++;
        }

        return board;
    }

    public void move(char movement) throws SnakeSimulationException
    {
        // Calculate the new snake head position and move the snake positions
        Vector2Int head = snake.Head();
        Vector2Int moveDelta = snake.GetDelta(movement);
        snake.setPreviousMovement(moveDelta);
        snake.positions.add(0, head.plus(moveDelta));

        if (!snake.WithinBoundaries()) {
            throw new OutsideBoundariesException(snake.Head());
        } else if (snake.WithinSelf()) {
            throw new InsideSelfException(snake.Head());
        } else {
            // Only remove the end node if it did not consume a pellet. Simulates the 'extension' of the tail naturally.
            if (!Consume())
                snake.positions.remove(snake.positions.size() - 1);
            else
                pelletCount++;
        }
    }

    /**
     * If possible, consumes a pellet at the Snake's head position. Returns a boolean signaling if it consumed a pellet.
     *
     * @return Returns True if the snake has consumed a pellet with the latest function execution.
     */
    private boolean Consume()
    {
        Vector2Int head = snake.Head();
        if (board[head.y][head.x] == 'F') {
            board[head.y][head.x] = ' ';
            return true;
        }
        return false;
    }

    public void simulate(String dataset) throws SnakeSimulationException
    {
        for (int i = 0; i < dataset.length(); i++) {
//            System.out.println(render());
//            System.out.println("---------------");
            this.move(dataset.charAt(i));
        }
    }

    /**
     * @return Returns a rendered version of the Board with Snake and Pellet positions marked.
     */
    public String render()
    {
        // Make a copy of the original board
        char[][] boardCopy = new char[15][15];
        for (int i = 0; i < 15; i++)
            boardCopy[i] = Arrays.copyOf(this.board[i], 15);

        // Mark the snake's location
        for (Vector2Int position : snake.positions)
            if (position.x >= 0 && position.y >= 0 && position.x < 15 && position.y < 15)
                boardCopy[position.y][position.x] = 'X';

        // Join the char arrays into strings
        String[] render = new String[15];
        for (int i = 0; i < 15; i++)
            render[i] = String.valueOf(boardCopy[i]);

        // Return all lines, joined by newlines
        return String.join("\n", render);
    }

    public int getPelletCount()
    {
        return pelletCount;
    }
}
