package edu.ncsu.csc216.carrental.util;

/**
 * A simple Stack interface representing a last-in-first-out (LIFO) stack of
 * objects. The usual push and pop operations are provided, as well as a method
 * to peek at the top item on the stack, and a method to test for whether the
 * stack is empty. When a stack is first created, it contains no items.
 * 
 * @author David Wright
 * @param <E>
 *            This is the type of object that is being used in this stack.
 */
public interface SimpleStack<E> {

	/**
	 * Tests if this stack is empty.
	 * 
	 * @return true if and only if this stack contains no items; false
	 *         otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Looks at the object at the top of this stack without removing it from the
	 * stack.
	 * 
	 * @return the object at the top of this stack.
	 */
	public E peek();

	/**
	 * Removes the object at the top of this stack and returns that object as
	 * the value of this function.
	 * 
	 * @return The object at the top of this stack
	 */
	public E pop();

	/**
	 * Pushes an item onto the top of this stack.
	 * 
	 * @param item
	 *            - the item to be pushed onto this stack.
	 */
	public void push(E item);
}