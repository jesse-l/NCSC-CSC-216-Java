package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for CurrencyCollection 
 * @author jaliddl2
 *
 */
public class CurrencyCollectionTest {
	/** CurrencyCollection object with 10$ with 1$ bills*/
	private CurrencyCollection c1;
	/** CurrencyCollection object with 20$ with 5$ bills*/
	private CurrencyCollection c2;
	
	/**
	 * Sets up the initial CurrencyCollections for this testing.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Sets up c1 as having 10 1s
		c1 = new CurrencyCollection(0);
		c1.modifyDenomination(CurrencyCollection.ONE_VALUE, 10);
		
		//Sets up c2 as having 4 5s
		c2 = new CurrencyCollection(0);
		c2.modifyDenomination(CurrencyCollection.FIVE_VALUE, 4);
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getCurrencyAtIdx(int)}.
	 */
	@Test
	public void testGetCurrencyAtIdx() {
		//test c1 for having 10 1$ bills
		assertEquals(new Currency(CurrencyCollection.ONE_VALUE, CurrencyCollection.ONE_NAME, 10), c1.getCurrencyAtIdx(4));
		//test c2 for having 4 5$ bills
		assertEquals(new Currency(CurrencyCollection.FIVE_VALUE, CurrencyCollection.FIVE_NAME, 4), c2.getCurrencyAtIdx(5));
		//Test c1 to see if there are any 5s 
		assertNotEquals(new Currency(CurrencyCollection.FIVE_VALUE, CurrencyCollection.FIVE_NAME, 2), c1.getCurrencyAtIdx(5));
		//Test c2 to see if there are any 1s
		assertNotEquals(new Currency(CurrencyCollection.ONE_VALUE, CurrencyCollection.ONE_NAME, 1), c2.getCurrencyAtIdx(4));
		boolean isCaught = false;
		try{
			c1.getCurrencyAtIdx(10);
		} catch(IndexOutOfBoundsException e) {
			isCaught = true;
		}
		
		assertTrue(isCaught);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#modifyDenomination(int, int)}.
	 */
	@Test
	public void testModifyDenomination() {
		
		c1.modifyDenomination(CurrencyCollection.FIVE_VALUE, 3);
		assertEquals(2500, c1.getBalance());
		
		boolean isCaught = false;
		try{
			c1.modifyDenomination(100000, 5);
		} catch(IllegalArgumentException e) {
			isCaught = true;
		}
		assertTrue(isCaught);
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#depositCurrencyCollection(edu.ncsu.csc216.cash_register.CurrencyCollection)}.
	 */
	@Test
	public void testDepositCurrencyCollection() {
		CurrencyCollection test = new CurrencyCollection(0);
		test.modifyDenomination(CurrencyCollection.TEN_VALUE, 2);
		test.modifyDenomination(CurrencyCollection.TWENTY_VALUE, 1);
		
		c1.depositCurrencyCollection(test);
		assertEquals(5000, c1.getBalance());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#refundByAmount(int)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testRefundByAmount() {
		/** Creates a new CurrencyCollection to compare to c1 */
		CurrencyCollection testC1 = new CurrencyCollection(0);
		/** Sets testC1 to having 5 1$ */
		testC1.modifyDenomination(CurrencyCollection.ONE_VALUE, 5);
		c1.refundByAmount(CurrencyCollection.FIVE_VALUE);
		assertEquals(testC1.getCurrencyCollection(), c1.getCurrencyCollection());
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getCurrencyCollection()}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetCurrencyCollection() {
		Currency[] testC1 = new Currency[CurrencyCollection.NUM_SLOTS];
		testC1[0] = new Currency(CurrencyCollection.PENNY_VALUE, CurrencyCollection.PENNY_NAME, 0);
		testC1[1] = new Currency(CurrencyCollection.NICKEL_VALUE, CurrencyCollection.NICKEL_NAME, 0);
		testC1[2] = new Currency(CurrencyCollection.DIME_VALUE, CurrencyCollection.DIME_NAME, 0);
		testC1[3] = new Currency(CurrencyCollection.QUARTER_VALUE, CurrencyCollection.QUARTER_NAME, 0);
		testC1[4] = new Currency(CurrencyCollection.ONE_VALUE, CurrencyCollection.ONE_NAME, 10);
		testC1[5] = new Currency(CurrencyCollection.FIVE_VALUE, CurrencyCollection.FIVE_NAME, 0);
		testC1[6] = new Currency(CurrencyCollection.TEN_VALUE, CurrencyCollection.TEN_NAME, 0);
		testC1[7] = new Currency(CurrencyCollection.TWENTY_VALUE, CurrencyCollection.TWENTY_NAME, 0);
		assertEquals(testC1, c1.getCurrencyCollection());
		
		Currency[] testC2 = new Currency[CurrencyCollection.NUM_SLOTS];
		testC2[0] = new Currency(CurrencyCollection.PENNY_VALUE, CurrencyCollection.PENNY_NAME, 0);
		testC2[1] = new Currency(CurrencyCollection.NICKEL_VALUE, CurrencyCollection.NICKEL_NAME, 0);
		testC2[2] = new Currency(CurrencyCollection.DIME_VALUE, CurrencyCollection.DIME_NAME, 0);
		testC2[3] = new Currency(CurrencyCollection.QUARTER_VALUE, CurrencyCollection.QUARTER_NAME, 0);
		testC2[4] = new Currency(CurrencyCollection.ONE_VALUE, CurrencyCollection.ONE_NAME, 0);
		testC2[5] = new Currency(CurrencyCollection.FIVE_VALUE, CurrencyCollection.FIVE_NAME, 4);
		testC2[6] = new Currency(CurrencyCollection.TEN_VALUE, CurrencyCollection.TEN_NAME, 0);
		testC2[7] = new Currency(CurrencyCollection.TWENTY_VALUE, CurrencyCollection.TWENTY_NAME, 0);
		assertEquals(testC2, c2.getCurrencyCollection());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getBalance()}.
	 */
	@Test
	public void testGetBalance() {
		//Checks for the 10$
		assertEquals(1000, c1.getBalance());
		//Checks for the 20$
		assertEquals(2000, c2.getBalance());
	}

}
