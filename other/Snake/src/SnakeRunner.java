import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.out;

class SnakeException extends Exception
{
    public SnakeException(String errorMessage)
    {
        super(errorMessage);
    }
}


public class SnakeRunner
{
    public static void main(String[] args) throws UnfindableSnakeException, FileNotFoundException
    {
        // Read board input
        Scanner input = new Scanner(new File("./src/snake2.dat"));
        char[][] charBoard = Board.GetCharBoard(input);

        // Read datasets
        String[] datasets = new String[input.nextInt()];
        input.nextLine();
        for (int i = 0; i < datasets.length; i++)
            datasets[i] = input.nextLine();

        // Simulate each dataset
        int i = 0;
        for (String dataset : datasets) {
            Board board = new Board(charBoard);
            boolean success = true;
            try {
//                out.println(dataset);
                board.simulate(dataset);
            } catch (SnakeSimulationException e) {
//                out.printf("Could not fully simulate dataset %s...%n", i);
//                e.printStackTrace();
                out.println("GAME OVER");
                success = false;
            }

            if (success)
                out.printf("%d Pellets%n", board.getPelletCount());

            out.println(board.render());
        }
    }
}
