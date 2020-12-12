import java.util.ArrayList;

class UnfindableSnakeException extends SnakeException {
    UnfindableSnakeException(String errorMessage) {
        super(errorMessage);
    }
}

public class Snake
{
    public ArrayList<Vector2Int> positions;
    private Vector2Int previousMovement = GetDelta('R');

    public Snake(char[][] board) throws UnfindableSnakeException
    {
        Vector2Int head = null;
        // Find head of the snake - lazily using problem constraints
        for (int y = 0; y < 15; y++) {
            for (int x = 15 - 1; x >= 0; x--) {
                if (board[y][x] == 'X') {
                    if (head == null)
                        head = new Vector2Int(x, y);
                    board[y][x] = ' ';
                }
            }
        }

        // If the head was find, build the Snake's known positions
        if (head != null) {
            positions = new ArrayList<>();
            positions.add(head);
            positions.add(new Vector2Int(head.x - 1, head.y));
            positions.add(new Vector2Int(head.x - 2, head.y));
        } else {
            throw new UnfindableSnakeException("Could not find Snake head within the provided Grid.");
        }
    }

    public Snake(ArrayList<Vector2Int> positions)
    {
        this.positions = positions;
    }

    /**
     * @return Returns the current length of the snake
     */
    public int Length()
    {
        return positions.size();
    }


    /**
     * @return Returns the 'head' (first) node in the snake.
     */
    public Vector2Int Head() { return positions.get(0); }


    /**
     * @return Returns he 'tail' (last) node in the snake.
     */
    public Vector2Int Tail() { return positions.get(positions.size() - 1); }

    public Vector2Int GetDelta(char movement) {
        switch (movement) {
            case 'U':
                return new Vector2Int(0, -1);
            case 'D':
                return new Vector2Int(0, 1);
            case 'L':
                return new Vector2Int(-1, 0);
            case 'R':
                return new Vector2Int(1, 0);
            case 'O':
                return previousMovement;
            default:
                return new Vector2Int();
        }
    }

    /**
     * @return Whether or not the Snake's head is within the game's boundaries.
     */
    public boolean WithinBoundaries()
    {
        Vector2Int head = positions.get(0);
        return head.x >= 0 && head.y >= 0 && head.x < 15 && head.y < 15;
    }

    /**
     * @return Returns true if the Snake's body positions overlap onto itself
     */
    public boolean WithinSelf() {
        Vector2Int head = Head();

        // Iterate along each previous node in the snake and check if it has moved into itself
        for (int i = 1; i < positions.size(); i++)
            if (head.equals(positions.get(i)))
                return true;
        return false;
    }

    public void setPreviousMovement(Vector2Int previousMovement)
    {
        this.previousMovement = previousMovement;
    }
}
