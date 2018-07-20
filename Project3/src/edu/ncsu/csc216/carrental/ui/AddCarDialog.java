package edu.ncsu.csc216.carrental.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.InvalidIDException;

/**
 * This class is responsible for creating the dialog box that can add a new car.
 * 
 * @author Jesse Liddle - jaliddl2
 */
@SuppressWarnings("serial")
public class AddCarDialog extends JDialog {
	/** The new car being created. */
	private Car newCar;
	/** Grid layout. */
	protected GridBagConstraints g;

	// Title for window.
	private static final String TITLE = "Add a New Car";

	// The panels that separate the parts of the pane.
	private JPanel pnlText = new JPanel();
	private JPanel pnlButtons = new JPanel();

	// The test for the labels
	private static final String MAKE = "Make:";
	private static final String MODEL = "Model:";
	private static final String COLOR = "Color:";
	private static final String FLEET_NUMBER = "Fleet Number:";

	// The labels
	private JLabel make = new JLabel(MAKE);
	private JLabel model = new JLabel(MODEL);
	private JLabel color = new JLabel(COLOR);
	private JLabel fleetNumber = new JLabel(FLEET_NUMBER);

	// The test fields
	private JTextField carMake = new JTextField();
	private JTextField carModel = new JTextField();
	private JTextField carColor = new JTextField();
	private JTextField carFleetNumber = new JTextField();

	// The buttons
	private JButton submit = new JButton("Submit");
	private JButton cancel = new JButton("Cancel");

	/**
	 * Constructor for the AddCarDialog object. This creates the GUI element.
	 * 
	 * @param frame
	 *            The frame that the dialog box is passed.
	 */
	public AddCarDialog(Frame frame) {
		super(frame, TITLE, true);
		this.setSize(400, 550);

		setUp();
		add(pnlText, BorderLayout.CENTER);
		add(pnlButtons, BorderLayout.SOUTH);

		pack();
	}

	/**
	 * This method sets up the components.
	 */
	private void setUp() {
		pnlText.setLayout(new GridLayout(4, 2));
		pnlText.add(make);
		pnlText.add(carMake);
		pnlText.add(model);
		pnlText.add(carModel);
		pnlText.add(color);
		pnlText.add(carColor);
		pnlText.add(fleetNumber);
		pnlText.add(carFleetNumber);

		pnlButtons.setLayout(new FlowLayout());
		pnlButtons.add(submit);
		pnlButtons.add(cancel);

		addActionListeners();
	}

	/**
	 * Adds internally defined action listeners to Submit and Cancel buttons
	 */
	private void addActionListeners() {
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// Try creating a car out of the user input. With success, close
				// the dialog.
				newCar = getInput();
				setVisible(false);
				dispose();
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				newCar = null;
				setVisible(false);
				dispose();
			}
		});
	}

	/**
	 * Grabs the information and creates a new car object.
	 * 
	 * @return A car if one was created or it returns null.
	 */
	private Car getInput() {
		String carMakeStr = carMake.getText().trim();
		String carModelStr = carModel.getText().trim();
		String carColorStr = carColor.getText().trim();
		String carFleetNumStr = carFleetNumber.getText().trim();

		try {
			return new Car(carFleetNumStr, carMakeStr, carModelStr, carColorStr);
		} catch (InvalidIDException error) {
			JOptionPane.showMessageDialog(null, error.getMessage());
		}

		return null;
	}

	/**
	 * This method is a getter method for the car the dialog box created.
	 * 
	 * @return Returns the newCar data member.
	 */
	public Car getNewCar() {
		return newCar;
	}
}
