package edu.ncsu.csc216.androtech.model.devices;

/**
 * The Prioritizable interface class is used by the device class. This class is
 * also used as a type in a couple cases.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public interface Prioritizable {
	/**
	 * Gets the service tier that the devices is.
	 * 
	 * @return Returns the integer related to the service tier.
	 */
	public int getTier();

	/**
	 * Compares two devices using their tiers to compare to each other.
	 * 
	 * @param device
	 *            The device being compared.
	 * @return Returns 0 if the objects match, -1 if the object is less, and 1
	 *         if the object is greater.
	 */
	public int compareToTier(Prioritizable device);
}
