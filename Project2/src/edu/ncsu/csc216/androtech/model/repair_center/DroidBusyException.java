package edu.ncsu.csc216.androtech.model.repair_center;

/**
 * This class is used to create an exception to be thrown when the TechDroid
 * that is being assigned a Device to repair is already busy repairing another
 * Device.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class DroidBusyException extends Exception {
	private static final long serialVersionUID = -922795020358621754L;

	/**
	 * Generic constructor for the exception. This exception will throw a
	 * generic message to the user.
	 */
	public DroidBusyException() {
		super("Droid is currently busy.");
	}

	/**
	 * This constructor is passed a string that will be used to display
	 * information to the user for a more specific exception.
	 * 
	 * @param message
	 *            This is the specific error messaged to be displayed to the
	 *            user.
	 */
	public DroidBusyException(String message) {
		super(message);
	}
}
