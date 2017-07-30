// ExpressionTree.java
// Author: Chris Wilcox
// Date:   3/19/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu
// edited by cole

// NOTE: Leave the package declaration like this:
// package cs165;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Student code for parsing and building a tree representing numeric expressions
 */
public class ExpressionTree extends AbstractTree {

    public InfixTokenList parse(String expression) {
        InfixTokenList infix = new InfixTokenList();

        // YOUR CODE HERE
        StringTokenizer st = new StringTokenizer(expression, "(?<=[-+*/()%])|(?=[-+*/()%])", true);
        while (st.hasMoreTokens()) {
        	String s = st.nextToken().trim();
        	if (!s.isEmpty())
        		infix.add(s);
        }
        return infix;
    }

    public PostfixTokenList convert(InfixTokenList infix) {
        PostfixTokenList postfix = new PostfixTokenList();
        
        // YOUR CODE HERE
        Stack<String> operator = new Stack<String>();
        for (String token : infix) {
        	
        	// Integer
        	if (isInteger(token.trim())) {
        		postfix.add(token);
        	}
        	
        	// Operator
        	else if (isOperator(token.trim())) {
        		while (!operator.isEmpty() && isOperator(operator.peek().trim()) &&
        				(precedence(operator.peek().trim()) <= precedence(token.trim()))) 
        			postfix.add(operator.pop()); //bug
        		operator.push(token);
        	}
        	
        	// left parenthesis
        	else if (token.trim().equals("(")) {
        		operator.push(token);
        	} 
        	
        	// Right parenthesis
        	else if (token.trim().equals(")")) {
        		while (!operator.isEmpty() && !operator.peek().trim().equals("(")) 
        			postfix.add(operator.pop());
        		if (!operator.isEmpty())
        			operator.pop();
        	}
        }
        while (!operator.isEmpty())
        	postfix.add(operator.pop());
        return postfix;
    }

    public void build(PostfixTokenList postfix) {
        while (!postfix.isEmpty()) {
            String token = postfix.remove(postfix.size() - 1);
            if(root == null)
                root = new Node(token);
            else
                buildRecursive(root, token);
        }
    }

    /**
     * Recursively adds tokens to the next available position in the subtree.
     * The algorithm should perform these steps in this order:
     * <ol>
     *     <li>If the right child of the current node is null, create a new node with the token, assign it as the
     *     right child, and return true.</li>
     *     <li>If the right child if the current node is an operator, make a recursive call passing the right
     *     child and the token, and return true if the recursive call is successful,
     *     otherwise continue down this list.</li>
     *     <li>If the left child of the current node is null, create a new node with the token,
     *     assign it as the left child, and return true.</li>
     *     <li>If the left child of the current node is an operator, make a recursive call passing the
     *     left child and the token, and return true if the recursive call is successful, otherwise all of the
     *     above steps have failed so we return false.</li>
     * </ol>
     * About 20 lines of code.
     * @param current The root of the subtree where the method will try to add the token
     * @param token The token to be added to the tree
     * @return true if the token could be added to the subtree, false otherwise
     */
    public boolean buildRecursive(Node current, String token) {

        // YOUR CODE HERE
    	if (current.right == null) {
    		Node right = new Node(token);
    		current.right = right;
    		return true;
    	} 
    	if (isOperator(current.right.token.trim())) {
    		if(buildRecursive(current.right, token))
    			return true;
    	}
    	if (current.left == null) {
    		Node left = new Node(token);
    		current.left = left;
    		return true;
    	}
    	if (isOperator(current.left.token.trim())) {
    		if(buildRecursive(current.left, token))
    			return true;
    	}
        return false;
    }

    public String prefix() {
        return prefixRecursive(root);
    }

    private String prefixRecursive(Node current) {
    	
        // YOUR CODE HERE
    	String preOrderNodes = "";
    	if (current != null) {
    		preOrderNodes += current.token + " ";
    		preOrderNodes += prefixRecursive(current.left);
    		preOrderNodes += prefixRecursive(current.right);
    	}
    	return preOrderNodes;
    }

    public String infix() {
        return infixRecursive(root);
    }

    private String infixRecursive(Node current) {

        // YOUR CODE HERE
    	String inOrderNodes = "";
    	if (current != null) {
    		if (current.left != null)
    			inOrderNodes += "(" + infixRecursive(current.left);
    		inOrderNodes += current.token;
    		if (current.right != null)
    			inOrderNodes += infixRecursive(current.right) + ")";
    	}
    	return inOrderNodes;
    }

    public String postfix() {
        return postfixRecursive(root);
    }

    private String postfixRecursive(Node current) {

        // YOUR CODE HERE
    	String postOrderNodes = "";
    	if (current != null) {
    		postOrderNodes += postfixRecursive(current.left);
    		postOrderNodes += postfixRecursive(current.right);
    		postOrderNodes += current.token + " ";
    	}
        return postOrderNodes;
    }

    public int evaluate() {
        return evaluateRecursive(root);
    }

    private int evaluateRecursive(Node current) {
        // YOUR CODE HERE
    	int result = Integer.MAX_VALUE, operand1, operand2;
    	String temp;
    	if (current == null)
    		result = 0;
    	else {
    		temp = current.token;
    		if (isOperator(current.token)) {
    			operand1 = evaluateRecursive(current.left);
    			operand2 = evaluateRecursive(current.right);
    			if (temp.equals("+"))
    				result = operand1 + operand2;
    			else if (temp.equals("-")) 
    				result = operand1 - operand2;
    			else if (temp.equals("*"))
    				result = operand1 * operand2;
    			else if (temp.equals("/"))
    				result = operand1 / operand2;
    			else if (temp.equals("%"))
    				result = operand1 % operand2;
    		} else
    			result = valueOf(current.token);
    	}
    	return result;
    }
}
