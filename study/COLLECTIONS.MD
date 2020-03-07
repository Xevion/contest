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

<!-- /TOC -->

## Hierarchy of Collection Framework

All datastructures referenced here can be imported using `import java.util.<structure>;`, or just `import java.util.*;` (I recommend making more direct import statements as a 'good practice' but it does not matter in the end).

[![Hierarchy of Interfaces and Classes from Collection (Iterable)](https://i.imgur.com/fifX1Ek.png)](https://static.javatpoint.com/images/java-collection-hierarchy.png)

## Methods of the Collection Interface

| Method                      | Description                                         |
|-----------------------------|-----------------------------------------------------|
| **public boolean add(E e)** | It is used to insert an element in this collection. |
| **public boolean addAll(Collection<? extends E> c)** | It is used to insert the specified collection elements in the invoking collection. |
| **public boolean remove(Object element)** | It is used to delete an element from the collection. || **public boolean removeAll(Collection<?> c)** | It is used to delete all the elements of the specified collection from the invoking collection. |
| **default boolean removeIf(Predicate<? super E> filter)** | It is used to delete all the elements of the collection that satisfy the specified predicate. |
| **public boolean retainAll(Collection<?> c)** | It is used to delete all the elements of invoking collection except the specified collection. |
| **public int size()** | It returns the total number of elements in the collection. |
| **public void clear()** | It removes the total number of elements from the collection. |
| **public boolean contains(Object element)** | It is used to search an element. |
| **public boolean containsAll(Collection<?> c)** | It is used to search the specified collection in the collection. |
| **public Iterator iterator()** | It returns an iterator. |
| **public Object[] toArray()** | It converts collection into array. |
| **public <T> T[] toArray(T[] a)** | It converts collection into array. Here, the runtime type of the returned array is that of the specified array. |
| **public boolean isEmpty()** | It checks if collection is empty. |
| **default Stream<E> parallelStream()** | It returns a possibly parallel Stream with the collection as its source. |
| **default Stream<E> stream()** | It returns a sequential Stream with the collection as its source. |
| **default Spliterator<E> spliterator()** | It generates a Spliterator over the specified elements in the collection. |
| **public boolean equals(Object element)** | It matches two collections. |
| **public int hashCode()** | It returns the hash code number of the collection. |