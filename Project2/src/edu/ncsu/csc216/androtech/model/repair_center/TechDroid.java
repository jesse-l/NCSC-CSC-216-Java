package edu.ncsu.csc216.androtech.model.repair_center;

/** Imports the Device class so it can use the type. **/
import edu.ncsu.csc216.androtech.model.devices.Device;

/**
 * This is the abstract class TechDroid it is used to create the TechDroids that
 * will be used to repair the devices.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public abstract class TechDroid {
	/** Boolean for seeing if the TechDroid is working on a Device. */
	private boolean assigned;
	/** The number of TechDroid and the type. */
	private String droidID;
	/** The next number to be used when a TechDroid is created. */
	private static int nextNumber = 1;
	/** This is the device that is currently being fixed. */
	private Device currentDevice;

	/**
	 * Resets the number of TechDroids created to 1.
	 */
	public static void startDroidNumberingAt01() {
		TechDroid.nextNumber = 1;
	}

	/**
	 * Constructor for the TechDroids, which accepts a String that contains the
	 * type of TechDroid it is.
	 * 
	 * @param droidID
	 *            This is the type of TechDroid that is being represented.
	 */
	public TechDroid(String droidID) {
		if (nextNumber < 10)
			this.droidID = "0" + nextNumber + droidID;
		else
			this.droidID = nextNumber + droidID;
		assigned = false;
		currentDevice = null;
		TechDroid.nextNumber++;
	}

	/**
	 * Getter method used to get the droidID for the TechDroid.
	 * 
	 * @return droidID The number of the droid and the type.
	 */
	public String getDroidID() {
		return droidID;
	}

	/**
	 * Getter method used to see if the TechDroid is assigned.
	 * 
	 * @return True if the TechDroid is working or false if it is not.
	 */
	public boolean isAssigned() {
		return assigned;
	}

	/**
	 * Releases the device that was being repaired by the TechDroid.
	 * 
	 * @return Device the device that was being repaired by the droid.
	 */
	public Device release() {
		assigned = false;
		Device removed = currentDevice;
		this.currentDevice = null;
		return removed;
	}

	/**
	 * This method assigns a device to a TechDroid to be fixed.
	 * 
	 * @param newDevice
	 *            The device going to be assigned to the TechDroid.
	 * @throws DroidBusyException
	 *             When a TechDroid is currently working on a Deivce.
	 * @throws DroidDeviceMismatchException
	 *             When Device type and TechDroid type do not match this is
	 *             thrown.
	 */
	public void assign(Device newDevice) throws DroidBusyException,
			DroidDeviceMismatchException {
		if (!assigned) {
			this.currentDevice = newDevice;
			assigned = true;
		} else {
			throw new DroidBusyException();
		}
	}

	/**
	 * This method returns the droidID plus if the device is assigned the serial
	 * number of the device and the owner's name or if it is not assigned it
	 * returns the ID and UNASSIGNED.
	 * 
	 * @return DroidString droidID + the serial number and owner of the device
	 *         it is assigned.
	 */
	@Override
	public String toString() {
		if (this.assigned)
			return droidID + ": " + currentDevice.getSerialNum() + " "
					+ currentDevice.getName();
		else
			return droidID + ": UNASSIGNED";
	}
}
