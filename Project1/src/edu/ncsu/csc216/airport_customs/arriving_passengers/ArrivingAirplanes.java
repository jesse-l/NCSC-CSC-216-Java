package edu.ncsu.csc216.airport_customs.arriving_passengers;
import java.util.*;

/**
 * A simple factory class to generate passengers on international flights to US Airports.
 *   Visitor passengers are generated 50% of the time.
 *   Diplomatic passengers are generated 5% of the time.
 *   Resident passengers are generated 45% of the time.
 * @author Jo Perry
 * @author Sarah Heckman
 * @see Visitor
 * @see Resident
 * @see Diplomat 
 */
public class ArrivingAirplanes {
	/** Absolute time for passengers created for the simulation.  The time starts at zero
	 * and increases by up to MAX_PASSENGER_GENERATION_DELAY for each passenger created. */
	private static int time = 0; 
	/** Random object with seed that allows for testing of simulation */
	private static Random randomNumber = new Random(10); 
	/** Maximum delay between creation of passengers */
	private static final double MAX_PASSENGER_GENERATION_DELAY = 15; 
	/** Percentage of time visitor should be created */
	private static final double VISITOR_PERCENT = .50;
	/** Percentage of time a diplomatic passenger should be created */
	private static final double DIPLOMAT_PERCENT = .05;
	/** Minimum amount of time required to process a visitor */
static int step = 1;
	
	/**
	 * Generate a new passenger as described in the class comments.  
	 * @return the passenger created
	 */
	public static Passenger generatePassenger() {
		// Update the overall time with up to the floor of MAX_PKG_GENERATION_DELAY seconds.
		// The value is cast to an int, which is the floor of the original double.
		time += (int)(1 + randomNumber.nextDouble() * MAX_PASSENGER_GENERATION_DELAY);
		// Random number that determines which type of passenger will be created.  The generated number
		// is between 0 and 1.0.  By splitting across the range of numbers generated, we can simulate
		// creation of different passengers of appropriate types.
		double x = randomNumber.nextDouble();
		if (x < VISITOR_PERCENT) {
			// If the generated number is less than VISITOR_PERCENT, create a visitor
			// with a process time between Visitor.MIN_PROCESS_TIME and Visitor.MAX_PROCESS_TIME.
			return new Visitor(time, Visitor.MIN_PROCESS_TIME + 
					(int) (randomNumber.nextDouble() * (Visitor.MAX_PROCESS_TIME - Visitor.MIN_PROCESS_TIME))); 
		}
		else if (x < VISITOR_PERCENT + DIPLOMAT_PERCENT) {
			// If the generated number is less than VISITOR_PERCENT + DIPLOMAT_PERCENT, create a diplomat
			// with a process time between Diplomat.MIN_PROCESS TIME and Diplomat.MAX_PROCESS_TIME.
			return new Diplomat(time, Diplomat.MIN_PROCESS_TIME + 
					(int) (randomNumber.nextDouble() * (Diplomat.MAX_PROCESS_TIME - Diplomat.MIN_PROCESS_TIME)));
		}
		else {
			// Otherwise, create a resident with a process time between Resident.MIN_PROCESS TIME and Resident.MAX_PROCESS_TIME
			return new Resident(time, Resident.MIN_PROCESS_TIME + 
					(int) (randomNumber.nextDouble() * (Resident.MAX_PROCESS_TIME - Resident.MIN_PROCESS_TIME)));
		}
	}

	/**
	 * Resets the factory.  Use this for testing ONLY! Do not use it in your simulator.
	 */
	public static void resetFactory() {
		time = 0;
		randomNumber = new Random(10);
	}
}
