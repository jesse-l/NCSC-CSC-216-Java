package edu.ncsu.csc216.airport_customs.queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.queues.ArrivalHall;
import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * JUnit test case for the ArrivalHall class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class ArrivalHallTest {

	/** Sets up an ArrivalHall for testing. */
	private ArrivalHall a;
	/** Sets up an ArrivalHall for testing. */
	private ArrivalHall a1;
	/** Sets up an ArrivalHall for testing. */
	private ArrivalHall a2;
	/** Sets up a CustomDesk array of 3 desks for testing. */
	private CustomsDesk[] c = new CustomsDesk[3];

	/**
	 * Initialize the variables set up before this method.
	 * 
	 * @throws java.lang.Exception
	 *             Throws exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < 3; i++)
			c[i] = new CustomsDesk(new Log());
		// Arrival hall with 10 passengers.
		a = new ArrivalHall(10, c);

		// Arrival hall with 50 passengers.
		a1 = new ArrivalHall(50, c);

		// Arrival hall with no passengers.
		a2 = new ArrivalHall(0, c);
	}

	/**
	 * Test method for the size() method inside of the ArrivalHall class.
	 */
	@Test
	public void testSize() {
		// Size of ArrivalHall a should be 10.
		assertEquals(10, a.size());
		// Size of ArrivalHall a1 should be 50.
		assertEquals(50, a1.size());
	}

	/**
	 * Test method for hasNext() method which checks to see if the line has a
	 * next passenger waiting.
	 */
	@Test
	public void testHasNext() {
		// Arrival hall should have a next passenger.
		assertTrue(a.hasNext());

		// Arrival hall should have a next passenger.
		assertTrue(a1.hasNext());

		// Arrival hall should not have a next passenger.
		assertFalse(a2.hasNext());
	}

	/**
	 * Test method for processNext() which sends the next passenger in line to
	 * get in a line for a customs desk.
	 */
	@Test
	public void testProcessNext() {
		Passenger p = null;
		assertTrue(a.hasNext());
		// Grabs the passenger that is next.
		p = a.processNext();
		// Checks to see if passenger is waiting.
		assertTrue(p.isWaitingInCustomsLine());
	}

	/**
	 * Test method for departTimeNext() which returns the time the front
	 * passenger will leave the arrival hall.
	 */
	@Test
	public void testDepartTimeNext() {
		// Grabs the arrival time of the first passenger in line.
		int depart = a.queue.front().getArrivalTime();
		assertEquals(depart, a.departTimeNext());

		// Grabs the arrival time of the first passenger in line.
		depart = a1.queue.front().getArrivalTime();
		assertEquals(depart, a1.departTimeNext());
	}

}
