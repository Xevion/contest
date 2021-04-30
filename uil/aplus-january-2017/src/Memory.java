import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Simple tuple class for storing pairs of Cards.
 */
class CardPair
{
    Card a;
    Card b;
    boolean marked = false;

    CardPair(Card a, Card b)
    {
        this.a = a;
        this.b = b;
    }

    public boolean bothVisible()
    {
        return a.visible && b.visible;
    }
}

class Card
{
    public Card other; // Two-way reference to the other card.

    int x; // Column
    int y; // Row
    int z; // Depth/Level
    char value; // The actual a-Z letter of the card.
    CardPair pair; // Reference to the CardPair containing both
    boolean visible = false; // Whether or not the Card is considered visible

    Card(char value, int x, int y, int z)
    {
        this.value = value;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString()
    {
        return String.format("Card{%s, x=%d, y=%d, z=%d, %s}", value, x, y, z, visible ? "Visible" : "Not Visible");
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return x == card.x &&
                y == card.y &&
                z == card.z;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z);
    }
}

class MemoryStructure
{
    private final char[][][] charStructure;
    private final Card[][][] cardStack;
    public int width;
    public int height;
    public int depth;

    private final HashMap<Character, CardPair> pairs;
    public final Queue<Card> revealed; // A queue containing all newly

    MemoryStructure(int width, int height, int depth)
    {
        this.width = width;
        this.height = height;
        this.depth = depth;

        charStructure = new char[width][height][depth];
        cardStack = new Card[width][height][depth];

        pairs = new HashMap<>();
        revealed = new LinkedList<>();
    }

    public CardPair getPair(char value)
    {
        return pairs.get(value);
    }

    public char getValue(int row, int column, int level)
    {
        return this.charStructure[column][row][level];
    }

    public Card getCard(int row, int column, int level)
    {
        return this.cardStack[column][row][level];
    }

    /**
     * @param card The card you want to place into the structure.
     */
    public void putCard(Card card)
    {
        if (card == null) return;

        this.charStructure[card.x][card.y][card.z] = card.value;
        this.cardStack[card.x][card.y][card.z] = card;
    }

    /**
     * @param row    The row (Y-coordinate)
     * @param column The column (X-coordinate)
     * @return The full vertical column of characters from top to bottom of the structure at a specific coordinate.
     */
    public Card[] getVertical(int row, int column)
    {
        Card[] vertical = new Card[depth];
        for (int z = 0; z < depth; z++)
            vertical[z] = getCard(row, column, z);
        return vertical;
    }

    /**
     * @param row    The row (Y-coordinate)
     * @param column The column (X-coordinate)
     * @param level  The level/depth (Z-coordinate)
     * @return A list of characters that are above the specific 3d coordinate
     */
    public Card[] getAbove(int row, int column, int level)
    {
        Card[] above = new Card[level];
        for (int z = 0; z < level; z++)
            above[z] = getCard(row, column, z);
        return above;
    }

    /**
     * Prepare the Character/Pair map for use after values are set.
     */
    public void prepareMap()
    {
        // Iterate through every position in structure
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    Card card = getCard(y, x, z);

                    if (pairs.containsKey(card.value)) {
                        // First part of pair has been found, add the other part
                        CardPair pair = pairs.get(card.value);
                        pair.b = card;

                        // Setup card references
                        pair.a.other = pair.b;
                        pair.b.other = pair.a;
                        pair.a.pair = pair;
                        pair.b.pair = pair;
                    } else {
                        // Never found before. Create new Pair instance and place first part in.
                        CardPair pair = new CardPair(card, null);
                        pairs.put(card.value, pair);
                    }
                }
            }
        }
    }

    /**
     * Adds all known visible cards by searching top down. Should only be ran once at the beginning (thus only
     * searching to one depth, but could be used later on to go down to any depth necessary).
     */
    public void setupVisible()
    {
        // Iterate only each XY coordinate in the grid
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Start moving down in depth until a non-marked card is found.
                for (int z = 0; z < depth; z++) {
                    Card card = getCard(y, x, z);
                    CardPair cp = getPair(card);

                    // If on top of this column's stack
                    if (!cp.marked) {
                        card.visible = true;
                        revealed.add(card);
                        break;
                    }
                }
            }
        }
    }

    private CardPair getPair(Card card)
    {
        return getPair(card.value);
    }

    @Override
    public String toString()
    {
        return String.format("MemoryStructure{width=%d, height=%d, depth=%d}", width, height, depth);
    }

    /**
     * Lifts a card off the stack by marking the one under a card as visible, and adding that one to the stack.
     *
     * @param card The card to 'lift' off the card stack, revealing the card beneath it.
     */
    public void lift(Card card)
    {
        // Get the card beneath it
        int newZ = card.z + 1;

        // If there is going to be a card beneath
        if (!(newZ > depth - 1)) {
            Card under = getCard(card.y, card.x, newZ);
            under.visible = true;
            revealed.add(under);
        }
    }

    public Set<Character> values()
    {
        return pairs.keySet();
    }
}

public class Memory
{
    public static void main(String[] args) throws FileNotFoundException
    {

        Scanner in = new Scanner(new File("memory.dat"));
        int inputs = in.nextInt();
        in.nextLine();

        for (int i = 0; i < inputs; i++) {
            MemoryStructure ms = new MemoryStructure(in.nextInt(), in.nextInt(), in.nextInt());
            in.nextLine();

            // Read in character inputs
            for (int z = 0; z < ms.depth; z++) {
                for (int y = 0; y < ms.height; y++) {
                    String line = in.nextLine();

                    for (int x = 0; x < ms.width; x++) {
                        Card card = new Card(line.charAt(x), y, x, z);
                        ms.putCard(card);
                    }
                }
            }

            ms.prepareMap(); // Form all pairs in map
            ms.setupVisible(); // Find initially visible cards

            Set<Character> unseen = ms.values();

            // Keep searching and unlocking card pairs
            while (ms.revealed.size() > 0) {
                Card next = ms.revealed.poll();

                // Skip Cards that may have been marked by it's sibling while inside the queue
                if (next.pair.marked)
                    continue;

                // If both cards in the pair are visible
                if (next.pair.bothVisible()) {
                    // Mark the pair
                    next.pair.marked = true;

                    // Remember that we've seen this character
                    unseen.remove(next.value);

                    // Mark cards beneath as visible and add them to the Queue
                    ms.lift(next);
                    ms.lift(next.other);
                }
            }

            System.out.println(unseen.size() == 0 ? "solvable" : "impossible");
        }
    }
}
