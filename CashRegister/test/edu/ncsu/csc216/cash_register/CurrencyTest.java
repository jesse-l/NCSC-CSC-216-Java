package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for Currency 
 * @author jaliddl2
 *
 */
public class CurrencyTest {
	/** Currency object with the value of a penny */
	private Currency penny;
	/** Currency object with the value of a dollar */
	private Currency dollar;
			
	/**
	 * Sets up the initial currency for the test.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		penny = new Currency(CurrencyCollection.PENNY_VALUE, CurrencyCollection.PENNY_NAME, 10);
		dollar = new Currency(CurrencyCollection.ONE_VALUE, CurrencyCollection.ONE_NAME, 10);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		//Create a new penny object that has the same state as our 
	   //field named penny
	   Currency penny2 = new Currency(CurrencyCollection.PENNY_VALUE, CurrencyCollection.PENNY_NAME, 10);
	   
	   Currency quarter = new Currency(CurrencyCollection.QUARTER_VALUE, CurrencyCollection.QUARTER_NAME, 1);
	   
	   //Assert that both of these objects have the same has code.
	   //When using assertTrue, the expected value is true.  The actual
	   //value is the result of the argument.
	   assertTrue(penny.hashCode() == penny2.hashCode());
		
	   //Assert that the penny and dollar objects have different
	   //hashcodes.
	   assertTrue(penny.hashCode() != dollar.hashCode());
	   
	   assertTrue(quarter.hashCode() != dollar.hashCode());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#getValue()}.
	 */
	@Test
	public void testGetValue() {
		//The PENNY_VALUE constant is our expected value
		//The actual value is the call to the getValue() method
		assertEquals(CurrencyCollection.PENNY_VALUE, penny.getValue());
	
		//The ONE_VALUE constant is our expected value
		//The actual value is the call to the getValue method
		assertEquals(CurrencyCollection.ONE_VALUE, dollar.getValue());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#getName()}.
	 */
	@Test
	public void testGetName() {
		//Checks name of penny object
		assertEquals("Penny", penny.getName());
		assertNotEquals("Dime", penny.getName());
		
		//Checks name of dollar object
		assertNotEquals("Dollar", dollar.getName());
		assertEquals("One Dollar", dollar.getName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#getCount()}.
	 */
	@Test
	public void testGetCount() {
		//count is expected to be 10
		//Checks count for penny variable
		assertEquals(10, penny.getCount());
		//Count is not 15
		assertNotEquals(15, penny.getCount());
		
		//count is expected to be 10
		//Checks count for dollar variable
		assertEquals(10, dollar.getCount());
		//Count is not 20
		assertNotEquals(20, dollar.getCount());
		
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#modifyCount(int)}.
	 */
	@Test
	public void testModifyCount() {
		//Ensure we're starting with 10 pennies
		//We can't assume that the getCount() method has been tested at this point
		//so this is our sanity check that we can use the getCount() method to evaluate
		//modifyCount()
		//10 is our expected value and penny.getCount() is our actual value
		assertEquals(10, penny.getCount());
		
		//Increase the count of pennies by 3
		penny.modifyCount(3);
		
		//Check that the count changed
		assertEquals(13, penny.getCount());
		
		//Decrease the count of pennies by 5
		penny.modifyCount(-5);
		
		//Check that the count changed
		assertEquals(8, penny.getCount());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		//Create a new penny object that has the same state as our 
		//field named penny
		Currency penny2 = new Currency(CurrencyCollection.PENNY_VALUE, CurrencyCollection.PENNY_NAME, 10);
				
		//Assert that both of these objects are the same using the 
		//equals method.
		//When using assertTrue, the expected value is true.  The actual
		//value is the result of the argument.
		assertTrue(penny.equals(penny2));
			
		//Assert that the penny and dollar objects are not the same.
		//When using assertFalse, the expected value is false.  The actual
		//value is the result of the argument.
		assertFalse(penny.equals(dollar));
		
		Currency test = new Currency(CurrencyCollection.ONE_VALUE, null, 0);
		assertFalse(dollar.equals(test));
	}

}
