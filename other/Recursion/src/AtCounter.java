//© A+ Computer Science  -  www.apluscompsci.com
//Name - Ryan Walters
//Date - November 12th, 2020
//Class - Computer Science II Honors
//Lab  - Labs 09b (AtCounter)

public class AtCounter {
    private static final char[][] atMat = new char[][]{{'@', '-', '@', '-', '-', '@', '-', '@', '@', '@'},
            {'@', '@', '@', '-', '@', '@', '-', '@', '-', '@'},
            {'-', '-', '-', '-', '-', '-', '-', '@', '@', '@'},
            {'-', '@', '@', '@', '@', '@', '-', '@', '-', '@'},
            {'-', '@', '-', '@', '-', '@', '-', '@', '-', '@'},
            {'@', '@', '@', '@', '@', '@', '-', '@', '@', '@'},
            {'-', '@', '-', '@', '-', '@', '-', '-', '-', '@'},
            {'-', '@', '@', '@', '-', '@', '-', '-', '-', '-'},
            {'-', '@', '-', '@', '-', '@', '-', '@', '@', '@'},
            {'-', '@', '@', '@', '@', '@', '-', '@', '@', '@'}};


    /**
     * @param r Row index
     * @param c Column index
     * @return Returns the number of
     */
    public static int countAts(int r, int c) {
        if (valid(r, c) && AtCounter.atMat[r][c] == '@') {
            AtCounter.atMat[r][c] = 'v'; // mark as visited
            return 1 + countAts(r + 1, c) + countAts(r - 1, c) + countAts(r, c + 1) + countAts(r, c - 1);
        }
        return 0;
    }

    /**
     * @param r Row index
     * @param c Column index
     * @return True if the indexes provided are a valid location within the array
     */
    private static boolean valid(int r, int c) {
        return r >= 0 && c >= 0 && r < 10 && c < 10;
    }

    /**
     * Resets the static map field to it's original state, changing visited nodes back to unvisited @ nodes.
     */
    public static void reset() {
        // iterate through all positions in character matrix
        for (int r = 0; r < AtCounter.atMat.length; r++) {
            for (int c = 0; c < AtCounter.atMat[0].length; c++) {
                // if visited, set back to unvisited @ node
                if(AtCounter.atMat[r][c] == 'v')
                    AtCounter.atMat[r][c] = '@';
            }
        }
    }
}