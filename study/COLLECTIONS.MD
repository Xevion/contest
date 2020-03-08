# Collections

Java contains many object based data structures, some sorted, some limited, some simple, some complex.

Not all of them work in familiar ways, and their methods can be confusing without proper usage, study, and a quick explanation over how they're used.

This page will be using [javaTpoint](https://www.javatpoint.com/collections-in-java) for sources regarding all interfaces and classes referenced in this file.

## Table of Contents

<!-- TOC -->

- [Collections](#collections)
    - [Table of Contents](#table-of-contents)
    - [Hierarchy of Collection Framework](#hierarchy-of-collection-framework)
    - [Methods of the Collection Interface](#methods-of-the-collection-interface)
    - [Interfaces](#interfaces)
        - [Iterator Interface](#iterator-interface)
        - [Iterable Interface](#iterable-interface)
        - [Collection Interface](#collection-interface)
        - [List Interface](#list-interface)
        - [Queue Interface](#queue-interface)
        - [Deque Interface](#deque-interface)
        - [Set Interface](#set-interface)
        - [SortedSet Interface](#sortedset-interface)
    - [Classes](#classes)
        - [ArrayList Class](#arraylist-class)
        - [LinkedList Class](#linkedlist-class)
        - [Vector Class](#vector-class)
        - [Stack Class](#stack-class)
        - [PriorityQueue Class](#priorityqueue-class)
        - [ArrayDeque Class](#arraydeque-class)
        - [HashSet Class](#hashset-class)
        - [LinkedHashSet Class](#linkedhashset-class)
        - [TreeSet Class](#treeset-class)
    - [Similarities and Differences](#similarities-and-differences)
        - [ArrayList vs Vector](#arraylist-vs-vector)
        - [HashSet vs TreeSet vs LinkedHashSet](#hashset-vs-treeset-vs-linkedhashset)
    - [Tips and Tricks](#tips-and-tricks)
        - [Datatype Conversion](#datatype-conversion)

<!-- /TOC -->

## Hierarchy of Collection Framework

All datastructures referenced here can be imported using `import java.util.<structure>;`, or just `import java.util.*;` (I recommend making more direct import statements as a 'good practice' but it does not matter in the end).

[![Hierarchy of Interfaces and Classes from Collection (Iterable)](https://i.imgur.com/fifX1Ek.png)](https://static.javatpoint.com/images/java-collection-hierarchy.png)

## Methods of the Collection Interface

| Method                                                    | Description                                                                                                     |
|-----------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| **public boolean add(E e)**                               | It is used to insert an element in this collection.                                                             |
| **public boolean addAll(Collection<? extends E> c)**      | It is used to insert the specified collection elements in the invoking collection.                              |
| **public boolean remove(Object element)**                 | It is used to delete an element from the collection.                                                            |
| **public boolean removeAll(Collection<?> c)**             | It is used to delete all the elements of the specified collection from the invoking collection.                 |
| **default boolean removeIf(Predicate<? super E> filter)** | It is used to delete all the elements of the collection that satisfy the specified predicate.                   |
| **public boolean retainAll(Collection<?> c)**             | It is used to delete all the elements of invoking collection except the specified collection.                   |
| **public int size()**                                     | It returns the total number of elements in the collection.                                                      |
| **public void clear()**                                   | It removes the total number of elements from the collection.                                                    |
| **public boolean contains(Object element)**               | It is used to search an element.                                                                                |
| **public boolean containsAll(Collection<?> c)**           | It is used to search the specified collection in the collection.                                                |
| **public Iterator iterator()**                            | It returns an iterator.                                                                                         |
| **public Object[] toArray()**                             | It converts collection into array.                                                                              |
| **public <T> T[] toArray(T[] a)**                         | It converts collection into array. Here, the runtime type of the returned array is that of the specified array. |
| **public boolean isEmpty()**                              | It checks if collection is empty.                                                                               |
| **default Stream<E> parallelStream()**                    | It returns a possibly parallel Stream with the collection as its source.                                        |
| **default Stream<E> stream()**                            | It returns a sequential Stream with the collection as its source.                                               |
| **default Spliterator<E> spliterator()**                  | It generates a Spliterator over the specified elements in the collection.                                       |
| **public boolean equals(Object element)**                 | It matches two collections.                                                                                     |
| **public int hashCode()**                                 | It returns the hash code number of the collection.                                                              |

Since all classes discussed onward inherit the `Collections` interface, you can be sure that a valid, working implementation exists for every single one of these methods.

However, the real power comes from the next set of interfaces, and the special methods devised by each class to manipulate these complex data structures.

## Interfaces

### Iterator Interface

### Iterable Interface

The Iterable interface is the root interface for all the collection classes. The Collection interface extends the Iterable interface and therefore all the subclasses of Collection interface also implement the Iterable interface.

It contains only one abstract method, `Iterator<T> iterator()` which returns the `Iterator` object used to iterate over the elements of the datastructure (type `T`).

### Collection Interface

The Collection interface is the interface which is implemented by all the classes in the collection framework. It declares the methods that every collection will have. In other words, we can say that the Collection interface builds the foundation on which the collection framework depends.

All methods the Collection interface implements can be seen [here](#methods-of-the-collection-interface).

### List Interface

List interface is the child interface of Collection interface. It inhibits a list type data structure in which we can store the ordered collection of objects. Lists can have duplicate values.

List interface is implemented by the classes [ArrayList](#arraylist-class), [LinkedList](#linkedlist-class), [Vector](#vector-class), and [Stack](#stack-class).

Remember, that *as a Interface*, `List<T>`s cannot be instantiated, but can be used to type any of the implementing classes. For example:

```java
List<Integer> list; 
list = new ArrayList<Integer>();
list = new LinkedList<Integer>();
list = new Stack<Integer>();
list = new Vector<Integer>();
```

### Queue Interface

### Deque Interface

### Set Interface

### SortedSet Interface

## Classes

### ArrayList Class

ArrayList uses a dynamically sized array.

See [ArrayList vs Vector](#arraylist-vs-vector).

### LinkedList Class

### Vector Class

Vector uses a dynamically sized array to store the data elements, with a functionality most similar to the [ArrayList Class](#arraylist-class)

- Synchronized (Thead Safe, Multi-user Application)

- Uses [Iterator](#iterator-interface) or Enumeration interface for element traversal

See [ArrayList vs Vector](#arraylist-vs-vector).

### Stack Class

### PriorityQueue Class

### ArrayDeque Class

### HashSet Class

### LinkedHashSet Class

### TreeSet Class

## Similarities and Differences

### ArrayList vs Vector

### HashSet vs TreeSet vs LinkedHashSet

## Tips and Tricks

### Datatype Conversion

To convert from Primitive Arrays to Object-based ArrayLists, Stacks or Queues, use `Arrays.asList(T[])`.

It returns a *fixed-size* `List<T>` that directly modifies the entries of the original Array, so be careful.

Most people feed this directly into a new `ArrayList` in order to avoid overwriting data, as well as to take advantage of ArrayList features (dynamically resizing).