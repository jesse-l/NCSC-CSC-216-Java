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
 * This class is designed to test the RepairCenter class and the methods inside
 * the class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class RepairCenterTest {

	private RepairCenter r1;

	/**
	 * Sets up the initial variables to be used during testing.
	 * 
	 * @throws java.lang.Exception General Exceptions.
	 */
	@SuppressWarnings("static-access")
	@Before
	public void setUp() throws Exception {
		r1 = new RepairCenter();
		TechDroid t = new ComDroid();
		t.startDroidNumberingAt01();
	}

	/**
	 * Test method for adding a new TechDroid to the existing list of
	 * TechDroids.
	 */
	@Test
	public void testAddTechDroid() {
		for (int i = 0; i < 25; i++) {
			r1.addTechDroid();
		}

		assertEquals(30, r1.numberOfAvailableDroids());

		r1.addTechDroid();
		r1.addTechDroid();

		assertEquals(30, r1.numberOfAvailableDroids());

	}

	/**
	 * Test method for the getter method of number of available TechDroids.
	 */
	@Test
	public void testNumberOfAviableDroids() {
		assertEquals(5, r1.numberOfAvailableDroids());

		r1.addTechDroid();
		r1.addTechDroid();
		r1.addTechDroid();

		assertEquals(8, r1.numberOfAvailableDroids());
	}

	/**
	 * Test method for getting the TechDroid at a specific index.
	 */
	@Test
	public void testGetDroidAt() {
		assertEquals("05V", r1.getDroidAt(0).getDroidID());
		assertEquals("03V", r1.getDroidAt(1).getDroidID());
		assertEquals("02E", r1.getDroidAt(2).getDroidID());
	}

	/**
	 * Test method for releasing the Device from a TechDroid.
	 */
	@Test
	public void testRelease() {
		try {
			r1.getDroidAt(0).assign(new VRDevice("123ABC", "Jack", 3));
		} catch (DroidBusyException e) {
			System.out.println(e);
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		try {
			r1.getDroidAt(0).assign(new VRDevice("123ABC", "Jack", 3));
		} catch (DroidBusyException e) {
			// Should throw this since the TechDroid has already been assigned.
			System.out.println(e);
		} catch (DroidDeviceMismatchException e) {
			System.out.println(e);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		try {
			r1.getDroidAt(0).assign(new ComDevice("123ABC", "Jack", 3));
		} catch (DroidBusyException e) {
			System.out.println(e);
		} catch (DroidDeviceMismatchException e) {
			// Should throw this since the TechDroid is a VrDroid and the Device
			// is a ComDevice.
			System.out.println(e);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		assertTrue(r1.getDroidAt(0).isAssigned());

		r1.getDroidAt(0).release();

		assertFalse(r1.getDroidAt(0).isAssigned());

	}

	/**
	 * Test method for printing the TechDroids out.
	 */
	@Test
	public void testPrintDroids() {
		assertEquals(
				"05V: UNASSIGNED\n03V: UNASSIGNED\n02E: UNASSIGNED\n01C: UNASSIGNED\n04C: UNASSIGNED\n",
				r1.printDroids());
	}

}
