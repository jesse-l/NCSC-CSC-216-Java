package edu.ncsu.csc216.carrental.model;

/**
 * An error that occurs when there is an attempt to create a Car object with an
 * invalid ID number.
 * 
 * @author Jesse Liddle - jaliddl2
 */
@SuppressWarnings("serial")
public class InvalidIDException extends RuntimeException {

	/**
	 * A constructor for an InvalidIDException that accepts a String as a
	 * parameter that passes that to the RuntimeException class.
	 * 
	 * @param message
	 *            This is the message that is passed to the RuntimeException
	 *            class.
	 */
	public InvalidIDException(String message) {
		super(message);
	}

	/**
	 * A constructor for an InvalidIDException that accepts a String as a
	 * parameter that passes that to the RuntimeException class.
	 */
	public InvalidIDException() {
		super("Please enter a valid Fleet Number for the car.");
	}
}
