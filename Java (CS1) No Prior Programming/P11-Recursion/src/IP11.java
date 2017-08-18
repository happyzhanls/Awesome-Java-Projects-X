import java.util.ArrayList;

// P11 Assignment
// Author: Russ Wakefield
// Date:   Apr 9, 2017
// Class:  CS163
// Email:  waker@cs.colostate.edu

public interface IP11 {
	
	/* 		Method: printPattern
	 		Precondition: n>0
			Postcondition: Print a pattern of n+1 ( 0 to n ) lines
			Postcondition: line i (i = 0 to n) has i stars ("*") followed by (n-i) stripes ("-") 
	 */
	public void printPattern(int n);
		
	/*		Method: convertNum
	 		Precondition: num.length > 0
	 		Postcondition: return int representation of num, (e.g num:{1,2,3} returns int: 123)
	 */

	public int convertNum(int[] num);
		
	/*		Method intersection
	  
	 		Precondition:  AL1 and AL2 are not empty
			Precondition: The elements in AL1 are unique within AL1
			Precondition: The elements in AL2 are unique within AL2
						(but AL1 and AL2 can contain the same elements)
			Postcondition: return an ArrayList with elements that are in both AL1 and AL2
	 					*** in the order they occur in AL1 ***
			Postcondition: The elements in AL1 and AL2 are unchanged.
	 */
	public ArrayList<String> intersection( ArrayList<String> AL1, ArrayList<String> AL2);

}
