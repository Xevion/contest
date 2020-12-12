import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

class Coordinate
{
    public int x;
    public int y;

    public Coordinate()
    {
        this(0, 0);
    }

    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Coordinate plus(Coordinate other)
    {
        return new Coordinate(this.x + other.x, this.y + other.y);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Coordinate)
            return this.x == ((Coordinate) obj).x && this.y == ((Coordinate) obj).y;
        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return String.format("(%s, %s)", this.x, this.y);
    }
}


public class Snake2
{
    private static final Coordinate UP = new Coordinate(0, -1);
    private static final Coordinate DOWN = new Coordinate(0, 1);
    private static final Coordinate LEFT = new Coordinate(-1, 0);
    private static final Coordinate RIGHT = new Coordinate(1, 0);

    public static void main(String[] args) throws FileNotFoundException
    {
        // Read in board input
        char[][] board = new char[15][15];
        Scanner input = new Scanner(new File("snake2.dat"));
        for (int y = 0; y < 15; y++) {
            String line = input.nextLine();
            for (int x = 0; x < 15; x++)
                // If the line was cut short, fill with spaces
                if (line.length() >= x + 1)
                    board[y][x] = line.charAt(x);
                else
                    board[y][x] = ' ';
        }

        input.nextLine();
        // Use all inputs and simulate Snake
        while (input.hasNextLine()) {
            // copy the original board
            char[][] copyBoard = new char[15][15];
            for (int y = 0; y < 15; y++)
                copyBoard[y] = board[y].clone();
            simulate(copyBoard, input.nextLine());
        }
    }

    public static void simulate(char[][] board, String commands)
    {
        Coordinate snakeHead = null;
        // Scan for the snake from top to bottom, right to left
        for (int y = 14; y >= 0; y--) {
            for (int x = 14; x >= 0; x--) {
                if (board[y][x] == 'X') {
                    board[y][x] = ' ';
                    if (snakeHead == null)
                        snakeHead = new Coordinate(x, y);
                }
            }
        }

        // Initialize simulation variables
        ArrayList<Coordinate> snake = new ArrayList<Coordinate>();
        Coordinate previousMove = RIGHT; // moves to the RIGHT by default
        Coordinate currentMove = null;
        boolean gameover = false;
        int pellets = 0;

        // Add initial snake positions
        assert snakeHead != null;
        snake.add(snakeHead); // Snake head is always the right most, and starts 3 nodes long
        snake.add(snakeHead.plus(new Coordinate(-1, 0)));
        snake.add(snakeHead.plus(new Coordinate(-2, 0)));

        // Parse each character command into a coordinate movement
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'L':
                    currentMove = LEFT;
                    break;
                case 'R':
                    currentMove = RIGHT;
                    break;
                case 'U':
                    currentMove = UP;
                    break;
                case 'D':
                    currentMove = DOWN;
                    break;
                case 'O':
                    currentMove = previousMove;
                    break;
            }
            previousMove = currentMove;
            Coordinate newPosition = snake.get(0).plus(currentMove);

            // Test if new move is outside boundaries or if the snake contains the new position
            if (newPosition.x < 0 || newPosition.y < 0 || newPosition.x >= 15 || newPosition.y >= 15) {
                gameover = true;
                break;
            }

            // Test if the snake contains the new position (hitting itself), minus the tail.
            for (int i = 0; i < snake.size() - 2; i++)
                if (newPosition.equals(snake.get(i))) {
                    gameover = true;
                    break;
                }

            // 'Move' the snake by adding new position, popping tail once
            snake.add(0, newPosition);

            // Check if pellet can be eaten, if not, pop off tail
            if (board[newPosition.y][newPosition.x] == 'F') {
                pellets++;
                board[newPosition.y][newPosition.x] = ' ';
            } else {
                snake.remove(snake.size() - 1);
            }
        }

        // Print game's end status
        if (gameover) {
            snake.remove(snake.size() - 1); // Remove tail to simulate out of bounds/into snake movement
            out.println("GAME OVER");
        }
        else
            out.println(String.format("%d pellets", pellets));

        // Print game board
        for (Coordinate node : snake) {
            board[node.y][node.x] = 'X';
        }
        for (char[] line : board)
            out.println(line);
    }
}
