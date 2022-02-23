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

import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T>{

	private ArrayList<T> stack;
	private int size;

	/**
	 * Provide two constructors
	 * 1. takes in an int as the size of the stack
	 * 2. default constructor - uses default as the size of the stack
	 */
	public MyStack() {
		stack = new ArrayList<T>();
		size=0;
	}
	public MyStack(int stackSize) {
		stack = new ArrayList<T>(stackSize);
		size = stackSize;
	}
	
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if(stack.size() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	@Override
	public boolean isFull() {
		if(stack.size()==size) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty() == true) {
			throw new StackUnderflowException();
		}
		return stack.remove(stack.size()-1);
	}
	
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	@Override
	public T top() throws StackUnderflowException {
		if(isEmpty() == true) {
			throw new StackUnderflowException();
		}
		return stack.get(stack.size()-1);	
	}
	
	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	@Override
	public int size() {
		return stack.size();
	}
	
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		if(stack.size()==size) {
			throw new StackOverflowException();
		}
		stack.add(e);
		return true;
	}
	
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	@Override
	public String toString() {
		String elementsString = "";
		for(int i=0; i<stack.size(); i++) {
			elementsString += stack.get(i);
		}
		return elementsString;
	}
	
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		String elementsString = "";
		for(int i=0; i<stack.size(); i++) {
			if(i == stack.size()-1) {
				elementsString += stack.get(i);
				break;
			}
			elementsString += stack.get(i) + delimiter;
			
		}
		return elementsString;
	}
	
	/**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
	  */
	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> copyList = new ArrayList<T>(list.size());
		for (int i=0; i<list.size(); i++) {	
			try {
				copyList.add(list.get(i));
			}catch(StackOverflowException e) {
				throw new StackOverflowException();
			}		
		}
		for (int i=0; i<copyList.size(); i++) {	
			try {
				this.push(copyList.get(i));
			}catch(StackOverflowException e) {
				throw new StackOverflowException();
			}		
		}
	}
	
}
