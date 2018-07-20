package edu.ncsu.csc216.carrental.model.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.management.NuxCarRental;

/**
 * This class test the Available class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class AvailableTest {
	RentalState a;
	RentalStateManager mgr = new NuxCarRental();
	boolean exceptionCaught;

	/**
	 * Sets up the variables to be used before each test.
	 * 
	 * @throws Exception
	 *             Throws general exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		a = new Available();
		
		Customer c = new Customer("Billy", "Bob", "12-3456");
		Car ca = new Car("C1234", "Chevy", "Sonic", "Silver");
		
		((NuxCarRental) mgr).addCar(ca);
		((NuxCarRental) mgr).addCustomer(c);
		
	}

	/**
	 * This method is used to test the rentCar method.
	 */
	@Test
	public void testRentCar() {
		assertTrue(((NuxCarRental)mgr).hasAvailableCars());
		assertTrue(((NuxCarRental)mgr).hasWaitingCustomers());
		
		mgr.processRental();
	}

	/**
	 * This method is used to test the returnCar method.
	 */
	@Test
	public void testReturnCar() {
		exceptionCaught = false;
		try {
			a.returnCar(mgr);
		} catch (IllegalStateException e) {
			exceptionCaught = true;
		}

		assertTrue(exceptionCaught);
	}

	/**
	 * This method is used to test the reportProblem method.
	 */
	@Test
	public void testReportProblem() {
		exceptionCaught = false;
		try {
			a.reportProblem(mgr);
		} catch (IllegalStateException e) {
			exceptionCaught = true;
		}

		assertTrue(exceptionCaught);
	}

	/**
	 * This method is used to test the detailDone method.
	 */
	@Test
	public void testDetailDone() {
		exceptionCaught = false;
		try {
			a.detailDone(mgr);
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
			a.repairDone(mgr);
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
		exceptionCaught = false;
		try {
			a.rentalInfo();
		} catch (IllegalStateException e) {
			exceptionCaught = true;
		}

		assertTrue(exceptionCaught);
	}

}
