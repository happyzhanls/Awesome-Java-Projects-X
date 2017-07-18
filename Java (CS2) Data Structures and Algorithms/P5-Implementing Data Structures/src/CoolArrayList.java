// CoolArrayList.java - student implementation of ArrayList
// Author: ?????
// Date:   ?????
// Class:  CS165
// Email:  ?????@cs.colostate.edu

import java.util.Arrays;

public class CoolArrayList<E> implements ArrayListInterface<E> {

    // Declare underlying array
    private Object[] underlyingArray;

    // Current size
    private int listSize;

    // Current capacity
    private int listCapacity;

    // Default capacity
    private static final int DEFAULT_CAPACITY = 16;
    
    // Default empty object array
    private static final Object[] DEFAULT_EMPTY_OBJECT_ARRAY = {};
    
    // Default constructor
    public CoolArrayList() {
        // YOUR CODE HERE
    	this(DEFAULT_CAPACITY);
    }

    // Overload constructor
    public CoolArrayList(int capacity) {
        // YOUR CODE HERE
    	if (capacity > 0) {
        	listCapacity = capacity;
        	listSize = 0;
    		underlyingArray = new Object[capacity];
    	} else if (capacity == 0) {
    		listCapacity = capacity;
    		listSize = 0;
    		underlyingArray = DEFAULT_EMPTY_OBJECT_ARRAY;
    	} else {
    		throw new IllegalArgumentException("Illegal Capacity: "+
                    capacity);
    	}
    }

    // Special debug method
    public void printDebug() {
        Debug.printf("ArrayList.size() = %d\n", listSize);
        Debug.printf("ArrayList.capacity() = %d\n", listCapacity);
        for (int index = 0; index < listSize; index++) {
            E e = (E) underlyingArray[index];
            String sElement = e.getClass().getName() + "@" + Integer.toHexString(e.hashCode());
            System.err.printf("ArrayList[%d]:  %s: %s\n", index, sElement, e.toString());
        }
    }
    
    private void checkIndex(int index) {
    	if (index > listSize || index < 0) 
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + listSize);
    }

    public boolean add(E e) {
        // YOUR CODE HERE
    	reallocateArray(listSize + 1);
    	underlyingArray[listSize++] = e;
    	return true;
    }

    public void add(int index, E e) {
        // YOUR CODE HERE
        checkIndex(index);
        reallocateArray(listSize + 1);
        underlyingArray[index] = e;
        listSize++;
    }

    public boolean remove(Object o) {
        // YOUR CODE HERE
        if (o == null) {
            for (int index = 0; index < listSize; index++)
                if (underlyingArray[index] == null) {
                    int numMoved = listSize - index - 1;
                    if (numMoved > 0)
                        System.arraycopy(underlyingArray, index+1, underlyingArray, index,
                                         numMoved);
                    underlyingArray[--listSize] = null; // clear to let GC do its work
                    return true;
                }
        } else {
            for (int index = 0; index < listSize; index++)
                if (o.equals(underlyingArray[index])) {
                    int numMoved = listSize - index - 1;
                    if (numMoved > 0)
                        System.arraycopy(underlyingArray, index+1, underlyingArray, index,
                                         numMoved);
                    underlyingArray[--listSize] = null; // clear to let GC do its work
                    return true;
                }
        }
        return false;
    }

    public E remove(int index) {
        // YOUR CODE HERE
    	checkIndex(index);
        E oldValue = (E) underlyingArray[index];

        int numMoved = listSize - index - 1;
        if (numMoved > 0)
            System.arraycopy(underlyingArray, index+1, underlyingArray, index,
                             numMoved);
        underlyingArray[--listSize] = null; // clear to let GC do its work

        return oldValue;
    }

    public E get(int index) {
        // YOUR CODE HERE
    	checkIndex(index);
    	return (E) underlyingArray[index];
    }

    public E set(int index, E e) {
        // YOUR CODE HERE
    	checkIndex(index);
    	E oldValue = (E) underlyingArray[index];
    	underlyingArray[index] = e;
    	return oldValue;
    }

    public boolean contains(Object o) {
        // YOUR CODE HERE
    	return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        // YOUR CODE HERE
    	if (o == null) {
            for (int i = 0; i < listSize; i++)
                if (underlyingArray[i]==null)
                    return i;
        } else {
            for (int i = 0; i < listSize; i++)
                if (o.equals(underlyingArray[i]))
                    return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        // YOUR CODE HERE
        if (o == null) {
            for (int i = listSize-1; i >= 0; i--)
                if (underlyingArray[i]==null)
                    return i;
        } else {
            for (int i = listSize-1; i >= 0; i--)
                if (o.equals(underlyingArray[i]))
                    return i;
        }
        return -1;
    }

    public void clear() {
        // YOUR CODE HERE
    	for (int i = 0; i < listSize; i++)
            underlyingArray[i] = null;

        listSize = 0;
    }

    public int size() {
        // YOUR CODE HERE
    	return listSize;
    }

    public boolean isEmpty() {
        // YOUR CODE HERE
    	return listSize == 0;
    }

    public void trimToSize() {
        // YOUR CODE HERE
    	if (listSize < underlyingArray.length) {
    		underlyingArray = (listSize == 0)
              ? DEFAULT_EMPTY_OBJECT_ARRAY
              : Arrays.copyOf(underlyingArray, listSize);
        }
    }

    //
    // Private methods
    //
    private void reallocateArray(int capacity) {
        // YOUR CODE HERE
    	int minCapacity = capacity;
    	if (underlyingArray == DEFAULT_EMPTY_OBJECT_ARRAY) {
    		minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    	}
    	if (capacity - underlyingArray.length > 0) {
    		int oldCapacity = listCapacity;
        	int newCapacity = oldCapacity + (oldCapacity >> 1);
        	// overflow conscious code
        	if (newCapacity - minCapacity < 0)
        		newCapacity = minCapacity;
        	underlyingArray = Arrays.copyOf(underlyingArray, newCapacity);
    	}
    }
}
