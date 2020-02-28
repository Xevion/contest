# Collections

Java contains many object based data structures, some sorted, some limited, some simple, some complex.

Not all of them work in familiar ways, and their methods can be confusing without proper usage, study, and a quick explanation over how they're used.

#### Interfaces
##### Collection

Common Implementing SubInterfaces:
* Deque
* List
* Queue
* Set
* SortedSet

Common Implementing Classes: 
* ArrayList
* ArrayDeque
* EnumSet
* HashSet
* LinkedList
* PriorityQueue
* Stack
* TreeSet

##### Iterable 

Common Implementing SubInterfaces
* Collection
* Deque
* List
* Queue
* Set
* SortedSet

Common Implementing Classes:
* ArrayDeque
* ArrayList
* EnumSet
* HashSet
* LinkedList
* PriorityQueue
* Stack
* TreeSet

##### List

Super Interfaces:
* Collection
* Iterable

Implementing Classes:
* ArrayList
* LinkedList
* Stack

A unordered collection (also known as a *sequence*) that allows precise control over where each element is inserted in a list.

Users have methods allowing all of the following to methods:
* 4 methods providing positional index-based access to list elements.
* 1 method for accessing a special iterator, `ListIterator` allowing element insertion and replacement starting from a specified position.
* 2 methods for searching for a specified object. Usually *costly* linear searches.
* 2 methods for efficiently inserting and removing *multiple* elements at an arbitrary point.

##### Set
##### Queue
##### Deque
##### SortedSet
##### Map
##### SortedMap
##### NavigableMap

#### Classes
##### ArrayList
##### LinkedList
##### Stack
##### PriorityQueue
##### TreeSet
##### ArrayDeque
##### EnumSet
##### HashSet
##### HashMap