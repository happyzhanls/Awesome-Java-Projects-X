// StackInterface.java - Interface for stack lab
// Author: Chris Wilcox
// Date:   2/11/2017
// Class:  CS165
// Email:  wilcox@cs.colostate,edu

public interface StackInterface<E> {

    // Push object onto top of stack
    public abstract E push(E item);

    // Push object from top of stack
    // Throws EmptyStackException if empty
    public abstract E pop();

    // Peek object on top of stack
    // Throws EmptyStackException if empty
    public abstract E peek();

    // Check for empty
    public abstract boolean isEmpty();

    // Returns size
    public abstract int size();
    
    // Clears stack
    public abstract void clear();

    // Various searches
    public abstract int search(Object o);
    public abstract boolean contains(Object o);
    public abstract int indexOf(Object o);
    public abstract int lastIndexOf(Object o);

    // Override toString
    public abstract String toString();
}

