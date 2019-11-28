# UIL Eligibility Packet

Question 1-26 are done in full. From then on, only harder problems will be explained at much lighter levels to save time while completing this packet.

## Question 1

This can be guessed on pretty easily by analyzing the magnitudes of the powers. Every number here is within a mildly close range of eachother, and the highest numbers have some of the *lowest* powers, so we can assume that the one with the highest power will be the largest when evaluated.

## Question 2

This is simple String and Integer addition.

`3 + 4 + "Hi" + 3 + 4 = "7Hi34"`

**Remember:** Left to right, Integer + String converts to String, no kind of math involved once integer is converted to String.

## Question 3

Super simple math.
`3 + (7 + 3) = 13`

## Question 4

This one deals with integer division.

```java
27 / 2 = 13
13 / 2 = 6
6 / 2 = 3
3 / 2 = 1
! 1 / 2 = 0
Output: 27
```

Program flow should be analyzed carefully at the start and end as the output will include the first, then it will divide, and then the block ends and revaluates the expression `x > 0`.

This means that the output will contain the first but not the last phase of the `x` variable.

## Question 5

This is another one of those painstaking problems where you have to verify the method without knowing it's purpose: making it a thousand times harder to trust your calculated answer.

The for loop operates on every index in the String up the center, which ends up being `5` (`10 / 2 = 5`).

For each index in the String being operated upon, the String `t` has the index being accessed and the last index in the String `s` added (always `e`).

```java
0 => He
1 => oe
2 => we
3 => de
4 => ye
```

The final output for this problem is `Heoewedeye`.

## Question 6

This one requires prior knowledge of `Arrays.fill`.

**Spoiler:** It's super easy.

`Arrays.fill(array, start, stop, val)` simply places the value in the array specified from start to stop (left only inclusive).

Since the specified range is `[2, 3)`, only one 4 is placed at index 2.

The final array looks like `[0, 0, 4, 0, 0]`

## Question 7

This is just pretty awful parentheses aside from the initial solving.

```java
!(!(!(...)))
   !true || !false && !false
   false || true && true
   true && true
   true
!(!(!(true)))
!(!(false))
!(true)
false
```

## Question 8

This one actually tricked me because I'm really dumb and can't read; they are `if`s and `else if`s to look for here.

It's pretty easy modulus, I got `ABE`.

## Question 9

Solve from left to right, (P)(E)(MD)(AS).

Tip: You can solve in sections with the parentheses above, as multiplication/division can be done in any order together without changing the result. It helps a little bit with cutting down the manual solving time, if you ask me.

```java
3 + 5 / 2 + 2.0
3 + 2 + 2.0
5 + 2.0
7.0
```

## Question 10

Just a little bit of math and tracking of a changing array.

```java
r[3] = 19;
r[1] = r[3] * 2 = 38;
r[4] = r[1] / 2 = 19;
r[2] = r[4 % 3 = 1] / 3 = 38 / 3 = 12; 
```
```java
r = [0, 38, 12, 19, 19]
```

## Question 11

`Math.ceil` on a number already at it's ceiling/floor does nothing.

```java
2.15 -> 2.0 -> 2.0
```

## Question 12

This one has a incredibly interesting output, and I believe the answer is incorrect, or something has changed to make this incorrect over time.

Let's split it up into sections to make the problem simpler.

### Regex

`\\.*+` may seem abstract and strange at first, but it's really just an amalgamation of escape sequences and the PCRE Regex Engine (Javascript based Regex engines are more common, PCRE is server side and thus is not commonly seen with it's set of PCRE specific features).

`\\` is simply a escape sequence for the backslash in **Java**. This will catch most as you might move to the mindset that we're working in Regex, but we're in fact sending a String to a regex engine before it gets processes, and thus we must escape for **Java** before Regex.

`\\.` leads to a escape sequence for a dot `.` in Regex. Note: this is not the metacharacter, this is the escaped period/dot.

`\\.*` matches 0 or more escaped periods.

`\\.*+` adds a reluctant quantifier, specific to the PCRE Regex Engine. This caught me, as I had never seen the reluctant quantifier like this before.

If you want a tutorial on how reluctant vs other quantifiers, see [this Stack Overflow answer](https://stackoverflow.com/a/5319978/6912830), it's perfect and is very easy to understand.

So what does this in total match? *Zero or more periods*, of course.

### Solving the split

`a...b..c.d.efg...h` is our string. Let's see how our regex, `zero or more periods`, actually works in practice.

First, the `a` is seen and nothing is found, but it matches the idea of `zero or more periods`, so it progresses on to the second character, the `.`. Between this and the next sequence, a split is added, so the current array looks like this: `["a",]`...

Here, it begins matching the 2nd to 4th characters, a line of 3 dots. All 3 are deleted and replaced with a empty string to designate a split. Our array looks like `["a", "",]` now.

The basic idea of how this regex ends up working and how it would react almost always should take form now. You could come up with a rule to work off of so that the rest of the array is easier, split by every character, but add a empty space where dots are.

Be careful and make sure that

The rest of the array ends up being `[a, , b, , c, , d, , e, f, g, , h]`.

## Question 13

This question is dependent on `pre-increment` and `post-increment` operator knowledge.

### Unary Increment and Decrement Operators

What I'm talking about is the `unary operators`, `++` and `--`.

"Post" vs "Pre" is shown by the position of the operator before or after the variable it's modifying.

Pre-increment `++x` 

Post-increment `y--`

Post-decrement `--this.halogen.id` 

Pre-increment `dragon.level++`

### Evaluation

   Let's evaluate the equation we're presented with.

   ```java
   int x = 3;
   int y = 4;
   println(++x + ++y + y++)
   ```

   The evaluating engine (or whatever) sees a 4 with the pre-increment operator, x is incremented to 4, and returns a 4. Then, y is incremented to 5, and the operator returns a 5.

   So far, we've got `4 + 5 + y++`.

   Finally, y is returned as it is currently (5) and then incremented.

   The final equation looks like `4 + 5 + 5 = 14`.

   `x = 4 and y = 6` in the end.

## Question 14

This question is based on how you can instantiate different data types.

You should remember that the `char` data type is really a integer that maps to a specific character, and thus, you can use a integer to instantiate it. The first line passes.

The second line is a short. Shorts are essentially integers, but 16 bit, and thus their maximum value is smaller by a very large portion. Their set notation is `[-32,768, 32,768]` inclusive.

The answer from here on out would be `line two`, but let's look at the rest, too.

The third line is a integer, which has a much larger range, and `33,000` comes no where near it's maximum. This line is okay.

The fourth line is alright, casting a integer to a float is totally okay, but there is a big caveat: the variable name being used to instantiate has *already been used in the previous line*.

This causes a second compiler error, so, one could rule 3 lines here could cause the problem, requiring at least two lines to be changed or removed to cease errors from occurring.

## Question 15

This question is to test your knowledge on ArrayLists and how they work.

Do notice here that the name actually almost looks like it's `1st` and not `lst`. The font is slightly ambiguous in meaning, one could say.

Let's see how the ArrayList is slowly changed over time.

```java
List<String> lst = new ArrayList<>();
> List<String>([])
lst.add("Bob");
=> List<String>(["Bob"])
lst.add(1, "Ralph");
=> List<String>(["Bob", "Ralph"])
lst.add(0, "Sue");
=> List<String>(["Sue", "Bob", "Ralph"])
lst.set(1, "Debbie");
=> List<String>(["Sue", "Debbie", "Ralph"])
out.println(lst.get(lst.size()-1));
%> out.println(lst.get(3 - 1))
%> out.println(lst.get(2))
=> "Ralph" 
```

`ArrayList.add(type value)` appends the value specified to the end of the array.

`ArrayList.add(int index, type value)` inserts the value specified at the position in front of the specified index. It moves the specified index forward and puts the value before it.

`ArrayList.set(int index, type value)` sets the value at the index specified to the value specified.

## Question 16

This question concerns the left shift operator, which is very easy to understand, but hard to evaluate on paper.

The value given is `1234`, and we shift it left `6` times.

The binary representation of `1234` is `10011010010`, so, to left shift it, we simply add 6 zeroes to the end. Now, we just convert it from it's new binary representation, `10011010010000000`, to base 10.

It's final base 10 representation is 78976.


## Question 17

First parameter is the number, second is the base.

Remember for 123<sub>4</sub>, the maximum values for each digit in order from left-to-right (ending on the right), with the number it's used to build (right * 4 = left) would be...

```java
n(n/4) - 256(64) - 64(16) - 16(4) - 4(1).
```

So to build 123 <sub>4</sub>, `123 - 64 - 48 - 8 - 3 = 0`.

And to build 234 <sub>5</sub>, `234 - 125 - 100 - 5 - 4`.

## Question 18

Notice that the for loops loop over usually, but use `mat.length` instead of `mat.length-1`. Major mistake which results in a `OutOfBoundsException` to be raised.

## Question 19 - 21

Class A has a couple of interesting operators and distinctions that make it a difficult problem to diagnose.

The constructor starts off by setting `int n` equal to itself after using the (pre) increment operator.

Class A starts by instantiating a `protected int` primitive and then, while inside the constructor, uses the pre-increment operator.

Additionally, since this is a class, all objects in `class scope` are given a `default value`, which is different to `local scope`.

In the end, `n` in the `A` class will always equal 1.

### Question 19

*Thanks Mr. Smith for explaining this one better so I could properly understand it.*

Question 19 makes the user create a new `B` object of type `A`, which is an important distinction as if it was instantiated as type `B`, `getN` would still be accessible - however as type `A`, it is not.

At compile time, it is evaluated for 

### Question 20

The pre-increment operator and being in class scope means that `n` will be equal to 1. `toString()` returns `n` in `String` type.

### Question 21

Class `B` is similar to the `A` class, and ends up using the `super()` operator. After `this.n` is equal to 1 as usual, `int n` (local constructor scope) is added to `this.n`.

It is important to understand local/class scope, the `+=` assignment operator, and how `toString()` works.

## Question 22 - 23

Another complicated setup, but at least it's based on the same output.

`String.lastIndexOf("#")` will return the index of the last `#` in the 21 character long `s` String. This index is `19`.

The `fTwo` method takes two parameters and essentially combines to substrings of everything but that one `#` at index 19.

The most complicated part of this is that `fOne` and `fTwo` return eachother recursively.

Check the final else-statement (the first to run if you check based on the first initial input).

It returns (recursively) the output of `fOne(fTwo(s, s.lastIndexOf("#")))`, which could go on recursively forever, but with careful analysis you'll see this does not happen.

If the first `#` in a String is the last one, then it'll return the String like that.

If the String's length is even, then it will recursively return `fOne(fTwo(s, s.indexOf("#")))`.

Otherwise, it will recursively return `fOne(fTwo(s, s.lastIndexOf("#")))`, as mentioned above.

### Question 22

Let's diagnose the output for real and see what is really going on.

```java
1 => H#i#t#h#i#s#i#s#f#u#n
2 => H#i#t#h#i#s#i#s#f#un
3 => Hi#t#h#i#s#i#s#f#un
4 => Hi#t#h#i#s#i#s#fun
5 => Hit#h#i#s#i#s#fun
6 => Hit#h#i#s#i#sfun
7 => Hit#h#i#s#isfun
8 => Hith#i#s#isfun
9 => Hith#i#sisfun
10 => Hithi#sisfun
```

On the tenth iteration, the String is finally left with only a single `#`.

The final string output is equal to `Hithi#sisfun`.

### Question 23

This one is rather easy as well, but it requires the output of `22` to continue, as well as a bit of knowledge of Regex.

The Regex pattern `[aeiou]` simply matches against any one vowel in a row. `String.split` is also pretty simply, creating a String array made out of the string that is broken into pieces based on the matching regex pattern.

To solve this, all we have to do is identify vowels in `Hithi#sisfun` and mark them out.

`H[i]th[i]#s[i]sf[u]n`, with brackets around the sections that will be deleted and used to split apart the String.

The final String array that is returned will be `["H", "th", "#s", "sf", "n"]`.

## Question 24

Pretty easy; from left to right `3 + 4 = 7`, and then it's converted to a String, so you add 7, 3 and 7 to make `7737`.

## Question 25

This is a rather simple regex pattern, but they all look like insane spaghetti initially. We should note, that since this isn't shown in Java, escaping is done using a single `\` instead of `\\`!

To better explain, see the code below.

```java
"hello.world".split("\\.")
```

The regex pattern in text that is universally understood would be seen as `\.`, which matches against a single period (dot), and not a *escaped backslash* and a `.` metacharacter.

This is minor, but I believe it should be properly explained.

---

To start understanding the pattern, we should break it apart.

`^\w{1,}@\w{1,}\.[a-z]{2,3}$`

`^` is a anchor, showing the start of the String. This is reciprocated at the end with a `$` ending anchor.

`\w{1,}` matches against any `word` character (alphanumeric or underscore, essentially alphabet, digits or a underscore, case insensitive). It matches against 1 or more, but at least 1. This portion of the pattern is equal to `\w+`.

`@` matches a single `@` character. We should take a moment to note that the sections before and this match these these kinds of emails:

```java
jgu6oaouih23_6@
d9hi@
i_3@
f@
```

but not these

```java
@
^35a@
   @
``` 
 
(must match 1 character minimum, no special characters other than underscore, no whitespace)

`\w{1,}\.` matches the same word character in a sequence at minimum 1 character long, and then a dot character (`\.`, not the metacharacter `.`).

`[a-z]{2, 3}$` matches a sequence of lowercase letters with a length of 2 or 3. The `$` denotes the ending anchor as specified earlier.

So in summary, the regex pattern matches a sequence of word characters, a `@` symbol, another sequence of word characters, a period, and then 2-3 lowercase letters. This is quite obviously a generate specification for an email (which allows underscores). The 2-3 lowercase letters only allows two or three letter domain extensions (`.com`, `.gov`, `.me` but not `.photography`, `.online`) This also does not allow second-level, or any multi-level domains such as `.co.uk`.

---

To solve the problem, let's just see which which matches.

```java
>>> bob@example.com
Matches!
```
```java
>>> bob@example.co.uk
Does not match!
".co.uk" does not match "\.[a-z]{2, 3}" which allows only 1 period.
```
```java  
>>> ralph@coding.guru
Does not match!
Domain extensions may only be 2-3 characters long: "\.[a-z]{2, 3}".
```
```java
>>> sue.smith@company.com
Does not match!
"sue.smith" does not match "\w{1,}", no periods are allowed in the username.
```
```java
>>> donaldgoose@dizney.com
Matches!
```

## Question 26

This is a rather long problem, so let's split it up into parts.

### Class X

Class X is a implementation of the Comparable Interface, and is essentially a method for comparing Strings when sorted. Focus on the `compareTo` method.

The `compareTo` method is how comparables "compare", and it returns a integer value to representation how it should be placed in the array. The array is sorted in descending order when `Collections.sort` is called on it.

The integer it returns is calculated based on the length first: if the two Strings have the same length, it returns the result of the two strings `compareTo` method, but if they're not the same length, it returns the difference in length of the two Strings.

## Question 27

Just know your data types.

```javascript
long => 64bit integer
int => 32bit integer
byte => 8bit integer
double => 64bit float
float => 32bit float
short => 16bit float
```

## Question 28

Just follow a simple idea as you look at each number in the String, take the first, check if it's more than zero.

If so, take the next double and add it to the sum variable you're keeping track of.

Repeat until the first condition evaluates to false.

This could error if there was no negative and it tried to take another double without one being present.

I ended with `7.5`.

## Question 29

This deals with a new type of object, a Queue, specifically the PriorityQueue (PriorityQueue is akin to ArrayList as Queue is to List), they are specific, more advanced implementations (PriorityQueue & List).

Let's go through and diagnose the behavior of `offer()`, `peek()` and `poll()`.

```java
Queue<Integer> q = new PriorityQueue<>();
>>> []
> Initializes the queue with zero items inside.

q.offer(15);
>>> [15]
> This adds a 15, which positions itself at the head of the Queue.

q.offer(13);
>>> [13, 15]
> This adds a 13, which positions itself at the head of the Queue.
> 15 is moved from the head of the Queue as a lesser item has taken its place: 13.

q.offer(9);
>>> [9, 13, 15]
> This adds a 9, which positions itself at the head of the Queue.
> Again, 13 has been moved back in place of 9, which is even lesser than 13 & 15.

q.poll();
>>> [13, 15]
> q.poll() is destructive in this context, and removes the item just offered above (9).

q.offer(q.peek());
>>> [13, 15, 13]
> q.offer() adds a item, and q.peek() looks at what would be returned by q.poll(), which would remove an item.
> Since however, you are peek()ing, it only adds a new item.

q.peek();
>>> 13
> peek() does not take items from the Queue, it only looks at what would be taken had you poll()'d.
```

Be sure to read up on how Queues work, specifically the ordering, this can be very confusing and it will help to learn this, along with Comparators & Collections in general.

## Question 30

This is pretty simple referencing stuff, but it gets very complicated on tests, so I recommend looking up stuff on "Pass by Value" and "Pass by reference" in Java, it's a whole topic of debate in conjunction with immutability, mutability, wrappers, primitives, objects, and more.

Since everything here is a primitive, we can assume "pass by reference". Thus, simple math reveals that in the matrix, sub-array 1 and 2 swap via a temporary swap variable.

## Question 31

This question deals with regex. The expression used is "[^0-9]" (which replaces all that matches that with *nothing*).
This expression matches all characters that are not integers (0, 1, 2, 3, 4, 5, 6, 7, 8 or 9). It only matches 1 character, but it matches as many as possible, and replaces all matched (with an empty string).

This effectively will reduce the output string for `Array.toString` (`[a, b, c, d, e, f]`) to a sequence of digits.
It replaces spaces, commas, brackets, and all other characters that *aren't  digits*.

## Question 32

This is a more "trivia-like" question that most. Arrays.sort, in *Java SE 7*.
The array in question is a primitive array, which is important, as the Arrays.sort() method will sort based on the content of the array.
If the array is for -objects, it will use **"Timsort"**, which is a hybrid of *Merge Sort* and *Insertion Sort*.
Otherwise, for primitives, it will use **Dual-Pivot Quicksort**.

## Question 33

Here, you build an ArrayList of ArrayLists and print out the third array.
Here, you'll want to concentrate on every number from 1 to 100 that is both odd, and when divided by 7, has a remainder of 3.

It needs to be odd because you start at 1 and move along by 2 each iteration (`3 % 2 = 1`), and it needs to have a remainder of 3 when divided by 7 because it selects the correlating ArrayList based on the current number mod `7`, and since we focus on the ArrayList at index `3`, 

To find all of them, start looking at every odd number that is divisible by both 7 and 2 in the range [1, 100), a good way to do this is start at 3 and add 14 continuously until your reach 99 or less.

The list of these numbers is the output.

## Question 34

LIFO means "Last In, First Out".
This means, the last item to be put in is the first item to be extracted.
For example, the numbers 4, 3, 7, 8, and 16 are put into a data structure that operates in a LIFO manner. When a program thereafter attempts to extract numbers, they are extracted in reverse, i.e. 16, 8, 7, 3, and 4.

**Stack**s operate in this manner, simply think of them as a stack of bricks, cards, boxes etc. You can't extract the bottom item first (because it has a buch of items on top) before you extract the one before it (on top). You must extract the one on top (the last one you placed) before any other.

## Question 35

## Question 36

## Question 37

## Question 38

## Question 39

## Question 40