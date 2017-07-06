import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// MazeSolver.java - solution for recursive maze assignment
// Author: Chris Wilcox
// Date:   12/27/2016
// Class:  CS165
// Email:  wilcox@cs.colostate.edu

public class MazeSolver {
	
	// Exception thrown when path is complete
	public static class MazeSolvedException extends Exception {
		private static final long serialVersionUID = 1L;
		MazeSolvedException() {
			super("Found the pot of gold!");
		}
	}
	
	// Store user interface
	private static UserInterface gui;
	
	// Data structures for maze
	private static char[][] maze;
	
	// Main entry point
	public static void main(String[] args) {
		
		// Load maze from file
		String p = "C:\\Users\\DanielZ\\workspace\\CS165_P1\\ImpossibleMaze.txt";
		String path = p.replace("\\", "/");
		loadMaze(path);
		int height = maze.length;
		int width = maze[0].length;
		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width;j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
		// Find leprechaun in maze
		int currentRow = -1;
		int currentCol = -1;
		
		// YOUR CODE HERE!
		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width;j++) {
				if (maze[i][j] == 'L') {
					currentRow = i;
					currentCol = j;
				}
					
			}
		}
    	// Instantiate graphical user interface
		gui = new UserInterface(maze);
		
    	try {
    		// Solve maze, using recursion
    		findPath(currentRow, currentCol);

    		// Impossible maze, notify user interface
	        gui.sendStatus(CallbackInterface.SearchStatus.PATH_IMPOSSIBLE, -1, -1); // Cannot solve
    		
    	} catch(MazeSolvedException e) {
    		
    		// Maze solved, exit normally 
		}

	}

	// Read maze into array
	private static void loadMaze(String filename) {
		// YOUR CODE HERE!
		File f = new File(filename);
		Scanner reader;
		try {
			reader = new Scanner(f);
			if (f.exists() && f.canRead() && f.length() != 0) {
				String[] wh = reader.nextLine().split(" ");
				int width = Integer.parseInt(wh[0]);
				int height = Integer.parseInt(wh[1]);
				maze = new char[height][width];
				for (int i = 0;i < height;i++) {
					String row = reader.nextLine();
					for (int j = 0;j < row.length();j++) {
						maze[i][j] = row.charAt(j);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Recursive method to find path
	public static boolean findPath(int row, int col) throws MazeSolvedException {
		// YOUR CODE HERE!
		if (row >= maze.length || row < 0 || col >= maze[0].length || col < 0) {
			// PATH_ILLEGAL
	        gui.sendStatus(CallbackInterface.SearchStatus.PATH_ILLEGAL, row, col); 
			return false;
		} else if (maze[row][col] == 'G') {
			// PATH_COMPLETE
	        gui.sendStatus(CallbackInterface.SearchStatus.PATH_COMPLETE, row, col); 
	        throw new MazeSolvedException();
		} else if (maze[row][col] == 'S') {
			// Already part of the valid path
			return false;
		} else if (maze[row][col] == 'W') {
			// Already part of the invalid path
			return false;
		} else if (maze[row][col] == '#') {
			// contains a blocking tree
			return false;
		} else if (maze[row][col] == ' ' || maze[row][col] == 'L') {
			// PATH_VALID
	        gui.sendStatus(CallbackInterface.SearchStatus.PATH_VALID, row, col); 
	        // Recursively call findPath with the direction UP, RIGHT, DOWN, LEFT.
	        boolean UP = findPath(row - 1, col); // UP
	        boolean RIGHT = findPath(row, col + 1); // RIGHT
	        boolean DOWN = findPath(row + 1, col); // DOWN
	        boolean LEFT = findPath(row, col - 1); // LEFT
	        
	        if (UP || RIGHT || DOWN || LEFT) {
	        	return true;
	        } else {
		        gui.sendStatus(CallbackInterface.SearchStatus.PATH_INVALID, row, col);
		        return false;
	        }
		}
		return false;
	}
}
	