// UserInterface.java - user interface for Tower of Hanoi
// Author: Chris Wilcox
// Date:   2/14/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu

// ACKNOWLEDGEMENTS:
// This assignment was heavily copied from the wonderfully competent faculty at Princeton University.
// Permission to use the assignment was granted via email by Dr. Robert Sedgewick. We gratefully
// acknowledge the material from Princeton University used in this assignment! The original Princeton
// assignment is here: http://introcs.cs.princeton.edu/java/23recursion/AnimatedHanoi.java.html
// Copyright &copy; 2000–2011, Robert Sedgewick and Kevin Wayne. 

import java.awt.Color;
import java.awt.Font;

public class UserInterface {

    private static int depth; // Tower depth
    
    // Create user interface
    public UserInterface(int depth) {

        // Store depth
        UserInterface.depth = depth;
        
        // Setup drawing canvas
        int WIDTH  = 200;
        int HEIGHT = 20;
        StdDraw.setCanvasSize(3*WIDTH, 250+6*HEIGHT);
        StdDraw.setXscale(0, 4);
        StdDraw.setYscale(-3, 12);
        StdDraw.enableDoubleBuffering();
        StdDraw.pause(1000);
    }

    // Draw current state of Towers of Hanoi
    public void draw(CoolStack<Integer> left, CoolStack<Integer> middle, CoolStack<Integer> right) {

        // Clear screen and setup colors
        StdDraw.clear();
        StdDraw.picture(2, 1, "images/Towers.jpg");

        // Draw puzzle
        for (int tower = 0; tower < 3; tower++) {

            // Select tower
            CoolStack<Integer> discs = null;
            switch (tower) {
                case 0: discs = left; break;
                case 1: discs = middle; break;
                case 2: discs = right; break;
            }
            
            // Draw discs
            for (int disc = 0; disc < discs.size(); disc++) {
                
                // Disc number
                int number = discs.get(disc);

                // Setup pen
                Color color = Color.getHSBColor(1.0f * number / depth, 0.7f, 0.7f);
                StdDraw.setPenColor(color);
                StdDraw.setPenRadius(0.035); // magic constant

                // Draw disc
                double size = 0.5 * number / depth;
                
                // Compute position of towers and discs
                double y = (disc - 1.75) * 0.75;
                double x = 0.0;
                switch (tower) {
                    case 0: x = 0.665; break;
                    case 1: x = 1.970; break;
                    case 2: x = 3.235; break;
                }
                
                StdDraw.setPenRadius(0.035); // magic constant
                StdDraw.line(x - size / 2.0, y, x + size / 2.0, y);
                StdDraw.setPenColor(Color.WHITE);
                StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 20));
                StdDraw.text(x, y-0.1, Integer.toString(number));
            }
        }

        StdDraw.show();
        StdDraw.pause(500);
    }

}
