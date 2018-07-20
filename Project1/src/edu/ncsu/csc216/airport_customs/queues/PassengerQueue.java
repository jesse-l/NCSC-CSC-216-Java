package edu.ncsu.csc216.airport_customs.queues;

import java.util.LinkedList;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;

/**
 * Implements a simple queue (first-in, first-out list) of Passengers.
 * @author Jo Perry
 */
public class PassengerQueue {
	
	/** The underlying queue data structure. */
	private LinkedList<Passenger> queue;  
	
	/**
	 * Creates an empty queue.
	 */
	public PassengerQueue() { 
		queue = new LinkedList<Passenger>(); 
	}
	
	/**
	 * Returns the number of Passengers in the queue.
	 * @return the number of Passengers
	 */
	public int size() {
		return queue.size();
	}
	
	/**
	 * Adds a new Passenger to the back of the queue.
	 * @param person the Passenger to add
	 */
	public void add(Passenger person){
		queue.addLast(person);
	}
	
	/**
	 * Removes and returns the front Passenger from the queue. 
	 * @return the Passenger at the front of the queue
	 */
	public Passenger remove() {
		//The call to queue.removeFirst() will throw the NoSuchElementException if
		//the queue is empty.  The exception is allowed to pass through to the calling code.
		return queue.removeFirst();
	}
	
	/**
	 * Gets the front Passenger of the queue without removing it, or null
	 * if the queue is empty. Does not remove the Passenger from the queue.
	 * @return the front Passenger or null if the queue is empty
	 */
	public Passenger front() {
		return queue.peek();
	}
	
	/**
	 * Returns true if the queue is empty, false otherwise.
	 * @return true if the queue has no elements
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}
}