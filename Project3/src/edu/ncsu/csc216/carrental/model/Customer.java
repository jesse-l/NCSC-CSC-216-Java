package edu.ncsu.csc216.carrental.model;

/**
 * This class creates the Customers that will be renting the vehicles or have
 * the vehicles rented.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class Customer {
	/** The first name of the customer. */
	private String firstName;
	/** The last name of the customer. */
	private String lastName;
	/** The customer's id number. */
	private String id;

	/**
	 * The constructor for the Customer type. It accepts three Strings as
	 * parameters.
	 * 
	 * @param firstName
	 *            The first name of the customer.
	 * @param lastName
	 *            The last name of the customer.
	 * @param id
	 *            The customer's id number.
	 */
	public Customer(String firstName, String lastName, String id) {
		if (firstName.matches("^[a-zA-Z]+(-*)('*)([a-zA-Z]*)")
				&& lastName.matches("^[a-zA-Z]+(-*)('*)([a-zA-Z]*)")
				&& id.matches("(\\d{2})-(\\d{4})")) {

			this.firstName = firstName.trim();
			this.lastName = lastName.trim();
			this.id = id.trim();

		} else if (!id.matches("(\\d{2})-(\\d{4})")) {
			throw new InvalidIDException("Make sure the customer id is valid.");
		} else {
			throw new IllegalArgumentException(
					"Make sure inforamtion is valid.");
		}
	}

	/**
	 * Getter method for the customer's first name.
	 * 
	 * @return The first name of the customer.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Getter method for the customer's last name.
	 * 
	 * @return The last name of the customer.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Getter method for the customer's id number.
	 * 
	 * @return The customer's id number.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns a string representation of the customer to be displayed in the
	 * user interface.
	 * 
	 * @return Returns a String to represent the customer.
	 */
	public String toString() {
		return id + ":  " + firstName + " " + lastName;
	}

	/**
	 * Generates hash code for the Customer object.
	 * 
	 * @return Returns the results of the hascode.
	 */
	@Override
	public int hashCode() {
		int result = 0;
		result = result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Overrides the equals method for the Customer class.
	 * 
	 * @param obj
	 *            This is another customer object that it is comparing to.
	 * @return Returns if the two objects have the same customer ID.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}