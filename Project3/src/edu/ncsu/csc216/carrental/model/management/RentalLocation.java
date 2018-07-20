package edu.ncsu.csc216.carrental.model.management;

import java.io.IOException;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;

/**
 * Interface providing UI access to information about a car rental manager.
 * 
 * @author David Wright
 *
 */
public interface RentalLocation {

	/**
	 * Rent the first car available to the customer at the front of the waiting
	 * list.
	 */
	public void rentCar();

	/**
	 * Customer returns a car without a problem report. The car must be detailed
	 * before it can be rented again.
	 */
	public void returnCar();

	/**
	 * Customer returns a car and reports a problem with it. The car must be
	 * repaired and detailed before it can be rented again.
	 */
	public void reportProblem();

	/**
	 * Detailing on the next car to leave the detail shop is completed and the
	 * car is available for renting.
	 */
	public void completeDetailing();

	/**
	 * Repairs to the next car to leave the repair shop are completed and the
	 * car must be moved to the detail shop before renting it again.
	 */
	public void completeRepairs();

	/**
	 * Return a String listing all of the cars available for renting, one car
	 * per line formatted as
	 * "&lt;fleet number&gt;:  &lt;make&gt; &lt;model&gt; (&lt;color&gt;)"
	 * 
	 * @return a String containing a list of all available cars
	 */
	String availableCars();

	/**
	 * Return a String listing all of the cars that are currently rented, one
	 * car per line formatted as
	 * "&lt;fleet number&gt;:  &lt;make&gt; &lt;model&gt; (&lt;color&gt;)"
	 * 
	 * @return a String containing a list of all currently rented cars
	 */
	String rentedCars();

	/**
	 * Return a String listing all of the cars that are currently in the repair
	 * shop, one car per line formatted as
	 * "&lt;fleet number&gt;:  &lt;make&gt; &lt;model&gt; (&lt;color&gt;)"
	 * 
	 * @return a String containing a list of all cars in the repair shop
	 */
	String detailingCars();

	/**
	 * Return a String listing all of the cars that are currently in the detail
	 * shop, one car per line formatted as
	 * "&lt;fleet number&gt;:  &lt;make&gt; &lt;model&gt; (&lt;color&gt;)"
	 * 
	 * @return a String containing a list of all cars in the detail shop
	 */
	String repairingCars();

	/**
	 * Return a String listing all of the customers waiting to rent a car, one
	 * customer per line formatted as
	 * "&lt;customer id&gt;:  &lt;first name&gt; &lt;last name&gt;"
	 * 
	 * @return a String containing a list of all cars in the detail shop
	 */
	String customersWaiting();

	/**
	 * Does this rental location have cars available for rental?
	 * 
	 * @return true if cars are available for rental, false otherwise.
	 */
	boolean hasAvailableCars();

	/**
	 * Does this rental location have cars that are currently rented?
	 * 
	 * @return true if one or more cars are rented, false otherwise.
	 */
	boolean hasRentedCars();

	/**
	 * Does this rental location have cars that are being detailed?
	 * 
	 * @return true if there is at least one car in the detail shop, false
	 *         otherwise.
	 */
	boolean hasDetailingCars();

	/**
	 * Does this rental location have cars that are being repaired?
	 * 
	 * @return true if is at least one car in the repair shop, false otherwise.
	 */
	boolean hasRepairingCars();

	/**
	 * Does this rental location have customers waiting to rent a car?
	 * 
	 * @return true if customers are waiting, false otherwise.
	 */
	boolean hasWaitingCustomers();

	/**
	 * Add a new customer to the waiting queue, checking that the new customer
	 * is not already in the queue
	 * 
	 * @param c
	 *            the customer to add
	 * 
	 * @return true if the customer was added, false if customer was not added
	 */
	boolean addCustomer(Customer c);

	/**
	 * Add a new car to the available cars stack
	 * 
	 * @param c
	 *            the car to add
	 * 
	 * @return true if the car was added, false if car was not added
	 */
	boolean addCar(Car c);

	/**
	 * Writes out the inventory of cars to the data file specified by the
	 * filename. The data file will have one line for each car in the inventory,
	 * using the following format:
	 * &lt;Make&gt;,&lt;Model&gt;,&lt;Color&gt;,&lt;Fleet
	 * Number&gt;,&lt;Status&gt;,&lt;Customer Name&gt;,&lt;Customer ID&gt;
	 * where: - Make is the make of the car - Model is the model of the car -
	 * Color is the color of the car - Fleet Number is the fleet number of the
	 * car - Status is the rental state of the car - Customer Name is the first
	 * and last name of the customer who is currently renting the car. This data
	 * should only be written if Status is "R" indicating the car is rented to a
	 * customer. - Customer ID is the customer's ID number
	 * 
	 * @param writer
	 *            - the Writer object that will receive the output. The client
	 *            has the obligation of creating this object.
	 */
	public void writeData(java.io.Writer writer) throws IOException;

}