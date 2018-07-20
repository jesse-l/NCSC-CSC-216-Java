package edu.ncsu.csc216.androtech.model.devices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is used to test the VRDevice class.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class VRDeviceTest {
	/** Sets aside memory for one VR Device. */
	private VRDevice v1;
	/** Sets aside memory for a second VR Device. */
	private VRDevice v2;

	/**
	 * Sets up the initial variables to be used during testing.
	 * 
	 * @throws java.lang.Exception General Exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		v1 = new VRDevice("123ABC", "James", 0);
		v2 = new VRDevice("456DEF", "Bob", 2);
	}

	/**
	 * Test method for the toString method inside the VRDevice class.
	 */
	@Test
	public void testToString() {
		assertEquals("V None      123ABC James", v1.toString());
		assertEquals("V Gold      456DEF Bob", v2.toString());
	}

}
