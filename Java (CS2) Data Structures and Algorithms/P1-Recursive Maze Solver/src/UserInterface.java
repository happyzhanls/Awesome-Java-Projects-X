// UserInterface.java - graphics for recursive maze assignment
// Author: Chris Wilcox
// Date:   12/27/2016
// Class:  CS165
// Email:  wilcox@cs.colostate.edu

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class UserInterface implements CallbackInterface {

	// Maze variables
	public char maze[][];
	private int leprechaunRow;
	private int leprechaunCol;

	// User interface
	private JFrame frame;
	private JPanel panel;
	private JPanel mazePanel;
	private Image leprechaun, potofgold, success, shamrock, wrongway; 
	private Image treeone, treetwo, treethree, treefour;
	private ArrayList<JButton> buttons;

	// Main program
	public UserInterface(char[][] maze) { 

		// Store maze data
		this.maze = maze;

		// Store initial position of leprechaun
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[0].length; col++) {
				if (maze[row][col] == 'L') {
					leprechaunRow = row;
					leprechaunCol = col;
				}
			}
		}
		
		// Setup graphics
		setupGraphics();
	}

	// Process status
	public void sendStatus(SearchStatus eStatus, int row, int col) {

		System.err.println("Solver reported " + eStatus + " at row " + row + ", column " + col);

		// Update squares, based on status
		if (eStatus == SearchStatus.PATH_INVALID) {
			maze[row][col] = 'W';
		} else if (eStatus == SearchStatus.PATH_VALID) {
			maze[row][col] = 'S'; 
		} else if (eStatus == SearchStatus.PATH_COMPLETE) {
			maze[row][col] = 'C';
		}

		// Update board, if necessary
		if (eStatus != SearchStatus.PATH_ILLEGAL) {
			updateBoard();
		}

		// Display success dialog, and exit
		if (eStatus == SearchStatus.PATH_COMPLETE) {

			ImageIcon icon = new ImageIcon("images/Success.jpg");
			Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image); 
			JOptionPane.showMessageDialog(frame, "Congratulations, you found the pot of gold!",
					"Maze Success", JOptionPane.WARNING_MESSAGE, icon);

			// Exit application
			frame.dispose();
		}
		// Display failure dialog, and exit
		else if (eStatus == SearchStatus.PATH_IMPOSSIBLE) {

			ImageIcon icon = new ImageIcon("images/Failure.jpg");
			Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image); 
			JOptionPane.showMessageDialog(frame, "Sorry, you cannot find the pot of gold!",
					"Maze Failure", JOptionPane.WARNING_MESSAGE, icon);

			// Exit application
			frame.dispose();
		}
	}

	// Update board
	private void updateBoard() {

		// Seed random generator
		Random random = new Random(12345678);

		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[0].length; col++) {
				int index = (row * maze[0].length) + col;

				// Update button
				JButton button = buttons.get(index);
				if (leprechaunRow == row && leprechaunCol == col) {
					button.setIcon(new ImageIcon(leprechaun));
				} else if (maze[row][col] == 'G') {
					button.setIcon(new ImageIcon(potofgold));
				} else if (maze[row][col] == 'S') {
					button.setIcon(new ImageIcon(shamrock));
				} else if (maze[row][col] == 'W') {
					button.setIcon(new ImageIcon(wrongway));
				} else if (maze[row][col] == 'C') {
					button.setIcon(new ImageIcon(success));
				} else if (maze[row][col] == '#') {
					switch (random.nextInt(4)) {
					case 0: button.setIcon(new ImageIcon(treeone)); break;
					case 1: button.setIcon(new ImageIcon(treetwo)); break;
					case 2: button.setIcon(new ImageIcon(treethree)); break;
					case 3: button.setIcon(new ImageIcon(treefour)); break;
					}
				} else {
					button.setIcon(null);
				}
			}
		}

		// Make leprechaun slow down!
		sleep(250);
	}

	// Setup graphics
	private void setupGraphics() {

		// Look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		mazePanel = new JPanel();
		mazePanel.setLayout(new GridLayout(maze.length, maze[0].length, 0, 0));

		// Add row and column labels
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 0.0;
	    gbc.weighty = 0.0;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(0, 2 * 5, 0, 2 * 5);
	    panel.add(createRowLabels(), gbc);
	    gbc.gridx = 2;
	    gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    panel.add(createRowLabels(), gbc);
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.SOUTH;
	    gbc.insets = new Insets(5, 0, 5, 0);
	    panel.add(createColLabels(), gbc);
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.anchor = GridBagConstraints.NORTH;
	    panel.add(createColLabels(), gbc);      
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.insets = new Insets(0, 0, 0, 0);
	    panel.add(mazePanel, gbc);
		
		// Load and scale images
		ImageIcon icon;
		icon = new ImageIcon("images/Leprechaun.jpg");
		leprechaun = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		icon = new ImageIcon("images/PotOfGold.jpg");
		potofgold = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		icon = new ImageIcon("images/Shamrock.jpg");
		shamrock = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		icon = new ImageIcon("images/WrongWay.jpg");
		wrongway = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		icon = new ImageIcon("images/Success.jpg");
		success = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		icon = new ImageIcon("images/TreeOne.jpg");
		treeone = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		icon = new ImageIcon("images/TreeTwo.jpg");
		treetwo = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		icon = new ImageIcon("images/TreeThree.jpg");
		treethree = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		icon = new ImageIcon("images/TreeFour.jpg");
		treefour = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

		// Build panel of buttons
		buttons = new ArrayList<JButton>();
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[0].length; col++) {

				// Initialize and add button
				JButton button = new JButton();
				Border border = new LineBorder(Color.darkGray, 4);
				button.setOpaque(true);
				button.setBackground(Color.gray);
				button.setBorder(border);
				mazePanel.add(button);
				buttons.add(button);
			}
		}

		// Initial update
		updateBoard();
		
		// Configure and show window
		frame = new JFrame();
	    frame.getContentPane().add(panel);
	    frame.pack();
		frame.setTitle("Maze");
		frame.setSize(maze[0].length * 80 + 150, maze.length * 80 + 150);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.setFocusable(true);
		frame.setVisible(true);
		frame.setVisible(true);
	}

	// Create row labels
	private JPanel createRowLabels() {
		JPanel filePanel = new JPanel(new GridLayout(0, 1));
		for (int row = 0; row < maze.length; row++) {
			filePanel.add(new JLabel(String.valueOf(row), SwingConstants.CENTER));
		}
		return filePanel;
	}

	// Create column labels
	private JPanel createColLabels() {
		JPanel filePanel = new JPanel(new GridLayout(1, 0));
		for (int col = 0; col < maze[0].length; col++) {
			filePanel.add(new JLabel(String.valueOf(col), SwingConstants.CENTER));
		}
		return filePanel;
	}
	
	// Wait for awhile
	private static void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
