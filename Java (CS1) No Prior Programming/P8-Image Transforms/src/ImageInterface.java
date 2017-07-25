// ImageInterface.java
// Interface for imaging program
// Chris Wilcox
// 11/16/2014

public interface ImageInterface {

	// Read the image
	public void readImage(String inFile);

	// Write the image
	public void writeImage(String outFile);

	// Get image data
	public int[][] imageData();

	// Decode the pixels
	public void decode();

	// Swap the nibbles in each pixel
	public void swap();

	// Mirror the image corner to corner
	public void mirror();
	
	// Exchange two rectangles in image
	public void exchange();
}


