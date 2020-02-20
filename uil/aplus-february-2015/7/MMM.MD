# MMM

Run my solution on **[repl.it](https://repl.it/@Xevion/A-Computer-Science-February-2015-MMM)!**

Despite this being one of the easier problems, it took much longer than it should have to solve.

This is mostly due to the `.median(int[] values)` method being programmed incorrectly, as if you're not familiar, or need a quick refresher: Median acts differently depending on whether or not there are a even or odd amount of numbers in the list.

When odd, it reports the center, which is easy enough, but when it's even, the average of the two centermost numbers is taken, which ended up being harder for me to program as I initially did not notice.

Simply put, the two values that were being averaged was shifted by 1 index, meaning the wrong values were selected.

Since I programmed the center to be calculated before a if statement concerning how the median is calculated is seen, I shifted the center over, which made the even-length Median(s) correct, but now the odd-length Median(s) incorrect.

This ends up making the `MMM` statistic incorrect too.

Remember to call `Arrays.sort` on the median values list, and use floating point arguments to correctly round the numbers (and effectively print quickly without a bunch of excessive string concatenations)!

This was a excessively long explanation for the problem. Sorry.