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

public class MyQueue<T> implements QueueInterface<T>{
	private ArrayList<T> queue;
	private int size;
	
	/** provide two constructors 
	 * 1. takes an int as the size of the queue
	 * 2. default constructor - uses a default as the size of the queue 
	 */
	public MyQueue() {
		queue = new ArrayList<T>();
		size=0;
	}
	public MyQueue(int queueSize) {
		queue = new ArrayList<T>(queueSize);
		size = queueSize;
	}
	
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if(queue.size() == 0) {
			return true;
		}else {
			return false;
		}
	} 

	/**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
	@Override
	public boolean isFull() {
		if(queue.size()==size) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()==true) {
			throw new QueueUnderflowException();
		}
		return queue.remove(0);
	}
	
	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {
		return queue.size();
	}

	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(queue.size() == size) {
			throw new QueueOverflowException();
		}
		queue.add(e);
		return true;
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	@Override
	public String toString() {
		String elementsString = "";
		for(int i=0; i<queue.size(); i++) {
			elementsString += queue.get(i);
		}
		return elementsString;
	}

	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		String elementsString = "";
		for(int i=0; i<queue.size(); i++) {
			if(i == queue.size()-1) {
				elementsString += queue.get(i);
				break;
			}
			elementsString += queue.get(i) + delimiter;
			
		}
		return elementsString;
	}

	/**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	  * list reference within your Queue, you will be allowing direct access to the data of
	  * your Queue causing a possible security breech.
	  * @param list elements to be added to the Queue
	  * @throws QueueOverflowException if queue is full
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
				this.enqueue(copyList.get(i));
			}catch(StackOverflowException e) {
				throw new StackOverflowException();
			}		
		}
	}
	
}
