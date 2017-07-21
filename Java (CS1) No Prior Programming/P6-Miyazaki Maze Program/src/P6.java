
public class P6 {
    // Class variables
    public static Maze maze;
    public static int mazeWidth;
    public static int mazeHeight;

    public static void main(String[] args) {

        // Create maze
        String fileName = args[0];
        System.err.println("Maze name: " + fileName);

        // Get dimensions
        maze = new Maze(fileName);
        mazeWidth = maze.getWidth();
        mazeHeight = maze.getHeight();
        System.err.println("Maze width: " + mazeWidth);
        System.err.println("Maze height: " + mazeHeight);

        // Add code to move around maze
        for (int i = 0; i < mazeHeight; i++) {
        	if (maze.getRow() % 2 == 0) {
        		for (int j = 0; j < mazeWidth - 1; j++) {
        			if (maze.moveRight()) {
        				
        			} else {
        				maze.moveDown();
        				maze.moveRight();
        				maze.moveRight();
        				maze.moveUp();
        				j++;
        			}
            	}
            	maze.moveDown();
        	} else {
        		for (int j = mazeWidth - 1; j > 0; j--) {
        			if (maze.moveLeft()) {
        				
        			} else {
        				maze.moveDown();
        				maze.moveLeft();
        				maze.moveLeft();
        				maze.moveUp();
        				j--;
        			}
            	}
            	maze.moveDown();
        	}
        }
    }
}
