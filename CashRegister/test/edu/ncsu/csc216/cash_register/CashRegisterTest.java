/**
 * 
 */
package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for CashRegister
 * @author jaliddl2
 *
 */
public class CashRegisterTest {
	private CashRegister cash1;
	
	/**
	 * Sets up the initial CashRegister for testing.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cash1 = new CashRegister();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CashRegister#getCurrentBalance()}.
	 */
	@Test
	public void testGetCurrentBalance() {
		assertEquals(36410, cash1.getCurrentBalance());
		cash1.processRefund(1500);
		assertEquals(34910, cash1.getCurrentBalance());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CashRegister#processPurchase(int, edu.ncsu.csc216.cash_register.CurrencyCollection)}.
	 */
	@Test
	public void testProcessPurchase() {
		CurrencyCollection payment = new CurrencyCollection(0);
		payment.modifyDenomination(CurrencyCollection.FIVE_VALUE, 3);
		assertEquals(1500, payment.getBalance());
		
		CurrencyCollection newCash1 = new CurrencyCollection(10);
		newCash1.modifyDenomination(CurrencyCollection.QUARTER_VALUE, 3);
		newCash1.modifyDenomination(CurrencyCollection.ONE_VALUE, 3);
		newCash1.modifyDenomination(CurrencyCollection.TEN_VALUE, 1);
		cash1.processPurchase(1375, payment);
		
		assertEquals(newCash1.getBalance(), cash1.getCurrentBalance());
		
		cash1.processPurchase(1025, payment);
		newCash1.modifyDenomination(CurrencyCollection.QUARTER_VALUE, 1);
		newCash1.modifyDenomination(CurrencyCollection.TEN_VALUE, 1);
		
		assertEquals(newCash1.getBalance(), cash1.getCurrentBalance());
				
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CashRegister#processRefund(int)}.
	 */
	@Test
	public void testProcessRefund() {
		cash1.processRefund(500);
		assertEquals(35910, cash1.getCurrentBalance());
		
		cash1.processRefund(1000);
		assertEquals(34910, cash1.getCurrentBalance());
		
	}

}
