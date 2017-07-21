
// Description: Yet Another Maze Program
// Author: Chris Wilcox
// Date:   9/25/2016
// Class:  CS160
// Email:  wilcox@cs.colostate.edu

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Maze implements KeyListener {

    // Maze variables
    private char mazeData[][];
    private int mazeWidth; 
    private int mazeHeight;
    private int currRow;
    private int currCol;
    private int prevRow = -1;
    private int prevCol = -1;
    
    // User interface
    private JFrame frame;
    private JPanel panel;
    private Image chihiro, haku, yubaba, success;
    private ArrayList<JButton> buttons;

    // Maze constructor
    public Maze(String fileName) {

        // Read maze
        readMaze(fileName);

        // Graphics setup
        setupGraphics();
    }

    // Get maze width
    public int getWidth() {
        return mazeWidth;
    }

    // Get maze height
    public int getHeight() {
        return mazeHeight;
    }

    // Get row
    public int getRow() {
        return currRow;
    }

    // Get column
    public int getColumn() {
        return currCol;
    }

    // Move right
    public boolean moveRight() {
        boolean b = makeMove(currRow, currCol+1, true);
        if (b) currCol++; // success 
        return b;
    }

    // Move left
    public boolean moveLeft() {
        boolean b = makeMove(currRow, currCol-1, true);
        if (b) currCol--; // success 
        return b;
    }

    // Move up
    public boolean moveUp() {
        boolean b = makeMove(currRow-1, currCol, true);
        if (b) currRow--; // success 
        return b;
    }

    // Move down
    public boolean moveDown() {
        boolean b = makeMove(currRow+1, currCol, true);
        if (b) currRow++; // success 
        return b;
    }

    // Move Chihiro maze
    private boolean makeMove(int moveRow, int moveCol, boolean print) {

        // Off the edge?
        if (moveRow < 0 || moveRow >= mazeHeight || moveCol < 0 || moveCol >= mazeWidth) {
            System.err.println("Chihiro went outside the maze!");
            sleep(5000);
            System.exit(0);
        }

        // Failed move
        if (mazeData[moveRow][moveCol] == 'Y')
            return false;
        
        // Print move
        if (print) {
            System.err.println("Moved to row " + moveRow + ", column " + moveCol);
        }

        // Compute index and remove icon
        int index = (prevRow * mazeWidth) + prevCol;
        if ((prevRow >= 0) && (prevCol >= 0)) {
            buttons.get(index).setIcon(null);
        }

        // Compute index and replace icon
        index = (moveRow * mazeWidth) + moveCol;
        if (mazeData[moveRow][moveCol] == 'H')
            buttons.get(index).setIcon(new ImageIcon(success));
        else
            buttons.get(index).setIcon(new ImageIcon(chihiro));

        // Store previous location
        prevRow = moveRow;
        prevCol = moveCol;

        // Found Haku!
        if (mazeData[moveRow][moveCol] == 'H') {
            System.err.println("Chihiro found Haku, congratulations!");
            sleep(5000);
            System.exit(0);
        }

        // Successful move
        sleep(1000);
        return true;
    }

    // Set button
    private void setButton(JButton button, int row, int col) {

        if (mazeData[row][col] == 'C') {
            button.setIcon(new ImageIcon(chihiro));
        } else if (mazeData[row][col] == 'H') {
            button.setIcon(new ImageIcon(haku));
        } else if (mazeData[row][col] == 'Y') {
            button.setIcon(new ImageIcon(yubaba));
        }
    }

    // Read maze
    private void readMaze(String filename) {
        try {

            // Open file
            Scanner scan = new Scanner(new File(filename));

            // Read numbers
            mazeWidth = scan.nextInt();
            mazeHeight = scan.nextInt();

            // Allocate maze
            mazeData = new char[mazeHeight][mazeWidth];

            // Read maze
            for (int row = 0; row < mazeHeight; row++) {

                // Read line
                String line = scan.next();
                for (int col = 0; col < mazeWidth; col++) {

                    // Read square
                    mazeData[row][col] = line.charAt(col);
                    
                    // Chihiro location
                    if (mazeData[row][col] == 'C') {
                        currRow = row;
                        currCol = col;
                    }
                }
            }

            // Close file
            scan.close();

        } catch (IOException e) {
            System.err.println("Cannot read maze: " + filename);
            System.exit(0);
        }
    }

    // Setup graphics
    private void setupGraphics() {

        // Create grid
        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(new GridLayout(mazeHeight, mazeWidth, 0, 0));
        frame.add(Box.createRigidArea(new Dimension(0, 5)), BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        // Look and feel
        try {
            UIManager.setLookAndFeel(UIManager
                    .getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configure window
        frame.setSize(mazeWidth * 100, mazeHeight * 100);
        frame.setTitle("Maze");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);

        // Load and scale images
        ImageIcon icon0 = new ImageIcon("Chihiro.png");
        Image image0 = icon0.getImage();
        chihiro = image0.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        ImageIcon icon1 = new ImageIcon("Haku.png");
        Image image1 = icon1.getImage();
        haku = image1.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        ImageIcon icon2 = new ImageIcon("Yubaba.png");
        Image image2 = icon2.getImage();
        yubaba = image2.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        ImageIcon icon3 = new ImageIcon("Success.png");
        Image image3 = icon3.getImage();
        success = image3.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        // Build panel of buttons
        buttons = new ArrayList<JButton>();

        for (int row = 0; row < mazeHeight; row++) {
            for (int col = 0; col < mazeWidth; col++) {

                // Initialize and add button
                JButton button = new JButton();
                Border border = new LineBorder(Color.darkGray, 4);
                button.setOpaque(true);
                button.setBackground(Color.gray);
                button.setBorder(border);
                setButton(button, row, col);
                panel.add(button);
                buttons.add(button);
            }
        }

        // Show window
        makeMove(currRow, currCol, false);
        frame.setVisible(true);
    }
    
    // Wait for awhile
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    


    // Keyboard input handler
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP:
                moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
    }
    public void keyTyped(KeyEvent e) {
    }
}
