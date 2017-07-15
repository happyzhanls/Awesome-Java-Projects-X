// TowersOfHanoi.java - solver program for Tower of Hanoi
// Author: ?????
// Date:   ?????
// Class:  CS165
// Email:  ?????

// ACKNOWLEDGEMENTS:
// This assignment was heavily copied from the wonderfully competent faculty at Princeton University.
// Permission to use the assignment was granted via email by Dr. Robert Sedgewick. We gratefully
// acknowledge the material from Princeton University used in this assignment! The original Princeton
// assignment is here: http://introcs.cs.princeton.edu/java/23recursion/AnimatedHanoi.java.html
// Copyright &copy; 2000–2011, Robert Sedgewick and Kevin Wayne. 

public class TowersOfHanoi {

    // User interface
    private static UserInterface ui;

    // Each tower is represented by a stack
    private static CoolStack<Integer> left = new CoolStack<Integer>();      // 0 = left
    private static CoolStack<Integer> center = new CoolStack<Integer>();    // 1 = center
    private static CoolStack<Integer> right = new CoolStack<Integer>();     // 2 = right

    // Stack for iterative solution
    private static CoolStack<Move> moves = new CoolStack<Move>();
    public static class Move {
    	
        // YOUR CODE HERE: variables and constructor
    	public int NumberOfDiscs, to, from, aux;
    	public boolean covered;
    	
    	public Move(int NumberOfDiscs) {
    		
    	}
    	
    	
    }

    public static void main(String[] args) throws InterruptedException {

        if (args.length != 2) {
            System.err.println("usage1: java TowersofHanoi depth <-recursive>");
            System.err.println("usage2: java TowersofHanoi depth <-iterative>");
            System.exit(-1);
        }
        
        // Parse tower depth
        int depth = Integer.parseInt(args[0]);

        // Parse options
        String option = args[1];

        // Setup initial state
        // YOUR CODE HERE: push discs onto left pole in inverted order
        for (int i = depth ; i > 0 ; i--) 
        	left.push(i);
        
        // Create user interface
        ui = new UserInterface(depth);

        // Call user interface to render (initial)
        ui.draw(left, center, right);

        // Solve Towers of Hanoi
        if (option.equals("-recursive"))
            recursiveHanoi(depth, 0, 1, 2);
        else
            iterativeHanoi(depth, 0, 1, 2);

        // Wait for awhile
        Thread.sleep(10000);

        // Destroy user interface
        System.exit(0);
    }

    // Recursive solver
    public static void recursiveHanoi(int disc, int from, int aux, int to) {

        // YOUR CODE HERE - recursive solution
        if (disc == 1) {
        	move(disc, from, to);
        } else {
        	recursiveHanoi(disc - 1, from, to, aux);
            move(disc, from, to);
            recursiveHanoi(disc - 1, aux, from, to);
        }
        
    }

    // Iterative solver
    public static void iterativeHanoi(int disc, int from, int aux, int to) {

        // YOUR CODE HERE - iterative solution using stack
    	
    }

    // Private method to report and move a disc
    private static void move(int disc, int source, int dest) {
        
        CoolStack<Integer> fromStack, toStack;
        String fromName, toName;
        
        // Translate integer to stacks and names
        switch (source) {
            case 0: fromStack = left;   fromName = "left"; break;
            case 1: fromStack = center; fromName = "center"; break;
            default: fromStack = right; fromName = "right"; break;
        }
        
        switch (dest) {
            case 0: toStack = left;   toName = "left"; break;
            case 1: toStack = center; toName = "center"; break;
            default: toStack = right; toName = "right"; break;
        }
        
        // Print the move
        System.err.println("Moved disc " + disc + " from " + fromName + " to " + toName + ".");

        // Perform the move
        toStack.push(fromStack.pop());
        
        // Call user interface to render (updated)
        ui.draw(left, center, right);
    }
}
