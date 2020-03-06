# Study

Questions I missed, things I need to pay attention to, study, or otherwise practice. All of these are curated from both online resources and my own experiences.

While I may claim most of these to be right, and it should, make sure to research and confirm anything you wish to take from this file.

If you find something incorrect, feel free to contribute and modify.

## Table of Contents

<!-- TOC -->

- [Study](#study)
    - [Table of Contents](#table-of-contents)
    - [All concepts](#all-concepts)
        - [Compound Assignment Operators](#compound-assignment-operators)
        - [Octal, Hexadecimal and Decimal Formats](#octal-hexadecimal-and-decimal-formats)
            - [Oct, Hex and Decimal Literals](#oct-hex-and-decimal-literals)
            - [Printing Oct, Hex or Decimal Integers](#printing-oct-hex-or-decimal-integers)
        - [Common Mistakes](#common-mistakes)
        - [Binary Trees](#binary-trees)
        - [String.split Trailing Strings](#stringsplit-trailing-strings)
        - [Java's Primary Four OOP Concepts](#javas-primary-four-oop-concepts)
        - [Abstraction vs Encapsulation](#abstraction-vs-encapsulation)
        - [Other important OOP concepts](#other-important-oop-concepts)
        - [Bad AP practices](#bad-ap-practices)
            - [Why?](#why)
            - [Guidelines](#guidelines)
        - [Most Common Imports](#most-common-imports)
        - [Declare, Instantiate, Initialize](#declare-instantiate-initialize)
        - [!!! Default Array Values](#-default-array-values)
            - [!!! Primitive Arrays](#-primitive-arrays)
            - [!!! ArrayList Arrays](#-arraylist-arrays)
        - [When Primitive Array Casts Are Required](#when-primitive-array-casts-are-required)
        - [!!! Basic Boolean Circuitry](#-basic-boolean-circuitry)
        - [!!! How to Rotate Matrixes](#-how-to-rotate-matrixes)
        - [!!! Boolean Truth Tables & Symbols](#-boolean-truth-tables--symbols)
        - [!!! Bitwise Meanings](#-bitwise-meanings)
        - [!!! How to Sort Different Types of Arrays](#-how-to-sort-different-types-of-arrays)
            - [!!! Primitives](#-primitives)
            - [!!! List Implementers (ArrayList, ?, ?..)](#-list-implementers-arraylist--)
        - [!!! char And int Are Interchangeable](#-char-and-int-are-interchangeable)
        - [Classes Call From Where They Originate](#classes-call-from-where-they-originate)
        - [`List.remove(int i)` Element Shifting](#listremoveint-i-element-shifting)
        - [`List.set(int i, Object obj)` requires a element to function](#listsetint-i-object-obj-requires-a-element-to-function)
        - [How `String.compareTo` Functions](#how-stringcompareto-functions)
        - [Access Privileges](#access-privileges)
            - [Public, Protected, Private](#public-protected-private)
            - [Privilege Level Never Changes](#privilege-level-never-changes)
            - [Access is Revoked, Methods Are Not Destroyed](#access-is-revoked-methods-are-not-destroyed)
        - [Are Arrays Pass By Value or Pass By Reference?](#are-arrays-pass-by-value-or-pass-by-reference)
        - [`Scanner.useDelimiter` And How Tokens Are Scanned](#scannerusedelimiter-and-how-tokens-are-scanned)
            - [What Are Tokens?](#what-are-tokens)
            - [What Are Delimiters?](#what-are-delimiters)
            - [`hasNext` and `hasNextInt`](#hasnext-and-hasnextint)
            - [`next` and `nextInt`](#next-and-nextint)
            - [Examples with Explanations](#examples-with-explanations)
        - [!!! Post-increment and Post-decrement Order is Important](#-post-increment-and-post-decrement-order-is-important)

<!-- /TOC -->

## All concepts

### Compound Assignment Operators

```java
int x = 12;
// x = x + (int)2.6;
x += 2.6;
```

Compound assignment operators effectively cast *before assigning values* to the specified variables.

### Octal, Hexadecimal and Decimal Formats

#### Oct, Hex and Decimal Literals

Octal Literals are defined by a zero prefix.

```java
012 + 021 = ?
012 = 8 + 2 = 10
021 = 16 + 1 = 17
10 + 17 = 27
```

Decimal literals are defined in traditional form, 0-9 in base 10.

Hexadecimal literals are defined with the *case insensitive* character set `a-fA-F0-9` and prefixed with `0x` or `0X`.

`0xFace = 61440 + 2560 + 192 + 14 = 64206`

Binary literals are defined with a `0b` or `0B` prefix.

`0b10101 = 16 + 4 + 1 = 21`

#### Printing Oct, Hex or Decimal Integers

String.format has the ability to print Decimal, Octal and Hexadecimal integers. However, they don't take on the traditional form that the literals do.

```java
out.println(String.format("%o", 4358));
> 10406
out.println(String.format("%o", 0x5FAE));
> 57656
out.println(String.format("%x", 07703));
> fc3
out.println(String.format("%4.1f", 28.16));
> 28.2
```

Despite their literal counterparts having prefixes of `0` and `0x` or `0X`, Octal and Hexadecimals will not be printed with a prefix. Adding one is as simple as typing the prefix in front, though, so there is nothing stopping you from doing it yourself should it be required.

For bases other than 8, 10 and 16, Java provides `Integer.toString(int i, int radix)` and `Integer.toBinaryString(int i)` for binary representations.

### Common Mistakes

Most common mistakes come from not looking at a myriad of things closely.

Here's a list of things you might want to pay attention to:

* Return Types
    - `double multiple(int a, int b)`
* Increments/Decrements when moving along Lists
    - `arr.remove(i--);`
* Short to Int needs a Explicit Cast Due To Lossy Conversion
    - `short a = 32;`
    `short b = 48;`
    `short ab = (short) a + b;`

### Binary Trees

Become familiar with how Binary Search Trees are created, and how one can manipulate them to create and level of elements.

More specifically, how many levels can a 32 element tree have if maximized?

Additionally, the concepts below should be studied and memorized for maximum effect.

* Min Heap
    * Every node's child nodes are greater or equal to itself (i.e. smaller nodes higher up).
    * Binary Tree is complete.
* Max Heap
    * Every node's child nodes are less than or equal to itself (i.e. greater nodes higher up).
    * Binary Tree is complete.
* Complete Trees
    *  Every level is filled (no null/empty spaces) except for the last, which has all nodes as far to the left as possible.
* Perfect Trees
    * Every internal node has two children, and all leaf nodes are at the same level.
        * The binary tree will look like a triangle at every height.
        * The tree will contain `(2 ^ h) - 1` nodes.
* Full Trees
    * Also known as a "Proper Binary Tree" or "2-tree" or "Strict Tree".
    * A tree where every node other than the Leaf Nodes has at least 2 has two children.
* Balanced Trees
    * A binary tree in which the left and right subtrees of every node differ in height by no more than 1.
    * To determine if a binary tree is unbalanced, find a node where the subtree on the left or right is two levels deeper (or 'shallower') than the other.
* Binary Search Tree (BST) Trees
    * The left subtree of a node contains only nodes with keys lesser than the node’s key.
    * The right subtree of a node contains only nodes with keys greater than the node’s key.
    * The left and right subtree each must also be a binary search tree.
* Child Node
    * Self explanatory, a node to the bottom left or right of a parent node. Child nodes traditionally do not include child nodes of child nodes, which is is instead referred to as a "subtree" or "grandchild node".
* Leaf Node
    * A node where the left and right child nodes are null or empty.
    * Can be spotted usually at the "bottom" of the tree, or "farthest" from the root node.

### String.split Trailing Strings

String.split will create empty strings, but trailing empty strings will not be included in the final array. Additionally, a integer can be appended to define a limit to the number of "splits" that will occur.

```java
out.println("xxOOxx".split("x"));
> ["", "", "00"]
out.println("xxx".split("x"));
> ["", "", ""]
out.println("xx0x0x0x".split("x"));
> ["", "", "0", "0", "0"]
out.println("xx0x0x0x".split("x", 3));
> ["", "", "0", "0x0x"]
```

### Java's Primary Four OOP Concepts

* Abstraction
    * Complex objects are given relatively simple forms. Complex code is hidden within a much simpler face. Code is rewritten less. Very similar to Encapsulation.
    ```java
    class Maze {
        // Contains few overloaded public methods, but many complex private methods.
        // Most of these methods wouldn't be easy to document the purpose of 
        // to project inactive developers thus they are made private for
        // user simplification.
        public Image render()
        public Image render(int x, int y)
        public void progress()
        public void progress(int steps)
        public void finish()
        private Node getNeighbors(int x, int y)
        private Node[] pathBetween(int x1, int y1, int x2, int y2)
        private Node[] pathBetween(Node start, Node end)
        private void initialize(boolean restart)
    }
    ```
* Encapsulation
    * Fields are kept private. Getters and Setters are used to access values within objects. The primary purpose is to make sure values that are not intended to be modified are not modified without the developer's explicit intention. See below for a example showing how money must be regulated before being added to a Student's account.
    ```java
    class Student extends Person {
        private double lunchMoney;
        public double addLunchMoney(int value) {return this.addLunchMoney((double)value)}
        public double addLunchMoney(double value) {
            assert value >= 0.0 : "You cannot add negative money to a Student's account!";
            value = value * 100
            this.lunchMoney += round(value, 2); // Nothing
        }
        private double round(int val, int places) {
            return ((int) val * Math.pow(10, places)) / Math.pow(10, places);
        }
    }
    ```
* Inheritance
    * Classes can use features and attributes from previously defined classes so as to not repeat code, and add unique, customized implementations of code quickly. What could be a extraordinarily large, nearly impossible to maintain codebase, can be simplified using Inheritance and a standardized style of implementation (i.e. `super` is expected to always work in a certain way even post modification).
    ```java
    class Organism
    class Animal extends Organism
    class Bipedal extends Animal
    class Ape extends Bipedal
    class Human extends Ape
    class Person extends Human
    class HumanChild extends Person
    class HumanAdult extends Person
    class BlueCollarWorker extends HumanAdult implements Worker
    class WhiteCollarWorker extends HumanAdult implements Worker
    class SoftwareEngineer extends WhiteCollarWorker implements Worker, Engineer
    ```
* Polymorphism
    * The ability for an object to take many forms. Most commonly used when a `parent class reference` is used to refer to a `child class object`.
        *   ```java
            Deer d = new Deer();
            Animal a = d;
            Vegetarian v = d;
            Object o = d;
            ```
    * Includes Method Overloading and Method Overriding, Inheritance by extension.
        * Method Overloading
            * A method may perform differently based on how it is called (i.e. different return type given different arguments).
            ```java
            int calculate(int val)
            double calculate(int val)
            double calculate(int val, int limit)
            ```
        * Method Overriding
            * Uses Polymorphism to *override* the functionality of a parent class's methods.
            ```java
            class Person {
                void live() {e}
                void tick() {
                    this.live()
                }
            }

            class Student extends Person {
                void study() {}
                void tick() {
                    this.study()
                    super.tick(); // Calls Person.tick()
                }
            }
            ```

### Abstraction vs Encapsulation

I've noticed that Abstraction and Encapsulation look *very* similar, and looking it up, it seems it's pretty common to mix it up.

![Abstraction vs Encapsulation Diagram by "zaynali53"](https://i.imgur.com/LzTH3qu.png)

I would mostly pay attention to what it looks like, more than truly understanding it. Tests will more likely show code with `Interface/Abstract Class` and ask you to determine what OOP Concept is being demonstrated, rather than a complex concept example.

### Other important OOP concepts 

* Coupling
    * Refers to how tightly or loosely two objects depend on or use eachother.
        * Tight Coupling 
            * For example, a tightly coupled relationship, a Human and his/her Heart, they are highly dependent on eachother to continue life.
            ```java
            class Human {
                private Heart heart;
                
                Human(int age) {
                    this.heart = new Heart(age);
                }
            }

            class Heart {
                Heart(int age) {}
            }
            ```
            * I'd believe the most likely thing you want to look for is a object within an object, especially one with methods that seem to  'pair' the two objects to eachother.
                * If you tried to implement the second object within a different outer class, how much code would have to change to handle this larger usability?
        * Loose Coupling
          
            * For example, a Teacher and a Student. Interactions between these two objects can be observed as infrequently as one desires, to the point where one can exist without the other.

### Bad AP practices

Some practices are likely to earn a more detailed inspection of the code than others, some stand out more, essentially. Here's a short compilation of what practices most likely win in this regard...

#### Why?

Grading of Handwritten code is more difficult and harder to do, as it can't be quickly verified to work, and since real life implementation of code is much more messy (no one writes perfect code the first time), it's probably a good thing AP graders are not going to grade Free Responses with perfect accuracy.

The take-away from this is essentially: write the most boring code, conform to the standards, don't be special. Graders will be seeing a million tests that look all the same, and they won't be able to dedicate their time to each and every problem. Looking like all the others can only help you when you make a tiny mistake.

In fact, it's likely that aspiring Software Engineers will want to get used to this treatment for the future, as writing code that is easy to understand is far more valuable than code that no one can understand (even if it works a little better in the short run).

#### Guidelines

* Ternary Operators
  
    * Ternary Operators are a uncommon operator, and while AP test graders should know of it, it might be better to retreat to a much simpler `if else` statement in the long run.
* Variable Names
    * Try to decipher what most people would likely name a variable from the prompt.
    * If the prompt says "number of days passed", you should write `int daysPassed`, but not `int DAYSPASSED`, or `double daysGONEby` and so forth.
* Good Handwriting
    * Bad handwriting requires more attention when grading, attempt to write cleaner so as to reduce the time graders spend looking at your paper.
    * Make sure your lines are straight across the paper, and do not slant haphazardly.
    * Write somewhat large. Not excessively large, but larger than one would do for Essays in English and other writing assignments. Gauge how much room you need to answer the Free Response and write in a size accordingly.
    * APCentral has actually released a example of good penmanship, seen below.
    ![APCentral Penmanship Example](https://i.imgur.com/mx1PP73.gif)
* Bracketing Style
    * When writing brackets, follow this format (I may not be following this format in the rest of this file, sorry).
    ```java
    class File
    {
        private String path;
        File(String path)
        {
            this.path = path;
        }

        void print()
        {
            // print the file
        }
    }
    ```

### Most Common Imports

```java
import static java.lang.System.out;
> out.println("shorthand println");
import java.io.File; // Read and Write files
> File input = new File("input1.dat"); // can be thrown directly into a Scanner's input
import java.io.FileNotFoundException;
> public static void main(String[] args ) throws FileNotFoundException {
import java.util.Scanner;
> Scanner s = new Scanner();
> s.nextInt();
> s.hasNextInt();
> s.nextLine();
import java.util.Arrays;
> Arrays.toString(new int[]{});
```

### Declare, Instantiate, Initialize

* Declaration: The code set in bold are all variable declarations that associate a variable name with an object type.
    ```java
        type name;
        int age;
        double magnitude;
        Point origin;
    ```
* Instantiation: The new keyword is a Java operator that creates the object.
    ```java
        Point origin = new Point();
    ```
    * Initialization: The new operator is followed by a call to a constructor, which initializes the new object. Instantiation and Initialization almost always occur on the same line, in the same instant.
    ```java
    class Point
    {
        private int x;
        private int y;
        Point(int x, int y)    <--- Initialization
        {                      <---      is
            this.x = x;        <---   occurring
            this.y = y;        <---     right
        }                      <---     here
    }
    ```

### !!! Default Array Values

#### !!! Primitive Arrays

[Run on Repl.it](https://repl.it/@Xevion/PrimitiveArrayNullOrZero)

#### !!! ArrayList Arrays

### When Primitive Array Casts Are Required

Primitive Arrays can be declared and initialized in all these ways.

```java
int[] myIntArray = new int[3];
int[] myIntArray = {1, 2, 3};
int[] myIntArray = new int[]{1, 2, 3};
```

Option 3 may seem rather extraneous to the Java language, but it actually has a very specific purpose.

```java
static String[] method() {
    return {"Method1", "Method2", "Method3"};
}
```
will not compile, as the compiler expects a type for primitive array literals.

```java
static String[] method2() {
    return new String[]{"Method1", "Method2", "Method3"};
}
```

It also expects a `new`, not just `String[]` before the *literal* part of a literal array definition.

### !!! Basic Boolean Circuitry

### !!! How to Rotate Matrixes

### !!! Boolean Truth Tables & Symbols

### !!! Bitwise Meanings

### !!! How to Sort Different Types of Arrays

For arrays, they can be sorted using `Arrays.sort` from `java.util.Arrays`.

For lists, one can use `Collections.sort` to sort any class that implements the `List` interface.

#### !!! Primitives

#### !!! List Implementers (ArrayList, ?, ?..)

### !!! char And int Are Interchangeable

```
void method(int a, char b)
method('c', 44)
// method('g', 78900)
```
char limit 65535

### Classes Call From Where They Originate

Classes call from where they originate, not where they are.
For example, given this setup, can you guess what will occur?

```java
class A {
    A() {
        method();
    }

    void method() {
        out.println("A");
    }
}

class B {
    B() {
        out.println("*");
    }

    void method() {
        out.println("B");
    }
}
```

```
A obj = new B();
```

Here, it would be normal to assume that it simply prints a asterisk.

But, as one should know, Java inserts a no-argument `super()` call to the superclass *if one is not **explicitly** invoked* inside the subclass constructor.

Behind the scenes, `B`'s constructor really behaves like this:

```java
B() {
    super()
    out.println("*");
}
```

Once you understand this, another half of the problem is unlocked, but here, you're still not out of the water yet. Despite the class `A` constructor calling `method()` from it's view in class `A`, it will in fact actually call the `method()` at class `B`.

Methods called at any level, unless explicit called using `super.method()`, methods will always refer to methods found at the origin class (by this, I mean, it will always call class `B` methods).

*Some more testing is order to see how variables, super() and more interact when called through a super constructor, as well as interaction*

### `List.remove(int i)` Element Shifting

[RemoveEvenIntegers on repl.it](https://repl.it/@Xevion/RemoveEvenIntegers)

The `List.remove` method interface method shifts all elements over to the left. It's important that you don't increment, and that you instead *decrement* while using a for loop along lists like this.

Make sure while answering multiple choice with questions like that that you pay attention to how they move along the array.

```java
public static void clearInteger(ArrayList<Integer> arr) {
        for(int i = 0; i < arr.size(); i++)
            if(arr.get(i) % 2 == 0)
                arr.remove(i--);
    }
```

### `List.set(int i, Object obj)` requires a element to function

Lists will not dynamically resize to accommodate the `.set()` method, i.e. to use set at a specified index, the index must be a *valid* position in that array to access using `.get(int i)` or `.remove(int i)`.

### How `String.compareTo` Functions

I've personally been doing `String.compareTo(String other)` incorrectly for a little too long. Here is how it works:

For each character in the array, compare for equality until one does not match. If

### Access Privileges

#### Public, Protected, Private

`public`, `protected`, `private` and *a lack of a modifier* are the three possible *access modifiers* one can set for *classes, interfaces, variables and methods*.

- `public`
    - Anything and everything can access this.
- *`(no modifier)`*
    - A
- `protected`
    - ?
- `private`
    - ?

#### Privilege Level Never Changes

When a method is defined in a superclass, whatever access privilege it was given cannot change.

Method access privilege level does not change, and subclasses MUST take on the exact same access modifier that the superclasses' original method did too.

#### Access is Revoked, Methods Are Not Destroyed

When you look into how `private` members function, some online resources will claim that the member is not inherited by subclasses, however, this isn't true.

Subclasses must retain the functionality of the superclass at minimum, and they do this through standard inheritance. You'll recall how if methods aren't overwritten, they simply 'copy' (or just go 'through' the super) the method of the superclass.

Subclasses do this too, and even if a private method isn't accessible, previously written methods that DO access the private method can do it for you.

Here's an example:

```java
class A {
    private List<Integer> read()
    public int sum() // returns the sum of read()
    public int product() // returns the product of read()
}
```

```java
class B {
    // sum() and product() are still accessible, even if read() is private!
}
```

Class B still retains the functionality provided by `sum()` and `product()`, even if these methods happen to be private.

Through getters and setters, `private` fields inaccessible to `B` could be accessed.

### Are Arrays Pass By Value or Pass By Reference?

In Java, arrays are **Pass By Value**, however, what is being passed is the `reference` to the array.

If you're familiar with how Objects work, you'll remember how literally everything in Java is "Pass By Value".

Arrays work in this fashion too, where arrays references are passed by value, but the array is kept the same, and changes made using that reference will show up elsewhere if not cloned using `array.clone()` or some other array cloning method.

There is a lot more to how shallow and deep cloning works, but for tests, all you need to know is that arrays function in a way most similar to `Pass by Reference`, just like Objects.

### `Scanner.useDelimiter` And How Tokens Are Scanned

Scanners are incredibly useful objects with many useful functions. One of them ends up being `useDelimiter`, which can be an effective way of skipping time consuming `String.split` operations.

#### What Are Tokens?

Tokens are the pieces of text you want to extract from a given input. They are pieces of text, integers, doubles, binary or whitespace, and are completely determined by the delimiters that separate them.

#### What Are Delimiters?

#### `hasNext` and `hasNextInt`

A important distinction in how exactly these functions work.

hasNext is a basic function telling you whether or not a token is still left for processing in the Scanner's input. It's the most useful of them all, allowing users to never run into exceptions for processing empty data or going too far.

hasNextInt is similar, and like the name, it simply asks if the next token is an int, and also, whether or not there is a token left.

This is mostly self explanatory, but it's important to remember that when used with a while loop, **it will pause immediately when a non integer token is found**, it won't skip forward to the next token in the sequence.

#### `next` and `nextInt`

`Scanner.next` and `Scanner.nextInt` are the other side of `hasNext` and `hasNextInt`, instead of telling you whether it's safe, whether or not the token exists for the taking, they take the token.

Again, to emphasize this clearly, as it escaped me completely when I looked into it: the next token MUST match the expected type that the method is requesting.

While `next` expects no particular type, `nextInt` expects a Integer, and `nextDouble` expects a Double.

This means that it won't skip ahead to the next set of tokens in the sequence until it finds a valid token, it will only check the exact next token in the sequence, and if it doesn't match, it will raise an exception.

#### Examples with Explanations

[Scanner.useDelimiter Usage](https://repl.it/@Xevion/ScanneruseDelimiter-Usage) on repl.it

```java
Scanner s = new Scanner("5.2.1.2\\2\\2.2");
s.useDelimiter("\\.");
int sum = 0;
while(s.hasNextInt()) {
    int i = s.nextInt();
    out.println(i);
    sum += i;
}
out.println(sum);
```
> yields 5 2 1 8

Given the delimiter `\\.` (a escaped period), the tokens accessible by the Scanner are `5` `2` `1` `2\\2\\2` and `2`.

But since we're working with a `haSNextInt` with no other way to skip through non-Integer tokens, it will fail to match upon the very strange mess of escaped backslashes and 2s that is `2\\2\\2`.

The only tokens accepted were `5`, `2`, and `1`, which gives us a sum of `8`.

```java
s = new Scanner("2 3 allure 5 yellow 39");
while(s.hasNextInt())
    out.println(s.nextInt());
```
> yields 2 3

Similar to above, the possible tokens given the default delimiter of all whitespace (`\\s+` in regex) are `2` `3` `allure` `5` `yellow` and `39`.

Since the first word, `allure` does not pass the Integer regular expression as defined by the Oracle JavaSE docs, `hasNextInt` returns false after Scanner yielded only two tokens, `2` and `3`.

```java
ArrayList<String> arr = new ArrayList<String>();
s = new Scanner("\t \t a  b  \t ");
s.useDelimiter("\\s");
while(s.hasNext())
    arr.add(s.next());
out.println(arr);
```
> yields [, , , a, , b, , , ]

Since the delimiter isn't `\\s+`, every other whitespace element (including the tabs) can be a token (similar to how String.split works, except trailing 0-length tokens are NOT thrown out).

Notice how spaces in between tokens are no longer respected since the default delimiter has been overwritten with a very messed up delimiter.

```java
s = new Scanner("2 h j l 3 4 o p 0 9");
for(int i = 0; i++ < 5;)
    out.println(s.nextInt());
```
> yields `2, InputMismatchException`

The Scanner will throw an exception while trying to parse `h` for an Integer. This is why `hasNextInt` is used, besides ensuring the Scanner input is not exhausted.


### !!! Post-increment and Post-decrement Order is Important

```java
public static int m(int x, int y) {
    if(x == 0)
        return 1;
    else
        return m(--x, y) * x;
}
```