package edu.ncsu.csc216.carrental.util;

/**
 * A simple collection designed for holding elements prior to processing. A
 * Queue typically orders elements in a FIFO (First-In-First-Out) manner.
 * 
 * @author David Wright
 * @param <E>
 *            This is the type of object that is being used in this queue.
 */
public interface SimpleQueue<E> {

	/**
	 * Inserts the specified element into this queue.
	 * 
	 * @param item
	 *            The element to add
	 */
	public void add(E item);

	/**
	 * Retrieves and removes the head of this queue.
	 * 
	 * @return the element at head of this queue
	 */
	public E remove();

	/**
	 * Retrieves, but does not remove, the head of this queue.
	 * 
	 * @return the head of this queue
	 */
	public E peek();

	/**
	 * Tests if this queue is empty.
	 * 
	 * @return true if and only if this queue contains no items; false
	 *         otherwise.
	 */
	public boolean isEmpty();
}