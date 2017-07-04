/* 
 * 	
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
