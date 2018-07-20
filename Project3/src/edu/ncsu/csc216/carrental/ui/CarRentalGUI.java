package edu.ncsu.csc216.carrental.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.management.NuxCarRental;
import edu.ncsu.csc216.carrental.model.management.RentalLocation;

/**
 * The user interface for the program. This is the class that houses the main
 * method used to run the CarRental program.
 * 
 * @author Jesse Liddle - jaliddl2
 */
@SuppressWarnings("serial")
public class CarRentalGUI extends JFrame implements ActionListener,
		WindowListener {
	// Sets up the main panel for the GUI.
	private JPanel contentPane;

	// The top half of the GUI
	private JPanel listsPanel = new JPanel();

	private JTextPane customersListText = new JTextPane();
	private JTextPane availableCarsText = new JTextPane();
	private JTextPane rentedCarsText = new JTextPane();
	private JTextPane detailShopText = new JTextPane();
	private JTextPane repairShopText = new JTextPane();

	// The bottom half of the GUI
	private JPanel buttonsPanel = new JPanel();
	private JButton addCustomer = new JButton("Add Customer");
	private JButton addCar = new JButton("Add New Car");
	private JButton rentCar = new JButton("Rent Car");
	private JButton returnCar = new JButton("Customer Return");
	private JButton finishDetailing = new JButton("Detailing Complete");
	private JButton reportProblem = new JButton("Problem Reported");
	private JButton finishRepairs = new JButton("Repairs Completed");

	// Elements for the list of cars and customers.
	private DefaultListModel<String> dlmWaitingCustomers = new DefaultListModel<String>();
	private DefaultListModel<String> dlmAvailableCars = new DefaultListModel<String>();
	private DefaultListModel<String> dlmRentedCars = new DefaultListModel<String>();
	private DefaultListModel<String> dlmDetailShop = new DefaultListModel<String>();
	private DefaultListModel<String> dlmRepairShop = new DefaultListModel<String>();

	private JList<String> lstWaitingCustomers = new JList<String>(
			dlmWaitingCustomers);
	private JList<String> lstAvailableCars = new JList<String>(dlmAvailableCars);
	private JList<String> lstRentedCars = new JList<String>(dlmRentedCars);
	private JList<String> lstDetailShop = new JList<String>(dlmDetailShop);
	private JList<String> lstRepairShop = new JList<String>(dlmRepairShop);

	private JScrollPane customersList = new JScrollPane(lstWaitingCustomers);
	private JScrollPane availableCarsList = new JScrollPane(lstAvailableCars);
	private JScrollPane rentedCarList = new JScrollPane(lstRentedCars);
	private JScrollPane detailShopList = new JScrollPane(lstDetailShop);
	private JScrollPane repairShopList = new JScrollPane(lstRepairShop);

	// Elements for the closing dialog.
	private JFrame closing = new JFrame("Saving File");
	private JPanel pnlClosing = new JPanel();
	private JPanel pnlClosingBtns = new JPanel();
	private JLabel closingTxt = new JLabel(
			"Enter a file name to save the locations inventor as:");
	private JTextField closingInput = new JTextField();
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");

	/** This is the NuxCarRental. */
	private RentalLocation carRental;

	/**
	 * Constructor for a car rental system. This constructor does not accept a
	 * parameter.
	 */
	public CarRentalGUI() {
		carRental = new NuxCarRental();
		setUpGUI();
	}

	/**
	 * Constructor for a car rental system. This constructor calls for a string
	 * to be passed to it. This string is a file name for a car list.
	 * 
	 * @param fileName
	 *            This is a string for a file name of cars.
	 */
	public CarRentalGUI(String fileName) {
		File f = new File(fileName);

		if (f.exists()) {
			try {
				Scanner fileScanner = new Scanner(f);

				carRental = new NuxCarRental(fileScanner);

				setUpGUI();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * The main method for the program to be run from.
	 * 
	 * @param args
	 *            A String array of arguments.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			JFileChooser fc = new JFileChooser();
			int returnValue = fc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();

				if (f.exists())
					new CarRentalGUI(f.getName());
			} else
				new CarRentalGUI();
		} else {
			StringBuilder builder = new StringBuilder();
			int counter = 0;
			for (String s : args) {
				if (counter > 0)
					builder.append(' ');
				builder.append(s);
				counter++;
			}

			new CarRentalGUI(builder.toString().trim());
		}

	}

	/**
	 * This class adds all the action listeners to the buttons in the gui.
	 */
	private void addListeners() {
		addCustomer.addActionListener(this);
		addCar.addActionListener(this);
		rentCar.addActionListener(this);
		returnCar.addActionListener(this);
		finishDetailing.addActionListener(this);
		finishRepairs.addActionListener(this);
		reportProblem.addActionListener(this);
		btnCancel.addActionListener(this);
		btnSave.addActionListener(this);
	}

	/**
	 * Checks the to see which button was clicked and then acts accordingly.
	 * 
	 * @param event
	 *            The event that occured for this method to be called.
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(addCar)) {
			AddCarDialog addingCar = new AddCarDialog(this);
			addingCar.setVisible(true);
			Car car = addingCar.getNewCar();
			if (car != null) {
				carRental.addCar(car);
			}

		} else if (event.getSource() == addCustomer) {
			AddCustomerDialog customerAdding = new AddCustomerDialog(this);

			customerAdding.setVisible(true);

			Customer customer = customerAdding.getNewCustomer();

			if (customer != null) {
				carRental.addCustomer(customer);
			}
		} else if (event.getSource() == rentCar) {

			// Rents a car to the top customer.
			carRental.rentCar();
		} else if (event.getSource() == returnCar) {

			// Returns a car without problems.
			carRental.returnCar();
		} else if (event.getSource() == finishDetailing) {

			// Completes the detailing of a vehicle.
			carRental.completeDetailing();
		} else if (event.getSource() == reportProblem) {

			// Reports a problem with the vehicle on return.
			carRental.reportProblem();
		} else if (event.getSource() == finishRepairs) {

			// Completes the repair of a vehicle.
			carRental.completeRepairs();
		} else if (event.getSource() == btnCancel) {

			setVisible(false);
			dispose();

			closing.setVisible(false);
			closing.dispose();

			System.exit(0);
		} else if (event.getSource() == btnSave) {

			String fn = closingInput.getText();

			try {
				FileWriter fw = new FileWriter(fn);
				carRental.writeData(fw);
			} catch (IOException e) {
				e.printStackTrace();
			}

			setVisible(false);
			dispose();

			closing.setVisible(false);
			closing.dispose();

			System.exit(0);
		}

		// Refreshes the list displayed
		loadModel(lstWaitingCustomers, dlmWaitingCustomers, carRental
				.customersWaiting().toString());
		loadModel(lstAvailableCars, dlmAvailableCars, carRental.availableCars());
		loadModel(lstRentedCars, dlmRentedCars, carRental.rentedCars());
		loadModel(lstDetailShop, dlmDetailShop, carRental.detailingCars());
		loadModel(lstRepairShop, dlmRepairShop, carRental.repairingCars());

		// Checks which buttons should be enabled.
		checkBtns();
	}

	/**
	 * This method checks to see if anything is in the list and sets the buttons
	 * accordingly.
	 */
	private void checkBtns() {
		if (carRental.hasAvailableCars() && carRental.hasWaitingCustomers())
			rentCar.setEnabled(true);
		else
			rentCar.setEnabled(false);

		if (carRental.hasDetailingCars())
			finishDetailing.setEnabled(true);
		else
			finishDetailing.setEnabled(false);

		if (carRental.hasRentedCars()) {
			reportProblem.setEnabled(true);
			returnCar.setEnabled(true);
		} else {
			reportProblem.setEnabled(false);
			returnCar.setEnabled(false);
		}

		if (carRental.hasRepairingCars())
			finishRepairs.setEnabled(true);
		else
			finishRepairs.setEnabled(false);
	}

	/**
	 * Loads a list model from a string using newline tokenizers.
	 * 
	 * @param list
	 *            the JList to refresh
	 * @param listModel
	 *            the default list model associated with j
	 * @param info
	 *            the String whose tokens initialize the default list model
	 */
	private void loadModel(JList<String> list,
			DefaultListModel<String> listModel, String info) {
		listModel.clear();
		if (info == null)
			return;
		StringTokenizer st = new StringTokenizer(info, "\n");
		while (st.hasMoreTokens()) {
			listModel.addElement(st.nextToken());
		}
		list.ensureIndexIsVisible(0);
	}

	/**
	 * This method is used to set up the closing dialog box.
	 */
	private void setUpClosing() {
		closing.setMinimumSize(new Dimension(500, 150));
		closing.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pnlClosing.setLayout(new GridLayout(2, 2));
		pnlClosing.add(closingTxt);
		pnlClosing.add(closingInput);
		pnlClosingBtns.add(btnSave);
		pnlClosingBtns.add(btnCancel);
	}

	/**
	 * Sets up the GUI to the rental management program.
	 */
	private void setUpGUI() {
		setResizable(false);
		setTitle("AndroTech Rental Car Management System");
		setBounds(100, 100, 1200, 350);
		addWindowListener(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setBounds(100, 100, 1200, 350);

		contentPane.add(listsPanel, BorderLayout.CENTER);

		customersList.setBorder(BorderFactory.createLineBorder(Color.black));
		availableCarsList
				.setBorder(BorderFactory.createLineBorder(Color.black));
		rentedCarList.setBorder(BorderFactory.createLineBorder(Color.black));
		detailShopList.setBorder(BorderFactory.createLineBorder(Color.black));
		repairShopList.setBorder(BorderFactory.createLineBorder(Color.black));

		lstAvailableCars.setFont(new Font("monospaced", Font.PLAIN, 12));
		lstRentedCars.setFont(new Font("monospaced", Font.PLAIN, 12));
		lstDetailShop.setFont(new Font("monospaced", Font.PLAIN, 12));
		lstRepairShop.setFont(new Font("monospaced", Font.PLAIN, 12));
		lstWaitingCustomers.setFont(new Font("monospaced", Font.PLAIN, 12));

		customersList
				.setToolTipText("The list of customers that are waiting to rent a car.");
		customersList.setViewportBorder(new TitledBorder(null, "Customers",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		availableCarsList
				.setToolTipText("The list of cars that are currently waiting to be rented.");
		availableCarsList.setViewportBorder(new TitledBorder(null,
				"Available Cars", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));

		rentedCarList
				.setToolTipText("The list of cars that are currently rented.");
		rentedCarList.setViewportBorder(new TitledBorder(null, "Rented Cars",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		detailShopList
				.setToolTipText("The list of cars that are currently in the detail shop.");
		detailShopList.setViewportBorder(new TitledBorder(null,
				"Cars in Detail Shop", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));

		repairShopList
				.setToolTipText("The list of cars that are currently in the repair shop.");
		repairShopList.setViewportBorder(new TitledBorder(null,
				"Cars out for Repair", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));

		listsPanel.setLayout(new GridLayout(1, 5, 10, 0));

		customersListText.setEditable(false);
		// customersList.setViewportView(customersListText);
		listsPanel.add(customersList, BorderLayout.CENTER);

		availableCarsText.setEditable(false);
		// availableCarsList.setViewportView(availableCarsText);
		listsPanel.add(availableCarsList, BorderLayout.CENTER);

		rentedCarsText.setEditable(false);
		// rentedCarList.setViewportView(rentedCarsText);
		listsPanel.add(rentedCarList, BorderLayout.CENTER);

		detailShopText.setEditable(false);
		// detailShopList.setViewportView(detailShopText);
		listsPanel.add(detailShopList, BorderLayout.CENTER);

		repairShopText.setEditable(false);
		// repairShopList.setViewportView(repairShopText);
		listsPanel.add(repairShopList, BorderLayout.CENTER);

		lstWaitingCustomers.setFont(new Font("monospaced", Font.PLAIN, 12));
		lstAvailableCars.setFont(new Font("monospaced", Font.PLAIN, 12));
		lstRentedCars.setFont(new Font("monospaced", Font.PLAIN, 12));
		lstDetailShop.setFont(new Font("monospaced", Font.PLAIN, 12));
		lstRepairShop.setFont(new Font("monospaced", Font.PLAIN, 12));

		// Sets up the bottom half of the GUI
		FlowLayout flowLayout = (FlowLayout) buttonsPanel.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setVgap(10);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);

		// Adds the buttons
		addCustomer
				.setToolTipText("Adds  a new customer to the end of the list of customers waiting to rent a car.");
		buttonsPanel.add(addCustomer);

		addCar.setToolTipText("Adds a new car to the list of available cars to be rented.");
		buttonsPanel.add(addCar);

		rentCar.setToolTipText("Rents the car on top of the list of available cars to the customer on top of the customer list.");
		buttonsPanel.add(rentCar);

		returnCar
				.setToolTipText("Return the first car on the rented cars list.");
		buttonsPanel.add(returnCar);

		finishDetailing
				.setToolTipText("Finishes the detailing of the car on top of the Cars in Detail Shop list.");
		buttonsPanel.add(finishDetailing);

		reportProblem
				.setToolTipText("Customer reported a problem with the car being returned.");
		buttonsPanel.add(reportProblem);

		finishRepairs
				.setToolTipText("Finishes the repairs of the car on top of the Cars out for Repair list.");
		buttonsPanel.add(finishRepairs);

		// Refreshes the list displayed
		loadModel(lstWaitingCustomers, dlmWaitingCustomers,
				carRental.customersWaiting());
		loadModel(lstAvailableCars, dlmAvailableCars, carRental.availableCars());
		loadModel(lstRentedCars, dlmRentedCars, carRental.rentedCars());
		loadModel(lstDetailShop, dlmDetailShop, carRental.detailingCars());
		loadModel(lstRepairShop, dlmRepairShop, carRental.repairingCars());

		// Checks which buttons should be enabled.
		checkBtns();

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addListeners();
		setVisible(true);
	}

	/**
	 * Window listener that waits for the close button to be hit. This displays
	 * a message asking the user for a name to same the file of cars as.
	 * 
	 * @param event
	 *            The window event that caused this to happen.
	 */
	@Override
	public void windowClosing(WindowEvent event) {
		setUpClosing();
		closing.setSize(350, 150);
		closing.add(pnlClosing, BorderLayout.CENTER);
		closing.add(pnlClosingBtns, BorderLayout.SOUTH);
		closing.setVisible(true);
	}

	/**
	 * Window listener for when the window is finally closed.
	 * 
	 * @param event
	 *            The window event that caused this to happen.
	 */
	@Override
	public void windowClosed(WindowEvent event) {
		dispose();
	}

	/**
	 * Window listener for when the window is activated.
	 * 
	 * @param event
	 *            The window event that caused this to happen.
	 */
	@Override
	public void windowActivated(WindowEvent event) {
		// Does nothing.
	}

	/**
	 * Window listener for when the window is deactivated.
	 * 
	 * @param event
	 *            The window event that caused this to happen.
	 */
	@Override
	public void windowDeactivated(WindowEvent event) {
		// Does nothing.
	}

	/**
	 * Window listener for when the window is deiconified.
	 * 
	 * @param event
	 *            The window event that caused this to happen.
	 */
	@Override
	public void windowDeiconified(WindowEvent event) {
		// Does nothing.
	}

	/**
	 * Window listener for when the window is iconified.
	 * 
	 * @param event
	 *            The window event that caused this to happen.
	 */
	@Override
	public void windowIconified(WindowEvent event) {
		// Does nothing.
	}

	/**
	 * Window listener for when the window is opened.
	 * 
	 * @param event
	 *            The window event that caused this to happen.
	 */
	@Override
	public void windowOpened(WindowEvent event) {
		// Does nothing.
	}
}
