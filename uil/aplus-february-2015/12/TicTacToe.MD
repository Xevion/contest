# TicTacToe

Run my solution on **[repl.it](https://repl.it/@Xevion/A-Computer-Science-February-2015-TicTacToe)!**

A overall very fun problem, one of my absolute favorite in this set. Inputs were easy to understand, and I believe my implementation was short, sweet, and easy to understand (I feel clever, give me kudos)!

Understanding the flow of decision is important here, as otherwise you'll get weird outputs, even if your code evaluated 'as it should' (your implementation worked as it should, even if your implementation did not fit the problem's specifications).
    * First, you should check Verticals/Horizontals/Diagonals for a actual *solutions* (three identical marks in a row). If one is found, that's the answer.
    * Second, you should check that all places are filled in. If not, this is counted as a "Incomplete Board", and you should return in turn.
    * Third, if the above two don't match up, all spots are filled in, yet no one has won - it's a tie, return as such.

My implementation essentially came down to implementing methods that returned 3 item long arrays of rows, columns, and diagonal spaces. These were given a input of the common X or Y coordinate associated (diagonal used a 'rising' and 'falling' argument instead [True/False]).

Then, in the actual main method that 'solved' it, I iterated over the data, calling these methods and checking them against a 'equals' method, which simply checked that all 3 elements were equal to eachother using `String.equals`.

Implementation has already been explained above, so as a last word of advice when solving - make sure that your input data is correctly typed in! Since I manually did it based on what my paper said, I wasn't as careful as I could have been, and simply assumed that the data was correct (as it should be in real competition, as you receive a file, not type it yourself).

I failed to capitalize an `X` correctly and the `String.equals` method failed to pick up this. One could argue using `String.toUpperCase` would be a valid way of ensuring that the input data is case-insensitive.

Overall, an interesting problem that challenged me and my understanding of matrixes when implementing `vertical` and `horizontal` data aggregation methods.