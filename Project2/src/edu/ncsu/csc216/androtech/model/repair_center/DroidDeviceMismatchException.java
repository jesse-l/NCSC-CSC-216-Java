package edu.ncsu.csc216.androtech.model.repair_center;

/**
 * This class is used to create an exception to be thrown when the techDroid
 * type and the device type do not match. This class extends the java exception
 * class.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class DroidDeviceMismatchException extends Exception {
	private static final long serialVersionUID = 5449238196099823003L;

	/**
	 * Generic constructor for the exception. This exception will throw a
	 * generic message to the user.
	 */
	public DroidDeviceMismatchException() {
		super("Device type does not match the Droid type.");
	}

	/**
	 * This constructor is passed a string that will be used to display
	 * information to the user for a more specific exception.
	 * 
	 * @param message
	 *            This is the specific error messaged to be displayed to the
	 *            user.
	 */
	public DroidDeviceMismatchException(String message) {
		super(message);
	}
}
