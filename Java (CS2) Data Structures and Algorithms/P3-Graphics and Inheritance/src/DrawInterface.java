// DrawInterface.java - Interface class for drawing program
// Author: Chris Wilcox
// Date: 2/2/2017
// Class: CS165
// Email: wilcox@cs.colostate.edu

public interface DrawInterface {
	
	//
	// Graphics control
	//
	// Open window
	public abstract void open();

	// Close window
	public abstract void close();

	// Clear surface
	public abstract void clear();

	//
	// Graphics attributes
	//
	// Color selection
	public abstract void lineColor(int color);
	public abstract void fillColor(int color);
	public abstract void textColor(int color);
	// Font selection
	public abstract void setFont(String fontName, int fontSize);
	
	//
	// Graphics primitives
	//
	// Draw text
	public abstract void drawText(int x, int y, String string);
	
	// Draw line
	public abstract void drawLine(int x0, int y0, int x1, int y1);

	// Draw rectangle (and square)
	public abstract void drawRectangle(int x, int y, int width, int height, boolean isFilled);

	// Draw polygon (and triangle)
	public abstract void drawPolygon(int[] xPoints, int[] yPoints, boolean isFilled);

	// Draw oval (and circle)
	public abstract void drawOval(int x, int y, int width, int height, boolean isFilled);
}
