import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class R20 {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		LinkedBookList lbl = new LinkedBookList();
		
		Scanner scan = new Scanner(new File("top10.txt"));
		int bookCount = (Integer.parseInt(scan.nextLine()));
		
		//reads in all of the books, and adds them to the linked list
		for(int i = 0; i < bookCount; i++){
			String line = scan.nextLine();
			String[] parts = line.split("\t");
			String name = parts[0];
			String title = parts[1];
			int numPages = Integer.parseInt(parts[2]);
			Book b = new Book(name, title, numPages);
			lbl.add(b);
		}
		
		System.out.println("Testing add");
		System.out.println(lbl);
		System.out.println(lbl.totalPages());
		System.out.println(lbl.size() + "\n");
		Book b1 = new Book("Harry Potter", "J. K. Rowling", 100);
		
		lbl.add(b1, 5);
		System.out.println(lbl);
		System.out.println(lbl.totalPages());
		System.out.println(lbl.size() + "\n");
		
//		LinkedBookList lbl0 = new LinkedBookList();
//		System.out.println(lbl0);
//		lbl0.add(b1);
//		System.out.println(lbl0);
		
		lbl.remove(5);
		System.out.println(lbl);
		System.out.println(lbl.totalPages());
		System.out.println(lbl.size() + "\n");
		
		lbl.remove(b1);
		System.out.println(lbl);
		System.out.println(lbl.totalPages());
		System.out.println(lbl.size());
		scan.close();
	}
}

