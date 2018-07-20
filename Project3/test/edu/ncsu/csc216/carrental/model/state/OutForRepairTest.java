package edu.ncsu.csc216.carrental.model.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.management.NuxCarRental;

/**
 * This class is used to test the OutForRepair class which handles the out for
 * repair state.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class OutForRepairTest {
	RentalState repair;
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
		repair = new OutForRepair();

		Customer c = new Customer("Bob", "Bill", "12-3456");
		Car ca = new Car("C1234", "Chevy", "Sonic", "Silver");

		((NuxCarRental) mgr).addCar(ca);
		((NuxCarRental) mgr).addCustomer(c);

		((NuxCarRental) mgr).processRental();

	}

	/**
	 * This method is used to test the rentCar method.
	 */
	@Test
	public void testRentCar() {
		exceptionCaught = false;
		try {
			repair.rentCar(mgr);
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
			repair.returnCar(mgr);
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
			repair.reportProblem(mgr);
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
			repair.detailDone(mgr);
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
		assertTrue(((NuxCarRental) mgr).hasRentedCars());

		mgr.processReturn(true);

		assertTrue(((NuxCarRental) mgr).hasRepairingCars());

		mgr.processRepaired();

		assertTrue(((NuxCarRental) mgr).hasDetailingCars());

	}

	/**
	 * This method is used to test the rentalInfo method.
	 */
	@Test
	public void testRentalInfo() {
		exceptionCaught = false;
		try {
			repair.rentalInfo();
		} catch (IllegalStateException e) {
			exceptionCaught = true;
		}

		assertTrue(exceptionCaught);
	}

}
