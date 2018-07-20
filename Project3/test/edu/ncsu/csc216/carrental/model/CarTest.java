package edu.ncsu.csc216.carrental.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.state.Available;
import edu.ncsu.csc216.carrental.model.state.OutForDetail;
import edu.ncsu.csc216.carrental.model.state.OutForRepair;
import edu.ncsu.csc216.carrental.model.state.Rented;

/**
 * This class is used to test the Car class which is used to create the car
 * objects.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class CarTest {
	private Car c1;
	private Car c2;

	/**
	 * Sets up the variables to be used before each test.
	 * 
	 * @throws Exception
	 *             Throws general exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		c1 = new Car("F0186", "Ford", "Explorer", "Red");
		c2 = new Car("D1461", "Dodge", "Colt", "Silver");

	}
	
	/**
	 * This method is used to test the InvalidIDException.
	 */
	@Test
	public void testInvailIDException() {
		boolean exceptionCaught = false;
		@SuppressWarnings("unused")
		Car c;
		
		try {
			c = new Car("AA234", "Ford", "Car", "Black");
		} catch(InvalidIDException e) {
			exceptionCaught = true;
		}
		
		assertTrue(exceptionCaught);
		
		try {
			c = new Car("12345", "Chevy", "Cruze", "White");
		} catch(InvalidIDException e) {
			exceptionCaught = true;
		}
		
		assertTrue(exceptionCaught);
		
		
	}

	/**
	 * This method is used to test the getFleetNum method.
	 */
	@Test
	public void testGetFleetNum() {
		assertEquals("F0186", c1.getFleetNum());
		assertEquals("D1461", c2.getFleetNum());
	}

	/**
	 * This method is used to test the getMake method.
	 */
	@Test
	public void testGetMake() {
		assertEquals("Ford", c1.getMake());
		assertEquals("Dodge", c2.getMake());
	}

	/**
	 * This method is used to test the getModel method.
	 */
	@Test
	public void testGetModel() {
		assertEquals("Explorer", c1.getModel());
		assertEquals("Colt", c2.getModel());
	}

	/**
	 * This method is used to test the getColor method.
	 */
	@Test
	public void testGetColor() {
		assertEquals("Red", c1.getColor());
		assertEquals("Silver", c2.getColor());
	}

	/**
	 * This method is used to test the getState method.
	 */
	@Test
	public void testGetState() {
		c1.setState(new Available());

		assertTrue(c1.getState() instanceof Available);

		c2.setState(new OutForRepair());

		assertTrue(c2.getState() instanceof OutForRepair);
	}

	/**
	 * This method is used to test the setState method.
	 */
	@Test
	public void testSetState() {
		c1.setState(new Rented(new Customer("Bob", "Barker", "12-3456")));

		assertTrue(c1.getState() instanceof Rented);

		c2.setState(new OutForDetail());

		assertTrue(c2.getState() instanceof OutForDetail);
	}

	/**
	 * This method is used to test the toString method.
	 */
	@Test
	public void testToString() {
		assertEquals("F0186:  Ford Explorer (Red)", c1.toString());
	}
	
	/**
	 * This method is used to test the equals method.
	 */
	@Test
	public void testEquals() {
		assertFalse(c1.equals(c2));
		
		assertTrue(c1.equals(c1));
		
		assertTrue(c2.equals(c2));
	}
	
	/**
	 * This method is used to test the hashCode method.
	 */
	@Test
	public void testHashCode() {
		assertEquals(c1.getFleetNum().hashCode(), c1.hashCode());
		
		assertNotEquals(c2.getFleetNum().hashCode(), c1.hashCode());
	}

}
