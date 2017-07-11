// UserInterface.java - Graphics Code for drawing program
// Author: Chris Wilcox
// Date: 2/2/2017
// Class: CS165
// Email: wilcox@cs.colostate.edu

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class UserInterface extends JFrame implements DrawInterface
{
	// User interface variables
	private static final long serialVersionUID = 1L;
	private JPanel topPanel; // Text panel
	private JPanel bottomPanel; // Drawing panel
	private JLabel textLabel;
	private Font font = new Font("Serif", Font.PLAIN, 24);
	private Color topColor = new Color(0x0076A3);
	private Color bottomColor = new Color(0x6B8E23);
	private BufferedImage surface; // Drawing surface
	private Graphics2D gc; // Graphics context
	private Color colorLine = Color.WHITE;
	private Color colorFill = Color.WHITE;
	private Color colorText = Color.WHITE;

	private int gWidth; // Surface width
	private int gHeight; // Surface height
	private int numberPrimitives = 0; // Counts primitives

	// User interface constructor
	public UserInterface() {

		// Platform customization
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setupTopPanel();    // Setup text area (top panel)
		setupBottomPanel(); // Setup drawing area (bottom panel)

		// Combine panels
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.CENTER);

		// Window setup
		setupWindow();
		
		// Initialize graphics
		initializeGraphics();
	}

	// Setup top panel for status area
	private void setupTopPanel() {

		textLabel = new JLabel("Number Primitives: " + numberPrimitives);
		textLabel.setFont(font);
		textLabel.setForeground(new Color(0xFFFFFF));
		topPanel = new JPanel();
		topPanel.add(textLabel);
		topPanel.setBackground(topColor);
	}

	// Setup bottom panel for drawing primitives
	@SuppressWarnings("serial")
	private void setupBottomPanel() {

		// Setup for rendering
		bottomPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(surface, 0, 0, null);
			}
		};
		bottomPanel.setBackground(bottomColor);
	}

	// Setup window attributes
	private void setupWindow() {
		setSize(550, 650);
		setTitle("Drawing Application");
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	
	// Setup graphics context
	public void initializeGraphics() {

		gWidth = bottomPanel.getWidth();
		gHeight = bottomPanel.getHeight();
		surface = new BufferedImage(gWidth, gHeight, BufferedImage.TYPE_INT_RGB);
		gc = (Graphics2D) surface.getGraphics();
		gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gc.setBackground(bottomColor);
		gc.clearRect(0, 0, gWidth, gHeight);
	}

	//
	// Interface methods
	//

	// Open window
	public void open() {
		setVisible(true);
	}

	// Close window
	public void close() {
		setVisible(false);
		dispose();
	}

	// Clear screen
	public void clear() {
		gc.setBackground(bottomColor);
		gc.clearRect(0, 0, gWidth, gHeight);
	}
	
	// Set colors
	public void lineColor(int color) {
		colorLine = new Color(color);
	}
	public void fillColor(int color) {
		colorFill = new Color(color);
	}
	public void textColor(int color) {
		colorText = new Color(color);
	}
	
	// Set font
	public void setFont(String fontName, int fontSize) {
		gc.setFont(new Font(fontName, Font.BOLD, fontSize));
	}

	// Draw text
	public void drawText(int x, int y, String string) {
		gc.setColor(colorText);
		gc.drawChars(string.toCharArray(), 0, string.length(), x, y);
		update();
	}
	
	// Draw line
	public void drawLine(int x0, int y0, int x1, int y1) {
		gc.setColor(colorLine);
		gc.drawLine(x0, y0, x1, y1);
		update();
	}

	// Draw rectangle
	public void drawRectangle(int x, int y, int width, int height, boolean isFilled) {
		if (isFilled) {
			gc.setColor(colorFill);
			gc.fillRect(x, y, width, height);
		}
		else {
			gc.setColor(colorLine);
			gc.drawRect(x, y, width, height);
		}
		update();
	}

	// Draw polygon
	public void drawPolygon(int[] xPoints, int[] yPoints, boolean isFilled) {
		if (isFilled) {
			gc.setColor(colorFill);
			gc.fillPolygon(xPoints, yPoints, xPoints.length);
		}
		else {
			gc.setColor(colorLine);
			gc.drawPolygon(xPoints, yPoints, xPoints.length);
		}
		update();
	}

	// Draw oval
	public void drawOval(int x, int y, int width, int height, boolean isFilled) {
		if (isFilled) {
			gc.setColor(colorFill);
			gc.fillOval(x, y, width, height);
		}
		else {
			gc.setColor(colorLine);
			gc.drawOval(x, y, width, height);
		}
		update();
	}
	
	// Update number of primitives
	private void update() {
		numberPrimitives++;
		textLabel.setText("Number Primitives: " + numberPrimitives);
		repaint();
	}
}
