package edu.ncsu.csc216.airport_customs.simulation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/** Imports types of passengers to be used for testing. */
import edu.ncsu.csc216.airport_customs.arriving_passengers.Diplomat;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Resident;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Visitor;

/**
 * JUnit test case for the Log class. This test case makes 3 different
 * passengers to be used testing the methods inside the log class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class LogTest {
	/** Creates a log used for testing. */
	private Log myLog;
	/** First passenger used for these tests. */
	private Passenger p;
	/** Second passenger used for these tests. */
	private Passenger p1;
	/** Third passenger used for these tests. */
	private Passenger p2;

	/**
	 * Sets up the variables used during the testing.
	 * 
	 * @throws java.lang.Exception
	 *             Throws exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		// Creates a log file.
		myLog = new Log();

		// Makes a few passengers to use for testing.
		p = new Resident(10, 200);
		p1 = new Diplomat(12, 50);
		p2 = new Visitor(8, 350);
	}

	/**
	 * Test method for getNumCompleted() which returns the number of completed
	 * passengers that have been logged.
	 */
	@Test
	public void testGetNumCompleted() {
		// No passengers have been logged.
		assertEquals(0, myLog.getNumCompleted());

		// Logs passengers information.
		myLog.logItem(p);
		myLog.logItem(p);
		myLog.logItem(p);

		// Three passengers have been logged.
		assertEquals(3, myLog.getNumCompleted());
	}

	/**
	 * Test method for logItem(Passenger) which logs the passengers information
	 * to a total count so it can be averaged.
	 */
	@Test
	public void testLogItem() {
		// Logs one passenger.
		myLog.logItem(p);
		assertEquals(1, myLog.getNumCompleted());
	}

	/**
	 * Test method for averageWaitTime() finds the wait process time for the
	 * entire simulation.
	 */
	@Test
	public void testAverageWaitTime() {
		// Sets a wait time for the passengers
		p1.setWaitTime(10);
		p2.setWaitTime(30);
		p.setWaitTime(20);

		// Logs the passenger's information.
		myLog.logItem(p);
		myLog.logItem(p1);
		myLog.logItem(p2);

		// Calculates the logged wait time average.
		double average = ((10.0 + 20 + 30) / 3) / 60;
		assertEquals(average, myLog.averageWaitTime(), 0.001);
	}

	/**
	 * Test method for averageProcessTime() finds the average process time for
	 * the entire simulation.
	 */
	@Test
	public void testAverageProcessTime() {
		// Logs the passenger's information.
		myLog.logItem(p);
		myLog.logItem(p1);
		myLog.logItem(p2);

		// Calculates the average process time.
		double average = ((200.0 + 50 + 350) / 3) / 60;
		assertEquals(average, myLog.averageProcessTime(), 0.00001);
	}

}
