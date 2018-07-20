/**
 * 
 */
package edu.ncsu.csc216.androtech.model.repair_center;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.devices.BadDeviceInformationException;
import edu.ncsu.csc216.androtech.model.devices.ComDevice;
import edu.ncsu.csc216.androtech.model.devices.VRDevice;

/**
 * This class is designed to test the ComDroid class and the methods inside the
 * class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class ComDroidTest {
	private ComDroid c1;
	private ComDroid c2;

	/**
	 * Sets up the initial variables to be used during testing.
	 * 
	 * @throws java.lang.Exception General Exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		c1 = new ComDroid();
		c2 = new ComDroid();
	}

	/**
	 * Test method for assign method inside of the ComDroid class.
	 */
	@Test
	public void testAssign() {
		try {
			ComDevice com = new ComDevice("blank", "Blank", 1);
			c1.assign(com);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		}

		assertTrue(c1.isAssigned());
		assertFalse(c2.isAssigned());

		// Test assigning a VRDevice to a ComDroid, which won't work
		VRDevice vr;
		try {
			vr = new VRDevice("blah", "blah", 1);
			c2.assign(vr);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		}

		assertFalse(c2.isAssigned());

		try {
			ComDevice com = new ComDevice("blank", "Blank", 1);
			c1.assign(com);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		}

		assertTrue(c1.isAssigned());
	}

}
