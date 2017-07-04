
import java.util.Scanner;

public class P3 {

	public static String evaluate(String expression) {

		// Print expression
		System.out.println(expression);

		// Declare variables for operands, operator, result, and return value
		String returnString = "";
		double operand1, operand2, result = 0;
		String operator = "";
		// Create Scanner object to parse expression
		Scanner sc = new Scanner(expression);

		// Use Scanner to read operands and operator
		if (sc.hasNextDouble()) {
			operand1 = sc.nextDouble();
		} else {
			return "Invalid Operand!";
		}
		
		if (sc.hasNext()) {
			switch (sc.next()) {
				case "+":
					operator += "+";
					break;
				case "-":
					operator += "-";
					break;
				case "*":
					operator += "*";
					break;
				case "/":
					operator += "/";
					break;
				case "%":
					operator += "%";
					break;
				case "^":
					operator += "^";
					break;
				default:
					return "Invalid Operator!";
			}
		} else {
			return "Invalid Operator!";
		}
		
		if (sc.hasNextDouble()) {
			operand2 = sc.nextDouble();
		} else {
			return "Invalid Operand!";
		}
		
		// Compute a numerical result for the expression
		switch (operator) {
			case "+":
				result = operand1 + operand2;
				break;
			case "-":
				result = operand1 - operand2;
				break;
			case "*":
				result = operand1 * operand2;
				break;
			case "/":
				result = operand1 / operand2;
				break;
			case "%":
				result = operand1 % operand2;
				break; 
			case "^":
				result = Math.pow(operand1, operand2);
		}
		// Convert numerical result to string
		returnString += Double.toString(result);
		// Return result
		return returnString;
 	}
}
