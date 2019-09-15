# UIL Eligibility Packet

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

This is just pretty awful parantheses aside from the initial solving.

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
## Question 12
## Question 13
## Question 14
## Question 15
## Question 16
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

So in summary, the regex pattern matches a sequence of word characters, a `@` symbol, another sequence of word characters, a period, and then 2-3 lowercase letters. This is quite obviously a generate specification for an email (which allows underscores). The 2-3 lowercase letters only allows two or three letter domain extensions (`.com`, `.gov`, `.me` but not `.photography`, `.online`)

## Question 26

This is a rather long problem, so let's split it up into the patrs.

### Class X

Class X is a implementation of the Comparable Interface, and is essentially a method for comparing Strings when sorted. Focus on the `compareTo` method.

The `compareTo` method