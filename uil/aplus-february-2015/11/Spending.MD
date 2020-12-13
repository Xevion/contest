# Spending

Run my solution on **[repl.it](https://repl.it/@Xevion/A-Computer-Science-February-2015-Spending-partial)!**

At first, this problem disguises itself as being relatively easy, once you setup all the data that you need to import. Other than understanding you have 'multiple spending limits' (whatever this means in the real world), the problem is relatively easy, and a quick glance over the questions one needs to solve and output, it makes sense.

However, this problem has a MASSIVE gotcha that stumps all who attempt to solve it, as it requires knowledge of **Combinatorics**, a mathematical area useful for generating all possible states a structure (typically a array or tree) can be in. Here, it's seen in Question 4, where you must identify the maximum amount of money one could spend.

Without thinking, it would seem that it's similar to the rest, and can be implemented relatively easy, and I did too, but test cases *should* fail quickly, and yield one questioning their code.

Here's an example of how it might go wrong, a implementation that essentially buys the most expensive items first... With three items with three distinct worths, $40, $30, and $20, with a total budget of $50, one can quickly notice that purchasing the two items worth $30 and $20 will yield the budget matched perfectly. However, a implementation that buys the most expensive item first will fail!

Similarly, a implementation that does the opposite will fail too: five items, [$50, $25, $15, $5, $1], budget of $65. The implementation will attempt a combination of $1, $5, $15, and $25 for a total of $46. The proper choice would in fact be $50 and $15. The choices picked have no distinct pattern, and a `Combinations` algorithm will need to be implemented, which can be very difficult for those new to it (me included!).

My implementation is incomplete as of this moment.