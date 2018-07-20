package edu.ncsu.csc216.airport_customs.simulation;

/** Imports Passenger to deal with accessing passenger's information. */
import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;

/**
 * Log file used to gather all of the information processed during this
 * simulation and to find the average wait time and process time.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class Log {
	/** Number of passengers who have logged their information */
	private int numCompleted;
	/** The total wait time of passengers who have finished */
	private int totalWaitTime;
	/** The total process time of passengers who have finished */
	private int totalProcessTime;

	/**
	 * The constructor for the log object. Initializes the variables to start as
	 * 0.
	 */
	public Log() {
		numCompleted = 0;
		totalWaitTime = 0;
		totalProcessTime = 0;
	}

	/**
	 * Gets the number of passengers who have completed the simulation.
	 * 
	 * @return numCompleted returns the number of completed passengers
	 */
	public int getNumCompleted() {
		return numCompleted;
	}

	/**
	 * Updates the information on wait time, process time, and number completed.
	 * 
	 * @param p
	 *            Passenger used to update information in log
	 */
	public void logItem(Passenger p) {
		// Checks to see if the passenger is null
		if (p != null) {

			// Checks to see if the process time is greater than 0.
			if (p.getProcessTime() > 0)
				totalProcessTime += p.getProcessTime();

			// Checks to see if the arrival time is greater than 0.
			if (p.getWaitTime() != 0)
				totalWaitTime += p.getWaitTime();

			// Adds to the number of passengers completed.
			numCompleted++;
		}
	}

	/**
	 * Finds the average wait time for all the passengers in the simulation.
	 * 
	 * @return averageWait The average wait time for the simulation
	 */
	public double averageWaitTime() {

		// Checks to see if passengers have been processed.
		if (numCompleted > 0) {

			// Finds the average wait time for all the passengers that have been
			// logged.
			double averageWait = (double) totalWaitTime / (double) numCompleted
					/ 60;
			return averageWait;
		} else {
			return 0;
		}
	}

	/**
	 * Finds the average process time for all the passengers in the simulation.
	 * 
	 * @return averageProcess The average process time for the simulation
	 */
	public double averageProcessTime() {

		// Checks to see if passengers have been processed.
		if (numCompleted > 0) {

			// Finds the average process time for all the passengers that have
			// been logged.
			double averageProcess = (double) totalProcessTime
					/ (double) numCompleted / 60;
			return averageProcess;
		} else {
			return 0;
		}
	}

}
