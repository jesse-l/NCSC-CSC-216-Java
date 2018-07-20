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
 * This class is designed to test the VRDroid class and the methods inside the
 * class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class VRDroidTest {
	private VRDroid v1;

	/**
	 * Sets up the initial variables to be used during testing.
	 * 
	 * @throws java.lang.Exception General Exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		v1 = new VRDroid();
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.VRDroid#VRDroid()}.
	 */
	@Test
	public void testAssign() {
		assertFalse(v1.isAssigned());

		try {
			v1.assign(new VRDevice("123ABC", "Jack", 2));
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		assertTrue(v1.isAssigned());

		try {
			v1.assign(new VRDevice("123ABC", "Jack", 2));
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		} catch (DroidBusyException e) {
			// TechDroid is busy so this should be thrown.
			System.out.println(e);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		try {
			v1.assign(new ComDevice("123ABC", "Jack", 2));
		} catch (DroidDeviceMismatchException e) {
			// TechDroid is a VRDroid and it is trying to assign a ComDevcie,
			// this should be thrown.
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

	}

}
