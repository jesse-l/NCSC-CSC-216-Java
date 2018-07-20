package edu.ncsu.csc216.androtech.model.devices;

/**
 * This class is used to create an exception to be thrown when the information
 * entered into the device fields are bad information. For example, no owner
 * name or no serial number or the wrong tier information.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class BadDeviceInformationException extends Exception {
	private static final long serialVersionUID = 3133449161525314546L;

	/**
	 * Generic constructor for the exception. This exception will throw a
	 * generic message to the user.
	 */
	public BadDeviceInformationException() {
		super("Bad inofmation entered for this device.");
	}

	/**
	 * This constructor is passed a string that will be used to display
	 * information to the user for a more specific exception.
	 * 
	 * @param message
	 *            This is the specific error messaged to be displayed to the
	 *            user.
	 */
	public BadDeviceInformationException(String message) {
		super(message);
	}
}