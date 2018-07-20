package edu.ncsu.csc216.airport_customs.queues;

/** Imports Passenger to deal with the waiting passengers. */
import java.util.NoSuchElementException;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
/** Imports Log to log the passengers information once they have finished processing. */
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * Represents the desks where the passengers pass through as they go through
 * their final processing. Implements TransitSystem and contains a
 * PassengerQueue for the line of passengers.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class CustomsDesk implements TransitSystem {
	/** The line of passengers waiting to be processed. */
	private PassengerQueue line;
	/**
	 * The passenger at the front of the line logs their information during
	 * processing.
	 */
	private Log myLog;
	/**
	 * The time when the line for this custom desk will finally be clear of all
	 * passengers.
	 */
	private int timeWhenAvailable;

	/**
	 * Constructor for the CustomsDesk that the passengers will be waiting in.
	 * 
	 * @param myLog
	 *            This is the log file that the passengers will log their
	 *            information into.
	 */
	public CustomsDesk(Log myLog) {
		this.myLog = myLog;
		line = new PassengerQueue();
		timeWhenAvailable = 0;
	}

	/**
	 * Returns the number of passengers still in line.
	 * 
	 * @return size The size of the line of passengers.
	 */
	public int size() {
		return line.size();
	}

	/**
	 * Returns the time when the line will be cleared.
	 * 
	 * @return timeWhenAvailable The time the line will be cleared of all
	 *         passengers.
	 */
	public int getTimeWhenAvailable() {
		return timeWhenAvailable;
	}

	/**
	 * Removes passenger from the line and logs their information in the
	 * process. Returning the removed passenger.
	 * 
	 * @return beenProcessed
	 */
	@Override
	public Passenger processNext() {
		Passenger beenProcessed = null;

		// Checks to see if there is a next passenger in line.
		if (hasNext()) {
			// Grabs passenger being processed
			beenProcessed = line.front();

			// Logs passengers information
			myLog.logItem(beenProcessed);

			// Removes passenger from the line
			beenProcessed.removeFromWaitinLine();
			line.remove();
		} else 
			throw new NoSuchElementException("No more passengers waiting.");
		return beenProcessed;
	}

	/**
	 * Returns if the line has a next passenger or not.
	 * 
	 * @return isNextPassenger True if there is a passenger next
	 */
	public boolean hasNext() {
		return !line.isEmpty();
	}

	/**
	 * Tells when the passenger at the front of the line will finish processing
	 * or returns max integer so it knows the lines are empty.
	 * 
	 * @return departTime The time the passenger currently processing will
	 *         depart
	 */
	@Override
	public int departTimeNext() {
		int departTime = 0;

		// Checks to see if there is a next passenger.
		if (line.front() != null) {

			// Grabs front passenger.
			Passenger p = line.front();
			departTime = (p.getProcessTime() + p.getWaitTime() + p
					.getArrivalTime());
		} else
			return Integer.MAX_VALUE;
		return departTime;
	}

	/**
	 * Adds a passenger to the end of the line updating the passenger's wait
	 * time and when the line will be clear.
	 * 
	 * @param p
	 *            Passenger currently being added to the line
	 */
	public void addToLine(Passenger p) {
		p = setPassengerWait(p);

		// Adds the passenger to the line.
		line.add(p);

		// Sets the line choice for the passenger.
		p.setLineIndex(p.getLineChoice());

	}

	/**
	 * This is a helper method to addToLine. This method is used to set the wait
	 * time for the passenger depending on the line it is getting in as well as
	 * changing the timeWhenAvailable for that time.
	 * 
	 * @param p
	 *            Passenger that is being added to the line.
	 * @return Passenger that has had his wait time changed.
	 */
	public Passenger setPassengerWait(Passenger p) {

		// Checks to see if the line is empty or if arrival time is greater than
		// timeWhenAvailable.
		if (line.isEmpty()) {

			// Sets wait time for passenger.
			p.setWaitTime(0);

			// Updates timeWhenAvailable for the next passenger.
			timeWhenAvailable = p.getProcessTime() + p.getArrivalTime();
		} else if (timeWhenAvailable - p.getArrivalTime() == 0) {

			// Sets wait time for passenger.
			p.setWaitTime(0);

			// Updates timeWhenAvailable for the next passenger.
			timeWhenAvailable += p.getProcessTime();
		} else if (timeWhenAvailable - p.getArrivalTime() >= 0) {

			// Sets wait time for passenger.
			p.setWaitTime(timeWhenAvailable - p.getArrivalTime());

			// Updates timeWhenAvailable for the next passenger.
			timeWhenAvailable += p.getProcessTime();

		}

		return p; // Return the passenger with updated wait time.
	}
}
