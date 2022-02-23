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

public class StackUnderflowException extends RuntimeException{
	public StackUnderflowException() {
		super("StackUnderflowException");
	}
}
