// CoolLinkedList.java - student implementation of LinkedList
// Author: ?????
// Date:   ?????
// Class:  CS165
// Email:  ?????@cs.colostate.edu

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CoolLinkedList<E> implements LinkedListInterface<E> {

    // Node data structure
    public class Node {
        // YOUR CODE HERE
    	E item;
    	Node next;
    	Node prev;
    	
    	Node(Node prev, E element, Node next) {
    		this.item = element;
    		this.next = next;
    		this.prev = prev;
    	}
    }
    
    // Head (first) pointer
    private Node listHead;

    // Tail (last) pointer
    private Node listTail;

    // Current size
    private int listSize;

    Node node(int index) {
		if (index < (listSize >> 1)) {
			Node x = listHead;
			for (int i = 0; i < index; i++)
				x = x.next;
			return x;
		} else {
			Node x = listTail;
			for (int i = listSize - 1; i > index; i--)
				x = x.prev;
			return x;
		}
    }
    
    // Default constructor
    public CoolLinkedList() {
        // YOUR CODE HERE
    }

    // Special debug method
    public void printDebug() {
        Debug.printf("LinkedList.size() = %d\n", listSize);
        int index = 0;
        for (Node c = listHead; c != null; c = c.next) {
            String sNode = c.getClass().getSimpleName() + "@" + Integer.toHexString(c.hashCode());
            String sNext;
            if (c.next == null)
                sNext = "null";
            else
                sNext = c.next.getClass().getSimpleName() + "@" + Integer.toHexString(c.hashCode());
            Debug.printf("LinkedList[%d]: element %s, next %s\n", index++, sNode, sNext);
        }
    }
    
    private void checkIndex(int index) {
    	if (index > listSize || index < 0) 
    		throw new IndexOutOfBoundsException("Index: "+index+", Size: "+listSize);     
    }
    
    public boolean add(E e) {
        // YOUR CODE HERE
    	addLast(e);
    	return true;
    }

    public void add(int index, E e) {
        // YOUR CODE HERE
    	checkIndex(index);
    	if (index == listSize)
    		add(e);
    	else {
    		Node x = node(index);
    		final Node pred = x.prev;
    		final Node newNode = new Node(pred, e, x);
    		x.prev = newNode;
    		if (pred == null)
    			listHead = newNode;
    		else
    			pred.next = newNode;
    		listSize++;
    	}
    }
    
    E unlink(Node x) {
    	// assert x != null;
        final E element = x.item;
        final Node next = x.next;
        final Node prev = x.prev;

        if (prev == null) {
            listHead = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            listTail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        listSize--;
        return element;
    }

    public boolean remove(Object o) {
        // YOUR CODE HERE
    	if (o == null) {
    		for (Node x = listHead; x != null; x = x.next) {
    			if (x.item == null) {
    				unlink(x);
    				return true;
    			}
    		}
    	} else {
            for (Node x = listHead; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
    	return false;
    }

    public E remove(int index) {
        // YOUR CODE HERE
    	checkIndex(index);
    	return unlink(node(index));
    }

    public E get(int index) {
        // YOUR CODE HERE
    	checkIndex(index);
		return node(index).item;
    }

    public E set(int index, E e) {
        // YOUR CODE HERE
    	checkIndex(index);
    	Node x = node(index);
    	E oldVal = x.item;
    	x.item = e;
    	return oldVal;
    }

    public boolean contains(Object o) {
        // YOUR CODE HERE
    	return indexOf(o) != -1;
    }

    public int indexOf(Object o) {
        // YOUR CODE HERE
    	int index = 0;
    	if (o == null) {
    		for (Node x = listHead; x != null; x = x.next) {
    			if (x.item == null)
    				return index;
    			index++;
    		} 
    	} else {
    		for (Node x = listHead; x != null; x = x.next) {
    			if (o.equals(x.item))
    				return index;
    			index++;
    		}
    	}
    	return -1;
    }

    public int lastIndexOf(Object o) {
        // YOUR CODE HERE
    	int index = listSize;
    	if (o == null) {
    		for (Node x = listTail; x != null; x = x.prev) {
    			index--;
    			if (x.item == null)
    				return index;
    		}
    	} else {
    		for (Node x = listTail; x != null; x = x.prev) {
    			index--;
    			if (o.equals(x.item))
    				return index;
    		}
    	}
    	return -1;
    }

    public void clear() {
        // YOUR CODE HERE
    	for (Node x = listHead; x != null;) {
    		Node next = x.next;
    		x.item = null;
    		x.next = null;
    		x.prev = null;
    		x = next;
    	}
    	listHead = listTail = null;
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

    public void addFirst(E e) {
        // YOUR CODE HERE
    	final Node h = listHead;
    	final Node newNode = new Node(null, e, h);
    	listHead = newNode;
    	if (h == null)
    		listTail = newNode;
    	else
    		h.prev = newNode;
    	listSize++;
    }

    public void addLast(E e) {
        // YOUR CODE HERE
    	final Node t = listTail;
    	final Node newNode = new Node(t, e, null);
    	listTail = newNode;
    	if (t == null)
    		listHead = newNode;
    	else
    		t.next = newNode;
    	listSize++;
    }

    public E removeFirst() {
        // YOUR CODE HERE
    	final Node h = listHead;
    	if (h == null)
    		throw new NoSuchElementException();
    	// java.util.LinkedList.unlinkFirst
    	final E element = h.item;
    	final Node next = h.next;
    	h.item = null;
    	h.next = null;
    	listHead = next;
    	if (next == null)
    		listTail = null;
    	else
    		next.prev = null;
    	listSize--;
    	return element;
    }

    public E removeLast() {
        // YOUR CODE HERE
    	final Node t = listTail;
    	if (t == null)
    		throw new NoSuchElementException();
    	// java.util.LinkedList.unlinkLast
    	final E element = t.item;
    	final Node prev = t.prev;
    	t.item = null;
    	t.prev = null;
    	listTail = prev;
    	if (prev == null)
    		listTail = null;
    	else
    		prev.next = null;
    	listSize--;
    	return element;
    }

    public void push(E e) {
        // YOUR CODE HERE
    	addFirst(e);
    }

    public E pop() {
        // YOUR CODE HERE
    	return removeFirst();
    }

    public E peek() {
        // YOUR CODE HERE
    	final Node h = listHead;
    	return (h == null) ? null : h.item;
    }

    // Extra credit
    public class ListIterator implements Iterator<E> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			return null;
		}
        // YOUR CODE HERE
    }
    
    public Iterator<E> iterator() {
        return new ListIterator();
    }
}

