/*
 * Name: Esther Soon
 * Class: CMSC 204-38176
 * Instructor: Farnaz Eivazi
 * Date: 2/22/2022
 * Description: A program to create a utility class that converts an infix expression to a postfix expression, 
 * 				a postfix expression to an infix expression and evaluates a postfix expression
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Esther Soon
*/
 /**@author Esther Soon*/

public class Notation {
	
	//Constructor
	public Notation() {
	}
	
	/**
	 * Evaluates a postfix expression from a string to a double
	 * @param postfixExpr - the postfix expression in String format
	 * @return the evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException - if the postfix expression format is invalid
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException{
		MyStack<Double> evaluate = new MyStack<Double>(postfixExpr.length());
		char[] ch = postfixExpr.toCharArray();
		
		for(int i=0; i<ch.length; i++) {
			//If the current character in the postfix expression is a space, ignore it.
			if (ch[i] == ' ') {
				break;
			}
			
			//If the current character is an operand or left parenthesis, push on the stack
			if(Character.isDigit(ch[i]) || isLeftPar(ch[i])) {
				double add = ch[i] - '0';
				try {
					evaluate.push(add);
				}catch (StackOverflowException e){
					throw new StackOverflowException();
				}
			}
			//If the current character is an operator
			else if(isOperator(ch[i])) {
				//If there are fewer than 2 values throw an error
				if(evaluate.size() < 2) {
					throw new InvalidNotationFormatException();
				}
				
				double pop1=0;
				double pop2=0;
				double total=0;
				
				//Pop the top 2 values from the stack
				try {
					pop2 = evaluate.pop();
					pop1 = evaluate.pop();
				}catch(StackUnderflowException e) {
					throw new StackUnderflowException();
				}
			
				//Perform arithmetic calculation
				switch(ch[i]) {
				case '+': total = pop1+pop2;
						  break;
				case '-': total = pop1 - pop2;
						  break;
				case '*': total = pop1 * pop2;
						  break;
				case '/': total = pop1 / pop2;
						  break;
				}
				
				//Put resulting value into stack
				try {
					evaluate.push(total);
				} catch (StackOverflowException e) {
					throw new StackOverflowException();
				}
			}
		}//end for loop
		
		//if more than one value, throw an error
		if(evaluate.size() > 1) {
			throw new InvalidNotationFormatException();
		}
		
		//Last value in the stack is the result of the postfix expression
		double result=0;
		try {
			result = evaluate.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * Convert the Postfix expression to the Infix expression
	 * @param postfix - the postfix expression in string format
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException - if the postfix expression format is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
		MyStack<String> evaluate = new MyStack<String>(postfix.length());
		char[] ch = postfix.toCharArray();
		
		for(int i=0; i<ch.length; i++) {
			//If the current character in the postfix expression is a space, ignore it.
			if (ch[i] == ' ') {
				break;
			}
			
			//If the current character is an operand, push it on the stack
			if(Character.isDigit(ch[i])) {
				String add = ""+ch[i];
				try {
					evaluate.push(add);
				}catch (StackOverflowException e){
					throw new StackOverflowException();
				}
			}
			
			//If the current character is an operator
			else if(isOperator(ch[i])) {
				//If there are fewer than 2 values throw an error
				if(evaluate.size() < 2) {
					throw new InvalidNotationFormatException();
				}
				
				//Create a string with 1st value and then the operator and then the 2nd value.
				String pop1="", pop2="", result="";
				
				//Encapsulate the resulting string within parenthesis
				try {
					pop2 = evaluate.pop();
					pop1 = evaluate.pop();
					result += "(" + pop1 + ch[i] + pop2 + ")";
					//Push the resulting string back to the stack
					evaluate.push(result);
				}catch(StackUnderflowException e) {
					throw new StackUnderflowException();
				}catch(StackOverflowException e) {
					throw new StackOverflowException();
				}
			}
		}//end for loop
		
		//if more than one value, throw an error
		if(evaluate.size() > 1) {
			throw new InvalidNotationFormatException();
		}
				
		//Last value in the stack is infix string
		String result="";
		try {
			result = evaluate.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix - the infix expression in string format
	 * @return the postfix expression in string format
	 * @throws InvalidNotationFormatException - if the infix ch format is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
		MyStack<String> evaluate = new MyStack<String>(infix.length());
		MyQueue<String> solution = new MyQueue<String>(infix.length());
		char[] ch = infix.toCharArray();
		
		for(int i=0; i<ch.length; i++) {
			//If the current character in the infix expression is a space, ignore it.
			if (ch[i] == ' ') {
				continue;
			}
			
			//If the current character in the infix is a digit, copy it to the postfix solution queue
			else if(Character.isDigit(ch[i])) {
				String add = ""+ch[i];
				try {
					solution.enqueue(add);
				}catch (QueueOverflowException e){
					throw new QueueOverflowException();
				}
			}
			
			//If the current character in the infix is a left parenthesis, push it onto the stack 
			else if(isLeftPar(ch[i])) {
				String add = ""+ch[i];
				try {
					evaluate.push(add);
				}catch (StackOverflowException e){
					throw new StackOverflowException();
				}
			}
			
			//If the current character in the infix is an operator
			else if(isOperator(ch[i])) {
				// Pop operators (if there are any) at the top of the stack while they have
				// equal or higher precedence than the current operator, and insert the 			
				// popped operators in postfix solution queue
				while(evaluate.isEmpty()==false && evaluate.top()!="(" && (precedence(ch[i])<=precedence(evaluate.top().charAt(0)))) {
					String pop1 = evaluate.pop();
					solution.enqueue(pop1);
				}
				//Push the current character in the infix onto the stack 
				String add= "" + ch[i];
				evaluate.push(add);			
			}
			
			//If the current character in the infix is a right parenthesis 
			else if(isRightPar(ch[i])) {
				// Pop operators from the top of the stack and insert them in postfix solution 
				// queue until a left parenthesis is at the top of the stack, if no left parenthesis-throw an error
				while(!evaluate.isEmpty() && !evaluate.top().equals("(")) {
					String pop1 = evaluate.pop();
					solution.enqueue(pop1);
				}
				if(evaluate.size()==0){
					throw new InvalidNotationFormatException();
				}else {
					evaluate.pop();
				}
					
			}
			
		}//end for loop
		
		//Pop any remaining operators and insert them into postfix solution queue
		try {
			while(evaluate.isEmpty()==false) {
				String pop1 = evaluate.pop();
				solution.enqueue(pop1);
			}
		}catch (StackUnderflowException e) {
			e.printStackTrace();
		}catch (QueueOverflowException e) {
			e.printStackTrace();
		}
		
		return solution.toString();

	}
	
	//Extra Methods
	/**
	 * Returns true is the character is a operator
	 * @param ch - a character
	 * @return true if the character is a operator
	 */
	public static boolean isOperator(char ch) {
		if (ch=='+' || ch=='-' || ch=='*' || ch=='/') {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Returns true is the character is a left parenthesis 
	 * @param ch - a character
	 * @return true if the character is a left parenthesis 
	 */
	public static boolean isLeftPar(char ch) {
		if(ch=='(') {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Returns true is the character is a right parenthesis 
	 * @param ch - a character
	 * @return true if the character is a right parenthesis 
	 */
	public static boolean isRightPar(char ch) {
		if(ch==')') {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Returns an integer depending on precedence of operations
	 * @param ch - a character
	 * @return 2 if ch is multiplication or division
	 * @return 1 if ch is addition or subtraction
	 */
	public static int precedence(char ch) {
		if(ch=='*' || ch=='/') {
			return 2;
		}else if(ch=='+' || ch=='-'){
			return 1;
		}else {
			return 0;
		}
	}
}
