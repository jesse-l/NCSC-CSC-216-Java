package edu.ncsu.csc216.carrental.model.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.management.NuxCarRental;

/**
 * This class is used to test the OutForDetail class which handles the out for
 * detailing state.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class OutForDetailTest {
	RentalState d;
	boolean exceptionCaught;
	RentalStateManager mgr = new NuxCarRental();

	/**
	 * Sets up the variables to be used before each test.
	 * 
	 * @throws Exception
	 *             Throws general exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		d = new OutForDetail();

		Customer c = new Customer("Billy", "Bob", "12-3456");
		Car ca = new Car("C1234", "Chevy", "Sonic", "Silver");

		((NuxCarRental) mgr).addCar(ca);
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
			d.rentCar(mgr);
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
		exceptionCaught = false;
		try {
			d.returnCar(mgr);
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
			d.reportProblem(mgr);
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
		assertTrue(((NuxCarRental) mgr).hasRentedCars());

		mgr.processReturn(false);

		assertTrue(((NuxCarRental) mgr).hasDetailingCars());

		mgr.processDetailed();

		assertTrue(((NuxCarRental) mgr).hasAvailableCars());
	}

	/**
	 * This method is used to test the repairDone method.
	 */
	@Test
	public void testRepairDone() {
		exceptionCaught = false;
		try {
			d.repairDone(mgr);
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
			d.rentalInfo();
		} catch (IllegalStateException e) {
			exceptionCaught = true;
		}

		assertTrue(exceptionCaught);
	}

}
