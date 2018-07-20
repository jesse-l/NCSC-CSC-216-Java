package edu.ncsu.csc216.airport_customs.arriving_passengers;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * JUnit test case for the Visitor Passenger class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class VisitorTest {

	/** Sets up the color for the before the halfway point in process time */
	private Color pink = new Color(255, 153, 153);
	/** Sets up the color for after the halfway point in process time */
	private Color red = new Color(255, 0, 0);
	/** One of my passengers used for testing. */
	private Passenger p;
	/** Another passenger used for testing. */
	private Passenger p1;
	/** The log file to create the customs desks. */
	private Log myLog = new Log();
	/** One customs desk array that will have 3 desks in it. */
	private CustomsDesk[] line = new CustomsDesk[3];
	/** My second customs desk array that will have desks in it. */
	private CustomsDesk[] lines = new CustomsDesk[6];

	/**
	 * Set up variables for use in this test case.
	 * 
	 * @throws java.lang.Exception
	 *             Throws exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		p = new Visitor(10, 400);
		p1 = new Visitor(8, 200);

		// Sets up one line of three desks for testing.
		line[0] = new CustomsDesk(myLog);
		line[1] = new CustomsDesk(myLog);
		line[2] = new CustomsDesk(myLog);

		// Sets up my second lines to have six desks in it for testing.
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
		// Passenger gets in line.
		p.getInLine(lines);
		// Visitor should get in line 4 first.
		assertEquals(4, p.getLineIndex());
		assertTrue(p.isWaitingInCustomsLine());

		// Passenger gets in line.
		p1.getInLine(lines);
		// Second visitor should get in line 5.
		assertEquals(5, p1.getLineIndex());
		assertTrue(p1.isWaitingInCustomsLine());

		// Creates an extra passenger.
		Passenger p2 = new Visitor(2, 250);
		// Passenger gets in line.
		p2.getInLine(lines);
		// Third passenger should get in line 4.
		assertEquals(4, p2.getLineIndex());
		assertTrue(p2.isWaitingInCustomsLine());

		// Creates an extra passenger.
		Passenger p3 = new Visitor(4, 300);
		// Passenger gets in line.
		p3.getInLine(line);
		// First visitor should get in line 2.
		assertEquals(2, p3.getLineIndex());
		assertTrue(p3.isWaitingInCustomsLine());

	}

	/**
	 * Test method for getColor() which displays the color of the passenger.
	 */
	@Test
	public void testGetColor() {
		assertEquals(red, p.getColor());
		assertEquals(pink, p1.getColor());
	}

	/**
	 * Test method for getArrivalTime() which returns the arrival time of the
	 * passenger.
	 */
	@Test
	public void testGetArrivalTime() {
		// Arrival time of p is 10.
		assertEquals(10, p.getArrivalTime());

		// Arrival time of p1 is 8.
		assertEquals(8, p1.getArrivalTime());
		assertNotEquals(10, p1.getArrivalTime());
	}

	/**
	 * Test method for getWaitTime() which returns the wait time of the
	 * passenger.
	 */
	@Test
	public void testGetWaitTime() {
		// Sets wait time to 130.
		p.setWaitTime(130);
		assertEquals(130, p.getWaitTime());

		// Sets wait time to 250.
		p1.setWaitTime(250);
		assertEquals(250, p1.getWaitTime());
	}

	/**
	 * Test method for setWaitTime(int) which sets the wait time for the
	 * passenger.
	 */
	@Test
	public void testSetWaitTime() {
		// Sets wait time to 10.
		p.setWaitTime(10);
		assertEquals(10, p.getWaitTime());

		// Sets wait time to 60.
		p1.setWaitTime(60);
		assertEquals(60, p1.getWaitTime());
	}

	/**
	 * Test method for getProcessTime() which returns the amount of time it will
	 * take the passenger to be processed.
	 */
	@Test
	public void testGetProcessTime() {
		// Process time for p is 400.
		assertEquals(400, p.getProcessTime());

		// Process time for p1 is 200.
		assertEquals(200, p1.getProcessTime());
	}

	/**
	 * Test method for getLineIndex() which returns the position in line the
	 * passenger is in.
	 */
	@Test
	public void testGetLineIndex() {
		// Sets line index to 2.
		p.setLineIndex(2);
		assertEquals(2, p.getLineIndex());

		// Sets line index to 15.
		p1.setLineIndex(15);
		assertEquals(15, p1.getLineIndex());
	}

	/**
	 * Test method for isWaitingInCustomsLine() which returns true if the
	 * passenger is waiting in line.
	 */
	@Test
	public void testIsWaitingInCustomsLine() {
		assertFalse(p.isWaitingInCustomsLine());
		// Passenger gets in line.
		p.getInLine(line);
		assertTrue(p.isWaitingInCustomsLine());

		assertFalse(p1.isWaitingInCustomsLine());
		// Passenger gets in line.
		p1.getInLine(line);
		assertTrue(p1.isWaitingInCustomsLine());
	}

	/**
	 * Test method for removeFromWaitinLine() which removes the passenger from
	 * waiting in line.
	 */
	@Test
	public void testRemoveFromWaitinLine() {
		// Passenger gets in line.
		p.getInLine(line);
		assertTrue(p.isWaitingInCustomsLine());
		// Removes passenger from the line.
		p.removeFromWaitinLine();
		assertFalse(p.isWaitingInCustomsLine());
	}

	/**
	 * Test method for setLineIndex(int) which sets the position in line the
	 * passenger is at.
	 */
	@Test
	public void testSetLineIndex() {
		// Sets line index to 5.
		p.setLineIndex(5);
		assertEquals(5, p.getLineIndex());
	}

}
