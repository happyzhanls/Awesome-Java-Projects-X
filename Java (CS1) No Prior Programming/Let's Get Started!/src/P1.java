/* A HelloWorld kind of Program with seven lines' console output.
 * 
 * The specifications of the seven lines are:  
 * On the first line of output print "Java programming is great!".
 * On the second line of output print your last name, a comma, a space, and your first name.
 * On the third line print your major or "Undeclared".
 * On the fourth line print the value of 1234 multiplied by 2.
 * On the fifth line print the due date of this assignment in the format MM/DD/YYYY.
 * On the sixth line print the "CS163: CS1 Java, No Prior Programming".
 * On the seventh line print a sentence about why you are taking this class.
 * On the seventh line the sentence must obey the following rules additionally: 
 * ~ Must start with a capital letter.
 * ~ Must end with a period.
 * ~ Must be between 60 and 80 characters total, including white space.
 * 
 * Examples:
 * 
 * Java programming is great!
 * Zhan, Linsong
 * Electrical and Computer Engineering
 * 2468
 * 06/12/2017
 * CS163: CS1 Java, No Prior Programming
 * I am in this class because programming using Java is really interesting.
 * 
 */

public class P1 {
	public static void main(String[] args) {
		System.out.println("Java programming is great!");
		System.out.println("Zhan, Linsong");
		System.out.println("Electrical and Computer Engineering");
		System.out.println(1234 * 2);
		System.out.println("06/12/2017");
		System.out.println("CS163: CS1 Java, No Prior Programming");
		String s = "I am in this class because programming using Java is really interesting.";
		if (!(s.charAt(0) >= 'A' && s.charAt(0) <= 'Z')) {
			System.out.println("ERROR: seventh line should start with a capital letter!");
		} else if (s.charAt(s.length() - 1) != '.') {
			System.out.println("ERROR: seventh line should end with a period!");
		} else if (!(s.length() >= 60 && s.length() <= 80)) {
			System.out.println("ERROR: seventh line has between 60 and 80 characters!");
		} else {
			System.out.println(s);
		}
	}
}
