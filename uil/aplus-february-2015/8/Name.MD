# Name

Run my solution on **[repl.it](https://repl.it/@Xevion/A-Computer-Science-February-2015-Name)!**

This one is very simple, and it's also one of those where there aren't any "gotchas" (sub-problems designed to slip one up, make mistakes and spend precious time testing and re-implementing better solutions), despite it seemingly radiating many.

I fabricated my own "gotcha" to waste time and get confused, which ended up being that I should have "reversed" the right or left circle additions before concatenating/moving them to the opposing side. My brain didn't predict that they would actually just "move" fluidly, that instead they would come out reversed (I believe I was visualizing a reflection of some sorts instead).

I noticed that the way they explained the input data, the two integers provided, was rather lackluster, one had to read carefully to pick up on the fact that they were in fact denominators of fractions representing the length of the substrings that one would move around using `rightCircle` and `leftCircle` methods.

I implemented the solution using basic methods like `reverse`, `rightCircle`, and `leftCircle` methods, which vastly simplified it for me.
I think it's important to note that it would be very difficult to do this all in main, and it's why I believe that one should split up stuff into as many separate, simpler operations to make narrowing things down easier.

The way the output is formatted seems weird to me, the lack of spaces between the arrow delimiter seems weird. I would have appreciated one space on each side being included, but that's just my OCD side kicking in.

Otherwise, a fun problem to implement. I hope there are more of these, even if I'm not particularly quick at implementing them.