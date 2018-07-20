package edu.ncsu.csc216.androtech.model.devices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is used to test the ComDevice class.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class ComDeviceTest {
	/** Sets aside memory for one Com Device. */
	private ComDevice c1;
	/** Sets aside memory for a second Com Device. */
	private ComDevice c2;

	/**
	 * Sets up the initial variables to be used during testing.
	 * 
	 * @throws java.lang.Exception General Exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		c1 = new ComDevice("123ABC", "Kate", 3);
		c2 = new ComDevice("456DEF", "Bender", 0);
	}

	/**
	 * Test method for the toString method inside the ComDevice class.
	 */
	@Test
	public void testToString() {
		assertEquals("C Platinum  123ABC Kate", c1.toString());
		assertEquals("C None      456DEF Bender", c2.toString());
	}

}
