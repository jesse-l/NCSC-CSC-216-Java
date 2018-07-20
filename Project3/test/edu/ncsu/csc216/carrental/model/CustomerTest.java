package edu.ncsu.csc216.carrental.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is used to test the Customer class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class CustomerTest {
	private Customer c1;
	private Customer c2;

	/**
	 * Sets up the variables to be used before each test.
	 * 
	 * @throws Exception
	 *             Throws general exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		c1 = new Customer("Han", "Solo", "14-0399");
		c2 = new Customer("Jane", "Doe", "03-9300");
	}

	/**
	 * This method test some extra names that will and will not throw
	 * exceptions.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testNames() {
		boolean catcher = false;

		try {
			Customer c3 = new Customer("ham2", "Jones", "12-0032");
		} catch (IllegalArgumentException e) {
			catcher = true;
		}

		assertTrue(catcher);
		catcher = false;

		try {
			Customer c4 = new Customer("K'bob", "Jones", "12-0012");
		} catch (IllegalArgumentException e) {
			catcher = true;
		}

		assertFalse(catcher);
		catcher = false;

		try {
			Customer c5 = new Customer("ham-bo", "Jones", "12-0132");
			assertEquals("ham-bo", c5.getFirstName());
		} catch (IllegalArgumentException e) {
			catcher = true;
		}

		assertFalse(catcher);
		catcher = false;
		
		try{
			Customer c6 = new Customer("James", "Bond", "007");
		} catch(InvalidIDException e)  {
			catcher = true;
		}
		
		assertTrue(catcher);
	}

	/**
	 * Test the getFirstName method for the customer class.
	 */
	@Test
	public void testGetFirstName() {
		assertEquals("Han", c1.getFirstName());
		assertEquals("Jane", c2.getFirstName());
	}

	/**
	 * Test the getLastName method for the customer class.
	 */
	@Test
	public void testGetLastName() {
		assertEquals("Solo", c1.getLastName());
		assertEquals("Doe", c2.getLastName());
	}

	/**
	 * Test the getId method for the customer class.
	 */
	@Test
	public void testGetId() {
		assertEquals("14-0399", c1.getId());
		assertEquals("03-9300", c2.getId());
	}

	/**
	 * Test the toString method for the customer class.
	 */
	@Test
	public void testToString() {
		assertEquals("14-0399:  Han Solo", c1.toString());
		assertEquals("03-9300:  Jane Doe", c2.toString());
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
		assertEquals(c1.getId().hashCode(), c1.hashCode());
		
		assertNotEquals(c2.getId().hashCode(), c1.hashCode());
	}
}
