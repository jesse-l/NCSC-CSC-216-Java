package edu.ncsu.csc216.androtech.model.management;

import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.androtech.model.devices.BadDeviceInformationException;
import edu.ncsu.csc216.androtech.model.devices.ComDevice;
import edu.ncsu.csc216.androtech.model.devices.Device;
import edu.ncsu.csc216.androtech.model.devices.DeviceList;
import edu.ncsu.csc216.androtech.model.devices.Prioritizable;
import edu.ncsu.csc216.androtech.model.devices.VRDevice;
import edu.ncsu.csc216.androtech.model.repair_center.ComDroid;
import edu.ncsu.csc216.androtech.model.repair_center.DroidBusyException;
import edu.ncsu.csc216.androtech.model.repair_center.DroidDeviceMismatchException;
import edu.ncsu.csc216.androtech.model.repair_center.ExpertDroid;
import edu.ncsu.csc216.androtech.model.repair_center.RepairCenter;
import edu.ncsu.csc216.androtech.model.repair_center.VRDroid;
import edu.ncsu.csc216.androtech.model.util.SimpleIterator;

/**
 * SerivceManager class is used to tie together both the DeviceList and the
 * RepairCenter classes interacting with both of them using the input by the GUI
 * or the console, depending on how the program is run.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class ServiceManager implements Manager {
	/** Creates the DeviceList that is going to be used. */
	private DeviceList devices;
	/** Creates the RepairCenter that is going to be used. */
	private RepairCenter repair;

	/**
	 * Constructor that creates both a DeviceList and the RepairCenter.
	 * 
	 */
	public ServiceManager() {
		devices = new DeviceList();
		repair = new RepairCenter();
	}

	/**
	 * Constructor that creates both a DeviceList and the RepairCenter. That
	 * takes input from a scanner and creates a list of devices that already
	 * existed in a file.
	 * 
	 * @param input
	 *            the input file that stored the device information.
	 */
	public ServiceManager(Scanner input) {
		if (input != null)
			devices = new DeviceList(input);
		else
			devices = new DeviceList();
		repair = new RepairCenter();
	}

	/**
	 * Assigns devices on the waiting list TechDroids that do not have devices
	 * assigned to them.
	 */
	@Override
	public void assignDroids() {
		// Creates an iterator to traverse the list of Devices.
		SimpleIterator<Device> itr = devices.iterator();
		Device device = null;
		int skipCounter = 0;
		boolean isAssigned = false;

		try {
			device = itr.next();
		} catch (NoSuchElementException e) {
			// Just in case the error is thrown.
		}

		while (device != null && repair.numberOfAvailableDroids() != 0) {

			if (device instanceof ComDevice) {
				for (int k = repair.totalNumberOfDroids() - 1; k >= 0; k--) {
					if (repair.getDroidAt(k) instanceof ComDroid
							|| repair.getDroidAt(k) instanceof ExpertDroid) {
						if (!repair.getDroidAt(k).isAssigned()) {

							try {
								repair.getDroidAt(k).assign(device);
								remove("", skipCounter);
								isAssigned = true;
								break;

							} catch (DroidBusyException e) {
								System.out.println(e);
							} catch (DroidDeviceMismatchException e) {
								System.out.println(e);
							}

						} else
							isAssigned = false;
					} else
						isAssigned = false;
				}

				if (!isAssigned) {
					skipCounter++;
				}
			} else if (device instanceof VRDevice) {
				for (int k = 0; k < repair.totalNumberOfDroids(); k++) {
					if (repair.getDroidAt(k) instanceof VRDroid
							|| repair.getDroidAt(k) instanceof ExpertDroid) {
						if (!repair.getDroidAt(k).isAssigned()) {
							try {
								repair.getDroidAt(k).assign(device);
								remove("", skipCounter);
								isAssigned = true;
								break;

							} catch (DroidBusyException e) {
								System.out.println(e);
							} catch (DroidDeviceMismatchException e) {
								System.out.println(e);
							}

						} else {
							isAssigned = false;
						}
					} else {
						isAssigned = false;
					}
				}

				if (!isAssigned)
					skipCounter++;
			}

			if (itr.hasNext()) {
				device = itr.next();
			} else
				break;
		}

	}

	/**
	 * Removes a device from the device list.
	 * 
	 * @param filter
	 *            A String filter that can be used with the filteredList method.
	 * @param position
	 *            The position that the device is located at.
	 * @return Returns the device that was removed from the list.
	 */
	@Override
	public Prioritizable remove(String filter, int position) {
		if (position < 0)
			return null;
		Prioritizable removed = devices.remove(filter, position);
		return removed;
	}

	/**
	 * Releases a Device that a TechDroid is currently working on.
	 * 
	 * @param index
	 *            The position of the TechDroid that the Device is being
	 *            released from.
	 * @return Returns the Device that the TechDroid was working on.
	 */
	@Override
	public Prioritizable releaseFromService(int index) {

		if (index >= 0 && index <= repair.totalNumberOfDroids())
			return repair.release(index);
		else
			return null;

	}

	/**
	 * Adds a new techDroid to the list of existing techDroids.
	 */
	@Override
	public void addNewDroid() {
		repair.addTechDroid();
	}

	/**
	 * Prints the waiting list of devices.
	 * 
	 * @param filter
	 *            The filter that can be used with the filteredList method to
	 *            display the current Devices.
	 * @return Returns a String of all the Devices that currently meet the
	 *         filtered conditions.
	 */
	@Override
	public String printWaitList(String filter) {
		String deviceList;

		if (filter == null) {
			deviceList = devices.filteredList("");
		} else {
			deviceList = devices.filteredList(filter.trim());
		}

		return deviceList;
	}

	/**
	 * Prints the techDroids that currently exist.
	 * 
	 * @return Returns a String of all the TechDroids that are generated so far.
	 */
	@Override
	public String printDroids() {
		return repair.printDroids();
	}

	/**
	 * Creates a new device to be added to the list of existing devices awaiting
	 * service.
	 * 
	 * @param type
	 *            String that determines the type of the device.
	 * @param serialNum
	 *            String that is the serial number for the device.
	 * @param name
	 *            String that is the owner's name for the device.
	 * @param tier
	 *            Integer that determines the tier of the device.
	 */
	@Override
	public void putOnWaitingList(String type, String serialNum, String name,
			int tier) {
		if (type.equalsIgnoreCase("C")) {
			Device com;
			try {
				com = new ComDevice(serialNum.trim(), name.trim(), tier);
				devices.add(com);
			} catch (BadDeviceInformationException e) {
				System.out.println(e);
			}

		} else {
			Device vr;
			try {
				vr = new VRDevice(serialNum.trim(), name.trim(), tier);
				devices.add(vr);
			} catch (BadDeviceInformationException e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * Adds an existing device to the list of existing devices awaiting service.
	 * 
	 * @param device
	 *            A device to be added to the waiting list.
	 */
	@Override
	public void putOnWaitingList(Prioritizable device) {
		devices.add((Device) device);

	}
}
