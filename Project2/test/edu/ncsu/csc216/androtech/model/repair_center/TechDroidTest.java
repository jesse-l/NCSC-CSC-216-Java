package edu.ncsu.csc216.androtech.model.repair_center;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.devices.BadDeviceInformationException;
import edu.ncsu.csc216.androtech.model.devices.ComDevice;
import edu.ncsu.csc216.androtech.model.devices.VRDevice;

/**
 * This class is designed to test the TechDroid class and the methods inside the
 * class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class TechDroidTest {
	private VRDroid v1;
	private ComDroid c1;

	/**
	 * Sets up the initial variables to be used during testing.
	 * 
	 * @throws java.lang.Exception General Exceptions.
	 */
	@SuppressWarnings("static-access")
	@Before
	public void setUp() throws Exception {
		v1.startDroidNumberingAt01();
		v1 = new VRDroid();
		c1 = new ComDroid();
	}

	/**
	 * Test method used to test the exceptions thrown by the TechDroid Class.
	 */
	@Test
	public void testExceptions() {
		boolean isError = false;
		try {
			throw new DroidDeviceMismatchException();
		} catch (DroidDeviceMismatchException e) {
			isError = true;
		}

		assertTrue(isError);

		isError = false;
		try {
			throw new DroidDeviceMismatchException(
					"Device type and TechDroid type do not match.");
		} catch (DroidDeviceMismatchException e) {
			isError = true;
		}

		assertTrue(isError);

		isError = false;

		try {
			throw new DroidBusyException();
		} catch (DroidBusyException e) {
			isError = true;
		}

		assertTrue(isError);

		isError = false;

		try {
			throw new DroidBusyException("Current TechDroid is busy.");
		} catch (DroidBusyException e) {
			isError = true;
		}

		assertTrue(isError);

		isError = false;
		try {
			try {
				v1.assign(new ComDevice("Test", "Test", 0));
			} catch (DroidBusyException e) {
				System.out.println(e);
			} catch (BadDeviceInformationException e) {
				System.out.println(e);
			}
		} catch (DroidDeviceMismatchException e) {
			isError = true;
		}

		assertTrue(isError);

		isError = false;
		try {
			try {
				v1.assign(new VRDevice("Test", "Test", 2));
			} catch (DroidDeviceMismatchException e) {
				System.out.println(e);
			} catch (BadDeviceInformationException e) {
				System.out.println(e);
			}

			assertTrue(v1.isAssigned());

			try {
				v1.assign(new VRDevice("test2", "test2", 1));
			} catch (DroidDeviceMismatchException e) {
				System.out.println(e);
			} catch (BadDeviceInformationException e) {
				System.out.println(e);
			}
		} catch (DroidBusyException e) {
			isError = true;
		}

		assertTrue(isError);
	}

	/**
	 * Test method for getting the TechDroid ID.
	 */
	@Test
	public void testGetDroidID() {
		assertEquals("01V", v1.getDroidID());
		assertEquals("02C", c1.getDroidID());
	}

	/**
	 * Test method for checking to see is a TecDroid is assigned or not.
	 */
	@Test
	public void testIsAssigned() {
		assertFalse(v1.isAssigned());
		assertFalse(c1.isAssigned());

		try {
			v1.assign(new VRDevice("123ABC", "Bob", 2));
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		assertTrue(v1.isAssigned());

	}

	/**
	 * Test method for releasing the Device from the current TechDroid.
	 */
	@Test
	public void testRelease() {
		assertFalse(v1.isAssigned());

		try {
			v1.assign(new VRDevice("123ABC", "Bob", 2));
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		assertTrue(v1.isAssigned());

		v1.release();

		assertFalse(v1.isAssigned());

	}

	/**
	 * Test method for assigning a Device to the current TechDroid.
	 */
	@Test
	public void testAssign() {
		try {
			v1.assign(new ComDevice("123ABC", "Jill", 2));
		} catch (DroidDeviceMismatchException e) {
			// Should catch this exception.
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		assertFalse(v1.isAssigned());

		try {
			c1.assign(new VRDevice("123ABC", "Jill", 2));
		} catch (DroidDeviceMismatchException e) {
			// Should catch this exception.
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		assertFalse(c1.isAssigned());

		try {
			c1.assign(new ComDevice("123ABC", "Bob", 1));
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		assertTrue(c1.isAssigned());

	}

	/**
	 * Test method for displaying the information about the TechDroid as a
	 * String.
	 */
	@Test
	public void testToString() {
		assertEquals("01V: UNASSIGNED", v1.toString());
		assertEquals("02C: UNASSIGNED", c1.toString());
	}

}
