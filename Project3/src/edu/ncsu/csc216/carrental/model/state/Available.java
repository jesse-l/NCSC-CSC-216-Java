package edu.ncsu.csc216.carrental.model.state;

/**
 * This class is used to create the available state for the car. This means the
 * car is available to rent.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class Available implements RentalState {

	/**
	 * Constructor for an available state for a car.
	 */
	public Available() {
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
		mgr.processRental();
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
		throw new IllegalStateException();
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
