package edu.ncsu.csc216.androtech.model.repair_center;

import edu.ncsu.csc216.androtech.model.devices.Device;

/**
 * This class is used to create the ComDroid, which can repair the Com Devices
 * and only that device.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class ComDroid extends TechDroid {

	/**
	 * Constructor for the ComDroid. This is the repair Droid that is able to
	 * fix ComDevices. Sends the letter C to the TechDroid constructor to be
	 * used to created the droidID
	 */
	public ComDroid() {
		super("C");
	}

	/**
	 * This method assigns a device to a TechDroid to be fixed.
	 * 
	 * @param assignedDevice
	 *            The device going to be assigned to the TechDroid.
	 * @throws DroidDeviceMismatchException
	 *             Checks to see if the TechDroid type and device type are the
	 *             same.
	 */
	public void assign(Device assignedDevice)
			throws DroidDeviceMismatchException, DroidBusyException {
		if (assignedDevice.toString().substring(0, 1).equalsIgnoreCase("C"))
			super.assign(assignedDevice);
		else
			throw new DroidDeviceMismatchException();
	}
}
