package edu.ncsu.csc216.airport_customs.queues;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;


/**
 * Operations for passengers in a transit system.
 * @author Jo Perry
 * @version 2.1
 */
public interface TransitSystem {

	/**
	 * Does the system have more passengers?
	 * @return true if the system has more passengers
	 */
	boolean hasNext();

	/**
	 * Processes the next passenger in the system and removes the passenger from the system.
	 * @return the passenger who was just processed
	 */
	Passenger processNext();

	/**
	 * Determines the time that the next passenger will leave the system.
	 * If the system is empty, that time is Integer.MAX_VALUE.
	 * @return departure time of the next passenger or Integer.MAX_VALUE if there is none
	 * line is empty.
	 */
	int departTimeNext();

	/**
	 * Determines the number of passengers in the system.
	 * @return the number of passengers
	 */
	int size();
}