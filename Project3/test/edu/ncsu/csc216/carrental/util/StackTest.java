package edu.ncsu.csc216.carrental.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;

/**
 * This class is used to test the Stack class and the methods inside it.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class StackTest {
	Stack<Car> carStack;
	Car car1;
	Car car2;

	/**
	 * This method sets up the objects to be used during testing.
	 * 
	 * @throws Exception
	 *             General exception.
	 */
	@Before
	public void setUp() throws Exception {
		carStack = new Stack<Car>();
		car1 = new Car("C1234", "Chevy", "Sonic", "Silver");
		car2 = new Car("F1234", "Ford", "Explorer", "Red");
	}

	/**
	 * This method test the isEmpty method in the stack class.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(carStack.isEmpty());

		carStack.push(car1);

		assertTrue(!carStack.isEmpty());
		
		carStack.push(car2);
		
		assertFalse(carStack.isEmpty());
	}

	/**
	 * This method test the peek method in the stack class.
	 */
	@Test
	public void testPeek() {
		try {
			assertNull(carStack.peek());
		} catch (EmptyStackException e) {
			// Nothing
		}
		carStack.push(car1);

		assertTrue(car1.equals(carStack.peek()));

		carStack.push(car2);

		assertTrue(car2.equals(carStack.peek()));
	}

	/**
	 * This method test the pop method in the stack class.
	 */
	@Test
	public void testPop() {
		carStack.push(car2);
		carStack.push(car1);

		assertTrue(car1.equals(carStack.pop()));

		assertTrue(car2.equals(carStack.pop()));
	}

	/**
	 * This method test the push method in the stack class.
	 */
	@Test
	public void testPush() {
		assertTrue(carStack.isEmpty());

		carStack.push(car1);

		assertTrue(!carStack.isEmpty());
		
		carStack.push(car2);
		
		assertFalse(carStack.isEmpty());
	}

}
