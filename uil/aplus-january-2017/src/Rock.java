import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rock
{
    public static final char ROCK = 'R';
    public static final char PAPER = 'P';
    public static final char SCISSORS = 'S';

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File("rock.dat"));
        int lines = in.nextInt();
        in.nextLine();

        for (int i = 0; i < lines; i++)
            process(in.nextLine());
    }

    public static void process(String input)
    {
        int wins = 0, losses = 0, ties = 0;
        char current = ROCK; // Always starts on Rock

        for (char next : input.toCharArray()) {
            // Get match result
            int result = check(next, current);

            // Increment win/loss/tie counnt
            switch (result) {
                case -1:
                    losses++;
                    break;
                case 0:
                    ties++;
                    break;
                case 1:
                    wins++;
                    current = chooseNext(next); // Opponent lost, strategy applied
                    break;
            }
        }

        System.out.println(String.format("Wins: %d\nLosses: %d\nTies: %d\n", wins, losses, ties));
    }

    /**
     * @param current The current RPS choice used
     * @return The new RPS choice used by opponent
     */
    public static char chooseNext(char current)
    {
        switch (current) {
            case ROCK:
                return PAPER;
            case PAPER:
                return SCISSORS;
            case SCISSORS:
                return ROCK;
        }
        return 0;
    }

    /**
     * Calculate the result of a Rock, Paper, Scissors match.
     *
     * @param self     The move used by yourself
     * @param opponent The move used by your opponent
     * @return A integer representing a loss (-1), tie (0), or win (1) for yourself.
     */
    public static int check(char self, char opponent)
    {
        if (self == opponent)
            return 0;

        switch (self) {
            // Rock beats Scissors, loses to Paper
            case ROCK:
                return opponent == SCISSORS ? 1 : -1;
            // Paper beats Rock, loses to Scissors
            case PAPER:
                return opponent == ROCK ? 1 : -1;
            // Scissors beats Paper, loses to Rock
            case SCISSORS:
                return opponent == PAPER ? 1 : -1;
        }
        return Integer.MAX_VALUE;
    }
}
