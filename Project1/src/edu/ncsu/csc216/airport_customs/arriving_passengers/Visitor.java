package edu.ncsu.csc216.airport_customs.arriving_passengers;

/** Importing the java color library. */
import java.awt.Color;
/** Importing the CustomsDesk class to be used with the getInLine method. */
import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * This is a child class of Passenger, it makes a Visitor type. This sets the
 * color for the visitor passenger as well as the minimum and maximum process
 * time.
 * 
 * @author jaliddl2 - Jesse Liddle
 */
public class Visitor extends Passenger {

	/** Sets up the color for the before the halfway point in process time */
	private Color pink = new Color(255, 153, 153);
	/** Sets up the color for after the halfway point in process time */
	private Color red = new Color(255, 0, 0);
	/** Sets up minimum process time */
	public static final int MIN_PROCESS_TIME = 180;
	/** Sets up maximum process time */
	public static final int MAX_PROCESS_TIME = 600;
	/** The line that the passenger will get in. */
	private int lineChoice;
	/** The most recent number of passenger generated. */
	private static int mostRecentIndex;
	/** Passes the information from mostRecent Index to the program. */
	private int counter;

	/**
	 * Constructor for a Visitor type of passenger.
	 * 
	 * @param arrivalTime
	 *            Time in which the passenger arrives
	 * @param processTime
	 *            Time it takes to process the passenger
	 */
	public Visitor(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		Visitor.mostRecentIndex = 0;
	}

	/**
	 * This method sets the Visitor in a Visitor customs desk. Overrides the
	 * getInLine from the Passenger class.
	 * 
	 * @param lines
	 *            The array of customs desks lines
	 */
	@Override
	public void getInLine(CustomsDesk[] lines) {
		// Calls method to decided which line this passenger will join.
		lineChoice = pickLine(lines);

		// Adds this passenger to the line of his/her choice.
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
		// Finds the halfway point between the max and min process time.
		int halfTime = ((MAX_PROCESS_TIME - MIN_PROCESS_TIME) / 2)
				+ MIN_PROCESS_TIME;

		// Sets color according to the halfway point.
		if (getProcessTime() < halfTime) {
			return pink;
		} else {
			return red;
		}
	}

	/**
	 * Helper method for getInLine. This method is the brains behind which line
	 * the passenger will hop in.
	 * 
	 * @param lines
	 *            The array of customs desks that are in the airport.
	 * @return lineChoice This is the index for the line the passenger will
	 *         pick.
	 */
	private int pickLine(CustomsDesk[] lines) {
		// Finds the total number of desks.
		int desks = lines.length - 1;
		// Finds how many desks the visitors can use.
		int visitorDesks = desks / 2;
		counter = mostRecentIndex;

		// Determines which desk the visitor will get in.
		if (visitorDesks == 1) {
			lineChoice = desks;
		} else {
			int line = counter % visitorDesks;
			lineChoice = (lines.length - visitorDesks) + line;
		}
		Visitor.mostRecentIndex++;
		return lineChoice;
	}

	/**
	 * Gets the line that the passenger decided to get into.
	 * 
	 * @return lineChoice The line the passenger is in.
	 */
	public int getLineChoice() {
		return lineChoice;
	}

	/**
	 * Resets the mostRecentIndex variable after the simulation is finished.
	 */
	public void resetMostRecentIndex() {
		Visitor.mostRecentIndex = 0;
	}
}