
public class Book {

	private String title;
	private String author;
	private int numPages;
	
	public Book(String title, String author, int numPages)
	{
		this.title = title;
		this.author = author;
		this.numPages = numPages;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public int getNumPages(){
		return numPages;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	
	public String toString()
	{
		return "\"" + title + "\" by " + author;
	}
	

	public boolean equals(Object other)
	{
		if (other instanceof Book) {
			return equals((Book)other);
		}
		return false;
	}
	
	public boolean equals(Book other)
	{
		return other != null && title.equals(other.title) && author.equals(other.author);
	}
}

