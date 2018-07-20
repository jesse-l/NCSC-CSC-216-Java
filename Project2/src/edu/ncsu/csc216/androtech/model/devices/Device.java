package edu.ncsu.csc216.androtech.model.devices;

/**
 * The Device class is an abstract class used to create both the com and vr
 * devices. This class handles the variables and methods that are used by the
 * devices.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public abstract class Device implements Prioritizable {
	/** The serial number that the device has. */
	private String serialNum;
	/** The name of the person who owns the device/ */
	private String name;
	/** The type of service plan that the owner has on the device. */
	private int tierIndex;
	/** The customer tier */
	public static final String[] CUSTOMER_TIER = { "None", "Silver", "Gold",
			"Platinum" };

	/**
	 * Constructor for a Device. Uses two strings one for the owner's name and
	 * one for the device serial number and one integer for the device's service
	 * plan.
	 * 
	 * @param serialNum
	 *            The serial number for the device.
	 * @param name
	 *            The owner's name that has the device.
	 * @param tier
	 *            The service plan tier the owner has on the device.
	 * @throws BadDeviceInformationException
	 *             Throws a BadDeviceInfromationException when someone does not
	 *             enter a owner name or a serial number.
	 */
	public Device(String serialNum, String name, int tier)
			throws BadDeviceInformationException {
		// Checks to see if the serial number is null.
		if (serialNum != null)
			this.serialNum = serialNum.trim();
		else
			this.serialNum = serialNum;

		// Checks to see if the name is null.
		if (name != null)
			this.name = name.trim();
		else
			this.name = name;

		// Sets the service plan tier.
		tierIndex = tier;

		// Validates all the information for the device.
		validateTier();
		validateName();
		validateSerialNum();

	}

	/**
	 * Checks the owner's name of the device against a String the user has
	 * entered.
	 * 
	 * @param compare
	 *            The string that the name field is compared to.
	 * @return Returns true if the name matches the string the user entered.
	 */
	public boolean meetsFilter(String compare) {

		if (compare == null)
			return true;
		else {
			compare = compare.trim();

			int size = compare.length();

			if (size > name.length()) {
				return false;
			} else {
				return name.substring(0, size).equalsIgnoreCase(compare);
			}

		}
	}

	/**
	 * A method that returns a string to be display in the GUI of the program
	 * that depicts the device's information.
	 * 
	 * @return Returns a string with the device's information displayed.
	 */
	public String toString() {
		String tier;
		if (tierIndex == 0) {
			tier = CUSTOMER_TIER[tierIndex] + "      ";
		} else if (tierIndex == 1) {
			tier = CUSTOMER_TIER[tierIndex] + "    ";
		} else if (tierIndex == 2) {
			tier = CUSTOMER_TIER[tierIndex] + "      ";
		} else {
			tier = CUSTOMER_TIER[tierIndex] + "  ";
		}
		return tier + serialNum + " " + name;
	}

	/**
	 * Returns the name of the owner of the device.
	 * 
	 * @return name The name of the owner of the device.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the serial number of the device.
	 * 
	 * @return serialNum The serial number for the device.
	 */
	public String getSerialNum() {
		return serialNum;
	}

	/**
	 * Gets the service tier that the devices is.
	 * 
	 * @return Returns the integer related to the service tier.
	 */
	public int getTier() {
		return tierIndex;
	}

	/**
	 * Compares two devices using their tiers to compare to each other.
	 * 
	 * @param device
	 *            The device being compared to. The device being compared.
	 * @return Returns 0 if the objects match, -1 if the object is less, and 1
	 *         if the object is greater.
	 */
	public int compareToTier(Prioritizable device) {
		int compareTier = device.getTier();
		int status;

		if (tierIndex == compareTier)
			status = 0;
		else if (tierIndex > compareTier)
			status = 1;
		else
			status = -1;

		return status;
	}

	/**
	 * Checks to see if the information entered for the service plan tier the
	 * device has is correct.
	 * 
	 * @throws BadDeviceInformationException
	 *             The information entered for the device was bad.
	 */
	private void validateTier() throws BadDeviceInformationException {
		if (tierIndex >= 4) {
			tierIndex = 3;
		} else if (tierIndex < 0) {
			tierIndex = 0;
		}
	}

	/**
	 * Checks to see if the information entered for the owner's name is correct.
	 * 
	 * @throws BadDeviceInformationException
	 *             The information entered for the device was bad.
	 */
	private void validateName() throws BadDeviceInformationException {
		try {
			if (name == null || name.trim() == null || name.isEmpty())
				throw new BadDeviceInformationException(
						"Owner name cannot be blank.");
		} catch (NullPointerException e) {
			// Just in case this exception is thrown.
		}
	}

	/**
	 * Checks to see if the information entered for the serial number of the
	 * device is correct.
	 * 
	 * @throws BadDeviceInformationException
	 *             The information entered for the device was bad.
	 */
	private void validateSerialNum() throws BadDeviceInformationException {
		try {
			if (serialNum == null || serialNum.trim() == null
					|| serialNum.isEmpty())
				throw new BadDeviceInformationException(
						"Serial number cannot be blank.");
		} catch (NullPointerException e) {
			// Just in case this exception is thrown.
		}
	}

}
