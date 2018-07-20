package edu.ncsu.csc216.androtech.model.devices;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.util.SimpleIterator;

/**
 * The DeviceListTest class is a class used to test the DeviceList class and the
 * methods inside the class.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class DeviceListTest {
	private DeviceList list;
	private DeviceList list2;
	private DeviceList listScan;
	private VRDevice v1;
	private ComDevice c1;

	/**
	 * Sets up the initial variables used for testing this program.
	 * 
	 * @throws java.lang.Exception General Exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		list = new DeviceList();
		list2 = new DeviceList();

		File f = new File("deviceList.txt");
		Scanner fileScanner = new Scanner(f);

		listScan = new DeviceList(fileScanner);

		v1 = new VRDevice("123ABC", "Jack", 0);
		c1 = new ComDevice("456DEF", "Jill", 3);

	}

	/**
	 * Test method for testing the iterator created to traverse the linkedlist
	 * created in the DeviceList class.
	 */
	@Test
	public void testIterator() {
		list.add(c1);
		list.add(v1);
		SimpleIterator<Device> s1 = list.iterator();
		assertTrue(s1.hasNext());
		s1.next();
		assertTrue(s1.hasNext());
		s1.next();
		assertFalse(s1.hasNext());

		assertFalse(list2.iterator().hasNext());
	}

	/**
	 * Test method for removing a Device from the list of Devices waiting
	 * service. This method also calls a private method to help deal with a name
	 * filter.
	 */
	@Test
	public void testRemove() {
		list.add(c1);
		list.add(v1);

		assertEquals("C Platinum  456DEF Jill\nV None      123ABC Jack\n",
				list.filteredList(""));

		list.remove("", 0);
		assertEquals("V None      123ABC Jack\n", list.filteredList(""));

		list.remove("Ja", 0);
		assertEquals("", list.filteredList(null));

		listScan.remove("j", 2);
		assertEquals(
				"V Platinum  123ABC Jack Shit\nC Platinum  567EFG Pruitt, Keith\nC Gold      234BCD Jill Hill\nV Silver    456DEF Liddle, Jesse\n",
				listScan.filteredList(""));

		listScan.remove("", 2);
		assertEquals(
				"V Platinum  123ABC Jack Shit\nC Platinum  567EFG Pruitt, Keith\nV Silver    456DEF Liddle, Jesse\n",
				listScan.filteredList(""));

	}

	/**
	 * Test method for adding a new Device to the list of Devices waiting
	 * service.
	 */
	@Test
	public void testAdd() {
		list.add(c1);
		list.add(v1);

		assertEquals("C Platinum  456DEF Jill\nV None      123ABC Jack\n",
				list.filteredList(""));

		list2.add(v1);
		assertEquals("V None      123ABC Jack\n", list2.filteredList(""));
	}

	/**
	 * Test method for the filtered list of Device using the prefix of the name.
	 */
	@Test
	public void testFilteredList() {
		list.add(c1);
		list.add(v1);

		assertEquals("C Platinum  456DEF Jill\nV None      123ABC Jack\n",
				list.filteredList(""));

		assertEquals("C Platinum  456DEF Jill\n", list.filteredList("Ji"));

		assertEquals("V None      123ABC Jack\n", list.filteredList("Ja"));

		try {
			list.add(new ComDevice("123", "Jake", 2));
		} catch (BadDeviceInformationException e) {
			System.out.println(e);
		}

		assertEquals("C Gold      123 Jake\nV None      123ABC Jack\n",
				list.filteredList("Ja"));

		assertEquals(
				"V Platinum  123ABC Jack Shit\nC None      345CDE James\n",
				listScan.filteredList("JA"));

	}

}
