package edu.ncsu.csc216.carrental.model.state;

/**
 * This class is used to create the OutForDetail state for the car. This means
 * the car is in the detail shop.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class OutForDetail implements RentalState {

	/**
	 * A constructor for the OutForDetail state for a car.
	 */
	public OutForDetail() {
		// Empty constructor
	}

	/**
	 * Perform the actions necessary to rent a Car to a Customer
	 * 
	 * @param mgr
	 *            the context for the stateful behavior, the RentalStateManager
	 *            this case.
	 */
	public void rentCar(RentalStateManager mgr) {
		throw new IllegalStateException();
	}

	/**
	 * Perform the actions necessary to return a Car from a rental with no
	 * problems reported.
	 * 
	 * @param mgr
	 *            the context for the stateful behavior, the RentalStateManager
	 *            this case.
	 */
	public void returnCar(RentalStateManager mgr) {
		throw new IllegalStateException();
	}

	/**
	 * Perform the actions necessary to return a Car from a rental with a
	 * problem reported.
	 * 
	 * @param mgr
	 *            the context for the stateful behavior, the RentalStateManager
	 *            this case.
	 */
	public void reportProblem(RentalStateManager mgr) {
		throw new IllegalStateException();
	}

	/**
	 * Perform the actions necessary to indicate that detailing has been
	 * completed.
	 * 
	 * @param mgr
	 *            the context for the stateful behavior, the RentalStateManager
	 *            this case.
	 */
	public void detailDone(RentalStateManager mgr) {
		mgr.processDetailed();
	}

	/**
	 * Perform the actions necessary to indicate that repairs has been
	 * completed.
	 * 
	 * @param mgr
	 *            the context for the stateful behavior, the RentalStateManager
	 *            this case.
	 */
	public void repairDone(RentalStateManager mgr) {
		throw new IllegalStateException();
	}

	/**
	 * Returns a String representing the rental information for a rented car.
	 * 
	 * @return a string containing the rental information
	 */
	public String rentalInfo() {
		throw new IllegalStateException();
	}

}
