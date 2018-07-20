package edu.ncsu.csc216.carrental.model.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.management.NuxCarRental;

/**
 * This class is used to test the Rented class which handles the rented state.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class RentedTest {
	RentalState r;
	RentalStateManager mgr = new NuxCarRental();
	boolean exceptionCaught;
	Customer c;

	/**
	 * Sets up the variables to be used before each test.
	 * 
	 * @throws Exception
	 *             Throws general exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		c = new Customer("Bob", "Barker", "12-3456");
		
		r = new Rented(c);
		
		//Creates the new car.
		mgr.processNewCar("A1234", "Chevy", "Sonic", "Silver");
		((NuxCarRental) mgr).addCustomer(c);
		
		mgr.processRental();
	}

	/**
	 * This method is used to test the rentCar method.
	 */
	@Test
	public void testRentCar() {
		exceptionCaught = false;
		try {
			r.rentCar(mgr);
		} catch (IllegalStateException e) {
			exceptionCaught = true;
		}

		assertTrue(exceptionCaught);
	}

	/**
	 * This method is used to test the returnCar method.
	 */
	@Test
	public void testReturnCar() {
		assertTrue(((NuxCarRental) mgr).hasRentedCars());
		
		mgr.processReturn(false);
		
		assertTrue(((NuxCarRental) mgr).hasDetailingCars());
	}

	/**
	 * This method is used to test the reportProblem method.
	 */
	@Test
	public void testReportProblem() {
		assertTrue(((NuxCarRental) mgr).hasRentedCars());
		
		mgr.processReturn(true);
		
		assertTrue(((NuxCarRental) mgr).hasRepairingCars());
		
	}

	/**
	 * This method is used to test the detailDone method.
	 */
	@Test
	public void testDetailDone() {
		exceptionCaught = false;
		try {
			r.detailDone(mgr);
		} catch (IllegalStateException e) {
			exceptionCaught = true;
		}

		assertTrue(exceptionCaught);
	}

	/**
	 * This method is used to test the repairDone method.
	 */
	@Test
	public void testRepairDone() {
		exceptionCaught = false;
		try {
			r.repairDone(mgr);
		} catch (IllegalStateException e) {
			exceptionCaught = true;
		}

		assertTrue(exceptionCaught);
	}

	/**
	 * This method is used to test the rentalInfo method.
	 */
	@Test
	public void testRentalInfo() {
		assertEquals("(B Barker)", r.rentalInfo());
	}

	/**
	 * This method is used to test the getCustomer method.
	 */
	@Test
	public void testGetCustomer() {
		assertEquals(c, ((Rented) r).getCustomer());
	}

}
