package edu.ncsu.csc216.androtech.model.devices;

/**
 * The ComDevice class extends the Device class. This is used to create a Com
 * device which can be repaired by the ComDroid or ExpertDroid.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class ComDevice extends Device {
	/**
	 * Constructor for the ComDevice. This creates a Com Device that needs to be
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
	public ComDevice(String serialNum, String name, int tier)
			throws BadDeviceInformationException {
		super(serialNum, name, tier);
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
		return "C " + super.toString();
	}
}