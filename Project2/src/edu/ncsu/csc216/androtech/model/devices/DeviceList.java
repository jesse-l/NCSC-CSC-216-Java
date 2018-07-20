package edu.ncsu.csc216.androtech.model.devices;

import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.androtech.model.util.SimpleIterator;

/**
 * The DeviceList class is used to create a list of devices waiting to be
 * repaired. This list of devices will be sorted by the service plan that the
 * device has: Platinum, Gold, Silver, and none is the last tier.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class DeviceList {
	/** The size of the list of devices. */
	private int size;
	/** This is the head node for the linked list. */
	private Node head;

	/**
	 * DeviceList that uses a scanner to create existing devices to be added
	 * back into the list.
	 * 
	 * @param input
	 *            the input file that stored the device information.
	 */
	public DeviceList(Scanner input) {
		head = null;
		size = 0;
		// input.useDelimiter("\\n| ");

		String name = new String();
		String serialNum = new String();
		int tier = 0;
		String line = new String();
		Scanner lineScanner = null;
		String type = new String();

		// While the scanner has more lines it will keep creating new devices
		while (input.hasNextLine()) {

			line = input.nextLine();
			if (line == null) {
				break;
			}
			lineScanner = new Scanner(line);
			try {
				type = lineScanner.next();

				if (type.equalsIgnoreCase("C")) {
					if (lineScanner.hasNextInt()) {
						tier = lineScanner.nextInt();
					} else {
						lineScanner.next();
						if (lineScanner.hasNextInt())
							tier = lineScanner.nextInt();

					}

					if (lineScanner.hasNext()) {
						serialNum = lineScanner.next();
					}

					if (lineScanner.hasNext()) {
						name = lineScanner.next();

						int nameLength = name.length();
						if (name.substring(nameLength - 1, nameLength).equals(
								",")) {
							name += " " + lineScanner.next();
						}

						if (lineScanner.hasNext()) {
							name += " " + lineScanner.next();
						}

						try {
							add(new ComDevice(serialNum.trim(), name.trim(),
									tier));
						} catch (BadDeviceInformationException e) {
							System.out.println(e);
						}

					}

				} else if (type.equalsIgnoreCase("V")) {
					if (lineScanner.hasNextInt()) {
						tier = lineScanner.nextInt();
					} else {
						lineScanner.next();
						if (lineScanner.hasNextInt())
							tier = lineScanner.nextInt();

					}

					if (lineScanner.hasNext()) {
						serialNum = lineScanner.next();
					}

					if (lineScanner.hasNext()) {
						name = lineScanner.next();

						int nameLength = name.length();
						if (name.substring(nameLength - 1, nameLength).equals(
								",")) {
							name += " " + lineScanner.next();
						}

						if (lineScanner.hasNext()) {
							name += " " + lineScanner.next();
						}
					}

					try {
						add(new VRDevice(serialNum.trim(), name.trim(), tier));
					} catch (BadDeviceInformationException e) {
						System.out.println(e);
					}
				}
			} catch (NoSuchElementException e) {
				// Just in case this exception is thrown.
			}

		}
		lineScanner.close();
	}

	/**
	 * DeviceList constructor that will create a new blank list of devices.
	 */
	public DeviceList() {
		head = null;
		size = 0;
	}

	/**
	 * Returns a SimpleIterator that points to the device in the front of the
	 * list.
	 * 
	 * @return Returns a simpleIterator pointing to the device at the front of
	 *         the list.
	 */
	public SimpleIterator<Device> iterator() {
		return new Cursor();
	}

	/**
	 * Removes the device that appears in the filtered list in the given
	 * position.
	 * 
	 * @param name
	 *            The name of the filter for the device list.
	 * @param position
	 *            The position of the device being removed.
	 * @return Returns the device that was removed from the list.
	 */
	public Device remove(String name, int position) {
		Node current = head;
		Node previous = null;

		if (size <= 0 && position > size) {
			return null;
		} else if (name == null || name.equals("")) {

			while (current != null && position > 0) {
				previous = current;
				current = current.link;
				position--;
			}

			if (current != null) {
				if (current == head) {
					head = head.link;
				} else {
					if (current.link != null)
						previous.link = current.link;
					else
						previous.link = null;
				}
			} else {
				return null;
			}
			size--;
			return current.device;

		} else {
			return findDevice(name, position);
		}
	}

	/**
	 * This method assists the remove method. This method is used to search for
	 * a Device in the list and remove the device with a certain filter at a
	 * specific position.
	 * 
	 * @param name
	 *            The name filter for the device.
	 * @param position
	 *            The position of the device after the filter is applied.
	 * @return Returns the device that was removed from the list.
	 */
	private Device findDevice(String name, int position) {
		Node current = head;
		Node previous = null;
		name = name.trim();
		int length = name.length();

		if (position >= size) {
			return null;
		} else {
			while (current != null) {
				if (position == 0
						&& current.device.getName().substring(0, length)
								.equalsIgnoreCase(name)) {
					if (current == head) {
						head = current.link;
					} else if (current.link == null) {
						previous.link = null;
					} else {
						if (current != null && previous != null)
							previous.link = current.link;
					}
					break;
				}

				if (current.device.getName().substring(0, length)
						.equalsIgnoreCase(name)) {
					position--;
				}

				previous = current;
				current = current.link;

			}
			if (current == null || current.device == null)
				return null;
			else {
				size--;
				return current.device;
			}
		}
	}

	/**
	 * Adds a device to the list of devices.
	 * 
	 * @param newDevice
	 *            The new device to be added to the list of devices.
	 */
	public void add(Device newDevice) {
		if (size == 0) {
			Node newHead = new Node(newDevice, head);
			head = newHead;
		} else if (newDevice.getTier() == 0) {
			Node current = head;
			Node previous = null;
			for (Node p = head; p != null; p = p.link) {
				previous = current;
				current = current.link;
			}

			if (current == head) {
				head = new Node(newDevice, current);
			} else {
				previous.link = new Node(newDevice, current);
			}

		} else {
			Node current = head;
			Node previous = null;

			while (current != null
					&& (current.device.compareToTier(newDevice) >= 0)) {
				previous = current;
				current = current.link;
			}

			if (current == head) {
				head = new Node(newDevice, current);
			} else {
				previous.link = new Node(newDevice, current);
			}
		}

		// Increases the size of the LinkedList.
		size++;
	}

	/**
	 * Returns a filtered list of all the Devices that match a certain String,
	 * which can also be null.
	 * 
	 * @param compare
	 *            The String used to compare the owner's name to.
	 * @return A list of all the Devices that match that specific search.
	 */
	public String filteredList(String compare) {

		StringBuffer list = new StringBuffer();
		boolean match;

		for (Node p = head; p != null; p = p.link) {
			match = p.device.meetsFilter(compare.trim());

			// If they match add the device to the list.
			if (match) {

				list.append(p.device.toString().trim());
				list.append("\n");
			}
		}

		return list.toString();
	}

	// ////////////////////////////////////////////////////////////

	/**
	 * A private inner class inside of the DeviceList class that is used in
	 * creating the linkedList of devices.
	 * 
	 * @author Jesse Liddle - jaliddl2
	 *
	 */
	private class Node {
		Device device;
		Node link;

		/**
		 * Constructor for a node object.
		 * 
		 * @param device
		 *            The device being added to the node.
		 * @param link
		 *            The node behind of the current one.
		 */
		public Node(Device device, Node link) {
			this.device = device;
			this.link = link;
		}
	}

	// ////////////////////////////////////////////////////////////

	/**
	 * A private inner class inside of the DeviceList class that is used to
	 * assists the linkedList of devices.
	 * 
	 * @author Jesse Liddle - jaliddl2
	 *
	 */
	private class Cursor implements SimpleIterator<Device> {
		private Node cursor;

		private Cursor() {
			cursor = head;
		}

		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		@Override
		public Device next() {
			if (cursor == null)
				throw new NoSuchElementException();
			Device device = cursor.device;
			cursor = cursor.link;
			return device;
		}

	}
}
