package edu.ncsu.csc216.androtech.model.repair_center;

import edu.ncsu.csc216.androtech.model.devices.Device;
import edu.ncsu.csc216.androtech.model.devices.VRDevice;

/**
 * This class is used to create the VRDroid, which can repair the VR Devices and
 * only that device.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class VRDroid extends TechDroid {

	/**
	 * Constructor for the ComDroid. This is the repair Droid that is able to
	 * fix VR Devices. Sends the letter V to the TechDroid constructor to be
	 * used to created the droidID
	 */
	public VRDroid() {
		super("V");
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
		if (assignedDevice instanceof VRDevice)
			super.assign(assignedDevice);
		else
			throw new DroidDeviceMismatchException();
	}
}
