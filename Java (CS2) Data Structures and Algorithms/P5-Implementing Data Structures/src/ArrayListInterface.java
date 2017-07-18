import java.util.Comparator;

// ArrayListInterface.java - ArrayList interface for data structure assignment
// Author: Chris Wilcox
// Date:   2/17/2017
// Class:  CS165
// Email:  wilcox@cs.colostate,edu

public interface ArrayListInterface<E> extends ListInterface<E> {

    // Trims capacity to size
    public abstract void trimToSize();
}

