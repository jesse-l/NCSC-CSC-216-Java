package edu.ncsu.csc216.carrental.model;

import edu.ncsu.csc216.carrental.model.state.RentalState;

/**
 * This class constructs the car object. The car object has five data members:
 * fleetNum, make, model, color, and state.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class Car {
	/** The fleet number of the vehicle. */
	private String fleetNum;
	/** The maker of the vehicle. */
	private String make;
	/** The model of the vehicle. */
	private String model;
	/** The color of the vehicle. */
	private String color;
	/** The state that the vehicle is in. */
	private RentalState state;

	/**
	 * Constructor for a Car object. This constructor accepts four Strings as
	 * parameters to create a Car.
	 * 
	 * @param fleetNum
	 *            The fleet number of the vehicle.
	 * @param make
	 *            The make of the vehicle.
	 * @param model
	 *            The model of the vehicle.
	 * @param color
	 *            The color of the vehicle.
	 * @throws InvalidIDException
	 *             If fleet number does not match the specific pattern this
	 *             exception will be thrown.
	 */
	public Car(String fleetNum, String make, String model, String color) {
		if (!fleetNum.matches("^[A-Z]{1}\\d{4}")) {
			throw new InvalidIDException();
		} else if (!make.matches("[a-zA-z]+")
				|| !model.matches("[a-zA-z 0-9-]+")
				|| !color.matches("[a-zA-Z]+")) {
			throw new IllegalArgumentException(
					"Make sure inforamtion is valid.");
		} else {
			this.fleetNum = fleetNum.trim();
			this.make = make.trim();
			this.model = model.trim();
			this.color = color.trim();

			state = null;
		}
	}

	/**
	 * Getter method for the fleet number of the vehicle.
	 * 
	 * @return Returns the fleet number of the vehicle.
	 */
	public String getFleetNum() {
		return fleetNum;
	}

	/**
	 * Getter method for the make of the vehicle.
	 * 
	 * @return Returns the make of the vehicle.
	 */
	public String getMake() {
		return make;
	}

	/**
	 * Getter method for the model of the vehicle.
	 * 
	 * @return Returns the model of the vehicle.
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Getter method for the color of the vehicle.
	 * 
	 * @return Returns the color of the vehicle.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Getter method for the state of the vehicle.
	 * 
	 * @return Returns the state that the vehicle is currently in.
	 */
	public RentalState getState() {
		return state;
	}

	/**
	 * Method used to set the state of the vehicle.
	 * 
	 * @param state
	 *            The new state the vehicle is entering.
	 */
	public void setState(RentalState state) {
		this.state = state;
	}

	/**
	 * Returns a string representation of the vehicle to be displayed in the
	 * user interface.
	 * 
	 * @return Returns a string to represent the vehicle in the user interface.
	 */
	public String toString() {
		return fleetNum + ":  " + make + " " + model + " (" + color + ")";
	}

	/**
	 * Hash code for the fleetNum.
	 * 
	 * @return Returns the result of the hashcode.
	 */
	@Override
	public int hashCode() {
		int result = 0;
		result = result + ((fleetNum == null) ? 0 : fleetNum.hashCode());
		return result;
	}

	/**
	 * Method used to compare the fleetNum of two cars.
	 * 
	 * @return Returns true if the two cars' fleet number match.
	 * @param obj
	 *            This is the second car being compared to this car.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (fleetNum == null) {
			if (other.fleetNum != null)
				return false;
		} else if (!fleetNum.equals(other.fleetNum))
			return false;
		return true;
	}

}