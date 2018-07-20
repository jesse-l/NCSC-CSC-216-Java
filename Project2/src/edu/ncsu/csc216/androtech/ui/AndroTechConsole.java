package edu.ncsu.csc216.androtech.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.androtech.model.management.Manager;
import edu.ncsu.csc216.androtech.model.management.ServiceManager;

/**
 * Simple command-line interface for a AndroTech Service Center. (There is no
 * code to correct the placement of the cursor.) The file "devices.txt" can
 * serve as a source of initial data.
 * 
 * @author Jo Perry
 * @author David Wright
 *
 */
public class AndroTechConsole {

	private Manager serviceMgr; // Service RepairCenter backend
	private String menu = // Menu of choices
	"1) Add new device\n2) Remove a device\n3) Assign to service droids\n4) Complete servicing a device\n"
			+ "5) Print the devices waiting for service\n6) Print the service droids\n7) Activate a new service droid\n8) Quit\n"
			+ "\nEnter Selection: ";
	private String filter = ""; // Restricts the waiting list display to
								// vehicles whose owners' names begin with the
								// filter
	private Scanner s = new Scanner(System.in); // Scans user input

	/**
	 * Creates a new console with device wait list drawn from "devices.txt" and
	 * 5 Tech Droids. If "devices.txt" is not available, the patient list is
	 * initially empty
	 */
	public AndroTechConsole() {
		File f = new File("devices.txt");
		try {
			serviceMgr = new ServiceManager(new Scanner(f));
		} catch (FileNotFoundException e) {
			System.err
					.println("ServiceManager constructor: Starting with an empty file.");
			serviceMgr = new ServiceManager();
		}
	}

	/**
	 * Prints the menu of user choices.
	 * 
	 * @return the user's menu choice. Errors result in re-calling printMenu().
	 */
	public int printMenu() {
		System.out.print(menu);
		Scanner s1 = new Scanner(System.in);
		try {
			return s1.nextInt();
		} catch (Exception e) {
			return printMenu();
		}
	}

	/**
	 * Displays the menu and carries out the user's menu choice:<br>
	 * 1. Add new device <br>
	 * 2. Remove a device <br>
	 * 3. Assign to service droids <br>
	 * 4. Complete servicing a device<br>
	 * 5. Print the devices waiting for service <br>
	 * 6. Print the service droids<br>
	 * 7. Activate a new service droid<br>
	 * 8. Quit
	 */
	public void run() {
		switch (printMenu()) {
		case 1: // Admit a new device to the wait list
			System.out.print("\nADD DEVICE\n\nOwner Name: ");
			String name = s.nextLine().trim();
			System.out.print("Serial number: ");
			String serialNum = s.nextLine().trim();
			System.out
					.print("Customer tier (3 = Platinum, 2 = Gold, 1 = Silver, 0 = None): ");
			int tier = s.nextInt();
			System.out.print("Virtual Reality device? <y/n>: ");
			String kind = s.next();
			if (kind.equalsIgnoreCase("y"))
				serviceMgr.putOnWaitingList("V", serialNum, name, tier);
			else
				serviceMgr.putOnWaitingList("C", serialNum, name, tier);
			break;
		case 2: // Remove a device from the wait list. The position is relative
				// to the filter
			System.out
					.print("\nREMOVE\n\nRemove device at location on filtered list: ");
			int psn = s.nextInt();
			serviceMgr.remove(filter, psn);
			break;
		case 3: // Assign devices on the waiting list to available service
				// droids
			System.out.println("\nASSIGN TO SERVICE DROIDS \n\n");
			serviceMgr.assignDroids();
			break;
		case 4: // Release a device from the service droid
			System.out
					.print("\nRELEASE\n\nRelease from droid with numeric index: ");
			psn = s.nextInt();
			serviceMgr.releaseFromService(psn);
			break;
		case 5: // Print the list of vehicles on the waiting list
			System.out
					.print("\nPRINT FILTERED DEVICE LIST\n\nEnter filter (return for no filter): ");
			filter = s.nextLine().trim();
			System.out.println("\n" + serviceMgr.printWaitList(filter));
			break;
		case 6: // Print the contents of the service bays
			System.out.println("\nPRINT SERVICE DROIDS \n\n"
					+ serviceMgr.printDroids());
			break;
		case 7: // Add a new service bay to the garage;
			System.out.println("\nACTIVATE NEW DROID \n\n");
			serviceMgr.addNewDroid();
			break;
		case 8: // Exit the program
			System.out.println("\nEXIT PROGRAM \n\n");
			System.exit(0);
			break;
		default:
			break;
		}
		run();
	}

	/**
	 * Main method to start program execution.
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		System.out
				.println("Use this simple application to do preliminary functional testing on the Service RepairCenter project.\n "
						+ "There is no no error checking.\n"
						+ "The cursor location varies according to the menu selection.\n\n");
		(new AndroTechConsole()).run();
	}

}
