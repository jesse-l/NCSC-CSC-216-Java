package edu.ncsu.csc216.carrental.model.state;

/**
 * Interface representing the state transition functionality of RentalStates in
 * the Car Rental System.
 * 
 * @author David Wright
 */
public interface RentalStateManager {

	/**
	 * Rent the next available car to the first waiting customer
	 */
	void processRental();

	/**
	 * Process the return of a rented car. Processing depends upon whether a
	 * problem was reported or not. The car returned is the one at the front of
	 * the queue of rented cars.
	 * 
	 * @param problem
	 *            true if a problem was reported, false otherwise.
	 */
	void processReturn(boolean problem);

	/**
	 * Process a car that was out for detailing and make it available. The car
	 * processed is the one at the front of the detail shop queue.
	 */
	void processDetailed();

	/**
	 * Process a car that was out for repair and send it for detailing. The car
	 * processed is the one at the front of the repair shop queue.
	 */
	void processRepaired();

	/**
	 * Add a new car to the inventory of cars, making it available for renting.
	 * 
	 * @param id
	 *            the fleet number for the new car
	 * @param make
	 *            the make of the new car
	 * @param model
	 *            the model of the new car
	 * @param color
	 *            the color of the new car
	 */
	void processNewCar(String id, String make, String model, String color);

}