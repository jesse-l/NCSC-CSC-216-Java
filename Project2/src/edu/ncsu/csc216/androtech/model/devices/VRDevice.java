package edu.ncsu.csc216.androtech.model.devices;

/**
 * The VRDevice class extends the Device class. This is used to create a VR
 * device which can be repaired by the VRDroid or ExpertDroid.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class VRDevice extends Device {
	/**
	 * Constructor for the VRDevice. This creates a VR Device that needs to be
	 * repaired.
	 * 
	 * @param serialNum
	 *            The serial number for the device.
	 * @param name
	 *            The name of the owner of the device.
	 * @param tier
	 *            The service plan the device has.
	 * @throws BadDeviceInformationException
	 *             Throws a BadDeviceInfromationException when someone does not
	 *             enter a owner name or a serial number.
	 */
	public VRDevice(String serialNum, String name, int tier)
			throws BadDeviceInformationException {
		super(serialNum.trim(), name.trim(), tier);
	}

	/**
	 * Creates a string of data from the device. First it shows the type of
	 * device, then it calls the super.toString to get the toString method from
	 * the device class to finish printing out the information.
	 * 
	 * @return A string description of the device.
	 */
	@Override
	public String toString() {
		return "V " + super.toString();
	}
}
