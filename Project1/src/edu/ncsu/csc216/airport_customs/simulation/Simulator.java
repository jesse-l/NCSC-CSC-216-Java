package edu.ncsu.csc216.airport_customs.simulation;

/** Importing the Java color library. */
import java.awt.Color;

/** Importing the Passenger class to handle the getCurrentPassengerColor method. */
import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Resident;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Visitor;
/** Importing the ArivalHall class to be used for generating the ArrivalHall. */
import edu.ncsu.csc216.airport_customs.queues.ArrivalHall;
/** Importing the CustomsDesk class to be used for generating the array of desks. */
import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;
/** Importing the TransitSystem class to be used for creating an EventCalendar. */
import edu.ncsu.csc216.airport_customs.queues.TransitSystem;

/**
 * This class oversees the whole entire simulation. This class sets up the
 * ArrivalHall and the array of CustomsDesk. Also reports to the EventCalander
 * for each step in the simulation.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class Simulator {
	/** Minimum number of required customs desks. */
	private static final int MIN_NUM_DESKS = 3;
	/** Maximum number of required customs desks. */
	private static final int MAX_NUM_DESKS = 17;
	/** Number of customs desks the user wants to use in the simulation. */
	private int numCustomsDesks;
	/** The array of customs desks being used during the simulation. */
	private CustomsDesk[] desk;
	/** Number of passengers the user wants to simulate. */
	private int numPassengers;
	/** The arrival hall dealing with the incoming passengers. */
	private ArrivalHall arrivingPassengers;
	/** Number of steps taken during the simulation. */
	private int stepsTaken;
	/** Creates the reference for myLog to be made. */
	private Log myLog;
	/** EventCalendar for the simulation. */
	private EventCalendar events;
	/** Current index in the array of customs desks. */
	private int currentIndex;
	/** Current passenger the simulation is looking at. */
	private Passenger currentPassenger;

	/**
	 * Constructor for the main simulation. This gathers the number of
	 * passengers and the amount of customs desks to be used during this
	 * simulation run.
	 * 
	 * @param numPassengers
	 *            number of passengers to be simulated during the simulation.
	 * @param numCustomsDesks
	 *            number of customs desks to be used in this simulation.
	 * @throws IllegalArgumentException
	 *             if the number of customs desks is not between 3 and 17.
	 * @throws IllegalArgumentException
	 *             if the number of passengers is not at least 1.
	 */
	public Simulator(int numCustomsDesks, int numPassengers) {
		// Checks to see that there are between 3 and 17 customs desks.
		if (numCustomsDesks < MIN_NUM_DESKS || numCustomsDesks > MAX_NUM_DESKS) {
			throw new IllegalArgumentException(
					"Number of customs desks must be between 3 and 17.");

		} else if (numPassengers < 1) {
			throw new IllegalArgumentException(
					"There must be at least one passenger.");
		} else {

			// If more than 1 passenger and between 3 and 17 customs desk then a
			// Simulation is created.
			this.numCustomsDesks = numCustomsDesks;
			this.numPassengers = numPassengers;
			myLog = new Log();
			setUp();
		}
	}

	/**
	 * Sets up the EventCalander, ArrivalHall, and Log for the simulation
	 */
	private void setUp() {
		// Sets up how many customs desks there will be.
		desk = new CustomsDesk[numCustomsDesks];

		// Initializes each customs desk with the Log.
		for (int i = 0; i < numCustomsDesks; i++) {
			desk[i] = new CustomsDesk(myLog);
		}

		// Initializes the arrival hall for the incoming passengers.
		arrivingPassengers = new ArrivalHall(numPassengers, desk);

		// Initializes the event calendar to handle which event is next.
		events = new EventCalendar(desk, arrivingPassengers);

		// Initializes stepsTaken and currentIndex
		stepsTaken = 0;
		currentIndex = -1;
	}

	/**
	 * Handles the next event for the EventCalendar.
	 * 
	 * @throws IllegalStateException
	 *             Throws exception if program tries to take too many steps.
	 */
	public void step() {
		// Checks to see if there are more steps to be made for the simulation.
		if (moreSteps()) {

			// Checks the event calendar to see which event is happening next.
			TransitSystem returnedSystem = events.nextToBeProcessed();

			if (returnedSystem.equals(arrivingPassengers)) {
				currentPassenger = arrivingPassengers.processNext();
				this.currentIndex = currentPassenger.getLineChoice();
			} else {
				// Checks the desks to find which desk was returned.
				int i = 0;
				while (i < desk.length) {
					// Compares the two desks to see if they match.
					if (returnedSystem.equals(desk[i])) {
						currentIndex = i;
						currentPassenger = desk[i].processNext();
						currentPassenger.removeFromWaitinLine();
						break;
					}
					i++;
				}
			}
			stepsTaken++;
		} else {
			currentPassenger = null;
			throw new IllegalStateException("Too many steps.");
		}
	}

	/**
	 * Number of steps taken so far.
	 * 
	 * @return stepTaken the number of steps the simulation has ran.
	 */
	public int getStepsTaken() {
		return stepsTaken;
	}

	/**
	 * Total number of steps the simulation will make.
	 * 
	 * @return totalSteps the total amount of steps to be taken
	 */
	public int totalNumberOfSteps() {
		return (numPassengers * 2);
	}

	/**
	 * Checks to see if there are more steps to be made
	 * 
	 * @return true if the simulation has not finished
	 */
	public boolean moreSteps() {
		if (stepsTaken < totalNumberOfSteps())
			return true;
		else {
			// No more steps resets everything
			currentIndex = -1;
			Passenger p = new Resident(1, 1);
			((Resident) p).resetMostRecentIndex();
			Passenger p1 = new Visitor(1, 1);
			((Visitor) p1).resetMostRecentIndex();
			return false;
		}
	}

	/**
	 * Index of customsDesk selected by the currentPassenger or -1 if null
	 * 
	 * @return lineSelection the line that the current passenger is in
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}

	/**
	 * Gets the color of the current passenger or null if there is no passenger.
	 * This method also catches null pointer exceptions.
	 * 
	 * @return color color of current passenger
	 */
	public Color getCurrentPassengerColor() {
		// Try-catch for nullPointerException in case currentPassenger is null.
		try {
			// Checks to see if currentPassenger is null or not.
			if (currentPassenger.getColor() == null) {
				return null;
			} else {
				return currentPassenger.getColor();
			}
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * Returns true if the most recent passenger has completed customs and left
	 * the line.
	 * 
	 * @return True if the most recent passenger has completed customs and left
	 *         the line.
	 */
	public boolean passengerClearedCustoms() {
		if (currentPassenger != null) {
			return !currentPassenger.isWaitingInCustomsLine();
		} else
			return false;
	}

	/**
	 * Average number of seconds the passengers spent waiting in a customs desk
	 * line prior to actually processing.
	 * 
	 * @return averageWait the average for every passengers wait time
	 */
	public double averageWaitTime() {
		double averageWait = myLog.averageWaitTime();
		return averageWait;
	}

	/**
	 * Average number of seconds the passengers spent waiting to process.
	 * 
	 * @return averageProcess the average for every passengers process time
	 */
	public double averageProcessTime() {
		double averageProcess = myLog.averageProcessTime();
		return averageProcess;
	}
}
