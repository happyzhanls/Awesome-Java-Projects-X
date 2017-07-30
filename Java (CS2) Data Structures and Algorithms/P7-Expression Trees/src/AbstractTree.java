// AbstractTree.java - abstract class for expression tree assignment.
// Author: Chris Wilcox
// Date:   3/19/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu
// edited by cole

// NOTE: Leave the package declaration like this:
// package cs165;

import java.util.ArrayList;

/**
 * A List of Strings representing an infix expression
 */
class InfixTokenList extends ArrayList<String> {
}

/**
 * A List of Strings representing a postfix expression
 */
class PostfixTokenList extends ArrayList<String> {
}

/**
 * A class providing basic tree ingredients and method signatures.
 * AbstractTree provides the Node data structure and methods to display a tree graphically.
 */
public abstract class AbstractTree {
    // Tree root
    public Node root = null;

    /**
     * Node data structure for building binary trees
     */
    public class Node {
        // Expression token
        public String token;
        // Children nodes
        public Node left = null;
        public Node right = null;

        // Constructor
        public Node(String value) {
            this.token = value;
        }
    }

    /**
     * Separate an infix expression string into a list of tokens.
     * Each token is either an operator, and operand (an integer), or a parenthesis.
     * Use one of the methods you developed in lab to do this job. Perhaps use Scanner
     * specifying the delimiter with useDelimiter(String regex). White space in the
     * expression should not matter in separating out the tokens. About 8 lines of code.
     * @param expression A string containing an infix expression
     * @return A list of the tokens contained in the expression
     */
    public abstract InfixTokenList parse(String expression);

    /**
     * Convert tokens in infix order to postfix order.
     * Use the Shunting-yard algorithm from Edsger Dijkstra, a famous computer scientist. The algorithm calls for
     * a queue, but just add to the end of a list when building up the postfix tokens. Use a java.util.Stack for
     * the stack of operators. Consider using the utility methods defined in AbstractTree: isOperator, isInteger, and
     * precedence. About 30 lines of code.
     * @see <a target="_blank" href="http://en.wikipedia.org/wiki/Shunting-yard_algorithm">Shunting-yard Algorithm</a>
     * @param infix A list of tokens in infix notation
     * @return A list of tokens in postfix notation
     */
    public abstract PostfixTokenList convert(InfixTokenList infix);

    /**
     * Build an expression tree rooted at root from tokens in postfix notation.
     * The starter code in ExpressionTree pops tokens from the list and places them into the
     * tree using a recursive helper method. Complete the recursive helper
     * according to the documentation on the recursive helper in ExpressionTree.
     * @param postfix A list of tokens in postfix notation
     */
    public abstract void build(PostfixTokenList postfix);

    /**
     * Compute the prefix notation string for the expression represented by this tree.
     * Build the result as you traverse the tree in prefix order, meaning accumulate the operator first,
     * then the string from the left and right subtrees. Add an extra space after each token.
     * The recursive helper is about 6 lines of code.
     * @return The expression represented in prefix notation
     */
    public abstract String prefix();

    /**
     * Compute the infix notation string for the expression represented by this tree.
     * Build the result as you traverse the tree in infix order, meaning first accumulate the string from
     * the left subtree, then add the operator, then accumulate the string from the right subtree.
     * This method adds parentheses to maintain the correct evaluation order, add a left parenthesis before
     * traversing the left subtree, and a right parenthesis after traversing the right subtree.
     * Do not add any space to the expression string.
     * The recursive helper is about 11 lines of code.
     * @return The expression represented in infix notation
     */
    public abstract String infix();

    /**
     * Compute the postfix notation string for the expression represented by this tree.
     * Build the result as you traverse the tree in postfix order, meaning first accumulate the string from
     * the left and right subtrees, then add the operator. Add an extra space after each token.
     * The recursive helper is about 6 lines of code.
     * @return The expression represented in postfix notation
     */
    public abstract String postfix();

    /**
     * Evaluate the tree to produce the result of the expression it represents.
     * To evaluate, call the recursive version of the method to get the result from the left subtree,
     * do the same for the right subtree, then combine these two results using the operator.
     * A case statement based on the operator is needed to perform the arithmetic.
     * The recursive helper is about 16 lines of code.
     * @return The int result of the expression
     */
    public abstract int evaluate();

    /**
     * Serialize the expression tree into a graphical format for display.
     * Uses the tree rooted at root.
     * @return Lines of text that can be written to a file and then displayed by graphviz.
     */
    public ArrayList<String> display() {
        ArrayList<String> graph = new ArrayList<>();
        graph.add("digraph BST {");
        graph.add("    ratio = 1.0;");
        graph.add("    node [style=filled]");
        graph.add("    node [fontname=Arial]");
        graph.add("    edge [arrowType=normal]");
        graph.add("    edge [fillcolor=orchid]");
        displayRecursive(root, graph, "root");
        graph.add("}");
        return graph;
    }

    private void displayRecursive(Node current, ArrayList<String> graph, String name) {
        if ((current.left) != null)
            displayRecursive(current.left, graph, name + "L");
        if ((current.right) != null)
            displayRecursive(current.right, graph, name + "R");
        if (isOperator(current.token)) {
            String operator = current.token;
            String left = current.left.token;
            String right = current.right.token;
            if (operator.equals("%")) operator = "\\%";
            if (left.equals("%")) left = "\\%";
            if (right.equals("%")) right = "\\%";
            // Add node
            graph.add("    " + name + " [label=\"" + operator + "\";shape=square;fillcolor=lightskyblue]");
            graph.add("    " + name + " -> " + name + "L");
            graph.add("    " + name + " -> " + name + "R");
        } else
            graph.add("    " + name + "[label=\"" + current.token + "\";shape=circle;fillcolor=lightseagreen]");
    }

    /**
     * Tests whether the token represents one of the allowed operators
     * @param token The string to be tested.
     * @return true if token represents an operator
     */
    public static boolean isOperator(String token) {
        switch (token) {
            case "*":
            case "/":
            case "%":
            case "+":
            case "-":
                return true;
            default:
                return false;
        }
    }

    /**
     * Tests whether the token represents a valid integer
     * @param token The string to be tested
     * @return true if token represents an int
     */
    public static boolean isInteger(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parse the string as an integer
     * @param token The string representing the integer
     * @return The int value or -1 if the string does not represent an integer
     */
    public static int valueOf(String token) {
        try {
            int value = Integer.parseInt(token);
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Match an operator to its infix precedence level
     * @param operator The operator to be matched
     * @return The precedence level, where lower numbers mean higher precedence
     */
    public static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 2;
            case "*":
            case "/":
            case "%":
                return 1;
            default:
                return 0;
        }
    }
}
