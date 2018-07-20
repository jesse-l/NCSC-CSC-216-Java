package edu.ncsu.csc216.androtech.ui;

import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

import javax.swing.*;

import edu.ncsu.csc216.androtech.model.devices.Device;
import edu.ncsu.csc216.androtech.model.management.Manager;
import edu.ncsu.csc216.androtech.model.management.ServiceManager;

/**
 * Creates the AndroTech Repair Center GUI
 * 
 * @author Jo Perry
 * @author David Wright
 */
public class AndroTechGUI extends JFrame implements ActionListener {

	// Window and button strings
	private final static String TITLE = "AndroTech RepairCenter Manager";
	private final static String ADD = "Add New Device";
	private final static String REMOVE = "Remove Selected Device";
	private final static String FILTER = "Display Filter: ";
	private final static String WAITING_ROOM = "Devices Awaiting Service";
	private final static String FILL = "Assign TechDroids";
	private final static String ADD_DROID = "Add TechDroid";
	private final static String RELEASE = "Finish Repair of Selected Device";
	private final static String GARAGE = "TechDroids";
	private final static String QUIT = "Quit";

	// Size constants for the window
	private final static int FRAME_WIDTH = 730;
	private final static int FRAME_HEIGHT = 500;

	// Buttons
	private JButton btnAdd = new JButton(ADD);
	private JButton btnRemove = new JButton(REMOVE);
	private JButton btnFill = new JButton(FILL);
	private JButton btnRelease = new JButton(RELEASE);
	private JButton btnAddDroid = new JButton(ADD_DROID);
	private JButton btnQuit = new JButton(QUIT);

	// Panels
	private JPanel pnlWaitingRepairs = new JPanel(new BorderLayout()); // Main
																		// left
																		// panel
	private JPanel pnlWaitTop = new JPanel(); // Top of left panel
	private JPanel pnlDroids = new JPanel(new BorderLayout()); // Main right
																// panel
	private JPanel pnlDroidTop = new JPanel(); // Top of right panel
	private JPanel pnlFilter = new JPanel(); // Panel for filtering the display
												// list

	// List model stuff
	// Default list models for the scrollable lists
	private DefaultListModel<String> dlmWaitingDevices = new DefaultListModel<String>(); // Waiting
																							// list
																							// default
																							// list
																							// model
	private DefaultListModel<String> dlmServiceDroids = new DefaultListModel<String>(); // Droid
																						// repair
																						// center
																						// default
																						// list
																						// model
	private JList<String> lstServiceDroids = new JList<String>(dlmServiceDroids); // Actual
																					// list
																					// of
																					// droids
	private JList<String> lstWaitingDevices = new JList<String>(
			dlmWaitingDevices); // Actual waiting list
	private JScrollPane scpWaitingDevices = new JScrollPane(lstWaitingDevices); // Scroll
																				// pane
																				// for
																				// waiting
																				// list
	private JScrollPane scpServiceDroids = new JScrollPane(lstServiceDroids); // Scroll
																				// pane
																				// for
																				// droid
																				// list

	// Labels
	private JLabel lblFilter = new JLabel(FILTER); // Filter for display list

	// Fields, combos for user input
	private JTextField txtFilter = new JTextField(10); // Display filter goes
														// here

	// Most recent filter entry
	private String filter = "";

	UserDialog pane;

	// Backend model
	private Manager serviceMgr;

	// ------ Constructors and GUI set up -------------

	/**
	 * Constructor: Creates a new GUI with no occupied droids.
	 */
	public AndroTechGUI() {
		serviceMgr = new ServiceManager();
		setUpGUI();
	}

	/**
	 * Creates a new GUI with a device list coming from the named file. If the
	 * name is not valid the device list is initialized to empty.
	 * 
	 * @param fileName
	 *            name of file to use
	 * 
	 */
	public AndroTechGUI(String fileName) {
		if (fileName == null) { // The user specifies the file from a file
								// chooser
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int returnVal = fc.showOpenDialog(this);
			try {
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					serviceMgr = new ServiceManager(new Scanner(
							fc.getSelectedFile()));
				} else {
					serviceMgr = new ServiceManager();
				}
			} catch (Exception e) {
				serviceMgr = new ServiceManager();
			}
		} else { // file name is command-line parameter
			try {
				serviceMgr = new ServiceManager(new Scanner(new File(fileName)));
			} catch (Exception e) {
				serviceMgr = new ServiceManager();
			}
		}
		setUpGUI();
	}

	// ------------ Private Methods ------------

	/**
	 * Sets up the GUI framework, adds listeners.
	 */
	private void setUpGUI() {
		// Construct the main window.
		setTitle(TITLE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.LINE_AXIS));
		setUpPanels();
		loadModel(lstWaitingDevices, dlmWaitingDevices,
				serviceMgr.printWaitList(""));
		loadModel(lstServiceDroids, dlmServiceDroids, serviceMgr.printDroids());
		lstWaitingDevices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstServiceDroids.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Add the waiting room and ward panels to the main window
		c.add(pnlWaitingRepairs);
		c.add(pnlDroids);

		addListeners();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Sets up the panels that comprise the GUI.
	 */
	private void setUpPanels() {
		pnlWaitTop.setLayout(new BoxLayout(pnlWaitTop, BoxLayout.PAGE_AXIS));
		pnlDroidTop.setLayout(new BoxLayout(pnlDroidTop, BoxLayout.PAGE_AXIS));

		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlWaitTop.add(btnAdd);
		pnlWaitTop.add(btnRemove);
		pnlFilter.add(lblFilter);
		pnlFilter.add(txtFilter);
		pnlFilter.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlWaitTop.add(pnlFilter);

		// Put the devices/droids in scrolling text areas
		scpWaitingDevices
				.setBorder(BorderFactory.createLineBorder(Color.black));
		scpServiceDroids.setBorder(BorderFactory.createLineBorder(Color.black));
		lstWaitingDevices.setFont(new Font("monospaced", Font.PLAIN, 12));
		lstServiceDroids.setFont(new Font("monospaced", Font.PLAIN, 12));

		// Set up "waiting lot" panels for left side of the window
		pnlWaitingRepairs.add(pnlWaitTop, BorderLayout.NORTH);
		pnlWaitingRepairs.add(scpWaitingDevices, BorderLayout.CENTER);

		// Set up "droid" buttons
		btnAddDroid.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnFill.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRelease.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnQuit.setAlignmentX(Component.CENTER_ALIGNMENT);

		pnlDroidTop.add(btnAddDroid);
		pnlDroidTop.add(btnFill);
		pnlDroidTop.add(btnRelease);
		pnlDroidTop.add(new JLabel(" "));
		pnlDroids.add(pnlDroidTop, BorderLayout.NORTH);
		pnlDroids.add(scpServiceDroids, BorderLayout.CENTER);
		pnlDroids.add(btnQuit, BorderLayout.SOUTH);

		pnlWaitingRepairs.setBorder(BorderFactory
				.createTitledBorder(WAITING_ROOM));
		pnlDroids.setBorder(BorderFactory.createTitledBorder(GARAGE));
	}

	// ---- Methods to handle event processing ---------

	/* Adds action listeners to all the buttons. */
	private void addListeners() {
		btnAdd.addActionListener(this);
		btnRemove.addActionListener(this);
		btnQuit.addActionListener(this);
		btnAddDroid.addActionListener(this);
		btnFill.addActionListener(this);
		btnRelease.addActionListener(this);
		txtFilter.addActionListener(this);
	}

	/**
	 * Determines the actions for each button click.
	 * 
	 * @param e
	 *            ActionEvent to perform
	 */
	public void actionPerformed(ActionEvent e) {
		filter = txtFilter.getText();
		if (filter == null)
			filter = "";
		else
			filter = filter.trim();
		if (e.getSource().equals(btnAdd)) {
			UserDialog pane = new UserDialog();
			pane.setVisible(true);
			Device v = pane.getNewVehicle();
			if (v != null)
				serviceMgr.putOnWaitingList(v);
		} else if (e.getSource().equals(btnRemove)) {
			doRemove();
		} else if (e.getSource().equals(btnAddDroid)) {
			doAddBay();
		} else if (e.getSource().equals(btnFill)) {
			doFill();
		} else if (e.getSource().equals(btnRelease)) {
			doRelease();
		} else if (e.getSource().equals(btnQuit)) {
			System.exit(0);
		}

		loadModel(lstWaitingDevices, dlmWaitingDevices,
				serviceMgr.printWaitList(filter));
		loadModel(lstServiceDroids, dlmServiceDroids, serviceMgr.printDroids());
	}

	/**
	 * Private Method - loads a list model from a string using newline
	 * tokenizers.
	 * 
	 * @param j
	 *            the JList to refresh
	 * @param m
	 *            the default list model associated with j
	 * @param info
	 *            the String whose tokens initialize the default list model
	 */
	private void loadModel(JList<String> j, DefaultListModel<String> m,
			String info) {
		m.clear();
		if (info == null)
			return;
		StringTokenizer st = new StringTokenizer(info, "\n");
		while (st.hasMoreTokens()) {
			m.addElement(st.nextToken());
		}
		j.ensureIndexIsVisible(0);
	}

	/**
	 * Assigns devices to available, appropriate droids
	 */
	private void doFill() {
		serviceMgr.assignDroids();
	}

	/**
	 * Adds a new droid
	 */
	private void doAddBay() {
		serviceMgr.addNewDroid();
	}

	/**
	 * Releases the selected device from the droid (completes the repair)
	 */
	private void doRelease() {
		int index = lstServiceDroids.getSelectedIndex();
		serviceMgr.releaseFromService(index);
	}

	/* Implements remove operation. */
	private void doRemove() {
		int psn = lstWaitingDevices.getSelectedIndex();
		serviceMgr.remove(filter, psn);
	}

	// --- Start up methods ---------

	/**
	 * Startup method for the application. Creates the GUI.
	 * 
	 * @param args
	 *            args[0] is the name of the input text file.
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			new AndroTechGUI(args[0]);
		} else
			new AndroTechGUI(null);

	}
}
