// TestCode.java - test code for expression tree assignment.
// Author: Chris Wilcox
// Date:   3/19/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TestCode {

    /**
     * Runs basic tests similar to checkin.
     * main expects args[0] to be an entire expression, so you'll have to surround it in double quotes
     * when entering your expression in the run configuration.
     * main calls all abstract methods, but you can comment out or ignore methods that you have
     * not implemented to allow for incremental development.
     * @param args The first element should be an entire expression string. For example "3*5+6/2" or "5-3+12/2*(9%5)"
     */
    public static void main(String[] args) {
        // Instantiate student code
        ExpressionTree eTree = new ExpressionTree();

        String expression = args[0];
        System.out.println("Original Expression: " + expression);

        // Verify parse
        InfixTokenList infix = eTree.parse(expression);
        System.out.println("Infix Tokens: " + infix.toString());

        // Verify convert
        PostfixTokenList postfix = eTree.convert(infix);
        System.out.println("Postfix Tokens: " + postfix.toString());

        // Verify build
        eTree.build(postfix);
        System.out.println("Build: complete");

        // Verify prefix
        System.out.println("Prefix: " + eTree.prefix());

        // Verify infix
        System.out.println("Infix: " + eTree.infix());

        // Verify postfix
        System.out.println("Postfix: " + eTree.postfix());

        // Verify evaluate
        System.out.println("Evaluate: " + eTree.evaluate());

        // Verify display
        System.out.println("Display: complete");
        ArrayList<String> graph = eTree.display();
        writeFile("graph.dot", graph);
    }

    /**
     * Utility method to write contents of List to a file, line by line.
     * @param filename Name of the file to write to
     * @param contents The List of lines to write to the file
     */
    public static void writeFile(String filename, List<String> contents) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            for (String line : contents)
                writer.println(line);
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filename + "!");
        }
    }
}
