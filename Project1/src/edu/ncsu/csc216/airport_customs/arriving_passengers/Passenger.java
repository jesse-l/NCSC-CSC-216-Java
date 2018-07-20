package edu.ncsu.csc216.airport_customs.arriving_passengers;

/** Importing the java color library. */
import java.awt.Color;
/** Importing the CustomsDesk class to be used with the getInLine method. */
import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * An abstract class that is used to create different types of passengers. This
 * class contains common variables between all the different passenger types.
 * 
 * @author jaliddl2 - Jesse Liddle
 * @version 1.06 - Feb. 8, 2015
 */
public abstract class Passenger {
	/**
	 * Constant representing that the passenger is not yet in line for
	 * processing.
	 */
	private static final int INITIAL_CUSTOMS_LINE_INDEX = -1;
	/** Time it takes for the passenger to arrive at the line. */
	private int arrivalTime;
	/** Time it takes for the passenger to process at the custom desk. */
	private int processTime;
	/** Time it will take to wait in line to process. */
	private int waitTime;
	/** Position in the line the passenger is waiting at. */
	private int lineIndex;
	/** True if the passenger is waiting in line to process. */
	private boolean waitingProcessing;

	/**
	 * Parent constructor for the children Passenger types. This constructor
	 * takes an int for both the arrivalTime and processTime.
	 * 
	 * @param arrivalTime
	 *            the amount of time it will take that passenger to arrive at
	 *            the desk.
	 * @param processTime
	 *            the amount of time it will take that passenger to process.
	 * @throws IllegalArgumentException
	 *             if the process time or arrival time is less than 0.
	 */
	public Passenger(int arrivalTime, int processTime) {
		// Checks to make sure parameters are valid
		if (arrivalTime < 0 || processTime < 0) {
			throw new IllegalArgumentException(
					"Arrival Time or Process Time needs to be greater than 0.");
		}
		this.arrivalTime = arrivalTime;
		this.processTime = processTime;
		waitingProcessing = false;
		lineIndex = INITIAL_CUSTOMS_LINE_INDEX;
	}

	/**
	 * Gets the arrivalTime of the passenger.
	 * 
	 * @return arrivalTime The amount of time it will take the passenger to
	 *         arrive at the gate.
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Gets the waitTime of the passenger.
	 * 
	 * @return waitTime The amount of time the passenger has to wait before
	 *         getting to the front of the line.
	 */
	public int getWaitTime() {
		return waitTime;
	}

	/**
	 * Sets the wait time for the passenger.
	 * 
	 * @param time
	 *            The amount of time it will take for the line in front of the
	 *            passenger to clear.
	 */
	public void setWaitTime(int time) {
		waitTime = time;
	}

	/**
	 * Gets the processTime of the passenger.
	 * 
	 * @return processTime The amount of time it will take the passenger to
	 *         process at the desk.
	 */
	public int getProcessTime() {
		return processTime;
	}

	/**
	 * Gets the position in line the passenger is currently at.
	 * 
	 * @return lineIndex The position in line the passenger is currently at.
	 */
	protected int getLineIndex() {
		return lineIndex;
	}

	/**
	 * Gets if the passenger is currently waiting in line or not.
	 * 
	 * @return waitingProcess True if the passenger is currently waiting to be
	 *         processed.
	 */
	public boolean isWaitingInCustomsLine() {
		return waitingProcessing;
	}

	/**
	 * Removes the passenger from the line.
	 */
	public void removeFromWaitinLine() {
		waitingProcessing = false;
	}

	/**
	 * Sets and updates the position that the passenger is in line.
	 * 
	 * @param index
	 *            The new position of the passenger.
	 */
	public void setLineIndex(int index) {
		lineIndex = index;
		waitingProcessing = true;
	}

	/**
	 * Sets the passenger to waiting in a customs desk line.
	 * 
	 * @param line
	 *            The line that the passenger will join.
	 */
	public abstract void getInLine(CustomsDesk[] line);

	/**
	 * Gets the color of the passenger.
	 * 
	 * @return c The color of the current passenger.
	 */
	public abstract Color getColor();

	/**
	 * Gets the line choice of the passenger.
	 * 
	 * @return lineChoice Is the line the passenger picked to enter.
	 */
	public abstract int getLineChoice();
}