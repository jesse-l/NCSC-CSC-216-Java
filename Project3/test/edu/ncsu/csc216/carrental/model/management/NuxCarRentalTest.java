package edu.ncsu.csc216.carrental.model.management;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;

/**
 * This class test the NuxCarRental class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class NuxCarRentalTest {
	NuxCarRental mgr;
	File f = new File("carList.csv");
	Scanner fileScanner;
	NuxCarRental mgr2;
	Car car1;
	Customer customer1;

	/**
	 * Sets up the variables to be used before each test.
	 * 
	 * @throws Exception
	 *             Throws general exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		mgr = new NuxCarRental();

		fileScanner = new Scanner(f);

		mgr2 = new NuxCarRental(fileScanner);

		customer1 = new Customer("Billy", "Bob", "12-3456");
		car1 = new Car("C1234", "Chevy", "Sonic", "Silver");

		mgr.addCar(car1);
		mgr.addCustomer(customer1);
	}

	/**
	 * This method test the rentCar method.
	 */
	@Test
	public void testRentCar() {
		assertTrue(mgr.hasAvailableCars());
		assertTrue(mgr.hasWaitingCustomers());

		mgr.rentCar();

		assertTrue(mgr.hasRentedCars());
	}

	/**
	 * This method test the returnCar method.
	 */
	@Test
	public void testReturnCar() {
		mgr.rentCar();
		assertTrue(mgr.hasRentedCars());

		mgr.returnCar();

		assertTrue(mgr.hasDetailingCars());
	}

	/**
	 * This method test the reportProblem method.
	 */
	@Test
	public void testReportProblem() {
		mgr.rentCar();
		assertTrue(mgr.hasRentedCars());

		mgr.reportProblem();

		assertTrue(mgr.hasRepairingCars());
	}

	/**
	 * This method test the completeDetailing method.
	 */
	@Test
	public void testCompleteDetailing() {
		mgr.rentCar();
		mgr.returnCar();
		assertTrue(mgr.hasDetailingCars());

		mgr.completeDetailing();

		assertTrue(mgr.hasAvailableCars());
	}

	/**
	 * This method test the completeRepairs method.
	 */
	@Test
	public void testCompleteRepairs() {
		mgr.rentCar();
		mgr.reportProblem();
		assertTrue(mgr.hasRepairingCars());

		mgr.completeRepairs();

		assertTrue(mgr.hasDetailingCars());
	}

	/**
	 * This method test the availableCars method.
	 */
	@Test
	public void testAvailableCars() {
		assertEquals("C1234:  Chevy Sonic (Silver)\n", mgr.availableCars());
	}

	/**
	 * This method test the rentedCars method.
	 */
	@Test
	public void testRentedCars() {
		assertTrue(mgr.hasAvailableCars());

		mgr.rentCar();

		assertEquals("C1234:  Chevy Sonic (Silver) (B Bob)\n", mgr.rentedCars());

	}

	/**
	 * This method test the detailingCars method.
	 */
	@Test
	public void testDetailingCars() {
		mgr.rentCar();
		mgr.returnCar();
		assertTrue(mgr.hasDetailingCars());

		assertEquals("C1234:  Chevy Sonic (Silver)\n", mgr.detailingCars());

		mgr2.hasDetailingCars();

	}

	/**
	 * This method test the repairingCars method.
	 */
	@Test
	public void testRepairingCars() {
		mgr.rentCar();
		mgr.reportProblem();
		assertTrue(mgr.hasRepairingCars());

		assertEquals("C1234:  Chevy Sonic (Silver)\n", mgr.repairingCars());
	}

	/**
	 * This method test the customersWairing method.
	 */
	@Test
	public void testCustomersWaiting() {
		assertTrue(mgr.hasWaitingCustomers());

		mgr.rentCar();

		assertFalse(mgr.hasWaitingCustomers());
	}

	/**
	 * This method test the hasAvailableCars method.
	 */
	@Test
	public void testHasAvailableCars() {
		assertTrue(mgr.hasAvailableCars());

		mgr.rentCar();

		assertFalse(mgr.hasAvailableCars());

		assertTrue(mgr2.hasAvailableCars());
	}

	/**
	 * This method test the hasRentedCars method.
	 */
	@Test
	public void testHasRentedCars() {
		assertFalse(mgr.hasRentedCars());

		mgr.rentCar();

		assertTrue(mgr.hasRentedCars());

		assertTrue(mgr2.hasRentedCars());
	}

	/**
	 * This method test the hasDetailingCars method.
	 */
	@Test
	public void testHasDetailingCars() {
		assertFalse(mgr.hasDetailingCars());

		mgr.rentCar();
		mgr.returnCar();

		assertTrue(mgr.hasDetailingCars());

		assertTrue(mgr2.hasDetailingCars());
	}

	/**
	 * This method test the hasRepairingCars method.
	 */
	@Test
	public void testHasRepairingCars() {
		assertFalse(mgr.hasRepairingCars());

		mgr.rentCar();
		mgr.reportProblem();

		assertTrue(mgr.hasRepairingCars());

		assertTrue(mgr2.hasRepairingCars());
	}

	/**
	 * This method test the hasWaitingCustomers method.
	 */
	@Test
	public void testHasWaitingCustomers() {
		assertTrue(mgr.hasWaitingCustomers());

		mgr.rentCar();

		assertFalse(mgr.hasWaitingCustomers());
	}

	/**
	 * This method test the addCustomer method.
	 */
	@Test
	public void testAddCustomer() {
		Customer c2 = new Customer("Joe", "Brown", "23-4567");

		mgr.rentCar();
		assertTrue(mgr.hasRentedCars());
		mgr.addCustomer(c2);

		assertTrue(mgr.hasWaitingCustomers());
	}

	/**
	 * This method test the addCar method.
	 */
	@Test
	public void testAddCar() {
		Car c2 = new Car("C2345", "Chevy", "Sonic", "Silver");

		// mgr.rentCar();

		mgr.addCar(c2);
		mgr.addCar(car1);

		// assertTrue(mgr.hasAvailableCars());
		assertEquals(
				"C2345:  Chevy Sonic (Silver)\nC1234:  Chevy Sonic (Silver)\n",
				mgr.availableCars());
	}

	/**
	 * This method test the processRental method.
	 */
	@Test
	public void testProcessRental() {
		assertTrue(mgr.hasAvailableCars());
		assertTrue(mgr.hasWaitingCustomers());

		mgr.processRental();

		assertFalse(mgr.hasAvailableCars());
		assertFalse(mgr.hasWaitingCustomers());
		assertTrue(mgr.hasRentedCars());
	}

	/**
	 * This method test the processReturn method.
	 */
	@Test
	public void testProcessReturn() {
		mgr.processRental();
		assertTrue(mgr.hasRentedCars());

		mgr.processReturn(false);

		assertTrue(mgr.hasDetailingCars());

		assertTrue(mgr.hasWaitingCustomers());
		Car c2 = new Car("F2345", "Ford", "Escape", "Blue");
		mgr.addCar(c2);

		assertTrue(mgr.hasAvailableCars());
		mgr.rentCar();
		mgr.processReturn(true);

		assertTrue(mgr.hasRepairingCars());

	}

	/**
	 * This method test the processDetailed method.
	 */
	@Test
	public void testProcessDetailed() {
		mgr.rentCar();
		mgr.returnCar();
		assertTrue(mgr.hasDetailingCars());

		mgr.processDetailed();

		assertTrue(mgr.hasAvailableCars());
	}

	/**
	 * This method test the processRepaired method.
	 */
	@Test
	public void testProcessRepaired() {
		mgr.rentCar();
		mgr.reportProblem();
		assertTrue(mgr.hasRepairingCars());

		mgr.processRepaired();

		assertTrue(mgr.hasDetailingCars());
	}

	/**
	 * This method test the processNewCar method.
	 */
	@Test
	public void testProcessNewCar() {
		mgr.rentCar();
		assertFalse(mgr.hasAvailableCars());

		mgr.processNewCar("E2345", "Test", "Tester", "Gold");

		assertTrue(mgr.hasAvailableCars());
	}

	/**
	 * This method test the writeData method.
	 */
	@Test
	public void testWriteData() {
		assertTrue(mgr.hasAvailableCars());

		try {
			BufferedWriter fw = new BufferedWriter(new FileWriter(
					"TestCase.csv"));

			mgr.writeData(fw);

			f = new File("TestCase.csv");
			fileScanner = new Scanner(f);

			String line;
			if (fileScanner.hasNextLine()) {
				line = fileScanner.nextLine();

				assertEquals("Chevy,Sonic,Silver,C1234,A", line);
			}

			fileScanner.close();

		} catch (IOException e) {
			// Do Nothing.
		}

	}

}
