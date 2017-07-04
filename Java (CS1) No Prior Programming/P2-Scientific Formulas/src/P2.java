/* A small scientific formula implementation using scanner(System.in).
 * 
 * At first, it takes the radius of the circle as input on the console window
 * and prints the circumference, area and volume of the circle separately as outputs. 
 * Secondly, it takes the mass as the input and prints the energy on the console 
 * window as the output using the famous formula E=MC^2.
 * 
 * Example:
 * Radius? 1.0
 * The circumference is 6.28319.
 * The area is 3.14159.
 * The volume is 4.18879.
 * 
 * Mass? 1.0
 * The energy is 89875517873681760.0 joules.
 */	

import java.util.Scanner;

public class P2 {
	public static void main(String[] args) {
		
		// Declare variables for geometric formulas
		double radius, circumference, area, volume;
		
		// Instantiate scanner
		Scanner reader = new Scanner(System.in);
		
		// Prompt and read radius from keyboard
		System.out.print("Radius? ");
		radius = reader.nextDouble();
		
		// Calculate circumference, area, and volume
		circumference = 2 * Math.PI * radius;
		area = Math.PI * Math.pow(radius, 2);
		volume = (4 * Math.PI * Math.pow(radius, 3)) / 3;
		
		// Print circumference, area, and volume to console
		System.out.printf("The circumference is %.5f.\n", circumference);
		System.out.printf("The area is %.5f.\n", area);
		System.out.printf("The volume is %.5f.\n", volume);
		
		// Declare variables for converting mass to energy
		double energy, mass, speedOfLight = 299792458.0;
		
		// Prompt and read mass from keyboard
		System.out.print("Mass? ");
		mass = reader.nextDouble();
		
		// Compute the energy using the formula
		energy = mass * Math.pow(speedOfLight, 2);
		
		// Print energy to console
		System.out.printf("The energy is %.1f joules.\n", energy);
		
		// Close scanner
		reader.close();
	}
}
