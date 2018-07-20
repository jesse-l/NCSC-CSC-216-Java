package edu.ncsu.csc216.androtech.model.util;

/**
 * This interface class is designed to assist with the LinkedList of Devices
 * inside the DeviceList class. This interface generates the functionality for
 * getting the next object as well as checking to see if there is a next object.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 * @param <E>
 *            This is the type of object that the SimpleIterator interface will
 *            be handling.
 */
public interface SimpleIterator<E> {
	/**
	 * This method checks to see if the list has a next object and returns true
	 * if it does.
	 * 
	 * @return Returns true if the list has a next object otherwise it returns
	 *         false.
	 */
	public boolean hasNext();

	/**
	 * Gets the next object in the list, if it exists.
	 * 
	 * @return Returns the next object in the list.
	 */
	public E next();
}
