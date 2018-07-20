package edu.ncsu.csc216.androtech.model.repair_center;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.devices.BadDeviceInformationException;
import edu.ncsu.csc216.androtech.model.devices.ComDevice;
import edu.ncsu.csc216.androtech.model.devices.VRDevice;

/**
 * This class is designed to test the ExpertDroid class and the methods inside
 * the class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class ExpertDroidTest {
	private ExpertDroid e1;
	private ExpertDroid e2;

	/**
	 * Sets up the initial variables to be used during testing.
	 * 
	 * @throws java.lang.Exception General Exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		e1 = new ExpertDroid();
		e2 = new ExpertDroid();
	}

	/**
	 * Test method for the assign() method.
	 */
	@Test
	public void testAssign() {
		try {
			ComDevice com = new ComDevice("blank", "Blank", 1);
			e1.assign(com);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		}

		assertTrue(e1.isAssigned());
		assertFalse(e2.isAssigned());

		VRDevice vr;
		try {
			vr = new VRDevice("blah", "blah", 1);
			e2.assign(vr);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		}

		assertTrue(e2.isAssigned());

		try {
			ComDevice com = new ComDevice("blank", "Blank", 1);
			e1.assign(com);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		} catch (DroidBusyException e) {
			System.out.println(e);
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		}

	}

}
