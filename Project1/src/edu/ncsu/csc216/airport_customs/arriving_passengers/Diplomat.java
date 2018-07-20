package edu.ncsu.csc216.airport_customs.arriving_passengers;

/** Importing the java color library. */
import java.awt.Color;
/** Importing the CustomsDesk class to be used with the getInLine method. */
import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * This is a child class of Passenger, it makes a Diplomat type. This sets the
 * color for the diplomat passenger as well as the minimum and maximum process
 * time.
 *
 * @author jaliddl2 - Jesse Liddle
 */
public class Diplomat extends Passenger {

	/** Sets up the color for the before the halfway point in process time */
	private Color lightGreen = new Color(153, 255, 153);
	/** Sets up the color for after the halfway point in process time */
	private Color green = new Color(0, 255, 0);
	/** Sets up minimum process time */
	public static final int MIN_PROCESS_TIME = 30;
	/** Sets up maximum process time */
	public static final int MAX_PROCESS_TIME = 90;
	/** The line that the passenger will get in. */
	private int lineChoice;

	/**
	 * Constructor for the Diplomat type of passenger
	 * 
	 * @param arrivalTime
	 *            Time in which the passenger arrives
	 * @param processTime
	 *            Time it takes to process the passenger
	 */
	public Diplomat(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
	}

	/**
	 * This method sets the Diplomat in a Diplomat customs desk. Overrides the
	 * getInLine from the Passenger class.
	 * 
	 * @param lines
	 *            The array of customs desks lines
	 */
	@Override
	public void getInLine(CustomsDesk[] lines) {
		// Finds total number of desks.
		int numOfDesks = lines.length;
		// Finds the middle desk.
		lineChoice = numOfDesks / 2;

		// Inserts the new diplomat into the line it picked.
		lines[lineChoice].addToLine(this);
		setLineIndex(lineChoice);
	}

	/**
	 * This class returns the color of the passenger depending on how long they
	 * have been processing. Overrides the getColor from the Passenger class.
	 * 
	 * @return color Returns the color of the passenger depending on the time.
	 */
	@Override
	public Color getColor() {

		// Finds the halfway point for the process time.
		int halfTime = ((MAX_PROCESS_TIME - MIN_PROCESS_TIME) / 2)
				+ MIN_PROCESS_TIME;

		// Returns the color depending on the time compared to the halfway
		// point.
		if (getProcessTime() < halfTime) {
			return lightGreen;
		} else {
			return green;
		}
	}

	/**
	 * Gets the line that the passenger decided to get into.
	 * 
	 * @return lineChoice The line the passenger is in.
	 */
	public int getLineChoice() {
		return lineChoice;
	}
}
