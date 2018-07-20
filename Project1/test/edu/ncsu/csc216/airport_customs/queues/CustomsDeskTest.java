package edu.ncsu.csc216.airport_customs.queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Resident;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * JUnit test case for the CustomsDesk class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class CustomsDeskTest {
	/** Sets up a custom desk to be used for the testing. */
	private CustomsDesk c;
	/** Sets up a passenger to be used for testing. */
	private Passenger p;
	/** Sets up a passenger to be used for testing. */
	private Passenger p1;
	/** Sets up a passenger to be used for testing. */
	private Passenger p2;
	/** Sets up a log so the customs desk can be created. */
	private Log myLog;

	/**
	 * Sets up the variables that will be used during this testing.
	 * 
	 * @throws java.lang.Exception
	 *             Throws exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		// Makes a log so the customs desk can use it.
		myLog = new Log();

		// Creates a customs desk for this test.
		c = new CustomsDesk(myLog);

		// Makes some passengers to be used during the test.
		p = new Resident(10, 200);
		p1 = new Resident(8, 175);
		p2 = new Resident(12, 225);
	}

	/**
	 * Test method for size() which returns the size of the line waiting for the
	 * desk.
	 */
	@Test
	public void testSize() {
		// No one is waiting in line.
		assertEquals(0, c.size());
		c.addToLine(p);
		c.addToLine(p1);
		c.addToLine(p1);
		c.addToLine(p);
		c.addToLine(p2);
		// Added five passengers to this line.
		assertEquals(5, c.size());
	}

	/**
	 * Test method for processNext() which takes the passenger out of line and
	 * processes them.
	 */
	@Test
	public void testProcessNext() {
		boolean pass = false;
		// Passenger gets in customs desk line.
		c.addToLine(p);
		// Grabs the passenger being processed.
		Passenger test = c.processNext();
		if (test.equals(p)) {
			pass = true;
		}

		assertTrue(pass);
	}

	/**
	 * Test method for hasNext() which checks to see if there is a passenger
	 * next in line.
	 */
	@Test
	public void testHasNext() {
		// No one is in line.
		assertFalse(c.hasNext());

		// Adds a passengers to the line.
		c.addToLine(p1);

		// Desk has a passenger waiting in line.
		assertTrue(c.hasNext());
	}

	/**
	 * Test method for departTimeNext() which is used to get the depart time of
	 * the first passenger waiting in line.
	 */
	@Test
	public void testDepartTimeNext() {
		// No one is waiting in line.
		assertEquals(Integer.MAX_VALUE, c.departTimeNext());

		// Calculates departNext
		int departNext = p.getArrivalTime() + p.getProcessTime()
				+ p.getWaitTime();

		// Adds passenger to the line.
		c.addToLine(p);
		assertEquals(departNext, c.departTimeNext());

		// Adds passenger to the line.
		c.addToLine(p1);
		assertEquals(departNext, c.departTimeNext());

		c.processNext();
		departNext = p1.getArrivalTime() + p1.getProcessTime()
				+ p1.getWaitTime();
		assertEquals(departNext, c.departTimeNext());

	}

	/**
	 * Test method for getTimeWhenAvailable which returns the time the line will
	 * be finished with all its passengers.
	 */
	@Test
	public void testGetTimeWhenAvailable() {
		// Adds passenger to the line.
		c.addToLine(p);
		// Checks timeWhenAvailable for the line.
		assertEquals(210, c.getTimeWhenAvailable());
	}

	/**
	 * Test method for addToLine() which adds the passenger to the line to be
	 * processed.
	 */
	@Test
	public void testAddToLine() {
		// Passenger is not waiting in line.
		assertFalse(p.isWaitingInCustomsLine());
		// Adds passenger to the line.
		c.addToLine(p);
		// Passenger is now waiting in line.
		assertTrue(p.isWaitingInCustomsLine());
		// There is one passenger in line so size is 1.
		assertEquals(1, c.size());
		assertTrue(c.hasNext());
	}

}
