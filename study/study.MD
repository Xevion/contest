# Study

Questions I missed, things I need to pay attention to, study, or otherwise practice.

## 2020

### Compound Assignment Operators
```
int x = 12;
int x += 2.6;
x = x + (int)2.6;
```

Compound assignment operators effectively cast *before assigning values* to the specified variables.

### Octal Literals

Octal Literals are defined by a zero prefix.

```
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

### Return Types

Check **return type** of methods! Pretty simple, but easy to miss when speeding through a test.

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
    * Complex objects are given relatively simple forms. Complex code is hidden within a much simpler face. Code is rewritten less.
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