/**
 * 
 */
package edu.ncsu.csc216.androtech.model.management;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.devices.BadDeviceInformationException;
import edu.ncsu.csc216.androtech.model.devices.ComDevice;
import edu.ncsu.csc216.androtech.model.devices.VRDevice;

/**
 * This class is designed to test the ServiceManager class and the methods
 * inside the class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class ServiceManagerTest {
	/** Sets aside memory for a ServiceManager. */
	private ServiceManager s1;
	private ServiceManager s2;

	/**
	 * Sets up the initial variables to be used during testing.
	 * 
	 * @throws java.lang.Exception
	 *             General Exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		s1 = new ServiceManager();

		File f = new File("deviceList.txt");
		Scanner fileScanner = new Scanner(f);

		s2 = new ServiceManager(fileScanner);
	}


	/**
	 * Test method for assigning the Devices to the TechDroids that are
	 * available to fix the Devices. .
	 */
	@Test
	public void testAssignDroids() {
		try {
			VRDevice v1 = new VRDevice("Serial", "Name", 0);
			ComDevice c1 = new ComDevice("123", "Bob", 2);
			VRDevice v2 = new VRDevice("test", "test", 3);
			VRDevice v3 = new VRDevice("test2", "test2", 3);
			VRDevice v4 = new VRDevice("Test3", "test3", 3);
			s1.putOnWaitingList(v1);
			s1.putOnWaitingList(v2);
			s1.putOnWaitingList(v3);
			s1.putOnWaitingList(v4);
			s1.putOnWaitingList(c1);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		s1.assignDroids();

		assertEquals("V None      Serial Name\n", s1.printWaitList(null));

		assertEquals(
				"05V: test test\n03V: test2 test2\n02E: Test3 test3\n01C: UNASSIGNED\n04C: 123 Bob\n",
				s1.printDroids());

		try {
			ComDevice c = new ComDevice("234", "Dan", 2);
			s1.putOnWaitingList(c);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		s1.assignDroids();

		assertEquals(
				"05V: test test\n03V: test2 test2\n02E: Test3 test3\n01C: 234 Dan\n04C: 123 Bob\n",
				s1.printDroids());

		s2.assignDroids();
		assertEquals(
				"05V: 123ABC Jack Shit\n03V: 456DEF Liddle, Jesse\n02E: 345CDE James\n01C: 234BCD Jill Hill\n04C: 567EFG Pruitt, Keith\n",
				s2.printDroids());

	}

	/**
	 * Test method for removing the Device from the list of Devices waiting
	 * service.
	 */
	@Test
	public void testRemove() {
		try {
			VRDevice v1 = new VRDevice("Serial", "Name", 0);
			s1.putOnWaitingList(v1);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		s1.remove(null, 0);

		assertEquals("", s1.printWaitList(null));
		
		try {
			VRDevice v1 = new VRDevice("Serial", "Name", 0);
			s1.putOnWaitingList(v1);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}
		
		s1.remove("n", 0);
		assertEquals("", s1.printWaitList(null));
	}

	/**
	 * Test method for release the Devices after the TechDroid has finished
	 * fixing it.
	 */
	@Test
	public void testReleaseFromService() {
		try {
			VRDevice v1 = new VRDevice("Serial", "Name", 0);
			s1.putOnWaitingList(v1);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		s1.assignDroids();

		assertNotNull(s1.releaseFromService(0));

		assertEquals(
				"05V: UNASSIGNED\n03V: UNASSIGNED\n02E: UNASSIGNED\n01C: UNASSIGNED\n04C: UNASSIGNED\n",
				s1.printDroids());

		assertNull(s1.releaseFromService(3));

		assertNull(s1.releaseFromService(23));

	}

	/**
	 * Test method for adding a new TechDroid to the list of TechDroids.
	 */
	@Test
	public void testAddNewDroid() {
		assertEquals(
				"05V: UNASSIGNED\n03V: UNASSIGNED\n02E: UNASSIGNED\n01C: UNASSIGNED\n04C: UNASSIGNED\n",
				s1.printDroids());

		s1.addNewDroid();

		assertEquals(
				"05V: UNASSIGNED\n03V: UNASSIGNED\n02E: UNASSIGNED\n01C: UNASSIGNED\n04C: UNASSIGNED\n06C: UNASSIGNED\n",
				s1.printDroids());
	}

	/**
	 * Test method for printing the list of Devices waiting to be fixed.
	 */
	@Test
	public void testPrintWaitList() {
		assertEquals("", s1.printWaitList(""));
	}

	/**
	 * Test method for printing the list of TechDroids.
	 */
	@Test
	public void testPrintDroids() {
		assertEquals(
				"05V: UNASSIGNED\n03V: UNASSIGNED\n02E: UNASSIGNED\n01C: UNASSIGNED\n04C: UNASSIGNED\n",
				s1.printDroids());
	}

	/**
	 * Test method for creating a Device and adding it to the waiting list to be
	 * fixed.
	 */
	@Test
	public void testPutOnWaitingListStringStringStringInt() {
		s1.putOnWaitingList("C", "123", "James", 2);

		assertEquals("C Gold      123 James\n", s1.printWaitList(null));
		
		s1.putOnWaitingList("V", "234", "Jill", 1);
		
		assertEquals("C Gold      123 James\nV Silver    234 Jill\n", s1.printWaitList(""));
		
		s1.putOnWaitingList("C", "  " , "Bill", 2);
		
	}

	/**
	 * Test method for taking a Device that has been created and adding it to
	 * the waiting list.
	 */
	@Test
	public void testPutOnWaitingListPrioritizable() {
		try {
			VRDevice v1 = new VRDevice("Serial", "Name", 0);
			s1.putOnWaitingList(v1);
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		assertEquals("V None      Serial Name\n", s1.printWaitList(null));

	}

}
