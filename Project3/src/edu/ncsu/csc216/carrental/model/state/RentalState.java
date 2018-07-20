package edu.ncsu.csc216.carrental.model.state;

/**
 * The state of a Car in a Car Rental Management system. Classes implementing
 * this interface provide state-specific behavior based on the State Design
 * Pattern.
 * 
 * @author David Wright
 */
public interface RentalState {

	/**
	 * Perform the actions necessary to rent a Car to a Customer
	 * 
	 * @param mgr
	 *            the context for the stateful behavior, the RentalStateManager
	 *            this case.
	 */
	public void rentCar(RentalStateManager mgr);

	/**
	 * Perform the actions necessary to return a Car from a rental with no
	 * problems reported.
	 * 
	 * @param mgr
	 *            the context for the stateful behavior, the RentalStateManager
	 *            this case.
	 */
	public void returnCar(RentalStateManager mgr);

	/**
	 * Perform the actions necessary to return a Car from a rental with a
	 * problem reported.
	 * 
	 * @param mgr
	 *            the context for the stateful behavior, the RentalStateManager
	 *            this case.
	 */
	public void reportProblem(RentalStateManager mgr);

	/**
	 * Perform the actions necessary to indicate that detailing has been
	 * completed.
	 * 
	 * @param mgr
	 *            the context for the stateful behavior, the RentalStateManager
	 *            this case.
	 */
	public void detailDone(RentalStateManager mgr);

	/**
	 * Perform the actions necessary to indicate that repairs has been
	 * completed.
	 * 
	 * @param mgr
	 *            the context for the stateful behavior, the RentalStateManager
	 *            this case.
	 */
	public void repairDone(RentalStateManager mgr);

	/**
	 * Returns a String representing the rental information for a rented car.
	 * 
	 * @return a string containing the rental information
	 */
	public String rentalInfo();
}