// LinkedListInterface.java - LinkedList interface for data structure assignment
// Author: Chris Wilcox
// Date:   2/17/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu

import java.util.Iterator;

public interface LinkedListInterface<E> extends ListInterface<E>  {

    // Adds element to head (first) of list
    public abstract void addFirst(E e);

    // Adds element to tail (last) of list
    public abstract void addLast(E e);

    // Removes element from head (first) of list
    public abstract E removeFirst();

    // Removes element from tail (last) of list
    public abstract E removeLast();
    
    // Pushes element onto head (first) of list
    public abstract void push(E e);

    // Pops element from head (first) of list
    public abstract E pop();

    // Peeks at element at head (first) of list
    public abstract E peek();
    
    // Return an iterator for the list
    public abstract Iterator<E> iterator();
}
