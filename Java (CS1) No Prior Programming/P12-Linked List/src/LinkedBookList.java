

public class LinkedBookList {

	private BookNode head;
	private int size;
	
	public LinkedBookList(){
		head = null;
		size = 0;
	}
	
	//returns size of the list
	public int size(){
		return size;
	}
	
	//IMPLEMENT -- adds a book to the end of the linked list
	public void add(Book b){
		BookNode newNode = new BookNode(b);
		BookNode current = head;
		if (head == null) {
			head = newNode;
			size++;
			return;
		}

		while (current.getNext() != null)
			current = current.getNext();
		current.setNext(newNode);
		size++;
	}
	
	//IMPLEMENT -- adds a book at the specific index, 
	//  or at the end if index is greater than size
	public void add(Book b, int index){
    	BookNode newNode = new BookNode(b);
    	BookNode current = head;
    	if (head == null) {
    		head = newNode;
    		size++;
    		return;
    	}
    	
    	if (index <= 0) {
    		// regard index < 0 as index = 0
    		newNode.setNext(current);
    		head = newNode;
    		size++;
    	} else if (index >= size)
    		// regard index >= size as index = size
    		add(b);
    	else {
        	for (int i = 1; i < index; i++) {
        		current = current.getNext();
        	}
        	newNode.setNext(current.getNext());
        	current.setNext(newNode);
        	size++;
    	}
	}
	
	//IMPLEMENT -- removes a book and returns it, or 
	//	returns null if book is not present
	public Book remove(Book b){
		if (b == null || head == null)
			return null;
		
		BookNode oldNode = new BookNode(b);
		
		if (b.equals(head.getBook())) {
			head = head.getNext();
			size--;
			return oldNode.getBook();
		}
		
		for (BookNode bookNode = head; bookNode.getNext() != null; 
				bookNode = bookNode.getNext()) {
			if (b.equals(bookNode.getNext().getBook())) {
				BookNode removeNode = bookNode.getNext();
				bookNode.setNext(bookNode.getNext().getNext());
				size--;
				return removeNode.getBook();
			}
		}
		
		// return null if the book is not present.
		return null;
	}

	//IMPLEMENT -- removes a book at a specific index and returns it, 
	//	or returns null if index is not present
	public Book remove(int index){
		if (index >= size || index < 0 || head == null) 
			return null;
		
		BookNode current = head;
		
		if (index == 0) {
			head = head.getNext();
			size--;
			return current.getBook();
		} else {
			for (int i = 1; i < index; i++) {
				current = current.getNext();
			}
			BookNode removeNode = current.getNext();
			current.setNext(current.getNext().getNext());
			size--;
			return removeNode.getBook();
		}
	}
	
	//IMPLEMENT -- returns the total number of pages in the linked list
	public int totalPages(){
		if (head == null)
			return 0;
		int totalNum = 0;
		for (BookNode bn = head; bn != null; bn = bn.getNext())
			totalNum += bn.getNumPages();
		return totalNum;
	}
	
    public String toString() {
        String res = "";
        for (BookNode pos = head; pos != null; pos = pos.getNext()) {
            if (pos.getBook() == null) {
                res += "null";
            } else {
                res += pos.getBook();
            }
            if (pos.getNext() != null) 
            	res += "\n";
            }
            return res;
    }
}
