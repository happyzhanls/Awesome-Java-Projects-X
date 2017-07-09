

import java.util.Scanner;

public class P4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double gross_salary, interest_income, capital_gains;
		int number_of_exemptions;
		System.out.print("Salary: ");
		gross_salary = sc.nextDouble();
		System.out.print("Exemptions: ");
		number_of_exemptions = sc.nextInt();
		System.out.print("Interest: ");
		interest_income = sc.nextDouble();
		System.out.print("Gains: ");
		capital_gains = sc.nextDouble();
		double total_income = gross_salary + interest_income + capital_gains - 5000;
		if (number_of_exemptions > 6)
			number_of_exemptions = 6;
		double adjust_income = total_income - (number_of_exemptions * 1500);
		double total_tax = 0;
		if (adjust_income >= 0 && adjust_income < 20000) {
			total_tax = 0.0;
		} else if (adjust_income >= 20000 && adjust_income < 35000) {
			total_tax = 0.13 * (adjust_income - 20000);
		} else if (adjust_income >= 35000 && adjust_income < 50000) {
			total_tax = 1950 + 0.23 * (adjust_income - 35000);
		} else if (adjust_income >= 50000) {
			total_tax = 1950 + 3450 + 0.28 * (adjust_income - 50000);
		}
		double state_tax = adjust_income * 0.065;
		sc.close();
		System.out.printf("Total Income: $%.2f\n" , total_income);
		System.out.printf("Adjusted Income: $%.2f\n" , adjust_income);
		System.out.printf("Total Tax: $%.2f\n" , total_tax);
		System.out.printf("State Tax: $%.2f\n" , state_tax);
	}
}
