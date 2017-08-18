import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P11 implements IP11 {
	
	@Override
	public void printPattern(int n) {
		printPattern_helper(0, n);
	}
	
	private void printPattern_helper(int stars, int stripes) {
		if (stars == 0) {
			printStripes(stripes);
			System.out.println();
		} else {
			printStars(stars);
			printStripes(stripes);
			System.out.println();
			if (stripes == 0)
				return;
		}
		printPattern_helper(++stars, --stripes);
	}
	
	private void printStars(int stars) {
		if (stars <= 0)
			return;
		else {
			System.out.print("*");
			printStars(--stars);
		}
	}
	
	private void printStripes(int stripes) {
		if (stripes <= 0) 
			return;
		else {
			System.out.print("-");
			printStripes(--stripes);
		}
	}
	
	@Override
	public int convertNum(int[] num) {
		if (num.length == 1)
			return num[0];
		else 
			return convertNum_helper(num, 0, num.length - 1, 0);
		
	}
	
	private int convertNum_helper(int[] num, int atIndex, int lastIndex, int result) {
		if (atIndex == lastIndex) { 
			result += num[atIndex];
			return result;
		}
		else {
			result += num[atIndex] * (int) Math.pow(10, lastIndex - atIndex);
			return convertNum_helper(num, ++atIndex, lastIndex, result);
		}
	}
	
	@Override
	public ArrayList<String> intersection(ArrayList<String> AL1, ArrayList<String> AL2) {
		return intersection_helper(new LinkedList<>(AL1), new LinkedList<>(AL2), new ArrayList<>());
	}
	
	private ArrayList<String> intersection_helper(LinkedList<String> aL1, LinkedList<String> aL2,
			ArrayList<String> result) {
		if (aL1.isEmpty())
			return result;
		String head = aL1.pop();
		if (aL2.contains(head))
			result.add(head);
		return intersection_helper(aL1, aL2, result);
	}

	public static void main(String[] args) {
		P11 p = new P11();
		p.printPattern(5);
		
		int[] num3456 = {1, 4, 5, 6};
		int NUM = p.convertNum(num3456);
		System.out.println(Arrays.toString(num3456));
		System.out.println(NUM);
		
		ArrayList<String> AL1 = new ArrayList<>();
		ArrayList<String> AL2 = new ArrayList<>();
		AL1.add("a"); AL1.add("b"); AL1.add("c");
		AL2.add("b"); AL2.add("c"); AL2.add("d"); AL2.add("e");
		ArrayList<String> intersect = p.intersection(AL1,AL2);
		System.out.println(AL1 + " intersect " + AL2 + " = " + intersect);
	}
}
