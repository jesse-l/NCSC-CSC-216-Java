package edu.ncsu.csc216.airport_customs.arriving_passengers;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * JUnit test cases for Diplomat Passenger class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class DiplomatTest {
	/** One of my passengers used for testing. */
	private Passenger p;
	/** Another passenger used for testing. */
	private Passenger p1;
	/** The log file to create the customs desks. */
	private Log myLog = new Log();
	/** One customs desk array that will have 3 desks in it. */
	private CustomsDesk[] line = new CustomsDesk[3];
	/** My second customs desk array that will have 4 desks in it. */
	private CustomsDesk[] lines = new CustomsDesk[4];

	/**
	 * Set up variables for use in this test case.
	 * 
	 * @throws java.lang.Exception
	 *             Throws exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		p = new Diplomat(10, 10);
		p1 = new Diplomat(25, 63);

		// Sets up one line of three desks for testing.
		line[0] = new CustomsDesk(myLog);
		line[1] = new CustomsDesk(myLog);
		line[2] = new CustomsDesk(myLog);

		// Sets up my second lines to have four desks in it for testing.
		lines[0] = new CustomsDesk(myLog);
		lines[1] = new CustomsDesk(myLog);
		lines[2] = new CustomsDesk(myLog);
		lines[3] = new CustomsDesk(myLog);

	}

	/**
	 * Test method for getInLine() which makes the passenger get in a line to
	 * wait.
	 */
	@Test
	public void testGetInLine() {
		// When a passenger is created it should not be waiting in line.
		assertFalse(p.isWaitingInCustomsLine());

		// Adds a passenger to a line.
		p.getInLine(line);

		// Should go to desk 1 out of the three.
		assertEquals(1, p.getLineIndex());

		// Makes sure that both diplomats get in the same line with four desks.
		p.getInLine(lines);
		assertEquals(2, p.getLineChoice());
		p.getInLine(lines);
		assertEquals(2, p.getLineChoice());
	}

	/**
	 * Test method for getColor() which displays the color of the passenger.
	 */
	@Test
	public void testGetColor() {
		Color c = new Color(153, 255, 153);
		Color c1 = new Color(0, 255, 0);

		// Checks the color based on process time for each diplomat.
		assertEquals(c, p.getColor());
		assertNotEquals(c1, p.getColor());
		assertEquals(c1, p1.getColor());
	}

	/**
	 * Test method for getArrivalTime() which returns the arrival time of the
	 * passenger.
	 */
	@Test
	public void testGetArrivalTime() {
		// Arrival time of p is 10.
		assertEquals(10, p.getArrivalTime());
		assertNotEquals(15, p.getArrivalTime());

		// Arrival time of p1 is 25.
		assertEquals(25, p1.getArrivalTime());
		assertNotEquals(50, p1.getArrivalTime());
	}

	/**
	 * Test method for getWaitTime() which returns the wait time of the
	 * passenger.
	 */
	@Test
	public void testGetWaitTime() {
		// Wait time is 0 p is not in a line.
		assertEquals(0, p.getWaitTime());
		assertNotEquals(25, p.getWaitTime());

		// Wait time is 0 p1 is not in a line.
		assertEquals(0, p1.getWaitTime());
		assertNotEquals(30, p1.getWaitTime());
	}

	/**
	 * Test method for setWaitTime(int) which sets the wait time for the
	 * passenger.
	 */
	@Test
	public void testSetWaitTime() {
		// Sets wait time to 30.
		p1.setWaitTime(30);
		// Test to make sure wait time was set to 30.
		assertEquals(30, p1.getWaitTime());
		assertNotEquals(40, p1.getWaitTime());

		// Sets wait time to 500.
		p.setWaitTime(500);
		// Checks that wait time is actually 500.
		assertEquals(500, p.getWaitTime());
		assertNotEquals(30, p.getWaitTime());
	}

	/**
	 * Test method for getProcessTime() which returns the amount of time it will
	 * take the passenger to be processed.
	 */
	@Test
	public void testGetProcessTime() {
		// Process time of p is 10.
		assertEquals(10, p.getProcessTime());
		assertNotEquals(25, p.getProcessTime());

		// Process time of p1 is 63.
		assertEquals(63, p1.getProcessTime());
		assertNotEquals(30, p1.getProcessTime());
	}

	/**
	 * Test method for getLineIndex() which returns the position in line the
	 * passenger is in.
	 */
	@Test
	public void testGetLineIndex() {
		// Test starting value of line index.
		assertEquals(-1, p.getLineIndex());
		assertNotEquals(2, p.getLineIndex());
		assertNotEquals(1, p1.getLineIndex());
	}

	/**
	 * Test method for isWaitingInCustomsLine() which returns true if the
	 * passenger is waiting in line.
	 */
	@Test
	public void testIsWaitingInCustomsLine() {
		// Passenger is not waiting.
		assertFalse(p.isWaitingInCustomsLine());

		// Passenger gets in line.
		p.getInLine(line);

		// Passenger is now waiting in line.
		assertTrue(p.isWaitingInCustomsLine());

		// Passengers gets in line.
		p1.getInLine(lines);

		// Passenger is now waiting in line.
		assertTrue(p1.isWaitingInCustomsLine());
	}

	/**
	 * Test method for removeFromWaitinLine() which removes the passenger from
	 * waiting in line.
	 */
	@Test
	public void testRemoveFromWaitinLine() {
		// Passenger is not waiting.
		assertFalse(p.isWaitingInCustomsLine());

		// Passenger gets in a line.
		p1.getInLine(line);

		// Passenger is now waiting.
		assertTrue(p1.isWaitingInCustomsLine());

		// Passenger is removed from waiting.
		p1.removeFromWaitinLine();

		// Passenger is no longer waiting in line.
		assertFalse(p1.isWaitingInCustomsLine());
	}

	/**
	 * Test method for setLineIndex(int) which sets the position in line the
	 * passenger is at.
	 */
	@Test
	public void testSetLineIndex() {
		// Sets line index to 2.
		p.setLineIndex(2);
		// Checks line index is 2.
		assertEquals(2, p.getLineIndex());
		assertNotEquals(5, p.getLineIndex());
	}

}
