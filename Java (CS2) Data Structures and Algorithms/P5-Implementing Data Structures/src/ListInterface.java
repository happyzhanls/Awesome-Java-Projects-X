// ListInterface.java - List interface for data structure assignment
// Author: Chris Wilcox
// Date:   2/17/2017
// Class:  CS165
// Email:  wilcox@cs.colostate,edu

public interface ListInterface<E> {

    // Any method that accepts an index may throw
    // an IndexOutOfBoundsException
    
    // Add element to end of list
    public abstract boolean add(E e);
    
    // Add element at specified index
    public abstract void add(int index, E e);

    // Remove specified element from list
    public abstract boolean remove(Object o);
    
    // Remove element at specified index
    public abstract E remove(int index);
    
    // Gets element at specified index
    public abstract E get(int index);

    // Sets element at specified index
    public abstract E set(int index, E e);

    // Returns whether list contains object 
    public abstract boolean contains(Object o);
    
    // Returns index of first object or -1 
    public abstract int indexOf(Object o);

    // Returns index of last object or -1 
    public abstract int lastIndexOf(Object o);

    // Removes all elements from list 
    public abstract void clear();
    
    // Returns number of elements 
    public abstract int size();

    // Returns whether list is empty 
    public abstract boolean isEmpty();
}

