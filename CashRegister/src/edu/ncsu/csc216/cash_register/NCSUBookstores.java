package edu.ncsu.csc216.cash_register;

import java.util.Scanner;

/**
 * User interface for the NCSU Bookstores cash register
 * @author SarahHeckman
 * @author jesse liddle - jaliddl2
 */
public class NCSUBookstores {
	/** User input string for refund */
	private static final String REFUND_STRING = "r";
	/** User input string for purchase */
	private static final String PURCHASE_STRING = "p";
	/** User input string for balance */
	private static final String BALANCE_STRING = "g";
	/** user input string for quitting */
	private static final String QUIT_STRING = "q";

	/** Price of baseball cap */
	private static final int BB_CAP_PRICE = 1357;
	/** Price of t-shirt */
	private static final int TSHIRT_PRICE = 1827;
	/** Price of sweatshirt */
	private static final int SWEATSHIRT_PRICE = 2592;
	/** Price of babydoll t-shirt */
	private static final int BABYDOLL_PRICE = 2150;
	/** Price of pencil */
	private static final int PENCIL_PRICE = 136;
	
	/** User input option for baseball */
	private static final int BB_CAP_OPTION = 1;
	/** User input option for t-shirt */
	private static final int TSHIRT_OPTION = 2;
	/** User input option for sweatshirt */
	private static final int SWEATSHIRT_OPTION = 3;
	/** User input option for babydoll t-shirt */
	private static final int BABYDOLL_OPTION = 4;
	/** User input option for pencil */
	private static final int PENCIL_OPTION = 5;
	/** User input option for back */
	private static final int BACK_OPTION = 6;
	
	/** CashRegister object that contains currency */
	private CashRegister register;
	/** Scanner for processing command line input */
	private Scanner console;
	
	/**
	 * Constructs the NCSU Bookstore cash register
	 * and Scanner
	 */
	public NCSUBookstores() {
		try{
			register = new CashRegister();
			console = new Scanner(System.in);
		} catch (NullPointerException e) {
			System.exit(0);
		}
	}
	
	/** 
	 * Starts the user interface and handles the main menu
	 */
	public void userInterface() {
		System.out.println("Welcome to NCSU Bookstores.");
		
		//Prints main menu
		menu();
		
		//Handles user input
		console = new Scanner(System.in);
		String line = console.next();
		if (line.equalsIgnoreCase(QUIT_STRING)) {
			System.out.println("Goodbye!");
			System.exit(0);
		} else if (line.equalsIgnoreCase(PURCHASE_STRING)) {
			System.out.println();
			handlePurchase();
		} else if (line.equalsIgnoreCase(REFUND_STRING)) {
			System.out.println();
			handleRefund();
		} else if (line.equalsIgnoreCase(BALANCE_STRING)) { 
			System.out.println();
			handleBalance();
		} else {
			//invalid option 
			System.out.println("Invalid option.");
			userInterface();
		}
		console.nextLine(); //throws away the rest of the line
		System.out.println(); //blank line for looks
	}
	
	/**
	 * Handles the get balance menu option
	 */
	public void handleBalance() {
		System.out.printf("Current register balance: $%1$.2f\n", register.getCurrentBalance() / 100.00);
		//Returns user to the interface
		userInterface();
	}
	
	/**
	 * Handles the refund menu option
	 */
	public void handleRefund() {
		int itemMenuNumber = 0; 
		
		while (true) {
			System.out.println("What would you like to return?");
			//Lists the items the customer can return
			itemMenu();
			
			while (!console.hasNextInt()) {
				System.out.println("Invalid option.");
				console.nextLine(); //throw away input
			}
			
			itemMenuNumber = console.nextInt();
			console.nextLine(); //throw away rest of input
			
			break;
		}
			
		try {
			//Processes refund
			switch (itemMenuNumber) {
				case BB_CAP_OPTION:
					printRefund(register.processRefund(BB_CAP_PRICE));
					break;
				case TSHIRT_OPTION:
					printRefund(register.processRefund(TSHIRT_PRICE));
					break;
				case SWEATSHIRT_OPTION:
					printRefund(register.processRefund(SWEATSHIRT_PRICE));
					break;
				case BABYDOLL_OPTION:
					printRefund(register.processRefund(BABYDOLL_PRICE));
					break;
				case PENCIL_OPTION:
					printRefund(register.processRefund(PENCIL_PRICE));
					break;
				case BACK_OPTION:
					userInterface();
					break;
				default:
					break; //should never reach here
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Cannot process refund at this time.");
		}
		//Returns user to the interface
		userInterface();
	}
	
	/**
	 * Prints the refund information
	 * @param c CurrencyCollection to refund to the user
	 */
	public void printRefund(CurrencyCollection c) {
		System.out.println();
		Currency [] currency = c.getCurrencyCollection();
		System.out.println("You are refunded: ");
		for (int i = currency.length - 1; i >= 0; i--) {
			System.out.println(currency[i].getCount() + " " + currency[i].getName());
		}
		System.out.printf("For a total of:  $%1$.2f", c.getBalance() / 100.00);
		System.out.println();
	}
	
	/**
	 * Handles the customer's purchase
	 */
	public void handlePurchase() {
		int itemMenuNumber = 0; 
		
		while (true) {
			System.out.println("What would you like to purchase?");
			//Lists the items the customer can return
			itemMenu();
			
			while (!console.hasNextInt()) {
				System.out.println("Invalid option.");
				console.nextLine(); //throw away input
			}
			
			itemMenuNumber = console.nextInt();
			console.nextLine(); //throw away rest of input
			
			if (itemMenuNumber < BB_CAP_OPTION || itemMenuNumber > BACK_OPTION) {
				System.out.println("Invalid option.");
			} else {
				break;
			}
		}
		
		try {			
			//Gets payment and change
			CurrencyCollection change = null;
			CurrencyCollection payment = null;
			
			//Handles purchase
			switch (itemMenuNumber) {
				case BB_CAP_OPTION:
					payment = processPayment();
					change = register.processPurchase(BB_CAP_PRICE, payment);
					break;
				case TSHIRT_OPTION:
					payment = processPayment();
					change = register.processPurchase(TSHIRT_PRICE, payment);
					break;
				case SWEATSHIRT_OPTION:
					payment = processPayment();
					change = register.processPurchase(SWEATSHIRT_PRICE, payment);
					break;
				case BABYDOLL_OPTION:
					payment = processPayment();
					change = register.processPurchase(BABYDOLL_PRICE, payment);
					break;
				case PENCIL_OPTION:
					payment = processPayment();
					change = register.processPurchase(PENCIL_PRICE, payment);
					break;
				case BACK_OPTION:
					userInterface();
					break;
				default:
					break; //do nothing
			}
			if (change != null) {
				//Prints the change
				printRefund(change);
			}
		} catch (IllegalArgumentException e) {
			//If not enough currency for change
			System.out.println("Unable to make purchase.  Money returned.");
		}
		//Returns user to the interface
		userInterface();
	}
	
	/**
	 * Processes the payment from the customer
	 * @return the bills the customer pays with
	 */
	public CurrencyCollection processPayment() {
		System.out.println();
		
		CurrencyCollection c = new CurrencyCollection();
		//Gets bills the customer pays with
		System.out.println("Enter how many of each bill or coin you will pay with:");
		
		int count = inputPayment("  Twenty Dollar Bills: ");
		c.modifyDenomination(CurrencyCollection.TWENTY_VALUE, count);
		
		count = inputPayment("  Ten Dollar Bills: ");
		c.modifyDenomination(CurrencyCollection.TEN_VALUE, count);
		
		count = inputPayment("  Five Dollar Bills: ");
		c.modifyDenomination(CurrencyCollection.FIVE_VALUE, count);
		
		count = inputPayment("  One Dollar Bills: ");
		c.modifyDenomination(CurrencyCollection.ONE_VALUE, count);
		
		count = inputPayment("  Quarters: ");
		c.modifyDenomination(CurrencyCollection.QUARTER_VALUE, count);
		
		count = inputPayment("  Dimes: ");
		c.modifyDenomination(CurrencyCollection.DIME_VALUE, count);
		
		count = inputPayment("  Nickels: ");
		c.modifyDenomination(CurrencyCollection.NICKEL_VALUE, count);
		
		count = inputPayment("  Pennies: ");
		c.modifyDenomination(CurrencyCollection.PENNY_VALUE, count);
		
		return c;
	}
	
	/**
	 * Handles the customer's input of payment information
	 * @param prompt the prompt to print for the user
	 * @return an int representing the number of bills/coins
	 * the user enters for a payment
	 */
	public int inputPayment(String prompt) {
		int number = -1;
		while (number < 0) {
			System.out.print(prompt);
			if (console.hasNextInt()) {
				number = console.nextInt();
				console.nextLine(); //throw away rest of input
				if (number < 0) {
					System.out.println("Must be a positive number.");
				}
			} else {
				System.out.println("Invalid option.");
				number = -1;
				console.nextLine(); //throw away rest of input
			}
		}
		return number;
	}
	
	/**
	 * Prints the items for sale in the bookstore
	 */
	public void itemMenu() {
		System.out.println("  (1) NC State Baseball Cap - $13.57");
		System.out.println("  (2) NC State T-shirt - $18.27");
		System.out.println("  (3) NC State Sweatshirt - $25.92");
		System.out.println("  (4) NC State Babydoll T-shirt - $21.50");
		System.out.println("  (5) NC State Pencil - $1.36");
		System.out.println("  (6) Back");
		System.out.print("Entry? ");
	}
	
	/**
	 * Prints the main menu
	 */
	public void menu() {
		System.out.println("Please select a command from the list below:");
		System.out.println("  (P)urchase Item");
		System.out.println("  (R)eturn Item");
		System.out.println("  (G)et Register Balance");
		System.out.println("  (Q)uit");
		System.out.print("Entry? ");
	}
	
	/** 
	 * Starts the bookstore program
	 * @param args the command line arguments
	 */
	public static void main(String [] args) {
		NCSUBookstores ui = new NCSUBookstores();
		ui.userInterface();
	}

}
