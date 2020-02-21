# Fate

Run my solution on **[repl.it](https://repl.it/@Xevion/A-Computer-Science-February-2015-Fate)!**

This one was interesting in all honesty. It's one of the few problems that stumped me, but I eventually got past. The rest simply stumped me and wouldn't budge.

I ended up implementing a much more robust class using ArrayLists, HashMaps, and Collections to support me.

My implementation essentially revolved around create a `Hand` object, using a `.add(String card)` method to add cards, which checked if it was above the specified card limit, and then consequently chose 
one to remove. I used a `HashMap<String, Integer>` to count and then decide upon which card to remove.

I first used the cards as keys and counted up the value by 1 each time I iterated over it (once each in ArrayList). I then iterated over the `HashMap` using the `.keySet()` method (which returns a `Set` object you can iterate over easily) and simply found the card with the least count and the lowest card value.

Card Value was implemented using `String.compareTo`, which you'll remember is simply `this - that`, i.e. a capital letter `A` has a value of `65`, `B` has `66` and so on. As such, if `this - that` (e.g. 65 - 66) returned a negative value when comparing the new possible letter versus the current selected 'minimum card', I swapped as usual. This was all done in a single if statement.

After that, I simply used `ArrayList.remove()` to remove the String, which by the way, has `String.equals`, allowing one to remove without requiring a reference to the variable you pick.

Once completed, I began printing, which started with sorting the `ArrayList` using `Collections.sort`, which sorted it in "Alphabetical" format as the problem specified. Then, I printed to `sysout` in the correct format using `String.join(" ", array)` for simplicity.

A interesting problem, but it did stump me when I forgot to add necessary code - although I forget exactly *what* I forgot to add that made it difficult to debug...