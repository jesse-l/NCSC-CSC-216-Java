package edu.ncsu.csc216.airport_customs.arriving_passengers;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * JUnit test case for the Resident Passenger class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class ResidentTest {

	/** Sets up a passenger for testing. */
	private Passenger p;
	/** Sets up a passenger for testing. */
	private Passenger p1;
	/** Sets up the color for the before the halfway point in process time. */
	private Color lightBlue = new Color(153, 153, 255);
	/** Sets up the color for after the halfway point in process time. */
	private Color blue = new Color(0, 0, 255);
	/** Sets up an array of 3 customs desks. */
	private CustomsDesk[] line = new CustomsDesk[3];
	/** Sets up an array of 6 customs desks. */
	private CustomsDesk[] lines = new CustomsDesk[6];
	/** Sets up a log to be passed to the customs desks. */
	private Log myLog = new Log();

	/**
	 * Set up variables for use in this test case.
	 * 
	 * @throws java.lang.Exception
	 *             Throws exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		p = new Resident(10, 250);
		p1 = new Resident(5, 130);

		// Sets up one line of three desks for testing.
		line[0] = new CustomsDesk(myLog);
		line[1] = new CustomsDesk(myLog);
		line[2] = new CustomsDesk(myLog);

		// Sets up my second lines to have four desks in it for testing.
		lines[0] = new CustomsDesk(myLog);
		lines[1] = new CustomsDesk(myLog);
		lines[2] = new CustomsDesk(myLog);
		lines[3] = new CustomsDesk(myLog);
		lines[4] = new CustomsDesk(myLog);
		lines[5] = new CustomsDesk(myLog);
	}

	/**
	 * Test method for getInLine() which makes the passenger get in a line to
	 * wait.
	 */
	@Test
	public void testGetInLine() {
		assertFalse(p.isWaitingInCustomsLine());
		// Passenger gets in line.
		p.getInLine(line);
		assertTrue(p.isWaitingInCustomsLine());
		// Resident should hop in desk 1 first.
		assertEquals(1, p.getLineIndex());

		// Passenger gets in line.
		p1.getInLine(lines);
		// Resident should hop in desk 3.
		assertEquals(3, p1.getLineIndex());

		// Passenger gets in line.
		p1.getInLine(lines);
		// Resident should hop in desk 0.
		assertEquals(0, p1.getLineChoice());

		// Passenger gets in line.
		p.getInLine(lines);
		// Resident should hop in desk 1.
		assertEquals(1, p.getLineIndex());

		// Passenger gets in line.
		p.getInLine(lines);
		// Resident should hop in desk 2.
		assertEquals(2, p.getLineIndex());

		// Passenger gets in line.
		p.getInLine(lines);
		// Resident should hop in desk 0.
		assertEquals(0, p.getLineIndex());

	}

	/**
	 * Test method for getColor() which displays the color of the passenger.
	 */
	@Test
	public void testGetColor() {
		assertEquals(blue, p.getColor());
		assertNotEquals(lightBlue, p.getColor());
		assertEquals(lightBlue, p1.getColor());
	}

	/**
	 * Test method for getArrivalTime() which returns the arrival time of the
	 * passenger.
	 */
	@Test
	public void testGetArrivalTime() {
		// Arrival time for p is 10.
		assertEquals(10, p.getArrivalTime());

		// Arrival time for p1 is 5.
		assertEquals(5, p1.getArrivalTime());
	}

	/**
	 * Test method for getWaitTime() which returns the wait time of the
	 * passenger.
	 */
	@Test
	public void testGetWaitTime() {
		// Passenger isn't in line so wait time is 0.
		assertEquals(0, p.getWaitTime());

		// Sets wait time to 20.
		p1.setWaitTime(20);
		assertEquals(20, p1.getWaitTime());
	}

	/**
	 * Test method for setWaitTime(int) which sets the wait time for the
	 * passenger.
	 */
	@Test
	public void testSetWaitTime() {
		// Sets wait time to 20.
		p.setWaitTime(20);
		assertEquals(20, p.getWaitTime());

		// Sets wait time to 456.
		p1.setWaitTime(456);
		assertEquals(456, p1.getWaitTime());
	}

	/**
	 * Test method for getProcessTime() which returns the amount of time it will
	 * take the passenger to be processed.
	 */
	@Test
	public void testGetProcessTime() {
		// Process time for p is 250.
		assertEquals(250, p.getProcessTime());

		// Process time for p2 is 130.
		assertEquals(130, p1.getProcessTime());
	}

	/**
	 * Test method for getLineIndex() which returns the position in line the
	 * passenger is in.
	 */
	@Test
	public void testGetLineIndex() {
		// Passenger isn't in line go line index is set to -1;
		assertEquals(-1, p.getLineIndex());
		// Sets the line index to 3.
		p.setLineIndex(3);
		assertEquals(3, p.getLineIndex());

		// Passenger isn't in line go line index is set to -1;
		assertEquals(-1, p1.getLineIndex());
	}

	/**
	 * Test method for isWaitingInCustomsLine() which returns true if the
	 * passenger is waiting in line.
	 */
	@Test
	public void testIsWaitingInCustomsLine() {
		// Passenger isn't waiting in line.
		assertFalse(p.isWaitingInCustomsLine());

		// Makes a passenger get in a line.
		p1.getInLine(line);
		assertTrue(p1.isWaitingInCustomsLine());
	}

	/**
	 * Test method for isWaitingInCustomsLine() which returns true if the
	 * passenger is waiting in line.
	 */
	@Test
	public void testSetWaitingProcessing() {
		// Sets wait time to 350.
		p.setWaitTime(350);
		assertEquals(350, p.getWaitTime());

		// Sets wait time to 50
		p1.setWaitTime(50);
		assertEquals(50, p1.getWaitTime());
	}

	/**
	 * Test method for removeFromWaitinLine() which removes the passenger from
	 * waiting in line.
	 */
	@Test
	public void testRemoveFromWaitinLine() {
		assertFalse(p.isWaitingInCustomsLine());
		// Makes passenger get in line.
		p.getInLine(lines);
		// Passenger is now waiting.
		assertTrue(p.isWaitingInCustomsLine());
		// Removes the passenger from the line.
		p.removeFromWaitinLine();
		assertFalse(p.isWaitingInCustomsLine());
	}

	/**
	 * Test method for setLineIndex(int) which sets the position in line the
	 * passenger is at.
	 */
	@Test
	public void testSetLineIndex() {
		assertEquals(-1, p.getLineIndex());
		// Sets line index to 2
		p.setLineIndex(2);
		assertEquals(2, p.getLineIndex());

		assertEquals(-1, p1.getLineIndex());
		// Sets line index to 5.
		p1.setLineIndex(5);
		assertEquals(5, p1.getLineIndex());
	}

}
