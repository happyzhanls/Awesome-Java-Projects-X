// DrawProgram.java - Solution for drawing program
// Author: Chris Wilcox
// Date: 2/2/2017
// Class: CS165
// Email: wilcox@cs.colostate.edu

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DrawProgram {

	public static UserInterface ui;

	// Main entry point
	public static void main(String[] args) throws InterruptedException {

		// Drawing primitives
		ArrayList<Primitive> primitives;

		// Instantiate user interface
		ui = new UserInterface();

		// Close window
		ui.open();

		// Read primitives
		primitives = load(args[0]);

		// Draw primitives
		draw(primitives);

		// Wait for a while
		Thread.sleep(100000);
		
		// Close window
		ui.close();
	}

	// Read graphics primitives from file
	public static ArrayList<Primitive> load(String filename) {

		ArrayList<Primitive> primitives = new ArrayList<>();
		try {
			Scanner scan = new Scanner(new File(filename));
			while (scan.hasNextLine()) {
				
				int x0,y0,x1,y1,x2,y2,width,height,radius;
				
				// Read and parse line
				String line = scan.nextLine();
				String[] fields = line.split(",");
				if (fields.length == 0) continue; // discard empty
				
				// Interpret primitives
				switch (fields[0]) {
				case "TEXT":
					x0 = Integer.parseInt(fields[3]);
					y0 = Integer.parseInt(fields[4]);
					Text text = new Text(x0, y0 , fields[2]);
					text.setColor(Integer.parseInt(fields[1], 16));
					text.setFont(fields[5], Integer.parseInt(fields[6]));
					primitives.add(text);
					break;
					
				case "SQUARE":
					x0 = Integer.parseInt(fields[2]);
					y0 = Integer.parseInt(fields[3]);
					Square square = new Square(x0, y0, Integer.parseInt(fields[4]));
					square.setColor(Integer.parseInt(fields[1], 16));
					square.setFilled(fields[5].equals("filled"));
					primitives.add(square);
					break;

				case "RECTANGLE":
					x0 = Integer.parseInt(fields[2]);
					y0 = Integer.parseInt(fields[3]);
					width = Integer.parseInt(fields[4]);
					height = Integer.parseInt(fields[5]);
					Rectangle rect = new Rectangle(x0, y0, width, height);
					rect.setColor(Integer.parseInt(fields[1], 16));
					rect.setFilled(fields[6].equals("filled"));
					primitives.add(rect);
					break;

				// YOUR CODE HERE - add triangle
				case "TRIANGLE":
					x0 = Integer.parseInt(fields[2]);
					y0 = Integer.parseInt(fields[3]);
					x1 = Integer.parseInt(fields[4]);
					y1 = Integer.parseInt(fields[5]);
					x2 = Integer.parseInt(fields[6]);
					y2 = Integer.parseInt(fields[7]);
					Triangle tria = new Triangle(x0, y0, x1, y1, x2, y2);
					tria.setColor(Integer.parseInt(fields[1], 16));
					tria.setFilled(fields[8].equals("filled"));
					primitives.add(tria);
					break;
					
				// YOUR CODE HERE - add circle
				case "CIRCLE":
					x0 = Integer.parseInt(fields[2]);
					y0 = Integer.parseInt(fields[3]);
					radius = Integer.parseInt(fields[4]);
					Circle circle = new Circle(x0, y0, radius);
					circle.setColor(Integer.parseInt(fields[1], 16));
					circle.setFilled(fields[5].equals("filled"));
					primitives.add(circle);
					break;
					
				// YOUR CODE HERE - add oval
				case "OVAL":
					x0 = Integer.parseInt(fields[2]);
					y0 = Integer.parseInt(fields[3]);
					width = Integer.parseInt(fields[4]);
					height = Integer.parseInt(fields[5]);
					Oval oval = new Oval(x0, y0, width, height);
					oval.setColor(Integer.parseInt(fields[1], 16));
					oval.setFilled(fields[6].equals("filled"));
					primitives.add(oval);
					break;
					
                case "POLYGON":
                    // YOUR CODE HERE - read in points and create polygon
                	int NumberOfEdges = Integer.parseInt(fields[3]);
                	int[] xPoints = new int[NumberOfEdges];
                	int[] yPoints = new int[NumberOfEdges];
                	for (int i = 0; i < NumberOfEdges; i++) {
                		xPoints[i] = Integer.parseInt(fields[(i + 2) * 2]);
                		yPoints[i] = Integer.parseInt(fields[(i + 2) * 2 + 1]);
                	}
                    Polygon polygon = new Polygon(xPoints, yPoints);
                    polygon.setColor(Integer.parseInt(fields[1], 16));
                    polygon.setFilled(fields[2].equals("filled"));
                    primitives.add(polygon);
                    break;
				}
			} 
			scan.close();
		} catch (IOException e) {
				System.out.println("Cannot open file: " + filename);
		}
		return primitives;
	}

	// Draw graphics primitives in arraylist
	public static void draw(ArrayList<Primitive> primitives) {
		
//		for (Primitive primitive : primitives) {
//			if (primitive instanceof Text)
//				((Text)primitive).draw(ui);
//			else if (primitive instanceof Square)
//				((Square)primitive).draw(ui);
//			else if (primitive instanceof Rectangle)
//				((Rectangle)primitive).draw(ui);
//			// YOUR CODE HERE - add triangle
//			else if (primitive instanceof Triangle)
//				((Triangle)primitive).draw(ui);
//			// YOUR CODE HERE - add circle
//			else if (primitive instanceof Circle)
//				((Circle)primitive).draw(ui);
//			// YOUR CODE HERE - add oval
//			else if (primitive instanceof Oval)
//				((Oval)primitive).draw(ui);
//		}
		
		// YOUR CODE HERE - loop to call draw method on all primitives
		for (Primitive primitive : primitives)
			primitive.draw(ui);
	}
}
