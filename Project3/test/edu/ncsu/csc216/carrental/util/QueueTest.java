package edu.ncsu.csc216.carrental.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;

/**
 * This class test the Queue class and the methods inside the class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class QueueTest {
	Queue<Car> carQueue;
	Queue<Customer> customerQueue;
	Customer test2;
	Car test1;

	/**
	 * This method sets up the objects to be used during testing.
	 * 
	 * @throws Exception
	 *             General exception.
	 */
	@Before
	public void setUp() throws Exception {
		carQueue = new Queue<Car>();
		customerQueue = new Queue<Customer>();

		test1 = new Car("A1234", "Blah", "Blah", "Blah");
		test2 = new Customer("Bob", "Billy", "12-3456");
	}

	/**
	 * This method test the add method for the queue class. Testing with both
	 * customer and car objects.
	 */
	@Test
	public void testAdd() {

		assertTrue(carQueue.isEmpty());

		carQueue.add(test1);

		assertTrue(!carQueue.isEmpty());

		assertTrue(customerQueue.isEmpty());

		customerQueue.add(test2);

		assertTrue(!customerQueue.isEmpty());
	}

	/**
	 * This method test the remove method for the queue class.
	 */
	@Test
	public void testRemove() {
		carQueue.add(test1);

		Car removed = carQueue.remove();

		assertTrue(removed.equals(test1));

		customerQueue.add(test2);

		Customer removedCus = customerQueue.remove();

		assertTrue(removedCus.equals(test2));
	}

	/**
	 * This method test the peek method in the queue class.
	 */
	@Test
	public void testPeek() {
		try {
			carQueue.peek();
		} catch (NoSuchElementException e) {
			// Nothing
		}

		carQueue.add(test1);
		Car look = carQueue.peek();
		assertTrue(look.equals(test1));

		Car look2 = new Car("C1234", "Chevy", "Sonic", "Silver");

		carQueue.add(look2);

		assertEquals(look, carQueue.peek());
	}

	/**
	 * This method test the isEmpty method in the queue class.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(carQueue.isEmpty());
		assertTrue(customerQueue.isEmpty());

		carQueue.add(test1);

		assertTrue(!carQueue.isEmpty());

		customerQueue.add(test2);

		assertTrue(!customerQueue.isEmpty());

	}

}
