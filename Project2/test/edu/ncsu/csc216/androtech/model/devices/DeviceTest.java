/**
 * 
 */
package edu.ncsu.csc216.androtech.model.devices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is designed to test the methods that exist in the Device class
 * using its children classes.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class DeviceTest {

	Device c1;
	Device v1;

	/**
	 * Sets up the two devices to be used during the testing.
	 * 
	 * @throws java.lang.Exception General Exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		c1 = new ComDevice("123ABC", "James", 0);
		v1 = new VRDevice("123ABS", "Bill", 2);
	}

	/**
	 * This method is used to test the BadDeviceInformationException
	 */
	@SuppressWarnings("unused")
	@Test
	public void testExceptions() {
		boolean isError = false;
		
		try {
			// No owner name for ComDevice
			ComDevice c2 = new ComDevice("123", "  ", 2);

		} catch (BadDeviceInformationException e) {
			isError = true;
		}

		assertTrue(isError);

		isError = false;
		// No Serial Number for ComDevice
		try {
			ComDevice c3 = new ComDevice("  ", "Bill", 1);
		} catch (BadDeviceInformationException e) {
			isError = true;
		}

		assertTrue(isError);

		isError = false;
		// No owner name for VRDevice
		try {
			VRDevice v2 = new VRDevice("123", "  ", 3);
		} catch (BadDeviceInformationException e) {
			isError = true;
		}

		assertTrue(isError);

		isError = false;
		// No Serial Number for VRDevice
		try {
			VRDevice v3 = new VRDevice("  ", "Bob", 1);
		} catch (BadDeviceInformationException e) {
			isError = true;
		}

		assertTrue(isError);

		isError = false;
		try {
			throw new BadDeviceInformationException();
		} catch (BadDeviceInformationException e) {
			isError = true;
		}

		assertTrue(isError);

	}

	/**
	 * Test method for filtering the list based of a prefix of the owner's name.
	 */
	@Test
	public void testMeetsFilter() {
		assertTrue(c1.meetsFilter("Jam"));
		assertFalse(v1.meetsFilter("Bo"));
		assertTrue(c1.meetsFilter(""));
		assertTrue(v1.meetsFilter(null));
	}

	/**
	 * Test method for writing the Device and information to a String.
	 */
	@Test
	public void testToString() {
		assertEquals("V Gold      123ABS Bill", v1.toString());
		assertEquals("C None      123ABC James", c1.toString());
	}

	/**
	 * Test method for getting the name of the owner of the Device.
	 */
	@Test
	public void testGetName() {
		assertEquals("James", c1.getName());
		assertEquals("Bill", v1.getName());
	}

	/**
	 * Test method for getting the serial number of the Device.
	 */
	@Test
	public void testGetSerialNum() {
		assertEquals("123ABS", v1.getSerialNum());
		assertEquals("123ABC", c1.getSerialNum());
	}

	/**
	 * Test method for getting the service tier of the Device.
	 */
	@Test
	public void testGetTier() {
		assertEquals(2, v1.getTier());
		assertEquals(0, c1.getTier());
	}

	/**
	 * Test method for comparing the service tier of two Devices together to see
	 * where in the linkedlist the new one should go.
	 */
	@Test
	public void testCompareToTier() {
		assertEquals(1, v1.compareToTier(c1));
		assertEquals(-1, c1.compareToTier(v1));
	}

}
