

// Calculator Graphics User Interface
// Author: Chris Wilcox
// Date:   7/20/2016
// Class:  CS163/164
// Email:  wilcox@cs.colostate.edu

// I hereby acknowledge example code for a calculator posted by
// on many places on the web.

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

class Calculator extends JFrame {

    // Class variables
    private static final long serialVersionUID = 1L; // serial version
    private static final Font font = new Font("monspaced", Font.PLAIN, 20); // calculator font
    private static JTextField display; // calculator display

    // Main method
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            ; // Do nothing!
        }

        // Display calculator window
        Calculator calculator = new Calculator();
        calculator.setupGraphics();
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.setVisible(true);
    }

    // Setup graphical user interface
    public void setupGraphics() {

        // Setup the display field
        display = new JTextField("0.0", 25);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(font);

        // Listener for numeric and operator buttons
        ActionListener buttonListener = new ButtonListener();

        // Layout numeric keys in a grid.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3, 2, 2));
        String buttonLabel = "";
        for (int button = 0; button < 12; button++) {
            switch (button) {
                case 0:  buttonLabel = "7"; break;
                case 1:  buttonLabel = "8"; break;
                case 2:  buttonLabel = "9"; break;
                case 3:  buttonLabel = "4"; break;
                case 4:  buttonLabel = "5"; break;
                case 5:  buttonLabel = "6"; break;
                case 6:  buttonLabel = "1"; break;
                case 7:  buttonLabel = "2"; break;
                case 8:  buttonLabel = "3"; break;
                case 9:  buttonLabel = "0"; break;
                case 10: buttonLabel = "."; break;
                case 11: buttonLabel = "="; break;
            }

            // Add button to panel
            JButton b = new JButton(buttonLabel);
            b.addActionListener(buttonListener);
            b.setFont(font);
            buttonPanel.add(b);
        }

        // Layout operators in a grid
        JPanel opPanel = new JPanel();
        opPanel.setLayout(new GridLayout(3, 2, 2, 2));
        String[] opOrder = { "+", "-", "*", "/", "%", "^"};
        for (int i = 0; i < opOrder.length; i++) {
            // Add button to panel
            JButton b = new JButton(opOrder[i]);
            b.addActionListener(buttonListener);
            b.setFont(font);
            opPanel.add(b);
        }
        
        // Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setFont(font);
        clearButton.addActionListener(new ClearListener());

        // Backspace button
        JButton backButton = new JButton("Backspace");
        backButton.setFont(font);
        backButton.addActionListener(new BackspaceListener());

        // Layout the content panel.
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout(5, 5));
        content.add(display, BorderLayout.NORTH);
        content.add(buttonPanel, BorderLayout.CENTER);
        content.add(opPanel, BorderLayout.EAST);
        JPanel subPanel = new JPanel();
        subPanel.add(clearButton);
        subPanel.add(backButton);
        content.add(subPanel, BorderLayout.SOUTH);
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Finish building the window
        this.setContentPane(content);
        this.pack();
        this.setTitle("Simple Calculator");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    // Button Listener
    class ButtonListener implements ActionListener {
       
        public void actionPerformed(ActionEvent e) {

            String action = e.getActionCommand();
            String current = display.getText();
            char digit = action.charAt(0);
            if ((Character.isDigit(digit)) || digit == '.') {

                // clear default
                if (current.equals("0.0")) current = "";
                
                // add number
                display.setText(current + action);
            }
            
            switch (action) {

                case "+": case "-": case "*": case "/": case "%": case "^": case "=":

                    // add operator
                    display.setText(current + " " + action + " ");
                    
            }

            // evaluate expression
            if (action.equals("=")) {
                display.setText(P3.evaluate(current));
            }
        }
    }

    // Clear Listener
    class ClearListener implements ActionListener {
       
        public void actionPerformed(ActionEvent e) {

            // reset display
            display.setText("0.0");
        }
    }

    // Backspace Listener
    class BackspaceListener implements ActionListener {
       
        public void actionPerformed(ActionEvent e) {

            // remove character
            String current = display.getText();
            current = current.substring(0, current.length()-1);
            display.setText(current);
        }
    }

}
