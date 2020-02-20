# Play

Run my solution on **[repl.it](https://repl.it/@Xevion/A-Computer-Science-February-2015-Play)!**

This solution took me way longer than it EVER should have taken to implement. I decided to take my sweet time creating a ultra-perfect solution that would be more accurate, faster, and analytical to how the problem actually functioned (which ended up being a good refresher on how absolute value parent functions can be determined from a graph or table).

I tried really hard to understand how to program, but I always came up with solutions I really hated, where a while loop would be used, a for loop twice, where printing would occur more than once, where multiple string concatenations would occur... It's rather idiotic to derail too much on why I hated this problem so much, and I understand that I should have simply created a solution worthy of the trash can, even if it ended up helping me win a real competition.

Never the less, I will detail on how I solved it. After a while of staring at it, I decided to create a table based on the length of the stars produced in the original string, alongside the value used to create it (one I called a "magnitude"). After observing it for a while, I discerned that the value started at a specific point based on the magnitude, and decreased in both directions. This variable ended up being equal to `n = 2m - 1` (where `m` is `magnitude`, `n` representing this mystery value).

After figuring out that the equation is definitely an absolute equation, I started typing in equations until I could figure out one that matched the table [I created in Desmos](https://www.desmos.com/calculator/0gszz81jpx). I eventually created my final equation: `y = n - 2|x - (n + 1) / 2|`, which can be expanded to `y = (2m - 1) - 2|x - ((2m - 1) + 1) / 2|`, and then simplified to `y = 2(m - |x - m|) - 1` (not sure why I just typed all those out).

Following me finding the correct equation, I used it to develop my solution. I created a integer array to store my table of values, with a length equal to the magnitude (and then later used that length to substitute in my equation - yeah I'm so optimized). A string sequence is then filled with a string repeat method against the now filled integer array. I then print the output of `String.join` fed a newline and the primitive String array.

Overall, I don't know specifics on other ways of solving this, but brute force analysis and typing "solutions" works, this isn't a complex problem, so deep understanding of the patterns that the output contains is not required.