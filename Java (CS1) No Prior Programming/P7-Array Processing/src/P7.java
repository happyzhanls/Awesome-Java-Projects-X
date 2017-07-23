import java.util.Arrays;

public class P7 {
	
	public static int[] createIntegers() {
		int[] array = {16, 21, 34, 49, 55, 60, 72, 83, 101};
		return array;
	}
	
	public static double[] createDoubles() {
		double step = 0.5;
		double[] result = new double[7];
		for (int i = 0; i < result.length; i++) 
			result[i] = Math.pow(i * step + 10, 2);
		return result;
	}
	
	public static String[] createStrings(String s) {
		String[] stringArray = new String[4];
		stringArray[0] = s;
		stringArray[1] = s.toUpperCase();
		stringArray[2] = s.toLowerCase();
		stringArray[3] = s.substring(1);
		return stringArray;
	}
	
	public static char[] createChars(String s) {
		char[] charArray = s.toCharArray();
		return charArray;
	}
	
	public static int sumArray(int[] a) {
		int sum = 0;
		for (int i : a)
			sum += i;
		return sum;
	}
	
	public static double findLargest(double[] array) {
		double max = array[0];
		for (double d : array) 
			if (d > max)
				max = d;
		return max;
	}
	
	public static int charFrequency(String[] array, char c) {
		int count = 0;
		String element = "";
		for (int i = 0; i < array.length; i++) {
			element = array[i];
			for (int j = 0; j < element.length(); j++)
				if (element.charAt(j) == c)
					count++;
		}
		return count;	
	}
	
	public static int[] translateChars(char[] array) {
		int[] iArray = new int[array.length];
		for (int i = 0; i < iArray.length; i++)
			iArray[i] = array[i];
		return iArray;
	}
	
    public static void main(String[] args) {

        // Create arrays
        int[] integerArray = createIntegers();
        System.out.println(Arrays.toString(integerArray));
        double[] doubleArray = createDoubles();
        System.out.println(Arrays.toString(doubleArray));
        String[] stringArray = createStrings("Hello There");
        System.out.println(Arrays.toString(stringArray));
        char[] charArray = createChars("Java1234!&");
        System.out.println(Arrays.toString(charArray));

        // Test processing
        System.out.println("Sum of integer array = " + sumArray(integerArray));
        System.out.println("Largest of double array = " + findLargest(doubleArray));
        System.out.println("Frequency of 'e' = " + charFrequency(stringArray, 'e'));
        System.out.println("Translated characters = " + Arrays.toString(translateChars(charArray)));
    }
}
