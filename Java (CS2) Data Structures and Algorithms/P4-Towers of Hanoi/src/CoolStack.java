

// CoolStack.java - Solution for stack lab
// Author: ?????
// Date:   ?????
// Class:  CS165
// Email:  ?????

import java.util.ArrayList;
import java.util.EmptyStackException;

class CoolStack<E> implements StackInterface<E> {

    // Underlying data structure
    
    // Default constructor
	
	public ArrayList<E> al = new ArrayList<>();
	
	@Override
	public E push(E item) {
		al.add(item);
		return item;
	}

	@Override
	public E pop() {
		if (al.isEmpty()) 
			throw new EmptyStackException();
		E e = al.get(al.size() - 1);
		al.remove(al.size() - 1);
		return e;
	}

	@Override
	public E peek() {
		return al.get(al.size() - 1);
	}

	@Override
	public boolean isEmpty() {
		return al.size() == 0;
	}

	@Override
	public int size() {
		return al.size();
	}

	@Override
	public void clear() {
		al.clear();
	}

	@Override
	public int search(Object o) {
		int index = -1;
		for (int i = 0; i < al.size(); i++) {
			if (o.equals(al.get(i))) 
				index = al.size() - i;
		}
		return index;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public int indexOf(Object o) {
		if (o == null) {
            for (int i = 0 ; i < al.size() ; i++)
                if (al.get(i) == null)
                    return i;
        } else {
            for (int i = 0 ; i < al.size() ; i++)
                if (o.equals(al.get(i)))
                    return i;
        }
        return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = al.size() - 1; i >= 0; i--)
                if (al.get(i) == null)
                    return i;
        } else {
            for (int i = al.size() - 1; i >= 0; i--)
                if (o.equals(al.get(i)))
                    return i;
        }
        return -1;
	}
	
	public E get(int index) {
	    return al.get(index);
	}	
	
	@Override
	public String toString() {
        if (al.isEmpty())
        	return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0 ; i < al.size() ; i++) {
        	sb.append(al.get(i).toString());
        	if (i != al.size() - 1) {
        		sb.append(", ");
        	} else {
        		sb.append(']');
        	}
        }
        return sb.toString();
	}

}
