package edu.ncsu.csc216.carrental.model.state;

import edu.ncsu.csc216.carrental.model.Customer;

/**
 * This class is the Rented state for a car. It holds the information for the
 * customer renting the car while it is out. This means that the car is rented.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class Rented implements RentalState {
	/** The customer renting the car. */
	private Customer customer;

	/**
	 * Constructor for the Rented state for a car.
	 * 
	 * @param customer
	 *            The customer that has the car rented.
	 */
	public Rented(Customer customer) {
		this.customer = customer;
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
		mgr.processReturn(false);
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
		mgr.processReturn(true);
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
		return "(" + customer.getFirstName().substring(0, 1) + " "
				+ customer.getLastName() + ")";
	}

	/**
	 * Getter method for the customer that has the car rented.
	 * 
	 * @return Returns the customer that has the car rented.
	 */
	public Customer getCustomer() {
		return customer;
	}
}
