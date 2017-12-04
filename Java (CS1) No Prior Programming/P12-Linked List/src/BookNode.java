public class BookNode {
	private Book book;
	private BookNode next;
	
	public BookNode(Book newBook)
	{
		this(newBook, null);
	}
	
	public BookNode(Book newBook, BookNode newNext)
	{
		setBook(newBook);
		setNext(newNext);
	}

	public Book getBook()
	{
		return book;
	}
	
	public int getNumPages(){
		return getBook().getNumPages();
	}
	
	public void setBook(Book newBook)
	{
		book = newBook;
	}
	
	public BookNode getNext()
	{
		return next;
	}
	
	public void setNext(BookNode newNext)
	{
		next = newNext;
	}
	
	public String toString()
	{
		return book.toString();
	}
}
