package edu.ncsu.csc216.cash_register;

/**
 * A cash register drawer that holds a collection of currency.  
 * The currency are bills and coins.  The cash register
 * knows what collection of currency is in the drawer and 
 * it's balance.
 * 
 * @author SarahHeckman
 * @author jesse liddle - jaliddl2
 */
public class CashRegister {
	
	/**
	 * Each day the cash register drawer starts with 10 
	 * items of each bill or coin
	 */
	private static final int INITIAL_COUNT = 10;
	/**
	 * The collection of currency in the drawer
	 */
	private CurrencyCollection cashDrawer;
	
	/**
	 * Creates a cash register drawer for the start of the day
	 */
	public CashRegister() {
		//Initializes the count of each piece of currency to 
		//the given constant.
		cashDrawer = new CurrencyCollection(INITIAL_COUNT);
	}
	
	/**
	 * Returns the balance of the cash register.  Since we are dealing
	 * with coins, we are representing our money as an int.  To see the 
	 * double value of the amount of money, divide by 100.00.
	 * @return the balance of the cash register
	 */
	public int getCurrentBalance() {
		return cashDrawer.getBalance();
	}
	
	/**
	 * Handles a customer purchase from the cash register
	 * @param purchaseAmount amount of item(s) customer is purchasing
	 * @param payment how much the customer has payed as a collection
	 * of the currency given to the cashier
	 * @return a collection of currency that is the change (if any)
	 * @throws IllegalArgumentException if the customer has not paid enough
	 * to purchase the item(s)
	 */
	public CurrencyCollection processPurchase(int purchaseAmount, CurrencyCollection payment) {
		int paymentAmount = payment.getBalance();
		int refundAmount = paymentAmount - purchaseAmount;
		
		if(refundAmount >= 0) {
			cashDrawer.depositCurrencyCollection(payment);		
		}

		return cashDrawer.refundByAmount(refundAmount);
	}
	
	/**
	 * Returns a collection of currency representing the refund
	 * @param refundAmount amount refunded to the customer
	 * @return a collection of currency representing the refund
	 */
	public CurrencyCollection processRefund(int refundAmount) {
		return cashDrawer.refundByAmount(refundAmount);
	}

}
