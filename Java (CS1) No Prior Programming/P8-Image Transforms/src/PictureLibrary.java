// Picture.java
// Reads and writes .pgm files
// Original from Fritz Sieker
// Modified by Chris Wilcox
// 11/20/2012

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class PictureLibrary {
    
    // Maximum intensity
    public static final int MAXVAL = 255;

    // Image data
    private int[][] image;

    // Get image height
    public int getHeight() {
        return image.length;
    }

    // Get image width
    public int getWidth() {
        return image[0].length;
    }

    // Get image data
    public int[][] getData() {
        return image;
    }
    
    // Set image data
    public void setData(int[][] data) {
        image = data;
    }

    // Read PGM file
    public void readPGM(String path) throws Exception {

        int width;
        int height;
        int maxval;
        try {
            Scanner in = new Scanner(new File(path));
            String magic = in.next();
            if (!magic.equals("P2")) {
                in.close();
                throw new Exception("ERROR: cannot read .pgm file " + path);
            }
            width  = in.nextInt();
            height = in.nextInt();
            maxval = in.nextInt();
            image = new int[height][width];
            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++)
                    image[y][x] = in.nextInt();
            in.close();
        } catch (IOException e) {
            throw new Exception("ERROR: cannot read .pgm file " + path);
        }

        // Scale values to the range 0-MAXVAL
        if (maxval != MAXVAL)
            for (int j = 0; j < height; j++)
                for (int i = 0; i < width; i++)
                    image[j][i] = (image[j][i] * MAXVAL) / maxval;

        return;
    }

    // Write PGM file
    public void writePGM(String path) throws Exception {
        
        int height = getHeight();
        int width  = getWidth();
        try {
            PrintStream output = new PrintStream(new FileOutputStream(path));
            output.println("P2");
            output.println(width + " " + height);
            output.println(MAXVAL);
            for (int row = 0; row < height; row++)
                for (int col = 0; col < width; col++)
                    output.println(image[row][col]); // One pixel per line!
            output.close();
        } catch (IOException e) {
            throw new Exception("ERROR: cannot write .pgm file " + path);
        }
    }
}
