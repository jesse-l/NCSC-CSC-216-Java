package edu.ncsu.csc216.carrental.model.management;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.InvalidIDException;
import edu.ncsu.csc216.carrental.model.state.Available;
import edu.ncsu.csc216.carrental.model.state.OutForDetail;
import edu.ncsu.csc216.carrental.model.state.OutForRepair;
import edu.ncsu.csc216.carrental.model.state.RentalStateManager;
import edu.ncsu.csc216.carrental.model.state.Rented;
import edu.ncsu.csc216.carrental.util.Queue;
import edu.ncsu.csc216.carrental.util.Stack;

/**
 * This class is the brains of the program. This class holds the lists of cars
 * and customers.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class NuxCarRental implements RentalLocation, RentalStateManager {
	/** The queue of customers waiting for a car. */
	private Queue<Customer> customers;
	/** The queue of cars in the detail shop. */
	private Queue<Car> detailShop;
	/** The queue of cars in the repair shop. */
	private Queue<Car> repairShop;
	/** The stack of cars that are available to rent. */
	private Stack<Car> availableCars;
	/** The queue of cars that are rented to customers. */
	private Queue<Car> rentedCars;

	private StringBuffer rentedList;
	private StringBuffer availableList;
	private StringBuffer detailList;
	private StringBuffer repairList;
	private StringBuffer customerList;

	/**
	 * The constructor for the NuxCarRental that starts with all clean list.
	 */
	public NuxCarRental() {
		// Set up for the lists.
		customers = new Queue<Customer>();
		detailShop = new Queue<Car>();
		repairShop = new Queue<Car>();
		availableCars = new Stack<Car>();
		rentedCars = new Queue<Car>();

		customerList = new StringBuffer();
		availableList = new StringBuffer();
		rentedList = new StringBuffer();
		detailList = new StringBuffer();
		repairList = new StringBuffer();
	}

	/**
	 * The constructor for the NuxCarRental that will load a list of cars from a
	 * previous location.
	 * 
	 * @param scanner
	 *            The list of previous cars stored as a scanner.
	 */
	public NuxCarRental(Scanner scanner) {
		// Set up for the lists.
		customers = new Queue<Customer>();
		detailShop = new Queue<Car>();
		repairShop = new Queue<Car>();
		availableCars = new Stack<Car>();
		rentedCars = new Queue<Car>();

		customerList = new StringBuffer();
		availableList = new StringBuffer();
		rentedList = new StringBuffer();
		detailList = new StringBuffer();
		repairList = new StringBuffer();

		Car car;

		String make;
		String model;
		String color;
		String fleetNum;
		String status;
		String customerName;
		String[] nameParts = new String[2];
		String customerId;

		Scanner scannerLine = null;

		scanner.useDelimiter("\n");

		while (scanner.hasNextLine()) {
			scannerLine = new Scanner(scanner.nextLine());
			scannerLine.useDelimiter(",");

			if (scannerLine.hasNext()) {
				make = scannerLine.next();
				if (scannerLine.hasNext()) {
					model = scannerLine.next();
					if (scannerLine.hasNext()) {
						color = scannerLine.next();
						if (scannerLine.hasNext()) {
							fleetNum = scannerLine.next();
							if (scannerLine.hasNext()) {
								status = scannerLine.next();

								if (status.equalsIgnoreCase("A")) {
									processNewCar(fleetNum, make, model, color);
								} else if (status.equalsIgnoreCase("D")) {

									try {
										car = new Car(fleetNum, make, model,
												color);

										if (!checkDuplicate(car)) {
											car.setState(new OutForDetail());
											detailShop.add(car);
										}
									} catch (InvalidIDException e) {
										// Do Nothing
									}
								} else if (status.equalsIgnoreCase("S")) {
									try {
										car = new Car(fleetNum, make, model,
												color);
										if (!checkDuplicate(car)) {
											car.setState(new OutForRepair());
											repairShop.add(car);
										}
									} catch (InvalidIDException e) {
										// Do Nothing
									}
								} else if (status.equalsIgnoreCase("R")
										&& scannerLine.hasNext()) {

									customerName = scannerLine.next();
									nameParts = customerName.split(" ");

									if (scannerLine.hasNext()) {

										try {
											customerId = scannerLine.next();

											car = new Car(fleetNum, make,
													model, color);

											if (!checkDuplicate(car)) {
												car.setState(new Rented(
														new Customer(
																nameParts[0]
																		.toString()
																		.trim(),
																nameParts[1]
																		.toString()
																		.trim(),
																customerId)));

												rentedCars.add(car);
											}
										} catch (InvalidIDException e) {
											// Do Nothing
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// Closes scanners
		scannerLine.close();
		scanner.close();
	}

	/**
	 * This method is used to check for duplicate cars during the scanner
	 * construction.
	 * 
	 * @param Car
	 *            The new car trying to be added.
	 * @return Returns true if the fleet number already exist.
	 */
	private boolean checkDuplicate(Car car) {
		boolean isMatch = false;
		Queue<Car> catchingQueue = new Queue<Car>();
		Stack<Car> catchingStack = new Stack<Car>();
		Car top;

		while (!detailShop.isEmpty()) {
			top = detailShop.remove();
			if (car.equals(top)) {
				isMatch = true;
			}

			catchingQueue.add(top);
		}

		while (!catchingQueue.isEmpty()) {
			detailShop.add(catchingQueue.remove());
		}

		while (!repairShop.isEmpty()) {
			top = repairShop.remove();
			if (car.equals(top)) {
				isMatch = true;
			}

			catchingQueue.add(top);
		}

		while (!catchingQueue.isEmpty()) {
			repairShop.add(catchingQueue.remove());
		}

		while (!rentedCars.isEmpty()) {
			top = rentedCars.remove();

			if (car.equals(top)) {
				isMatch = true;
			}

			catchingQueue.add(top);
		}

		while (!catchingQueue.isEmpty()) {
			rentedCars.add(catchingQueue.remove());
		}

		while (!availableCars.isEmpty()) {
			top = availableCars.pop();
			if (car.equals(top)) {
				isMatch = true;
			}

			catchingStack.push(top);
		}

		while (!catchingStack.isEmpty()) {
			availableCars.push(catchingStack.pop());
		}

		return isMatch;
	}

	/**
	 * This method is used to rent the top car of the available cars list to the
	 * top customer.
	 */
	public void rentCar() {
		if (!customers.isEmpty() && !availableCars.isEmpty()) {
			Car topCar = availableCars.peek();
			topCar.getState().rentCar(this);
		}
	}

	/**
	 * This method is used to return the first car on the rented cars list.
	 */
	public void returnCar() {
		if (!rentedCars.isEmpty()) {
			// Grabs the top car off of the rented car list.
			Car topCar = rentedCars.peek();
			topCar.getState().returnCar(this);
		}
	}

	/**
	 * Customer returns a car and reports a problem with it. The car must be
	 * repaired and detailed before it can be rented again.
	 */
	public void reportProblem() {
		if (!rentedCars.isEmpty()) {
			// Grabs the top car off of the rented car list.
			Car topCar = rentedCars.peek();
			topCar.getState().reportProblem(this);
		}
	}

	/**
	 * Detailing on the next car to leave the detail shop is completed and the
	 * car is available for renting.
	 */
	public void completeDetailing() {
		if (!detailShop.isEmpty()) {
			// Pulls the top car from the detail shop.
			Car top = detailShop.peek();
			top.getState().detailDone(this);
		}
	}

	/**
	 * Repairs to the next car to leave the repair shop are completed and the
	 * car must be moved to the detail shop before renting it again.
	 */
	public void completeRepairs() {
		if (!repairShop.isEmpty()) {
			// Pulls the top car from the detail shop.
			Car top = repairShop.peek();
			top.getState().repairDone(this);
		}
	}

	/**
	 * Return a String listing all of the cars available for renting, one car
	 * per line formatted as "<fleet number>:  <make> <model> (<color>)"
	 * 
	 * @return A String containing a list of all available cars.
	 */
	public String availableCars() {
		availableList.delete(0, availableList.length());

		Stack<Car> carStack = new Stack<Car>();
		Car topCar = null;

		if (availableCars == null)
			return "";
		else {
			while (!availableCars.isEmpty()) {
				topCar = availableCars.pop();

				availableList.append(topCar.toString() + "\n");

				carStack.push(topCar);
			}

			while (!carStack.isEmpty()) {
				topCar = carStack.pop();
				availableCars.push(topCar);
			}

			return availableList.toString();
		}
	}

	/**
	 * Return a String listing all of the cars that are currently rented, one
	 * car per line formatted as
	 * "<fleet number>:  <make> <model> (<customerName>)"
	 * 
	 * @return a String containing a list of all currently rented cars
	 */
	public String rentedCars() {
		rentedList.delete(0, rentedList.length());

		Queue<Car> rentedQueue = new Queue<Car>();
		Car topCar;

		if (rentedCars.isEmpty())
			return "";
		else {
			while (!rentedCars.isEmpty()) {
				topCar = rentedCars.remove();
				rentedList.append(topCar.toString() + " "
						+ topCar.getState().rentalInfo() + "\n");

				rentedQueue.add(topCar);
			}

			while (!rentedQueue.isEmpty()) {
				topCar = rentedQueue.remove();
				rentedCars.add(topCar);
			}

			return rentedList.toString();
		}
	}

	/**
	 * Return a String listing all of the cars that are currently in the repair
	 * shop, one car per line formatted as
	 * "<fleetNumber>:  <make> <model> (<color>)"
	 * 
	 * @return a String containing a list of all cars in the detail shop
	 */
	public String detailingCars() {
		detailList.delete(0, detailList.length());

		Queue<Car> detailQueue = new Queue<Car>();
		Car topCar;

		if (detailShop.isEmpty())
			return "";
		else {
			while (!detailShop.isEmpty()) {
				topCar = detailShop.remove();
				detailList.append(topCar.toString() + "\n");

				detailQueue.add(topCar);
			}

			while (!detailQueue.isEmpty()) {
				topCar = detailQueue.remove();
				detailShop.add(topCar);
			}

			return detailList.toString();
		}
	}

	/**
	 * Return a String listing all of the cars that are currently in the detail
	 * shop, one car per line formatted as
	 * "<fleet number>:  <make> <model> (<color>)"
	 * 
	 * @return a String containing a list of all cars in the repair shop
	 */
	public String repairingCars() {
		repairList.delete(0, repairList.length());

		Queue<Car> repairQueue = new Queue<Car>();
		Car topCar;

		if (repairShop.isEmpty())
			return "";
		else {
			while (!repairShop.isEmpty()) {
				topCar = repairShop.remove();
				repairList.append(topCar.toString() + "\n");
				repairQueue.add(topCar);
			}

			while (!repairQueue.isEmpty()) {
				topCar = repairQueue.remove();
				repairShop.add(topCar);
			}
			return repairList.toString();
		}
	}

	/**
	 * Return a String listing all of the customers waiting to rent a car, one
	 * customer per line formatted as "<Customer ID>:  <FirstName> <LastName>"
	 * 
	 * @return a String containing a list of all cars in the detail shop
	 */
	public String customersWaiting() {
		customerList.delete(0, customerList.length());

		Queue<Customer> customerQueue = new Queue<Customer>();
		Customer topCustomer;

		if (customers.isEmpty())
			return "";
		else {
			while (!customers.isEmpty()) {
				topCustomer = customers.remove();
				customerList.append(topCustomer.toString() + "\n");

				customerQueue.add(topCustomer);
			}

			while (!customerQueue.isEmpty()) {
				topCustomer = customerQueue.remove();
				customers.add(topCustomer);
			}

			return customerList.toString();
		}
	}

	/**
	 * Does this rental location have cars available for rental?
	 * 
	 * @return true if cars are available for rental, false otherwise.
	 */
	public boolean hasAvailableCars() {
		return !availableCars.isEmpty();
	}

	/**
	 * Does this rental location have cars that are currently rented?
	 * 
	 * @return true if one or more cars are rented, false otherwise.
	 */
	public boolean hasRentedCars() {
		return !rentedCars.isEmpty();
	}

	/**
	 * Does this rental location have cars that are being detailed?
	 * 
	 * @return true if there is at least one car in the detail shop, false
	 *         otherwise.
	 */
	public boolean hasDetailingCars() {
		return !detailShop.isEmpty();
	}

	/**
	 * Does this rental location have cars that are being repaired?
	 * 
	 * @return true if is at least one car in the repair shop, false otherwise.
	 */
	public boolean hasRepairingCars() {
		return !repairShop.isEmpty();
	}

	/**
	 * Does this rental location have customers waiting to rent a car?
	 * 
	 * @return true if customers are waiting, false otherwise.
	 */
	public boolean hasWaitingCustomers() {
		return !customers.isEmpty();
	}

	/**
	 * Add a new customer to the waiting queue, checking that the new customer
	 * is not already in the queue
	 * 
	 * @param customer
	 *            the customer to add
	 * 
	 * @return true if the customer was added, false if customer was not added
	 */
	public boolean addCustomer(Customer customer) {
		boolean match = false;

		if (!hasRentedCars() && !hasWaitingCustomers()) {

			customers.add(customer);
			return true;
		} else {
			Queue<Customer> catchCustomers = new Queue<Customer>();
			Customer topCus;
			Queue<Car> catchCars = new Queue<Car>();
			Car topCar;

			while (!customers.isEmpty()) {
				topCus = customers.remove();
				if (customer.equals(topCus)) {
					match = true;
				}
				catchCustomers.add(topCus);
			}

			while (!catchCustomers.isEmpty()) {
				customers.add(catchCustomers.remove());
			}

			while (!rentedCars.isEmpty()) {
				topCar = rentedCars.remove();
				if (((Rented) topCar.getState()).getCustomer().equals(customer)) {
					match = true;
				}

				catchCars.add(topCar);
			}

			while (!catchCars.isEmpty()) {
				rentedCars.add(catchCars.remove());
			}

			if (!match) {
				customers.add(customer);
				return true;
			}

			return false;
		}

	}

	/**
	 * Add a new car to the available cars stack
	 * 
	 * @param car
	 *            the car to add
	 * 
	 * @return true if the car was added, false if car was not added
	 */
	public boolean addCar(Car car) {
		boolean isMatch = false;
		Queue<Car> catchingQueue = new Queue<Car>();
		Stack<Car> catchingStack = new Stack<Car>();
		Car top;

		while (!detailShop.isEmpty()) {
			top = detailShop.remove();
			if (car.equals(top)) {
				isMatch = true;
			}

			catchingQueue.add(top);
		}

		while (!catchingQueue.isEmpty()) {
			detailShop.add(catchingQueue.remove());
		}

		while (!repairShop.isEmpty()) {
			top = repairShop.remove();
			if (car.equals(top)) {
				isMatch = true;
			}

			catchingQueue.add(top);
		}

		while (!catchingQueue.isEmpty()) {
			repairShop.add(catchingQueue.remove());
		}

		while (!rentedCars.isEmpty()) {
			top = rentedCars.remove();

			if (car.equals(top)) {
				isMatch = true;
			}

			catchingQueue.add(top);
		}

		while (!catchingQueue.isEmpty()) {
			rentedCars.add(catchingQueue.remove());
		}

		while (!availableCars.isEmpty()) {
			top = availableCars.pop();
			if (car.equals(top)) {
				isMatch = true;
			}

			catchingStack.push(top);
		}

		while (!catchingStack.isEmpty()) {
			availableCars.push(catchingStack.pop());
		}

		if (car.getFleetNum().matches("^[A-Z]{1}\\d{4}") && !isMatch) {
			if (!(car.getState() instanceof Available))
				car.setState(new Available());
			availableCars.push(car);
			return true;
		} else
			return false;

	}

	/**
	 * Processes renting a car. This method collects the top customer and top
	 * car then sets the car's state to rented with the customer renting it.
	 */
	public void processRental() {
		if (!availableCars.isEmpty() && !customers.isEmpty()) {
			Car topCar = availableCars.pop();
			Customer topCustomer = customers.remove();

			topCar.setState(new Rented(topCustomer));
			rentedCars.add(topCar);
		}
	}

	/**
	 * Process the return of a rented car. Processing depends upon whether a
	 * problem was reported or not. The car returned is the one at the front of
	 * the queue of rented cars.
	 * 
	 * @param problem
	 *            true if a problem was reported, false otherwise.
	 */
	public void processReturn(boolean problem) {
		if (rentedCars.isEmpty())
			return;

		Car topCar = rentedCars.remove();
		Customer customer = ((Rented) topCar.getState()).getCustomer();

		customers.add(customer);

		if (!problem) {
			topCar.setState(new OutForDetail());
			detailShop.add(topCar);
		} else {
			topCar.setState(new OutForRepair());
			repairShop.add(topCar);
		}
	}

	/**
	 * Process a car that was out for detailing and make it available. The car
	 * processed is the one at the front of the detail shop queue.
	 */
	public void processDetailed() {
		if (!detailShop.isEmpty()) {

			Car topCar = detailShop.remove();

			topCar.setState(new Available());
			availableCars.push(topCar);
		}
	}

	/**
	 * Process a car that was out for repair and send it for detailing. The car
	 * processed is the one at the front of the repair shop queue.
	 */
	public void processRepaired() {
		if (repairShop.isEmpty())
			return;

		Car topCar = repairShop.remove();

		topCar.setState(new OutForDetail());
		detailShop.add(topCar);
	}

	/**
	 * Add a new car to the inventory of cars, making it available for renting.
	 * 
	 * @param fleetNum
	 *            the fleet number for the new car
	 * @param make
	 *            the make of the new car
	 * @param model
	 *            the model of the new car
	 * @param color
	 *            the color of the new car
	 * 
	 * @throws InvalidIDException
	 *             if the fleet number for the car is invalid.
	 */
	public void processNewCar(String fleetNum, String make, String model,
			String color) {

		try {
			Car newCar = new Car(fleetNum, make, model, color);

			addCar(newCar);

		} catch (InvalidIDException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes out the inventory of cars to the data file specified by the
	 * filename. The data file will have one line for each car in the inventory,
	 * using the following format:
	 * <Make>,<Model>,<Color>,<FleetNumber>,<Status>,<CustomerName>,<CustomerID>;
	 * where: - Make is the make of the car - Model
	 * is the model of the car - Color is the color of the car - Fleet Number is
	 * the fleet number of the car - Status is the rental state of the car -
	 * Customer Name is the first and last name of the customer who is currently
	 * renting the car. This data should only be written if Status is "R"
	 * indicating the car is rented to a customer. - Customer ID is the
	 * customer's ID number
	 * 
	 * @param writer
	 *            - the Writer object that will receive the output. The client
	 *            has the obligation of creating this object.
	 */
	public void writeData(Writer writer) {
		BufferedWriter buffer = new BufferedWriter(writer);
		PrintWriter pw = new PrintWriter(buffer);

		Car top;

		// Writes the available cars list.
		while (!availableCars.isEmpty()) {
			top = availableCars.pop();

			pw.println(top.getMake() + "," + top.getModel() + ","
					+ top.getColor() + "," + top.getFleetNum() + ",A");

		}

		// Writes the rented cars list.
		while (!rentedCars.isEmpty()) {
			top = rentedCars.remove();

			pw.println(top.getMake() + "," + top.getModel() + ","
					+ top.getColor() + "," + top.getFleetNum() + ",R,"
					+ ((Rented) top.getState()).getCustomer().getFirstName()
					+ " "
					+ ((Rented) top.getState()).getCustomer().getLastName()
					+ "," + ((Rented) top.getState()).getCustomer().getId());
		}

		// Writes the detail shop list.
		while (!detailShop.isEmpty()) {
			top = detailShop.remove();

			pw.println(top.getMake() + "," + top.getModel() + ","
					+ top.getColor() + "," + top.getFleetNum() + ",D");
		}

		// Writes the repair shop list.
		while (!repairShop.isEmpty()) {
			top = repairShop.remove();

			pw.println(top.getMake() + "," + top.getModel() + ","
					+ top.getColor() + "," + top.getFleetNum() + ",S");
		}

		pw.close();
	}
}
